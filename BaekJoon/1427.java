import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input=br.readLine();
        char[] arr=input.toCharArray();
        //숫자여도 아스키코드의 char랑 오름차순, 내림차순은 똑같다 (int로 바꿀 필요 없음)
        
        Arrays.sort(arr);

        for(int i=arr.length-1;i>=0;i--){
            bw.write(arr[i]);
        }

        
        bw.flush();
        bw.close();
    }
    

}