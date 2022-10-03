package Programmers.Level2;
import java.util.Arrays;

class Solution {
    private static int[] weights;
    private static int maxIdx;
    private static int curIdx;
    public int solution(int[] people, int limit) {
        int answer = 0;
        //모든 사람들을 구출하기 위해 필요한 구명보트 개수의 최소값 => 한 보트에 최대한 많은 사람을 태우는 것? 
        //가장 몸무게가 가벼운 사람을 태움 -> 몸무게가 가장 많이 나가는 사람 중, 이 보트에 태울 수 있는 사람을 우선 태움
        //현재 태우지 않은 사람 중 가장 가벼운 사람과 최대로 무거운 사람을 함께 태우자!
        
        //30, 40, 50, 60 
        weights=people;
        Arrays.sort(weights);
        maxIdx=weights.length-1;
        for(int i=0;i<weights.length;i++){
            curIdx=i;
            if(weights[i]==0){
                continue;
            }
            if(limit-weights[i]>=40){ //더 태울 수 있다면 찾자
                findAnother(limit-weights[i], limit);
            }
        
            answer++;
        }
        return answer;
    }
    
    private static void findAnother(int weight, int limit){
        for(int i=maxIdx;i>curIdx;i--){
            if(weights[i]==0||weights[i]>weight){
                continue;
            }
            //weight보다 작거나 같은, 즉 보트에 함께 태울 수 있는 사람 발견
            weights[i]=0;
            maxIdx=i-1; //다음번에 서치 시, 이것보다 작거나 같은 몸무게를 가진 사람부터 찾으면 된다
            return;
            //더 태울 수 있는 사람이 없다면 그냥 return 됨
        }
    }
}