class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int min;
        if(n<m)
            min=n;
        else
            min=m;
        for(int i=min;i>0;i--)
            if((n%i==0)&&(m%i==0)){
                answer[0]=i;
                break;
            }
        answer[1]=n*m/answer[0];
                
        return answer;
    }
}