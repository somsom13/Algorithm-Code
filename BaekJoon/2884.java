import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int m=sc.nextInt();
		sc.close();
		
		if(h==0&&m<45) {
			System.out.println("23");
			System.out.println(59-(45-m)+1);
		}
		else if(m>45) {
			System.out.println(h);
			System.out.println(m-45);
		}
		else if(m==45) {
			System.out.println(h);
			System.out.println("0");
		}
		else {
			System.out.println(h-1);
			System.out.println(59-(45-m)+1);
		}		
	}
}
