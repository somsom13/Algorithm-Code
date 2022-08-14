import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int[] numbers=new int[6]; //1부터 6까지 각 숫자가 주사위에서 몇 번 나왔는지를 저장
        int twice=0;
        int three=0;
        int big=0;
        String[] input=br.readLine().split(" ");

        for(String s:input){
            int n=Integer.parseInt(s);
            numbers[n-1]++; //주사위에서 나온 숫자의 배열칸을 

            if(numbers[n-1]==2){
                twice=n;
            }else if(numbers[n-1]==3){
                three=n;
            }

            if(big<n){
                big=n;
            }
        }

        if(three!=0){
            System.out.println(10000+three*1000);
        }else if(twice!=0){
            System.out.println(1000+twice*100);
        }else{
            System.out.println(big*100);
        }
    }
     
}
