import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> st=new Stack<>();
        
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            
            //여는 괄호면 push
            if(c=='('){
                st.push(c);
            }else{
                //닫는 괄호인데, 스택에 이와 매칭되는 여는 괄호가 없다면? false
                if(st.isEmpty()){
                    answer=false;
                    break;
                }else{
                    //닫는 괄호이고 스택에 이와 매칭되는 여는 괄호가 있다면 해당 여는 괄호를 pop
                    st.pop();
                }
            }
        }
        
        //'(' 가 남아있으면 안닫힌 괄호 존재 -> false
        if(!st.isEmpty()){
            answer=false;
        }
        return answer;
    }
}