package Programmers.Level2;
import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        
        //h번 이상 인용된 논문이 h편 이상이어야 한다. 
        //=> 인용수 기준 내림차순 정렬, 1 <= 인용횟수 < 논문 수 인 인용횟수를 작은 값 부터 찾아서 배열[인용 수]>= 인용수 인지 체크! 
        //[6,5,3,1,0] 3번 이상 인용이려면 앞에서 부터 최소 3편의 인용횟수가 3회 이상이어야함. -> 배열[인용 횟수-1]>=인용횟수 인 최소 인용횟수가 바로 h-index!
        //아니면 그냥 오름차순 정렬해서 오른쪽 idx부터 보자 -> [0,1,3,5,6]: 배열[배열크기-인용횟수] >=인용횟수 인 인용횟수 찾기
        //그 인덱스 바로 이전의 값 <=인용횟수 여야함
    
        //[0, 1, 1, 2, 3, 5, 5, 5, 6] //총 길이=9 (마지막 idx==8)
        //[6, 5, 5, 5, 3, 2, 1, 0] -> h-index가 꼭 배열에 있는 값이 아닐 수도 있다!! (ex. 여기서는 h-index==4)
        Arrays.sort(citations);
        int n=citations.length-1;
        
        if(citations[n]==0){
            return 0;
        }
        
        int count=citations[n];
        while(count>0){
            if(count>n+1){
                count--;
                continue;
            }
            if(citations[n+1-count]>=count){
                break;
            }
            count--;
        }
        
        //while문 내에서 찾지 못한 경우 (ex. [4,4,4])
        if(count<=0){
            return citations.length;
        }
        
        return count;
    }
}