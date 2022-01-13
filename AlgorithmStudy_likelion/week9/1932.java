import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int n=Integer.parseInt(br.readLine());
        int[][] nums=new int[n][];
        for(int i=0;i<n;i++){
            int count=i+1;
            nums[i]=new int[count];
            String numString=br.readLine();
            String[] numsString=numString.split(" ");
            
            for(int j=0;j<numsString.length;j++){
                nums[i][j]=Integer.parseInt(numsString[j]);
            }
        }

        for(int i=n-1;i>0;i--){ //5층이면? i==4, 5칸
            int currentLen=i+1;
            for(int j=0;j<currentLen-1;j++){
                int max=Math.max(nums[i][j], nums[i][j+1]);
                nums[i-1][j]+=max;
            }
        }

        System.out.println(nums[0][0]);
    }
}