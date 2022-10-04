import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        char[][] board=new char[n][m];
        //다시색칠하는게 최소가 되는 경우: 자르는 모든 방향에서 잘못 색칠된게 최소여야함

        for(int i=0;i<n;i++){
            board[i]=br.readLine().toCharArray();
        }

        int startX=0;
        int endX=7;
        int startY=0;
        int endY=7;

        int min=Integer.MAX_VALUE;
        int count;

        Boolean isWhite;

        //endX, endY가 보드 전체 크기 넘어가지 않게. startX, startY가 0보다 작아지지 않게 8칸씩 자르기
        //오른쪽으로 1칸씩, 밑으로 1칸씩

        //0,0이 흰색이면: x+y가 짝수이면 흰색 / x+y가 홀수이면 검정색



        while(true){
            count=0;
            isWhite=board[startX][startY]=='W'?true:false;

            //첫 칸이 뭐냐에 따라서 X+y가 짝수여야 하는지 홀수여야 하느는지가 바뀐다. 
            //첫 칸 합이 짝수&흰색 -> 짝수합 흰색 / 홀수합 검정색
            //첫 칸 합이 홀수&흰색 -> 짝수합 검정색 / 홀수합 흰색
            //홀수&검정색 -> 짝수합 흰색 / 홀수합 검정색
            //짝수&검정색 -> 짝수합 검정색 /홀수합 흰색

            //첫 칸을 바꾸는 경우도 생각해야 한다

            if(isWhite&&(startX+startY)%2==0||!isWhite&&(startX+startY)%2!=0){
                for(int i=startX;i<=endX;i++){
                    for(int j=startY;j<=endY;j++){
                        if(i==0&&j==0){
                            continue;
                        }
                        if((i+j)%2==0&&board[i][j]=='B'){
                            count++;
                        }
                        if((i+j)%2!=0&&board[i][j]=='W'){
                            count++;
                        }
                    }
                }
            }else{
                for(int i=startX;i<=endX;i++){
                    for(int j=startY;j<=endY;j++){
                        if(i==0&&j==0){
                            continue;
                        }
                        if((i+j)%2==0&&board[i][j]=='W'){
                            count++;
                        }
                        if((i+j)%2!=0&&board[i][j]=='B'){
                            count++;
                        }
                    }
                }
            }


            //아예 첫 칸을 바꾸는 경우 -> 지금까지 count의 완전 정반대 이므로 8*8=64에서 count를 빼주면 된다
            if(count>64-count){
                count=64-count;
            }

            if(count<min){
                min=count;
            }

            if(endX==n-1&&endY<m-1){ //가로로 한 줄 다 갔으면 다음 줄로 넘어감
                startY++;
                endY++;
                startX=0;
                endX=7;
                continue;
            }
            if(endX==n-1&&endY==m-1){
                break;
            }

            startX++;
            endX++;
        }

        bw.write(String.valueOf(min));

        bw.flush();
        bw.close();
    }


}