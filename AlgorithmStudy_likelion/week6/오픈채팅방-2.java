import java.util.*;

/*
HashMap은 그냥 put(key,value) 했을 때
이미 존재하는 key라면? value를 바꾸어 저장
없는 key라면? 새롭게 값 저장 
=> Enter, Change 동작 시 어떤 경우에든 일단은 put 한다. (그러면 알아서 추가되거나, 기존값이 변경되거나 함)
*/

class Solution {
    List<String> answerList=new ArrayList<>();//"uid+님이+동작" 저장하는 list
    HashMap<String,String> userInfo=new HashMap<>();//uid와 nickname 저장, 입장시에 새롭게 추가 / 이미 존재하는 uid일 경우 nickname(value) 변경
    
    public String[] solution(String[] record) {
        String[] answer;
        //" "기준으로 2번 나누기 0: 동작 1: 유저아이디 2:닉네임
        
        for(int i=0;i<record.length;i++){
            String s=record[i];
            String[] recordSplit=s.split(" ");
            String action=recordSplit[0];
            String uid=recordSplit[1];//1은 항상 uid
            if(action.equals("Leave")){
                answerList.add(uid+"님이 나갔습니다.");
            }else if(action.equals("Enter")){
                answerList.add(uid+"님이 들어왔습니다.");
                //들어왔을 때, uid가 userInfo map에 존재하는지 확인하고, 있으면 닉네임 변경 반영
                userInfo.put(uid,recordSplit[2]);
            }else{
                //아이디를 변경한 경우 이기 때문에 userInfo 에서 uid 찾고, 닉네임 변경 반영
                userInfo.put(uid,recordSplit[2]);
            }
        }
        
        answer=new String[answerList.size()];
        
        for(int i=0;i<answer.length;i++){
            String[] splitMsg=answerList.get(i).split("님");//uid+님이 ~했습니다. 님 기준으로 자르기
            String uid=splitMsg[0];
            String actionString=splitMsg[1];
            answer[i]=userInfo.get(uid)+"님"+actionString;
        }
        return answer;
    }
    

}