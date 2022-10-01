import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        //집이 총 n개라고 할 때, 1번집: 첫 번째이므로 r,g,b 모든 경우에 대해서 자신의 값을 생각
        //2번집: 1번집+자기집의 비용이 최소가 되는 경우를 r,g,b 모두에 대해서 생각
        //3번집: 2번까지의 비용 + 자기 집의 비용이 최소가 되는 경우를 r,g,b 모두에 대해서 생각 **자기까지 했을 때 최소여야 하므로 자신을 포함한 누적합 계산**
        //모든 행에 자기까지의 최소 누적합이 R,G,B 마다 저장되게 하여 
        //이전까지의 누적합 중 현재 색을 제외한 것들 중 최소 누족합 == min(arr[i-1][0], arr[i-1][1]) + B = arr[i][2]; 가 된다
        
        int n=Integer.parseInt(br.readLine());
        int[][] dp=new int[n+1][3]; //1번집 부터 계산 -> 0행은 제외
        //0번열: R, 1번열: G, 2번열: B

        for(int i=1;i<n+1;i++){
            st=new StringTokenizer(br.readLine());
            dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+Integer.parseInt(st.nextToken()); //이전까지의 G,B 중 누적최소비용 + 이번 집의 R 색칠 시 비용
            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+Integer.parseInt(st.nextToken()); //이전까지의 R,B 중 누적최소비용 + 이번 G 비용
            dp[i][2]=Math.min(dp[i-1][0],dp[i-1][1])+Integer.parseInt(st.nextToken());
        }

        //N번집 까지 누적최소비용 모두 채워짐


        int minValue=Math.min(dp[n][0],Math.min(dp[n][1],dp[n][2])); //n번집이 R,G,B 일 때의 모든 누적합 중 최소 비용
        bw.write(String.valueOf(minValue));
        bw.flush();
        bw.close();

    }


}