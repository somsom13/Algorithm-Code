import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        HashMap<Integer,Integer> map=new HashMap<>(); //각 stage별 머물러 있는 사용자 수 저장
        HashMap<Integer,Double> failure=new HashMap<>();//각 stage별 실패율 저장
        //i 단계 도전자 수=i~끝까지의 단계에 멈춰있는 사용자 수 총합
        Arrays.sort(stages);

        //stages배열을 모두 돌면서 map에 각 단계에 대한 사용자 수 저장
        for(int i:stages){
            if(map.get(i)==null){
                //만약에 해당 stage가 등록되어 있지 않다면 사용자 수 1로 초기화
                map.put(i,1);
            }else{
                //이미 등록되어 있다면 값 1 증가
                map.put(i,map.get(i)+1);
            }
        }
        
        for(int i=1;i<N+1;i++){//1~N번째 stage를 모두 돌면서 실패율 확인
            //i번째 stage에 대해
            if(map.get(i)!=null){
                //만약 map에 값이 있다면, 즉 i번째 단계에 실패자가 있다면
                int userCount=0;
                for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
                    //entrySet을 사용해서 hashMap의 모든 값들을 가져온다
                    if(entry.getKey()>=i){
                        //해당 stage번호가 i번 보다 클 때만 사용자 수 추가 (i번째 단계보다 높은 단계에 있는 경우에만 i번째 단계를 거쳐온 사용자가 되므로)
                        userCount+=entry.getValue();
                    }
                }
                //for문을 다 돌아서 i stage에 대한 사용자 수(userCount)를 모두 센 후, failure hashMap에 실패율 저장
                failure.put(i,(double)map.get(i)/userCount);
            }else{
                //만약 map에 i번째 stage가 저장되어 있지 않다면 i번째 stage에서의 실패자가 없다는 것
                //즉, 실패율은 0
                failure.put(i,0.0);
            }
        }
        
        //failure hashMap을 value(=실패율) 기준 정렬
        List<Integer> listKeySet = new ArrayList<>(failure.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (failure.get(value2).compareTo(failure.get(value1))));

        int idx=0;
        for(int key : listKeySet) { 
            //각 set의 key값에 대해, answer 배열에 저장 (set에는 이미 내림차순 정렬 되어있는 상태)
            answer[idx++]=key;
        }

        return answer;
    }
}