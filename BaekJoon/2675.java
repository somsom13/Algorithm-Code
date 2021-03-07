import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int r;
		String s;
		for(int i=0;i<n;i++) {
			r=sc.nextInt();
			s=sc.next();
			for(int j=0;j<s.length();j++) {
				for(int k=0;k<r;k++)
					System.out.print(s.charAt(j));
			}
			System.out.println();
		}
		sc.close();	
	}	
}