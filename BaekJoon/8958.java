import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		for(int j=0;j<n;j++) {
			String ans=sc.next();
			int result=0;
			int num=0;
			char[] arr=new char[ans.length()-1];
			arr=ans.toCharArray();
			for(int i=0;i<arr.length;i++) {
				
				if(arr[i]=='O') {
					num++;
					result+=num;
				}
				else {
					num=0;
				}
			}
			System.out.println(result);
			}
			sc.close();		
	}	
}
	