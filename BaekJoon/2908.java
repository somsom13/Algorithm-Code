import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String a=sc.next();
		String b=sc.next(); //두 숫자를 모두 string으로 입력 받는다.
		sc.close(); 
		
		String A=""; //뒤집은 문자열
		String B="";
		for(int i=a.length()-1;i>=0;i--) //입력받은 문자열을 뒤집는다. 
			A+=a.charAt(i);
		for(int i=b.length()-1;i>=0;i--)
			B+=b.charAt(i);
		int numA=Integer.parseInt(A);//뒤집은 문자열을 int로 변환한다
		int numB=Integer.parseInt(B);
		if (numA>numB)
			System.out.println(numA);
		else
			System.out.println(numB);	
	}	
}