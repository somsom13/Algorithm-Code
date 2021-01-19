import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] st1={1,2,3,4,5};
        int[] st2={2,1,2,3,2,4,2,5};
        int[] st3={3,3,1,1,2,2,4,4,5,5};
        List<Integer> list=new ArrayList<>(); //학생을 저장하기 위한 List
        int c1=0;
        int c2=0;
        int c3=0;
        //st1: %5했을 때 나머지 0~4를 그 원소의 idx로
        //st2: %8했을 때 나머지 0~7를 그 원소의 idx로 
        //st3: %10했을 때 나머지 0~9를 그 원소의 idx로
        for(int i=0;i<answers.length;i++){
            if(st1[i%5]==answers[i])
                c1++;
            if(st2[i%8]==answers[i])
                c2++;
            if(st3[i%10]==answers[i])
                c3++;
        } //여기까지 맞은 문제 수!

        int res1=(c1>c2)? c1:c2;
        int res2=(c3>res1)? c3:res1;//res2=가장 높은 점수
        if(c1==res2)
            list.add(1);
        if(c2==res2)
            list.add(2);
        if(c3==res2)
            list.add(3);

        answer=new int[list.size()];
        for(int i=0;i<answer.length;i++)
            answer[i]=list.get(i).intValue(); //list를 int 배열로 변환
        return answer;
    }
}