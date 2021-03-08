import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int c=sc.nextInt();
		int n;
		double avg,per;
		int[] arr;
		for(int i=0;i<c;i++) {
			double sum=0;
			double over=0;
			n=sc.nextInt();
			arr=new int[n];
			for(int j=0;j<n;j++) {
				arr[j]=sc.nextInt();
				sum+=arr[j];
			}
			avg=sum/n;
			for(int j=0;j<n;j++) {
				if(arr[j]>avg)
					over++;
			}
			per=over/n*100;
			System.out.println(String.format("%.3f", per)+"%");
            //String.format 을 이용해 소수점 아래 셋 째 자리까지 반올림하여 출력
		}
		sc.close();
	}	
}