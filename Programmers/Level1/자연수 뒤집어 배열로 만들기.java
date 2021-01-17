class Solution {
    public int[] solution(long n) {
        String s=Long.toString(n);
        int[] answer=new int[s.length()];
        for(int i=0;i<answer.length;i++)
            answer[i]=s.charAt(answer.length-i-1)-'0';
        
        return answer;
    }
}