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
//단, 이렇게 할 경우 시간복잡도가 너무 커서 효율성 떨어진다!!! 
//에라토스테네스의 체 방법 찾아서 보완하기