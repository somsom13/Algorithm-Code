import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        //번호 순대로 나열, 오름차순 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        //체육복을 여러벌 가지고 있으면서도(reserve의 원소) 한 벌을 잃어버려서 다른 학생에게 빌려줄 수 없는 경우
        //reserve와 lost배열에서 해당 번호의 학생을 지우기 위해 0으로 바꾼다.
        for(int i=0;i<lost.length;i++){
            for(int j=0;j<reserve.length;j++){
                if(lost[i]==reserve[j]){
                    lost[i]=0;
                    reserve[j]=0;
                    answer++;
                    //0으로 바꾸면서 해당 학생은 더이상 체육복을 빌려줄 수 없으므로 체육을 할 수 있는 학생수를 증가시킨 후
                }
            }
        }
        
        //answer(2벌 중 한 벌을 잃어버린 학생 수 )+전체 학생 수 -lost에 들어있는 학생 수(체육복을 잃어버린 모!든! 학생 수(여러벌을 가지고 있는 친구 포함))
        //=> 현재까지의 answer = 전체 학생 수 - 체육복을 잃어버린 모든 학생 수 + 잃어버렸지만 다행히 한 벌이 더 있는 학생 수 
        answer+=n-lost.length; 


        for(int i=0;i<lost.length;i++){
            for(int j=0;j<reserve.length;j++){

                //만약 잃어버린 학생 번호가 0번 이라면, 즉 빌려줄 필요가 없다면 다음 학생으로
                if(lost[i]==0){
                    break;
                }else if(reserve[j]==0){//여벌을 가진 학생 번호가 0번 이라면, 즉 더이상 빌려줄 수 없다면 다음 학생으로
                    continue;
                }
                else if(lost[i]==reserve[j]-1||lost[i]==reserve[j]+1){//없는 학생의 번호가 여벌 학생의 번호 +-1 이라면
                   reserve[j]=0;//reserve[j]번 학생은 더이상 빌려줄 수 없으므로 학생의 번호를 지운 후 (0번으로 바꿈)
                   answer++;//lost[i]번 학생이 체육을 할 수 있게 되었으므로 answer 증가
                   break;//다음 lost학생으로 
                }
            }
        }
        return answer;
    }
}