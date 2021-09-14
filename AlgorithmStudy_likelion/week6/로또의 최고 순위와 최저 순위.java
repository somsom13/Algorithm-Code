import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer;
        int unknownNum=0;
        int count=0;
        List<Integer> checkLotto=new ArrayList<>();
        
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        
        for(int l:lottos){
            if(l==0)
                unknownNum++;
            checkLotto.add(l);
        }
        
        for(int num:win_nums){
            if(checkLotto.contains(num))
                count++;
        }
        
        answer=new int[2];
        //count+unknownNum: 최대 맞출 수 있는 문제 개수. 
        //7-맞춘 문제 수=등수
        if(count+unknownNum==0){
            answer[0]=6;
        }else{
            answer[0]=7-(count+unknownNum);
        }

        if(count==0){
            answer[1]=6;
        }else{
            answer[1]=7-count;
        }
        
        
        return answer;
    }
}