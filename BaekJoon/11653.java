import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n=Integer.parseInt(br.readLine());

        if(n==1){
            return;
        }
        
        //나누는 수가 소수가 아닐 경우: 없음
        int i=2;

        while(n>=2){
            if(n%i==0){
                bw.write(String.valueOf(i)+"\n");
                n/=i;
                continue;
            }
            i++;
        }

        bw.flush();
        bw.close();
    }
 
}