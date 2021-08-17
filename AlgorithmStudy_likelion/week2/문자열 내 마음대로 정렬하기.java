import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer=new String[strings.length];
        
        //n번째 char를 기준으로 정렬하되, 해당 char가 똑같은 문자열이 여러개라면 string의 사전 순으로 정렬하므로
        //n번째 char를 string의 맨 앞에 붙이고 그 뒤에 본 단어를 붙여 정렬
        // => n번째 char가 정렬 1순위, 다음이 정렬 2순위가 됨     
        for(int i=0;i<strings.length;i++){
            strings[i]=strings[i].charAt(n)+strings[i];
        }

        Arrays.sort(strings);
        
        for(int i=0;i<strings.length;i++){
            //0번째 index, 즉 이어붙인 n번째 char를 제외한 string을 answer에 저장
            answer[i]=strings[i].substring(1);
        }
        
        return answer;
    }
}