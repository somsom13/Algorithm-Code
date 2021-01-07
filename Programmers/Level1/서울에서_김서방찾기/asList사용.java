import java.util.*;
class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        int idx=Arrays.asList(seoul).indexOf("Kim");
        //Arrays.asList는 배열을 list 처럼 사용할 수 있게 해준다. 단, 배열 타입이 not primitive 일 때만 사용 가능하다. 
        answer+="김서방은 "+idx+"에 있다";
        return answer;
    }
}