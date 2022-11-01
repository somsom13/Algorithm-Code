package Programmers.Level2;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Work {
    int progress;
    int speed;
    
    public Work(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }
}

class Solution {
    private static Queue<Work> works;
    public int[] solution(int[] progresses, int[] speeds) {
        //뒤의 기능이 먼저 개발될 수 있으나, 배포는 앞의 기능과 함께 된다 (즉 FIFO) -> Queue 사용!
        //배포는 하루 한 번만 가능, 하루의 끝에! 
        works = new LinkedList<>();
        ArrayList<Integer> answerList = new ArrayList<>();
        
        initializeWorks(progresses, speeds);
     
        int leftWork = works.size();
        int todayOut = 0;
        while (leftWork != 0) {
            
            doTodayWork(leftWork);
            
            getServiceOut();
            
            int afterWork = works.size();
            
            todayOut = calculateTodaysOut(leftWork, afterWork);
            
            if (todayOut != 0) {
                answerList.add(todayOut);
            }
            
            leftWork = afterWork;
        }
        
        int[] answer = toArray(answerList);
        
        return answer;
    }
    
    private static void initializeWorks(int[] progresses, int[] speeds) {
        for (int i = 0; i < progresses.length; i++) {
            Work work = new Work(progresses[i], speeds[i]);
            works.offer(work);
        }
    }
    
    private static void doTodayWork(int leftWork) {
        for (int i = 0; i < leftWork; i++) {
            Work work = works.poll();
            work.progress += work.speed;
            works.offer(work);
        }
    }
    
    private static void getServiceOut() {
        while (!works.isEmpty()) {
            Work work = works.peek();
            if (work.progress >= 100) {
                works.poll();
                continue;
            }
            break;
        }
        return;
    }
    
    private static int calculateTodaysOut(int leftWork, int afterOut) {
        int todayOut = 0;
        
        if (afterOut < leftWork) {
            todayOut = leftWork - afterOut;
        }
        return todayOut;
    }
    
    private static int[] toArray(ArrayList<Integer> list) {
        int[] answer = new int[list.size()];
        
        for (int i = 0; i <answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
            
}
