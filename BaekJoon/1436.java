import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static char[][] star;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        //6이 연속으로 3개 이상 들어가는 숫자 중 작은 순서대로
        int n=Integer.parseInt(br.readLine());

        //666을 한 덩어리로 두고 이 덩어리에 앞뒤로 0~9 까지의 숫자를 덧붙임. 단, 맨 앞에 0이 올 수는 없음 -> 불가능 (이 숫자가 가장 작은 숫자인지 아닌지 확인할 방법 없음, 경우의 수가 너무 많다)
        //666부터 N까지 666이 담긴 모든 숫자를 찾자! -> 그럼 자연스럽게 가장 작은 666이 포함된 숫자부터 찾을 수 있다
        String smallString="666";
        long number=666;
        int count=1;

        while(count!=n){
            if(String.valueOf(++number).contains(smallString)){
                count++;
            }

        }

        bw.write(String.valueOf(number));
        bw.flush();
        bw.close();

    }
}