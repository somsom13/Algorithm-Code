import java.lang.StringBuilder;
import java.util.Stack;


//input으로 들어오는 number 문자열의 길이가 매우 길다 -> Long 범위도 벗어나는 큰 수가 올 수도 있음
//그렇기 때문에 number를 실제 숫자로 변환시켜서 큰 수를 찾는 방법은 쓸 수 없다! 
//number의 각 숫자에 접근하는 방식으로 생각해야함 -> for문을 돌면서 현재 문자열에서 가장 작은 수를 제거

//1이면 무조건 제거임. 
//가장 작은 수가 2가 되는 순간 -> 그 다음부터는 2가 나오면 무조건 바로 제거!

//아니지,,, 맨 앞에 최대한 가장 큰 숫자가 오게 해야함 -> 
//number 전체를 돌면서 i번째 수를 확인 -> 다음 수가 더 크다면? stack에서 제거하고 이번 수를 push

//ex. 4177252841  4 /  4>1 => 41 / 1<7&&4<7 => 7 (1,4 다 제거) / 7==7 => 77 / 7>2 => 772 / 2<5 => 775 / 
//즉 원래 스택에 있던 숫자 >= 새 숫자면? 새 숫자를 push
//원래 스택에 있던 숫자 <새 숫자면? -> 스택의 맨 앞까지 새 숫자와 비교 -> 새 숫자보다 크거나 같은게 나올 때 까지 다 pop 하고 새 숫자를 넣음
//이거를 제거한 문자 개수가 k개 일 때 까지 반복

class Solution {
    public String solution(String number, int k) {
        String answer;
        int count=1;
        
        Stack<Character> st=new Stack<>();
        Boolean end=false;
        int lastIdx=0;
        
        char c=number.charAt(0);

        StringBuilder sb=new StringBuilder();
        
        st.push(c);
        for(int i=1;i<number.length();i++){
            c=number.charAt(i);
            while(st.peek()<c){
                st.pop();
                if(count==k){
                    end=true;
                    lastIdx=i;
                    break;// break 하고 나면 그 뒤의 남은 숫자는 모두 스택에 붙여넣어줘야한다
                }
                count++;
                if(st.isEmpty()){
                    break;
                }
            }
            st.push(c);

            if(end){
                //목표한 만큼 숫자를 빼서 종료해야함 -> 그 뒤의 남은 number 숫자들을 뒤에 이어붙여야한다!
                for(int j=lastIdx+1;j<number.length();j++){
                    st.push(number.charAt(j));
                }
                break;
            }
            
            if(i==number.length()-1){
                //끝까지 다 돌았는데도 뺀 숫자가 목표한 숫자보다 적은 경우 (즉 뒤로갈수록 작아짐)
                //그러면 맨 뒤에서부터 빼주면 됨
                for(int j=count;j<=k;j++){
                    st.pop();
                }
            }
        }
    
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        sb=sb.reverse();
        answer=sb.toString();
        
        for(int i=0;i<answer.length();i++){
            if(answer.charAt(i)!='0'){
                break;
            }
            sb.deleteCharAt(0);
        }
        
        answer=sb.toString();
        if(answer.equals("")){
            answer="0";
        }
        
        return answer;
    }
}