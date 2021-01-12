class Solution {
    public long[] solution(long x, int n) { //주어진 문제조건에서 x는 -10000000 이상, 10000000 이하인 정수,n은 1000 이하인 자연수
        long[] answer = new long[n];
        for(int i=0;i<n;i++)
            answer[i]=x+i*x;
        return answer;
    }
}
//주어진 문제조건에서 x는 -10000000 이상, 10000000 이하인 정수,n은 1000 이하인 자연수 이므로 x를 long 형으로 두고 푸는게 중요하다!