import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        //progresses:각 작업이 현재 얼마나 진행되었는지
        //speeds: 각 작업의 하루 진행률이 얼마나 되는지
        //스택: LIFO  큐: FIFO
        //앞에 있는 기능이 먼저 배포되어야 하므로 FIFO, queue 사용
        Queue<Integer> workList=new LinkedList<>();//각 작업들의 진행현황 저장할 큐
        HashMap<Integer,Integer> workSpeedMap=new HashMap<>();//진행현황(인덱스로)-회당 진행률
        List<Integer> list=new ArrayList<>();//배포 시, 기능 갯수 저장할 리스트
        
        for(int i=0;i<speeds.length;i++){
            workList.add(progresses[i]);//큐에 순서대로 진행상황 저장
            workSpeedMap.put(i,speeds[i]);//해쉬맵, 진행상황 퍼센트는 유니크한?단일?값이 아니므로 그냥 progresses에 저장된 작업들을 0번~순서대로 임의의 인덱스를 줌
        }
        
        int count=0;//해쉬맵에서 완료된 작업을 처리하기 위한 count라는 변수(큐에서 작업이 완료되고 사라질 때, 인덱스(count)에 따라서 해당하는 값 사라지게)
        while(true){//무한반복
            if(workList.isEmpty()){//비어있다면 종료
                break;
            }
           
            if(workList.peek()>=100){//peek, 맨 앞의 진행률이 100보다 크면 아래 과정 수행
                workList.poll();//첫 번째 값 기능 제거 후
                workSpeedMap.remove(count++);//제거된 기능에 매치되는 해쉬맵(회당 진행속도)도 삭제
                list.add(1);//기능이 하나 완료된 것이므로 리스트에 1 추가
                while(!workList.isEmpty()&&workList.peek()>=100){//만약 다음 기능 진행상황이 100보다 크면
                    workList.poll();//해당 기능 제거
                    workSpeedMap.remove(count++);//마찬가지로 해당 기능의 진행속도 제거
                    int lastIdx=list.size()-1;
                    int updateVal=list.get(lastIdx)+1;//list의 마지막 인덱스 값(=가장 최근에 추가한 값=가장 최근의 배포)에 +1 해서 배포되는 기능의 수 증가
                    list.set(lastIdx,updateVal);//list에 변경된 값 반영
                }//큐의 다음 값(기능) 진행률이 <100이면 while종료
            }
            
            //큐의 기능들의 진행현황 업데이트 해주기
            int tmp=count;//idx로 각 기능별 진행속도 확인하기 위한 변수 tmp
            for(int i=0;i<workList.size();i++){
                int updateProgress=workList.poll()+workSpeedMap.get(tmp++);//큐의 맨 앞 기능의 값을 가져온 후, 해쉬맵을 통해서 해당 기능의 진행속도를 추가 -> 업데이트
                workList.add(updateProgress);//맨 앞 기능을 빼왔으므로, 맨 뒤에 다시 추가해주어서 for문을 다 돌고난 후에는 처음의 큐와 동일한 구조가 되어있도록 만들어준다
            }
            
        }//큐가 비었을 때, 전체 while 종료
        answer=new int[list.size()];
        
        //list를 array로
        for(int i=0;i<answer.length;i++){
            answer[i]=list.get(i).intValue();
        }
        
        return answer;
        
    }
}