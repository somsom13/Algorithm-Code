import java.util.*;
public class Main {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int[] exist=new int[26]; //소문자 알파벳 26개의 존재 위치를 담을 배열
		for(int i=0;i<26;i++)
			exist[i]=-1; //-1로 초기화
		for(int i=0;i<s.length();i++) { //입력받은 문자열 s에 대해서
			if(exist[s.charAt(i)-97]==-1) //소문자 알파벳의 아스키코드는 a,97부터 시작한다. 
            //그러므로 a,0부터 시작하는 배열에 저장하기 위해서는 s의 i번째 char의 아스키코드-97번 인덱스에 저장해준다. 
            //해당 알파벳이 처음 나타나는 위치를 배열에 저장해야하므로, 배열의 값이 -1일 때만 변경해준다
				exist[s.charAt(i)-97]=i;
		}
		
		for(int i=0;i<26;i++)
			System.out.print(exist[i]+" ");
		sc.close();
	}	
}