import java.util.Scanner;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		sc.close();
		
		String num=Integer.toString(a*b*c);
		char[] ch=num.toCharArray();
		int[] count=new int[10]; //자동초기화, 정수 0
		for(int i=0;i<ch.length;i++) 
			count[ch[i]-'0']++;
			
		for(int i=0;i<10;i++)
			System.out.println(count[i]);
	}	
}
	