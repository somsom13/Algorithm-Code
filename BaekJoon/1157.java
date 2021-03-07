import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int max=-1; //가장 큰 횟수
		char maxC='a';//가장 많이 나온 알파벳
		int index=-1;//중복 체크 위한 인덱스 저장
		int[] count=new int[26];
		for(int i=0;i<s.length();i++) { //문자열 s에 대해 소문자, 대문자에 따라 a,0부터 z,25까지 횟수추가
			if(s.charAt(i)>=97)
				count[s.charAt(i)-97]++;
			else
				count[s.charAt(i)-65]++;
		}
		for(int i=0;i<26;i++) {
			if(count[i]>=max) {//가장 많이 등장한 알파벳 찾기
				maxC=(char)(i+65);
				max=count[i];
				index=i;
			}
		}
		for(int i=0;i<26;i++) {
			if(index!=i&&max==count[i]) {//가장 많이 사용된 알파벳이 여러개인지 체크
				System.out.println("?");
				break;
			}
			else if(i==25)
				System.out.println(maxC);
		}
		
		sc.close();
	}	
}