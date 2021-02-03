import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int origin=n;
		int sum;
		int count=0;
		while(true) {
			sum=n/10+n%10;
			n=(n%10)*10+sum%10;
			count++;
			if(n==origin)
				break;
		}
		//이전 수의 %10한 값 *10 + 이전수의 합%10
		System.out.println(count);
		sc.close();
		
	}
}