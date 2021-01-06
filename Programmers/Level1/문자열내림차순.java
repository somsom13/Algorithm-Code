import java.util.Arrays;
class Solution {
    public String solution(String s) {
        String answer="";
        char[] ary=s.toCharArray();
        Arrays.sort(ary);
        for(int i=s.length()-1;i>=0;i--)
            answer+=ary[i];
        return answer;
    }
}