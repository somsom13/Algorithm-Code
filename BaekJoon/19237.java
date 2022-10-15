import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Shark{
    int num;
    int dir;

    public Shark(int num, int dir){
        this.num=num;
        this.dir=dir;
    }
}

class Board{
    Shark shark;
    int remain;

    public Board(Shark shark, int remain){
        this.shark=shark;
        this.remain=remain;
    }
}

class Main {
    private static int n;
    private static int m; //상어의 수
    private static int k; //향이 몇 초간 지속되는가
    private static Board[][] board;

    //1,2,3,4 순서대로 상,하,좌우 -> 인덱스로 접근해서 움직일 칸 수를 가져온다
    private static int[] dx={0,-1,1,0,0};
    private static int[] dy={0,0,0,-1,1};

    private static int[][][] priority; //1번 상어~ m번 상어 -> 1~m번 인덱스에 상,하,좌,우 순서대로 방향 당 우선순위

    //각 칸에 몇 번 상어의 냄새가 몇 초 동안 지속될 수 있는지를 저장
    //각 상어별로 "현재 자신이 보고 있는 방향" 일 때 우선순위 다음 방향이 정해져있다! 

    //1~m번 상어가 이번 턴에 이동한 위치
    private static Shark[][][] sharkMove;

    //각 상어는 방향과 번호를 가진다
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());;
        k=Integer.parseInt(st.nextToken());

        int[][] sharkExist=new int[m+1][2];
        priority=new int[m+1][5][5];
        sharkMove=new Shark[n+1][n+1][m+1]; //[i,j]번 칸으로 k번 상어가 이동했다면? [i][j][k]에 k번 상어가 들어있음

        board=new Board[n+1][n+1]; //(1,1)을 첫 칸으로
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                int s=Integer.parseInt(st.nextToken());
                if(s==0){
                    board[i][j]=null;
                }else{
                    board[i][j]=new Board(new Shark(s,0),k);
                    sharkExist[s]=new int[]{i,j}; //나중에 상어의 방향 입력받았을 때 세팅해주기 위해
                }
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=m;i++){
            int[] pos=sharkExist[i];
            board[pos[0]][pos[1]].shark.dir=Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=4;j++){
                st=new StringTokenizer(br.readLine());
                for(int k=1;k<=4;k++){
                    priority[i][j][k]=Integer.parseInt(st.nextToken());
                    //i번 상어가 현재 j방향일 때, 우선순위는 {1번방향, 2번 방향, 3번 방향}
                    //=> int[] dirPriority=priority[i][j];
                }
            }
        }

        //격자에 1번 상어가 남아있을 때 까지!
        int time=0;
        int x;
        int y;
        int nextX;
        int nextY;
        int[] prior;
        Shark shark;
        Boolean[] sharkLeft=new Boolean[m+1];
        for(int i=0;i<m+1;i++){
            sharkLeft[i]=true;
        }
        while(true){
            //모든 상어는 동시에, 향이 없는 칸으로, **현재 자신이 보고있는 방향 기준 우선순위가 높은 다음 방향**으로 이동한다.
            //만약 인접한 칸(상하좌우)이 모두 냄새가 있으면, 자신의 냄새가 있는 **현재 자신이 보고있는 방향 기준 우선순위가 높은 다음 방향**으로 틀고 (상하좌우) 이동한다.

            for(int i=1;i<=m;i++){
                Boolean move=false;
                //1~m번 상어의 위치가 sharkExist[i]번 인덱스에 저장됨
                x=sharkExist[i][0];
                y=sharkExist[i][1];
                if(x==-1||y==-1){
                    //격자밖으로 나간 상어
                    continue;
                }
                shark=board[x][y].shark;
                prior=priority[i][shark.dir]; //0~3번 인덱스까지 현재 상어의 방향 우선순위 담김

                //모두 이동을 마쳤을 때 겹치는 영역을 파악해야함 -> 이번에 새롭게 이동한 곳은 냄새를 5로 세팅, 5인 곳은 다른 상어도 들어갈 수 있게
                //그리고 모든상어가 이동을 마쳤을 때 밖으로 나갈 상어를 선택하면서 냄새가 남아있는 칸의 카운트를 --1

                //인접한 칸 중 냄새가 없는 칸으로 이동하자
                //한 칸에 여러 상어가 있을 수 있다.

                //아니면 이번에 상어가 새롭게 이동하는 위치들을 다 따로 저장 -> 같은 위치에 있는 애들을 쳐낸다음에 한 번에 업데이트?
                for(int p=1;p<=4;p++){
                    nextX=x+dx[prior[p]];
                    nextY=y+dy[prior[p]];
                    if(nextX<1||nextY<1||nextX>n||nextY>n||board[nextX][nextY]!=null){
                        //격자 밖으로 나가거나, 그 칸에 상어의 향기가 있고, 이게 방금 이동한 상어의 향기가 아니라면 갈 수 없음
                        continue;
                    }

                    shark.dir=prior[p]; //새로운 방향을 바라보게 설정하고
                    sharkMove[nextX][nextY][i]=shark; //[i][j]번 칸에 그 상어가 갔음을 표시
                    move=true;
                    break;
                }
                if(!move){
                    //인접한 칸에 갈 수 있는 곳이 없다면, 자신의 냄새가 있는 곳으로 이동하게 한다
                    for(int p=1;p<=4;p++){
                        //우선순위대로 돈다
                        nextX=x+dx[prior[p]];
                        nextY=y+dy[prior[p]];
                        if(nextX<1||nextY<1||nextX>n||nextY>n||board[nextX][nextY]!=null&&board[nextX][nextY].shark.num!=shark.num){
                            //격자 밖으로 나가거나, 자신의 향기가 아닌 경우
                            continue;
                        }

                        //자신의 향기를 찾으면, 그 칸으로 이동한다.이 때, 새롭게 이동하면 남은 시간도 업데이트!
                        shark.dir=prior[p];
                        sharkMove[nextX][nextY][i]=shark;
                        break;
                    }
                }
            }

            //모든 상어가 이동을 함
            //이제 한 칸에 여러 상어가 있는 경우 -> 가장 작은 상어만 남기고 다 제거 (sharkExist[i]를 (-1,-1)로 변경
            //나머지 상어들은 새로운 칸에 배치
            //과거의 향기들은 --remain, remain이 0이면 null처리 -> 이걸 먼저 하자

            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(board[i][j]!=null){
                        board[i][j].remain--;
                        if(board[i][j].remain==0){
                            board[i][j]=null;
                        }
                    }

                    Shark[] sharks=sharkMove[i][j];
                    int smallNum=m;
                    for(int s=1;s<=m;s++){
                        if(sharks[s]!=null&&sharks[s].num<=smallNum){
                            board[i][j]=new Board(sharks[s],k);
                            smallNum=sharks[s].num;
                            sharkExist[sharks[s].num][0]=i;
                            sharkExist[sharks[s].num][1]=j;
                        }
                    }
                    for(int s=1;s<=m;s++){
                        if(sharks[s]!=null&&sharks[s].num>smallNum){
                            sharkExist[sharks[s].num][0]=-1;
                            sharkExist[sharks[s].num][1]=-1;
                            sharkLeft[sharks[s].num]=false;
                        }
                        sharkMove[i][j][s]=null;
                    }

                }
            }
            time++;
    
            if(checkOnlyOneLeft(sharkLeft)){
                break;
            }
            if(time>=1000){
                time=-1;
                break;
            }
            
        }


        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    private static Boolean checkOnlyOneLeft(Boolean[] leftShark){
        for(int i=2;i<=m;i++){
            if(leftShark[i]){
                return false;
            }
        }
        return true;
    }

}