import java.util.*;
class Solution {
    public long solution(long n) {
        long answer = -1;
        Double res=Math.sqrt(n); //Math.sqrt() 메소드를 사용하면 n의 제곱근을 구할 수 있다.
        if(res==res.intValue()) //n이 양의 정수의 제곱이라면, 소수점 뒷자리를 intValue()로 떼어낸 결과와 sqrt 결과가 같다. 
            answer=(long)((res+1)*(res+1));
        return answer;
    }
}