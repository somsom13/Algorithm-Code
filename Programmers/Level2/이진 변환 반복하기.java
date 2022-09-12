class Solution {
    public int[] solution(String s) {
        //1. s에서 모든 0을 제거
        //2. s의 길이를 이진수로 표현
        //3. 이진수로 표현된 값이 1이 될 때 == s의 길이가 1일 때 까지 1과 2를 반복
        int count=0;
        int zeros=0;
        
        while(!s.equals("1")){
            count++;
            int thisZero=(int)s.chars().filter(c->c=='0').count();
            zeros+=thisZero;
            //1. toBinaryString 사용
            s = Integer.toBinaryString(s.length()-thisZero);   
        }
        int[] answer = {count,zeros};
        return answer;
    }
}