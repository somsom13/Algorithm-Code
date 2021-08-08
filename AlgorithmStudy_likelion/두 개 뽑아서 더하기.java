import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer;
        List<Integer> list=new ArrayList<>(); //덧셈결과 중복제거
        List<Integer> sumList=new ArrayList<>();//총 덧셈결과 저장
        
        //sumList에 numbers배열의 각 인덱스 합 저장
        for(int i=0;i<numbers.length-1;i++){
            for(int j=i+1;j<numbers.length;j++){
                sumList.add(numbers[i]+numbers[j]);
            }
        }
        
        //contains를 통해 중복제거한 덧셈 결과를 list에 저장
        for(int sum:sumList){
            if(!list.contains(sum)){
                list.add(sum);
            }
        }

        list.sort(null);
        answer=new int[list.size()];

        //list 정렬 후, list to array (int)
        for(int i=0;i<answer.length;i++){
            answer[i]=list.get(i).intValue();
        }
        Arrays.sort(answer);
        
        return answer;
    }
}