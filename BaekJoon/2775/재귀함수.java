import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        //k: 1~14 -> 14층까지, n: 1~14: 1~14호 까지 있음

        //0층 초기화
        int[][] rooms=new int [15][15];
        for(int i=0;i<15;i++){
            rooms[0][i]=i;
        }

        int t=Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            int k=Integer.parseInt(br.readLine());
            int n=Integer.parseInt(br.readLine());

            int total=calculate(k, n);
            bw.write(String.valueOf(total)+"\n");

        }

        bw.flush();
        bw.close();

    }

    //재귀함수 사용 -> 모든 층의 호수를 확인할 때 마다 for문을 돈다
    private static int calculate(int k, int n){
        if(k==0)
         return n;

         int total=0;
         for(int i=1;i<=n;i++){
            total+=calculate(k-1,i);
         }
         return total;
    }
     
}