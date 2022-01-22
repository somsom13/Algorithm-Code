import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main (String[] args) throws IOException{ 
        int n=Integer.parseInt(br.readLine());
        ArrayList<Integer> nums=new ArrayList<>();
        int total=0;

        for(int i=0;i<n;i++){
            int num=Integer.parseInt(br.readLine());
            if(num==0){
                int lastIdx=nums.size()-1;
                int lastNum=nums.get(lastIdx);
                total-=lastNum;
                nums.remove(lastIdx);

            }else{
                nums.add(num);
                total+=num;
            }
        }
        
        bw.write(String.valueOf(total)+"\n");
        bw.flush();
    }
}