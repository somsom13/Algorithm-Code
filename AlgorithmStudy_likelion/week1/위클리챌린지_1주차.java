/*  위클리 챌린지 1주차 - 부족한 금액 계산하기 */
class Solution {
    public long solution(int price, int money, int count) {
        long total=0;
        for(int i=1;i<=count;i++){
            total+=price*i;
        }
        long leftOver=money-total;
        //남은 금액 >=0 이면 부족한금액=0, 남은금액 <0 이면 부족한금액=(-남은금액)
        return (leftOver >= 0) ? 0 : -leftOver; 
    }
}