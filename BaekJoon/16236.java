import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class App{
    private static int[][] position;
    private static int sharkSize=2; //아기상어 크기는 2로 시작
    private static int eatCount=0;
    private static int totalMove=0;
    private static Queue<int[]> queue=new LinkedList<>(); //물고기의 {x,y} 저장
    private static int[] moveX={0,-1,0,+1}; //순서대로 상,좌, 하, 우
    private static int[] moveY={1,0,-1,0};
    private static int n;
    private static Boolean[][] visited;
    private static ArrayList<int[]> shortDistances=new ArrayList<>(); //{x,y} 지금까지 찾은 먹을 수 있는
    private static int shortestDist=Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        position=new int[n][n];
        visited=new Boolean[n][n];
        int[] startPos=new int[3];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                position[i][j]=Integer.parseInt(st.nextToken());
                visited[i][j]=false;
                if(position[i][j]==9){
                    startPos[0]=i;
                    startPos[1]=j;
                    startPos[2]=0;
                }
            }
        }

        int canEat;


        while(true){
            //bfs만으로는 안된다! 맨 위, 맨 왼쪽을 먼저 먹을 수 있게 다시 고려해주어야함
            //현재 가장 짧은 거리를 기억 -> 그 뒤에도 계속 서치하면서 짧은 거리>=인 좌표 + 이동거리는 전부 다 저장
            //짧은 거리 < 가 되는 좌표가 나오면 스탑 (더 이상 탐색해도 최소거리보다 먼 물고기만 나오게 되므로)
            queue.clear();
            shortDistances.clear();
            canEat=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    visited[i][j]=false;
                    if(position[i][j]==0){
                        continue;
                    }
                    if(position[i][j]<sharkSize){
                        canEat++;
                    }
                }
            }

            if(canEat==0){
                break;
            }

            shortDist(startPos);
            //먹을 수 있는 가장 가까운 거리의 물고기들을 모두 찾음 
            //가까운 물고기들의 좌표: shortDistances에 저장, 이동거리 == shortestDist
            //x기준 오름차순 정렬, 그 이후 y기준 오름차순 정렬

            Collections.sort(shortDistances,new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    if(o1[0]==o2[0]){
                        return o1[1]-o2[1];
                    }else{
                        return o1[0]-o2[0];
                    }
                }
            });

            if(shortDistances.isEmpty()){
                //먹을 수 있는 물고기는 있는데 도달할 수 없는 경우
                break;
            }

            int[] nowEat=shortDistances.get(0); //가장 위, 왼쪽에 있는 물고기를 이번에 먹음
            position[nowEat[0]][nowEat[1]]=0;
            eatCount++;
            totalMove+=shortestDist;
            shortestDist=Integer.MAX_VALUE;
            startPos[0]=nowEat[0]; //먹은 물고기의 칸으로 상어 위치 초기화
            startPos[1]=nowEat[1];
            startPos[2]=0;


            //상어 크기만큼 먹으면 증량, 먹은 물고기 수 초기화
            if(sharkSize==eatCount){
                sharkSize++;
                eatCount=0;
            }
            
        }

        bw.write(String.valueOf(totalMove));
        bw.flush();
        bw.close();
    }

    private static void shortDist(int[] startPos){
        queue.offer(startPos);
        visited[startPos[0]][startPos[1]]=true;
        position[startPos[0]][startPos[1]]=0;
        int x;
        int y;
        int move;
        while(!queue.isEmpty()){
            int[] nowPos=queue.poll();
            x=nowPos[0];
            y=nowPos[1];
            move=nowPos[2];

            if(position[x][y]>0&&position[x][y]<sharkSize){
                //먹을 수 있는 칸에 도달
                if(shortestDist>=move){ //최소거리에 있는 물고기라면 
                    shortestDist=move;
                    int[] newDist={x,y};
                    shortDistances.add(newDist);
                }else{
                    //shortestDist < move라면 더 이상 최소거리를 찾을 수 없다는 의미 -> break!
                    return;
                }

            }
            
            //상,하,좌,우 자기보다 크기가 작거나 같은 물고기라면 이동 가능
            for(int i=0;i<4;i++){
                int nextX=x+moveX[i];
                int nextY=y+moveY[i];
                if(nextX<0||nextY<0||nextX>=n||nextY>=n||visited[nextX][nextY]){
                    continue;
                }
                if(position[nextX][nextY]<=sharkSize){
                    
                    int[] nextPos={nextX,nextY,move+1};
                    queue.offer(nextPos); //갈 수 있는 칸이면 경로에 추가, 방문 처리
                    visited[nextX][nextY]=true;  
                }
            }
        }
    }


}