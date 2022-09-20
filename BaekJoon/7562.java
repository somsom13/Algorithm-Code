import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.io.IOException;


//현재 노드 (체스칸)의 x좌표, y좌표, 여기까지 오기 위해 이동한 거리를 저장하기 위한 클래스
class Node{
    int x;
    int y;
    int movedCount;

    public Node(int x, int y, int count){
        this.x=x;
        this.y=y;
        this.movedCount=count;
    }
}



class Main {
    private static Boolean[][] visited;
    private static Queue<Node> queue;

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int[] moveX={-2,-2,2,2,-1,-1,1,1};
    private static int[] moveY={1,-1,1,-1,2,-2,2,-2};

    //x와 y합해 상하좌우 총 3칸 이동 가능 -> 경우의 수는 8가지!
    public static void main(String[] args) throws Exception{
        br=new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int caseCount=Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] destNode=new int[2];
        
        for(int i=0;i<caseCount;i++){
            int n=Integer.parseInt(br.readLine());
            visited=new Boolean[n][n];
            queue=new LinkedList<>();


            for(int m=0;m<n;m++){
                for(int k=0;k<n;k++){
                    visited[m][k]=false;
                }
            }


                
            st=new StringTokenizer(br.readLine());
            Node startNode=new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);
            st=new StringTokenizer(br.readLine());
            destNode[0]=Integer.parseInt(st.nextToken());
            destNode[1]=Integer.parseInt(st.nextToken());

            //초기화 후, bfs 호출! 목적지에 도착할 때 까지 거리 3->거리 6 -> ... -> 거리 3n 까지, 3n==도착지의 좌표 가 될 때 까지
            //같은 거리의 노드를 모두 방문한다. 한 번에 같은 거리의 노드를 전부 방문하기 때문에 BFS 사용!
            //그리고 같은 거리의 노드와 그 다음 거리의 노드를 모두 한 번에 방문하기 때문에 거리 3n때의 좌표가 도착 좌표가 된다면, 그 때 이동한 거리가 최단 거리가 된다. 
        

            bfs(startNode, destNode, n);
        }

        bw.flush();
        bw.close();
        
    }

    private static void bfs(Node startNode, int[] destNode, int n) throws IOException{
        queue.offer(startNode);
        visited[startNode.x][startNode.y]=true;

        while(!queue.isEmpty()){
            //큐가 빌 때 까지 같은 거리의 체스칸 (모든 거리를 다 돌았다면 다음 거리의 체스칸)을 모두 서치한다
            Node nowNode=queue.poll();

            //지금 방문한 노드가 도착지라면 탐색을 끝낸다!
            if(nowNode.x==destNode[0]&&nowNode.y==destNode[1]){
                bw.write(nowNode.movedCount+"\n");
                return;
            }

            for(int i=0;i<8;i++){
                int nextX=nowNode.x+moveX[i];
                int nextY=nowNode.y+moveY[i];

                if(nextX<0||nextY<0||nextX>=n||nextY>=n){ //체스칸 범위를 벗어나는 곳이라면 갈 수 없다
                    continue;
                }

                if(!visited[nextX][nextY]){
                    visited[nextX][nextY]=true;
                    Node nextNode=new Node(nextX,nextY,nowNode.movedCount+1);
                    queue.offer(nextNode);
                }
            }
        }
    }


}