import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int count=0;
		for(int i=1;i<=n;i++) {
			int a=i/1000; //1000의 자릿수
			int b=i/100%10; //100의 자릿수
			int c=i/10%10; //10의 자릿수
			int d=i%10; //1의 자릿수
			if(i>=1000) { //해당 자연수가 몇자리 수인지에 따라 등차수열 판단을 다르게 한다. 
				if((a-b==b-c)&&(b-c==c-d))
					count++;}
			else if(i>=100) {
				if(b-c==c-d)
					count++;}
			else {
				count++;}
			}
		System.out.println(count);
		sc.close();
	}	
}