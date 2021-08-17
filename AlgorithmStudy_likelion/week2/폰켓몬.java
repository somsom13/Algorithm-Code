import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int choose=nums.length/2;
        List<Integer> list=new ArrayList<>();
        
        for(int i=0;i<nums.length;i++){
            //list에 중복되지 않는 폰켓몬만 추가
            if(!list.contains(nums[i])){
                list.add(nums[i]);
            }
            if(list.size()==choose){
                break;
            }
        }
        //중복되지 않는 폰켓몬을 담은 list의 크기가 골라야 하는 폰켓몬 수 보다 작아도 (즉 중복되지 않는 폰켓몬 수 < 골라야하는 폰켓몬 수)
        //해당 경우에는 부족한 수를 중복되는 폰켓몬 (이미 선택되어 있는 종류)로 채우게 되기 때문에 
        //"가장 많은 종류의 폰켓몬을 담았을 때의 폰켓몬 종류 수"는 중복되지 않는 폰켓몬들만 담았을 때와 같다. (같은 종류를 추가한다면 폰켓몬 종류는 증가하지 않는다!)
        //예를 들어 [1,1,3,3,3,3] 일 때 중복되지 않는 list는 [1,3] 이며 골라야 하는 수는 3종류 이다. [1,3] list 에 추가할 수 있는 값은 1,3 뿐이므로
        //[1,1,3] 이 되던 [1,3,3] 이 되던 최대 폰켓몬 종류는 총 2종류 ([1,3] 일 때와 동일) 로 동일하다. 
        answer=list.size();
        return answer;
    }
}