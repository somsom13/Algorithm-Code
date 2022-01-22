import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 재귀함수 사용시 시간초과 발생
 */

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int n=Integer.parseInt(br.readLine());
        int[][] cases=new int[n][2];
        int[][] combination=new int[31][31];

        for(int i=0;i<n;i++){
            String[] inputs=br.readLine().split(" ");
            cases[i][0]=Integer.parseInt(inputs[0]);
            cases[i][1]=Integer.parseInt(inputs[1]);
        }

        for(int i=1;i<=30;i++){
            for(int j=i;j<=30;j++){  // jCi (j>=i)
                if(i==1){
                    combination[j][i]=j;
                }else if(i==j){
                    combination[j][i]=1;
                }else{
                    combination[j][i]=combination[j-1][i-1]+combination[j-1][i];
                }
            }
        }

        checkCount(cases,combination);
    }

    private static void checkCount(int[][] cases,int[][] combination) throws IOException{
        int n,m;
        for(int[] site:cases){
            n=site[0];
            m=site[1];

            //m개 중에 n개를 뽑아놓고 -> 뽑은 결과에서 겹치지 않게 연결하면 된다
            int res=combination[m][n];
            bw.write(String.valueOf(res)+"\n");
        }
        bw.flush();
        
    }
   
}    
