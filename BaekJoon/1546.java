import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);	
		int n=sc.nextInt();
		double sum=0;
		int m;
		int[] arr=new int[n];
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		sc.close();
		
		Arrays.sort(arr);
		m=arr[n-1];
		for(int i=0;i<n;i++)
			sum+=(double)arr[i]/m*100;
		System.out.println(sum/n);
		
	}	
}