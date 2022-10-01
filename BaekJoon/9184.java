import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
    //N번 집까지 색칠, 이전 집과 다음 집의 색이 같으면 안된다.  -> 그러면 3개의 수가 연달아 빨,파,초 여야함
    //비용의 최솟값을 구해야 한다!!! -> 나라면 dfs로 생각할듯?  (모든 경로 탐색하면서 최저비용 찾기)
    //5개까지 칠해보자 
    //1번집의 색칠 비용 + 2번 집의 색칠 비용 + 3번 집의 색칠 비용 ... 
    //즉 1~n까지의 최소 비용 + 현재 비용 = 나까지의 최종 비용  => 점화식이 나왔으면 이걸 코드로 짜야한다!
    //blue, green, red 일 때 각각의 각 단계별 최소비용 식을 세울 수 있어야한다
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        //a,b,c <=0 : 1 return
        //a, b, c 중 하나 >20 : w(20,20,20) return
        //3차원 배열? 
        int[][][] dp=new int[21][21][21]; //이 배열을 다 초기화시켜놔야 한다
        for(int i=0;i<21;i++){
            for(int j=0;j<21;j++){
                for(int k=0;k<21;k++){
                    if(i==0||j==0||k==0){
                        dp[i][j][k]=1;
                    }
                    else if(i<j&&j<k){
                        dp[i][j][k]=dp[i][j][k-1]+dp[i][j-1][k-1]-dp[i][j-1][k];
                    }
                    else{
                        dp[i][j][k]=dp[i-1][j][k]+dp[i-1][j-1][k]+dp[i-1][j][k-1]-dp[i-1][k-1][j-1];
                    }
                }
            }
        }

        StringTokenizer st;
        int a,b,c;
        while(true){
            st=new StringTokenizer(br.readLine());
            a=Integer.parseInt(st.nextToken());
            b=Integer.parseInt(st.nextToken());
            c=Integer.parseInt(st.nextToken());



            if(a==-1&&b==-1&&c==-1){
                break;
            }
            bw.write(String.format("w(%d, %d, %d) = ", a,b,c));

            if(a<=0||b<=0||c<=0){
                bw.write(1+"\n");
            }
            else if(a>20||b>20||c>20){
                bw.write(dp[20][20][20]+"\n");
            }
            else{
                bw.write(dp[a][b][c]+"\n");
            }
        }


        bw.flush();
        bw.close();
    }


}