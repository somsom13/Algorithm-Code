
public class Solution {
    public int solution(int n) {
        int answer = 0;
        String str=Integer.toString(n); //int형을 string으로 바꾼다
        for(int i=0;i<str.length();i++)
            answer+=(str.charAt(i)-'0'); //문자열의 각 자리를 charAt으로 뽑아온 후, '0'을 빼주어 int형으로 바꾼다.

        return answer;
    }
}