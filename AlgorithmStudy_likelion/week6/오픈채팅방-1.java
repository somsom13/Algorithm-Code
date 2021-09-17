import java.util.*;

/**
user id, msg를 모두 저장하는 객체 하나로 관리 -> 시간초과 */

class User{
    String uid;
    String msg;
    
    User(String uid,String msg){
        this.uid=uid;
        this.msg=msg;
    }
}

class Solution {
    List<User> answerList=new ArrayList<User>();
    public String[] solution(String[] record) {
        String[] answer;
        //" "기준으로 2번 나누기 0: 동작 1: 유저아이디 2:닉네임
        //단, leave는 uid만 표시되므로 uid에 해당하는 닉네임은 알아서 찾아주어야함
        
        for(int i=0;i<record.length;i++){
            String s=record[i];
            String[] recordSplit=s.split(" ");
            String uid=recordSplit[1];//1은 항상 uid
            if(recordSplit[0].equals("Leave")){
                answerList.add(new User(uid,findLeaveNickname(uid)+"님이 나갔습니다."));
            }else if(recordSplit[0].equals("Enter")){
                answerList.add(new User(uid,recordSplit[2]+"님이 들어왔습니다."));
                //들어왔을 때, uid확인하고 일치하는거 있으면 이름 바꿔주기
                checkChange(uid,recordSplit[2]);
            }else{
                //answer[i]=recordSplit[2]+"님이 들어왔습니다.";
                //아이디를 변경한 경우 이기 때문에 기존의 기록들을 확인하고, 같은 uid에 대해서는 닉네임 변경
                checkChange(uid,recordSplit[2]);
            }
        }
        
        answer=new String[answerList.size()];
        
        for(int i=0;i<answer.length;i++){
            answer[i]=answerList.get(i).msg;
        }
        return answer;
    }
    
    
    private String findLeaveNickname(String uid){
        for(User user:answerList){
            String foundUid=user.uid;
            if(foundUid.equals(uid)){
                String[] splitMsg=user.msg.split("님");
                return splitMsg[0];
            }
        }
        return "none";//못찾으면 none이라고 return
    } 
    
    private void checkChange(String uid,String changeName){
        //System.out.println("checkChange! uid: "+uid+", answer length: "+answerList.size());
        for(int i=0;i<answerList.size();i++){
            String foundUid=answerList.get(i).uid;
            if(!foundUid.equals(uid))
                continue;
            String foundMsg=answerList.get(i).msg;
            String[] splitMsg=foundMsg.split("님");//그러면 [1]값이 님~뒤의 문장이 된다.
            answerList.set(i,new User(uid,changeName+"님"+splitMsg[1]));
        }
        return;
    }
}