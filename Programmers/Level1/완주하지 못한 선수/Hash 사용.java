import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String,Integer>map=new HashMap<String,Integer>(); //key:선수이름, value:key이름을 가진 선수의 수. 완주했다면 0, 완주하지 못했다면 1
        
        for(String part:participant)
            if(map.get(part)!=null){//map에 이미 있는 이름, 즉 동명이인이라면
                map.put(part,map.get(part)+1);//+1 해주어 n명의 동명이인임을 나타낸다.
            }
            else //map에 추가되어있지 않은 사람이라면
                map.put(part,1); //1로 표시
                
        for(String comp:completion)
            map.put(comp,map.get(comp)-1); //완주한 선수이름 (key)에 대해 value를 -1 해준다. 
            // 동명이인이 없는 경우: 완주했을 때 -> 1-1=0, 완주하지 못했을 때 -> 1
            // 동명이인이 n명 있는 경우: 완주한 사람이 m명이라 하면 
            // 모두 다 완주했을 때 -> m=n이므로 n-n=0, 한 명이 완주하지 못했을 때 -> m=n-1 이므로 n-n+1=1

        for(String st:map.keySet())
            if(map.get(st)==1){//value가 1이면 완주하지 못한 선수
                answer=st;
                break;
            }
            
        return answer;
    }
}