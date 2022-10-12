import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Main {
    private static int[][] A;
    private static int outBoard=0;
    private static int leftBoardAmount;
    private static int n;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        //1. 왼쪽으로 n칸 2. 아래로 n칸 3. 오른쪽으로 n+1 칸 4. 위로 n+1칸 5. 다시 왼쪽으로 n+2칸 .. 반복
        n=Integer.parseInt(br.readLine());
        A=new int[n+1][n+1]; //0행, 0열은 배제하고 생각
        StringTokenizer st;
        int move=1;
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                A[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int[] nowPos={n/2+1,n/2+1};
        String direction="left";

        int i,j;
        //종료조건: 이번 칸이 (1,1) 이면 마지막으로 모래를 뿌린 후 종료한다. 
        //즉 이 때 격자 밖으로 나간 모래의 양을 카운팅 -> x<1 || y<1 || x>n || y>n 으로 영역 밖으로 나가게 되는 모래를 계산하자 
        while(true){
            
            switch (direction){
                case "left":
                    for(int t=0;t<move;t++){
                        j=--nowPos[1];
                        i=nowPos[0];
                        leftBoardAmount=A[i][j];
                        updateValue(i+1,j-1,A[i][j],10);
                        updateValue(i-1,j-1,A[i][j],10);
                        updateValue(i+1,j+1,A[i][j],1);
                        updateValue(i-1,j+1,A[i][j],1);
                        updateValue(i+1,j,A[i][j],7);
                        updateValue(i-1,j,A[i][j],7);
                        updateValue(i+2,j,A[i][j],2);
                        updateValue(i-2,j,A[i][j],2);
                        updateValue(i,j-2,A[i][j],5);
                        updateValue(i, j-1, A[i][j],0);

                        A[i][j]=0;
                        if(i==1&&j==1){
                            //이제 더 이상 이동하지 않음.
                            bw.write(String.valueOf(outBoard));
                            bw.flush();
                            bw.close();
                            return;
                        }
                    }
                    direction="down";
                    break;
                case "down":
                    for(int t=0;t<move;t++){
                        j=nowPos[1];
                        i=++nowPos[0];
                        leftBoardAmount=A[i][j];
                        updateValue(i+1,j-1,A[i][j],10);
                        updateValue(i-1,j-1,A[i][j],1);
                        updateValue(i+1,j+1,A[i][j],10);
                        updateValue(i-1,j+1,A[i][j],1);
                        updateValue(i,j-1,A[i][j],7);
                        updateValue(i,j+1,A[i][j],7);
                        updateValue(i,j+2,A[i][j],2);
                        updateValue(i,j-2,A[i][j],2);
                        updateValue(i+2,j,A[i][j],5);
                        updateValue(i+1, j, A[i][j],0);

                        A[i][j]=0;
                        

                        if(i==1&&j==1){
                            //이제 더 이상 돌지 않음. 
                            bw.write(String.valueOf(outBoard));
                            bw.flush();
                            bw.close();
                            return;
                        }
                    }
                    direction="right";
                    move++; //아래로 이동 -> 우측으로 이동할 때 이동횟수 1 증가
                    break;
                case "right":
                    for(int t=0;t<move;t++){
                        j=++nowPos[1];
                        i=nowPos[0];
                        leftBoardAmount=A[i][j];
                        updateValue(i-1,j-1,A[i][j],1);
                        updateValue(i+1,j-1,A[i][j],1);
                        updateValue(i+1,j+1,A[i][j],10);
                        updateValue(i-1,j+1,A[i][j],10);
                        updateValue(i+1,j,A[i][j],7);
                        updateValue(i-1,j,A[i][j],7);
                        updateValue(i+2,j,A[i][j],2);
                        updateValue(i-2,j,A[i][j],2);
                        updateValue(i,j+2,A[i][j],5);
                        updateValue(i, j+1, A[i][j],0);

                        A[i][j]=0;

                        if(i==1&&j==1){
                            //이제 더 이상 돌지 않음. 
                            bw.write(String.valueOf(outBoard));
                            bw.flush();
                            bw.close();
                            return;
                        }
                    }
                    direction="up";
                    break;
                case "up":
                    for(int t=0;t<move;t++){
                        j=nowPos[1];
                        i=--nowPos[0];
                        leftBoardAmount=A[i][j];
                        updateValue(i+1,j-1,A[i][j],1);
                        updateValue(i+1,j+1,A[i][j],1);
                        updateValue(i-1,j+1,A[i][j],10);
                        updateValue(i-1,j-1,A[i][j],10);
                        updateValue(i,j+1,A[i][j],7);
                        updateValue(i,j-1,A[i][j],7);
                        updateValue(i,j+2,A[i][j],2);
                        updateValue(i,j-2,A[i][j],2);
                        updateValue(i-2,j,A[i][j],5);
                        updateValue(i-1, j, A[i][j],0);

                        A[i][j]=0;

                        if(i==1&&j==1){
                            //이제 더 이상 돌지 않음. 
                            bw.write(String.valueOf(outBoard));
                            bw.flush();
                            bw.close();
                            return;
                        }
                    }
                    direction="left";
                    move++; //위로 이동 -> 좌측으로 이동할 때 이동횟수 1 증가
                    continue;
            }

        }
    }
    
    

    private static void updateValue(int i, int j, int value, int percent){
        int thisValue=value*percent/100;
        if(i<1||j<1||i>n||j>n){ //범위를 벗어나면 outBoard에 추가
            outBoard+=percent==0?leftBoardAmount:thisValue; //퍼센트==0 이면 알파칸에 들어가는 모래이므로 **뿌리고 남은 모래양인 leftBoardAmount를 더해주고**, 아니라면 원래 그 칸에 들어가야하는 퍼센트 만큼의 모래를 추가한다
        }
        else if(percent==0){
            A[i][j]+=leftBoardAmount; //여기서의 i,j는 알파 칸을 의미함
            return; //알파칸은 맨 마지막에 남은 양을 다 넣어주는 것이므로, 값을 넣어준 후 바로 return
        }
        else{
            A[i][j]+=thisValue; //범위 내에 있는 칸이고 퍼센트 만큼 모래가 추가되는 칸이라면 기존의 모래 + 새로운 모래 로 양을 더해준다
        }

        
        leftBoardAmount-=thisValue; //뿌린 만큼 남은 모래양을 줄인다 -> 남은 모래는 모두 알파칸에 들어감
    }



}