import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        //완전탐색: brute_force, 백트래킹, bfs(너비 우선 탐색) 등등
        
        //아래 배열들: 수포자 3명이 찍는 방식 (1번 문제부터!)
        int[] st1={1,2,3,4,5}; //1,2,3,4,5 반복
        int[] st2={2,1,2,3,2,4,2,5}; //2로 시작, 그 뒤로 12345 반복
        int[] st3={3,3,1,1,2,2,4,4,5,5}; //3,1,2,4,5 순서로 두번씩 반복
        List<Integer> list=new ArrayList<>();
        
        //3명의 점수, 0으로 초기화
        int c1=0;
        int c2=0;
        int c3=0;
        
        
        //각 수포자들의 배열의 길이만큼 나눴을 때, 그 나머지를 점수 count 할 인덱스로 설정!
        //배열의 길이만큼 돌고, 그 다음 문제부터는 다시 배열의 처음부터 반복하기 때문. 
        //배열 0번~배열끝번 까지 
        //st1: %5했을 때 나머지 0~4 를 그 원소의 idx로
        //(배열이 총 5칸(idx: 0~4) 이므로 i를 5로 나눴을 때의 나머지로 모든 배열을 볼 수 있다) 
        //st2: %8했을 때 나머지 0~7를 그 원소의 idx로
        //st3: %10했을 때 나머지 0~9를 그 원소의 idx로
        for(int i=0;i<answers.length;i++){
            //정답배열의 길이 = 문제의 갯수
            if(st1[i%5]==answers[i])//각 학생마다 찍은 값과 정답이 일치하는지 확인
                c1++;
            if(st2[i%8]==answers[i])
                c2++;
            if(st3[i%10]==answers[i])
                c3++;
        } //여기까지 맞은 문제 수!
        
        int res1=(c1>c2)? c1:c2; //수포자1과 수포자2 점수 비교
        int res2=(c3>res1)? c3:res1;//위의 결과와 수포자3의 점수 비교 즉, 가장 점수가 높은 사람이 res2에 저장
        
        //가장 점수가 높은 학생을 배열에 저장한다. 
        if(c1==res2)
            list.add(1);
        if(c2==res2)
            list.add(2);
        if(c3==res2)
            list.add(3);
        
        //answer배열의 길이 = list의 크기
        answer=new int[list.size()];
        for(int i=0;i<answer.length;i++)
            //list의 값을 int로 변환시켜 배열에 저장
            answer[i]=list.get(i).intValue();
        return answer;
    }
}