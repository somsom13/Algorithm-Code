import java.util.Scanner;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] num=new int[10];
		int[] remain=new int[10];
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=0;i<num.length;i++)
			num[i]=sc.nextInt();
		sc.close();
		for(int i=0;i<num.length;i++)
			remain[i]=num[i]%42;
		for(int rem:remain)
			if(!list.contains(rem))
				list.add(rem);
		System.out.println(list.size());
		
		
	}
}