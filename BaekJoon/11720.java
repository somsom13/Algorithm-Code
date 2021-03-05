import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int sum=0;
		int n=sc.nextInt();
		String s=sc.next(); //문자열로 입력받고
		for(int i=0;i<n;i++) {
			sum+=s.charAt(i)-'0'; //charAt-'0'으로 문자열의 각 자리를 int로 바꾼다.
		}
		System.out.println(sum);
		sc.close();
	}	
}