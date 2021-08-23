import java.util.*;
class Solution {
    public long solution(String s) {

        HashMap<String,Integer> numMap = new HashMap<>();
        String[] numbers={"zero","one","two","three","four","five","six","seven","eight","nine"};
        
        for(int i=0;i<numbers.length;i++){
            numMap.put(numbers[i],i);
        }
        
        for(String str:numbers){
            //numbers 배열을 전부 돌면서, s라는 문자열에서 numbers 배열의 각 요소와 일치하는 문자열을 찾은 경우, 문자열(key)에 해당하는 숫자(value)를 가져와 바꿔준다! => replace 메소드 사용.  문자열 형태의 숫자로 return 해주자.
            s=s.replaceAll(str,numMap.get(str).toString());
        }

        return Long.parseLong(s);
    }
}