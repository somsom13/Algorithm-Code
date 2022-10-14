import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

class Fish{
    int num;
    int direction;

    public Fish(int n, int direction){
        this.num=n;
        this.direction=direction;
    }
}

public class Main {
    private static Fish[][] board=new Fish[4][4]; //물고기의 번호, 방향을 같이 저장
    private static int eatCount=0;
    private static int bigEatCount=0;
    private static int[][] smallOrder;
    private static int[] sharkPos;
    private static Boolean[] fishEaten=new Boolean[17];
    //방향에 따라서 x,y에 더해지는 좌표값을 hashmap으로 저장해두자
    private static HashMap<Integer, int[]> directionMap=new HashMap<>(){{
        put(1,new int[]{-1,0}); //상
        put(2,new int[]{-1,-1}); //대각선 위
        put(3,new int[]{0,-1}); //좌
        put(4,new int[]{1,-1}); //대각선 아래
        put(5,new int[]{1,0}); //하
        put(6,new int[]{1,1}); //대각선 오른쪽 아래
        put(7,new int[]{0,1}); //우
        put(8,new int[]{-1,1});  //대각선 오른쪽 위 -> 상 부터 시계반대방향 순서!
    }};

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        smallOrder=new int[17][2]; //1~16번의 물고기를 작은 번호부터 오름차순으로 인덱스를 저장
        //ex) 번호가 1인 물고기의 위치: smallOrder[1]에 저장 [x,y] 로
        for(int i=0;i<4;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++){
                int n=Integer.parseInt(st.nextToken());
                // visited[i][j]=false;
                board[i][j]=new Fish(n, Integer.parseInt(st.nextToken()));
                smallOrder[n]=new int[]{i,j};
                fishEaten[n]=false;
            }
        }

        //상어는 (0,0)에서 시작
        sharkPos=new int[]{0,0};
        eatCount+=board[0][0].num;
        fishEaten[board[0][0].num]=true;
        board[0][0].num=-1; //물고기를 먹고 상어가 해당 칸으로 이동 -> -1로 변경
        eatFish();
        //번호가 작은 물고기가 있는 인덱스를 순서대로 알아야함 -> 어떻게? => 처음에 값 입력받으면서 idx 기억해두자! 
        //그리고 이후에 물고기 자리바꾸거나, 상어가 먹어버리면서 인덱스 바뀔 때 마다 업데이트

        bw.write(String.valueOf(bigEatCount));
        bw.flush();
        bw.close();
    }

        

    //dfs할 때 마다 물고기 대이동이 이루어짐 -> 상어가 다른 물고기를 먹을 때 마다 물고기의 이동도 변해야함 -> 배열을 복사해두자!
    private static void eatFish(){
        //이번 턴에는 물고기를 이렇게 이동하고, 상어를 이렇게 이동해야지 시작!
        //따라서 이전 턴으로 돌아갈 때를 위해 이전의 물고기 위치를 기억해두어야함
        Fish[][] tmpBoard=new Fish[4][4];
        int[][] tmpSmallOrder=new int[17][2];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tmpBoard[i][j]=new Fish(board[i][j].num,board[i][j].direction);
            }
        }

        for(int i=1;i<=16;i++){
            tmpSmallOrder[i][0]=smallOrder[i][0];
            tmpSmallOrder[i][1]=smallOrder[i][1];
        }

        for(int i=1;i<=16;i++){
            int x=smallOrder[i][0];
            int y=smallOrder[i][1]; //이번에 이동할 물고기의 x, y 좌표

            //smallOrder는 1~16번의 idx에 각 번호의 크기를 가지는 물고기의 좌표가 저장된다! => 물고기가 자리를 이동하면서 위치가 바뀌면 이것도 업데이트 해야
            

            //그런데 이번 순서의 물고기가 잡아먹혔거나, 상어가 왔다면 패스 
            if(board[x][y].num<=0||board[x][y].num==-1||fishEaten[i]){
                continue;
            }
            //최대값을 구해야함 -> 현재 방향에서 시계반대방향으로 한 바퀴 돌면서 이 물고기가 갈 수 있는 모든 방향으로 이동해봐야한다! 
            int nowDirection;
            int nextX;
            int nextY;

            for(int j=board[x][y].direction;j<=board[x][y].direction+8;j++){
                nowDirection=j<=8?j:j-8; //1~8까지 방향과 매핑되어있으므로 9부터는 다시 1부터 시작해야한다
                nextX=x+directionMap.get(nowDirection)[0]; //x,y에 있는 물고기의 방향이 회전했다! 
                nextY=y+directionMap.get(nowDirection)[1];
                if(nextX<0||nextY<0||nextX>3||nextY>3||board[nextX][nextY].num==-1){
                    continue;
                }

                //이동하면서 물고기의 위치를 바꾼다
                int targetFishNum=board[nextX][nextY].num;
                int targetFishDir=board[nextX][nextY].direction;

                board[nextX][nextY].num=board[x][y].num;
                board[nextX][nextY].direction=nowDirection;
                board[x][y].num=targetFishNum;
                board[x][y].direction=targetFishDir;

                

                //i번 크기의 물고기의 위치와 뒤바꿀 번호의 물고기의 위치를 smallOrder에 업데이트 해야함
                smallOrder[i][0]=nextX;
                smallOrder[i][1]=nextY;
                smallOrder[targetFishNum][0]=x;
                smallOrder[targetFishNum][1]=y;
                break; 
                //하나의 물고기는 이동가능 방향 찾으면 바로 거기로 움직이기 때문에 바로 break!
            }
        } //1~16번 물고기가 모두 움직임

        //이제 상어가 자신의 방향으로 움직이면서 먹을 물고기를 골라야 한다 -> 이 때 먹을 수 있는 가짓수가 여러가지이므로 dfs!
        //상어는 상,하,좌,우,대각선 모두 최대 +3 만큼 움직일 수 있다
        int x=sharkPos[0];
        int y=sharkPos[1];
        int nextX;
        int nextY;
        int[] sharkDirection=directionMap.get(board[x][y].direction);
        int tmpNum;
        int tmpDir;

        for(int i=1;i<=3;i++){    
            nextX=x+sharkDirection[0]*i;
            nextY=y+sharkDirection[1]*i;
            if(nextX<0||nextY<0||nextX>3||nextY>3){
                break;
            }
            if(board[nextX][nextY].num>0){
                // tmp=board[nextX][nextY].num;
                // tmpFish=board[nextX][nextY];
                tmpNum=board[nextX][nextY].num;
                tmpDir=board[nextX][nextY].direction;
                eatCount+=board[nextX][nextY].num;

                board[nextX][nextY].num=-1; //상어가 감
                sharkPos[0]=nextX;
                sharkPos[1]=nextY;
                fishEaten[tmpNum]=true;
                board[x][y].num=0; //상어가 있던 자리는 비게됨
                
                //상어가 물고기 하나 골라서 먹음 -> 상어를 그 위치로 옮기고, 다음 물고기 대이동! 즉 위의 과정을 반복하는 dfs 호출
                eatFish();

                //상어가 먹을 만큼 다 먹어서 과거에 이랬더라면,, 하고 다른 상어이동을 하러 가야함 -> 먹은 횟수, 상어의 위치, 먹은 물고기를 초기화시켜줌
                
                //이번 노드의 탐색이 종료되고, 부모 노드로 돌아오면 여기로 온 다음 **다음 위치의 물고기를 먹는 상황** 으로 변경됨 -> eatCount를 다시 비교해주고 앞서 먹혔던 물고기를 안먹은걸로 쳐주고 상어의 위치를 바로 직전의 위치로 돌려야한다! 
                board[nextX][nextY].num=tmpNum;
                board[nextX][nextY].direction=tmpDir;
                fishEaten[tmpNum]=false;

                sharkPos[0]=x;
                sharkPos[1]=y;
                board[x][y].num=-1;
                eatCount-=tmpNum;
            }
        }

        //이번 경우의 수를 체크할 때 기억해뒀던 **물고기가 이동하기 전으 ㅣ배열** 으로 다시 복원시켜줌
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                board[i][j]=new Fish(tmpBoard[i][j].num,tmpBoard[i][j].direction);
            }
        }

        for(int i=1;i<=16;i++){
            smallOrder[i][0]=tmpSmallOrder[i][0];
            smallOrder[i][1]=tmpSmallOrder[i][1];
        }

        bigEatCount=Math.max(bigEatCount, eatCount); //상어가 먹을게 없을 때

        //상어가 먹을게 없으면 여기로 돌아옴 -> 한 턴의 종료!
        //즉 앞의 단계로 돌아가서 다른 상어 이동의 경우의 수를 체크하게 된다
        //지금까지의 eatCount를 역대 eatCount와 비교 -> 더 큰 값으로 변경
        
        

        //상어가 먹는 물고기의 수의 총합을 **상어가 먹을 물고기가 없을 때 까지** 더해준다
        //그리고 모든 가짓수의 총합 중에서 가장 큰 합을 체크해야함

        //위의 모든 과정이 한 번의 dfs 내에서 일어나야한다! 
        //**물고기는 이동가능한 칸을 찾으면 바로 거기로 이동하기 때문에 dfs 적용 X */

        //물고기를 먹고, eatCount를 그 만큼 증가한 후 다시 다음 물고기를 찾게 한다
        //상어가 먹을 수 있는 물고기가 없을 때 까지 반복 
        //즉 상어가 0번 칸으로 이동, 물고기의 첫 대이동 시작 ~ 상어가 먹을 물고기가 없음    -> 이 과정까지가 하나의 dfs! 
    }
}