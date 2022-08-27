import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int input=Integer.parseInt(br.readLine());

        //1+2+3+4+5로 한 줄에 있는 분수 개수가 늘어남
        //n번 째 줄 일때 분모 또는 분자가 n으로 시작함
        //n번째 줄에 n개의 숫자가 있음

        //1부터 돌면서 해당 input이 몇 번 줄에 있는 분수인지를 찾아야함
        int n=1;
        while(true){
            //input<=n이면 input번 째 분수가 이 n번줄에 속해있다는 의미 (더 이상 뺄 수 없음)
            if(input<=n){
                break;
            }
            input-=n++;
        }

        //input이 i이면
        //짝수일 때: 분자가 i, 분모가 현재 줄 수 - i + 1 (분자가 커지고 분모가 줄어듬)
        //홀수일 때: 분자가 (현재 줄 수 - i)+1, 분모가 i (분자가 작아지고 분모가 커짐)

        int up=input; 
        int down=n-input+1;
        if(n%2==0){
            //짝수면 분모가 큰 수에서 작은 수로
            bw.write(up+"/"+down);
        }else{
            bw.write(down+"/"+up);
        }

        bw.flush();
        bw.close();

    }
 
}
