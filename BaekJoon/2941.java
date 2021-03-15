import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int count=0;
		String s=sc.next();
		sc.close();
		
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
            //모든 알파벳에 count를 1씩 증가시킴.
			if(i>0&&c=='j'&&(s.charAt(i-1)=='l'||s.charAt(i-1)=='n'))
            //lj,nj는 하나의 알파벳 취급. 따라서 전 알파벳이 l이나 n이면 count 올리지 않는다, StringIndexOutofBounds 에러 막기위해 i>0으로 조건추가
				continue;
			else if(i>1&&c=='='&&s.charAt(i-1)=='z'&&s.charAt(i-2)=='d') 
            //dz=는 하나의 알파벳 취급, 따라서 전 알파벳이 z이고 그 전 알파벳이 d라면 count를 하나 내린다.(d와 z가 총 2번 count 되었을것이므로)
            //StringIndexOutofBounds 에러를 막기 위해 i>1 조건 추가
				count--;
			else if(c=='='||c=='-')
            //= 또는 - 는 하나의 알파벳 끝에 오므로 count를 높이지 않는다.
				continue;
			else
				count++;
		}
		System.out.println(count);
		
	}	
}