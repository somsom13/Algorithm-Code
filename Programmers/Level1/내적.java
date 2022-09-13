class Solution {
    public int solution(int[] a, int[] b) {
        //내적: 0<=i<=n 일 때, ai*bi 의 sum
        int answer = 0;
        for(int i=0;i<a.length;i++){
            answer+=a[i]*b[i];
        }
        
        return answer;
    }
}