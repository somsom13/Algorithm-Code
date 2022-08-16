import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        //k: 1~14 -> 14층까지, n: 1~14: 1~14호 까지 있음
        int[][] rooms=new int [15][15];
        for(int i=0;i<15;i++){
            rooms[0][i]=i;
        }

        //(k-1)층의 1호~n호 까지의 합 = k층 n호의 사람 수
        //k층 n-1호의 사람 수 = (k-1)층의 1호~(n-1)호 까지의 합 
        //=> K층 N 호 = k층 n-1호의 사람 수 + K층 n호의 사람 수

        for(int j=1;j<15;j++){
            for(int i=1;i<15;i++){
                rooms[j][i]=rooms[j-1][i]+rooms[j][i-1];
            }
        }

        int t=Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            int k=Integer.parseInt(br.readLine());
            int n=Integer.parseInt(br.readLine());

            int total=rooms[k][n];
            bw.write(String.valueOf(total)+"\n");

        }

        bw.flush();
        bw.close();

    }
     
}