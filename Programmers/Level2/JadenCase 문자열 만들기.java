class Solution {
    public String solution(String s) throws Exception{
        StringBuilder sb=new StringBuilder(s);
        Boolean firstChar=true;
        
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c==' '){
                firstChar=true; //스페이스바가 나오면 다음 단어는 단어의 첫 시작
                continue;
            }
            else if(firstChar){
                //단어의 첫 문자이면 다음 단어는 첫 문자가 아님
                firstChar=false;
                if(c>=97&&c<=122){
                    sb.setCharAt(i,Character.toUpperCase(c)); //첫 문자가 소문자일 때만 대문자로
                }
            }
            else if(!firstChar&&c>=65&&c<=90){
                //첫 글자가 아닌데 대문자라면 소문자로 변경
                sb.setCharAt(i,Character.toLowerCase(c));
            }
        }
        
        String answer = sb.toString();
        return answer;
    }
}
