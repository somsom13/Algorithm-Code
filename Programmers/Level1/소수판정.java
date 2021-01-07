//2개 시간초과, 나중에 수정하기
class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=2;i<=n;i++){
            answer++;
            for(int j=2;j<i;j++)
                if(i!=2&&i%j==0){
                    answer--;
                    break;
                }
                }
        return answer;
    }
}