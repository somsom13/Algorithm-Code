import java.util.*;

public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String a=sc.nextLine();
		System.out.println(a.charAt(0)-0); //ASCII code로 출력하기 위해서 0을 빼주었다. 
        //'0'을 빼게 된다면, char '0'의 ASCII code 값에 해당하는 48이 빼진다.
		//int num=a.charAt(0); 으로 따로 처리해도 좋다.
		sc.close();
	}	
}