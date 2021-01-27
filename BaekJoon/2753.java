import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int Y=0;
		int year=sc.nextInt();
		if(year%4==0&&year%100!=0)
			Y=1;
		else if(year%400==0)
				Y=1;
		System.out.println(Y);
		sc.close();
	}

}