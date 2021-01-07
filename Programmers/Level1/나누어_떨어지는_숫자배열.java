import java.util.*;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer={};
        List<Integer> list=new ArrayList<>();
        for(int a:arr)
            if(a%divisor==0)
                list.add(a);
        if(list.size()==0){
            answer=new int[1];
            answer[0]=-1;
        }
        else{
            answer=new int[list.size()];
            Collections.sort(list);
            for(int i=0;i<list.size();i++)
                answer[i]=list.get(i).intValue();
        }
            return answer;
    }
}