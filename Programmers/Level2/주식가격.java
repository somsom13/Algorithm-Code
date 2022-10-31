import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] prices) {
        //나 보다 뒤에 있는 가격이 지금 내 가격보다 낮아지면? -> 그 때 가격이 떨어진 것!
        //모든 prices 에 대해서 지금 내 가격보다 몇 번 idx 뒤에 있는 가격에서 처음으로 가격이 떨어지는가를 찾으면 된다
        int[] answer = new int[prices.length];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int price : prices) {
            queue.offer(price);
        }
        
        int idx = 0;
        
        while (!queue.isEmpty()) {
            int nowPrice = queue.poll(); //모든 가격을 순서대로 체크
            int queueSize = queue.size();
            int maintainTime = 0;
            
            if (queueSize == 0) { //마지막 가격
                answer[idx] = 0;
                break;
            }
            
            for (int i = 1; i <= queueSize; i++) {
                int nextPrice = prices[idx+i]; //이번에 체크하는 것의 다음 가격 ~ 맨 끝 가격까지 모두 비교
                if (nowPrice <= nextPrice) {
                    maintainTime++;
                    continue;
                }
                //가격이 떨어지는 첫 지점을 발견하면
                maintainTime++; //바로 다음에 떨어져도 "1초 유지" 로 생각되므로! 
                break;
            }

            answer[idx] = maintainTime;
            idx++;
        }
        
        return answer;
    }
    
}