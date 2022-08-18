public import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

       String[] input=br.readLine().split(" ");
    
       BigInteger a=new BigInteger(input[0]);
       BigInteger b=new BigInteger(input[1]);

       bw.write(a.add(b).toString());

        bw.flush();
        bw.close();

    }
 
} 10757 {
    
}
