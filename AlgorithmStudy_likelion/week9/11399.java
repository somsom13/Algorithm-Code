import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int n=Integer.parseInt(br.readLine());
        int[] times=new int[n];
        String inputs=br.readLine();
        String[] inputNums=inputs.split(" ");
        for(int i=0;i<times.length;i++){
            times[i]=Integer.parseInt(inputNums[i]);
        }

        Arrays.sort(times);

        int total=0;
        for(int i=0;i<times.length;i++){
            total+=times[i]*(times.length-i);
        }
        System.out.println(total);
    }
}