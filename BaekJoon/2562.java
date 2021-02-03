import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] arr=new int[9];
		int count=0;
		for(int i=0;i<9;i++)
			arr[i]=sc.nextInt();
		sc.close(); 
		
		int max=0;
		int maxC=0;
		for(int num:arr) {
			count++;
			if(num>max) {
				max=num;
				maxC=count;
			}
		}
			
		System.out.println(max);
		System.out.println(maxC);
				
	}
}