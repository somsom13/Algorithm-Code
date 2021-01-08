class Solution {
    public String solution(String s, int n) {
        String answer = "";
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            char res=(char)(ch+n);
            if(ch==' ')
                answer+=" ";
            else if(res>'z'||res>'Z'&&ch<='Z'&&ch>='A')
                /*소문자 ASCII CODE는 대문자보다 크다. 
                그러므로 소문자의 경우, n번만큼뒤로 밀었을 때 'z'를 넘기는 지만 확인하면 된다.
                반면 대문자의 경우, n번 만큼 밀었을 때 'Z'를 넘기는지 그리고 원래 문자(ch)가 대문자 범위 내에 있는 문자였는지 확인해주어야 한다. */
                answer+=(char)(res-26);
            else
                answer+=res;
        }
        return answer;
    }
}