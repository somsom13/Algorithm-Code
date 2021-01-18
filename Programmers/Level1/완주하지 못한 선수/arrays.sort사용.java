import java.util.Arrays;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);//두 배열을 오름차순으로 정렬

        for(int i=0;i<participant.length;i++)
            if(i==participant.length-1){//완주하지 못한 선수가 배열의 마지막 원소일 때 따로 처리
                answer=participant[i];
            }
            else if(!participant[i].equals(completion[i])){//배열을 돌다가 participant에는 있지만 completion 배열에 없는 선수 발견시 정답처리
                answer+=participant[i];
                break;
            }
        return answer;
    }
}