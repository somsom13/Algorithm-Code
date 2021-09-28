import java.util.*;

class Solution {
    boolean[] visited;
    List<Integer> answerList=new ArrayList<>();
    List<Integer> selectNum=new ArrayList<>(); 
    public int solution(int[] nums) {
        visited=new boolean[nums.length];       
        //소수: 1과 자기자신만을 약수로 가짐
        //조합 사용...! 백트래킹?

        makeCombination(nums,0); //순열이 아니라 조합이므로, 중복된 조합이 되는경우는 생략해줘야함

        return answerList.size();
    }
    
    private void makeCombination(int[] nums,int startNum){   
        //3개 뽑으면 소수 체크하고 끝!  
        if(selectNum.size()==3){
            checkDecimal();
            return;
        }
        
        for(int i=startNum;i<nums.length;i++){
            //방문했던 노드라면 다음 노드 검사!
            if(visited[i]==true)
                continue;
            
            selectNum.add(nums[i]);//리스트에 현재 숫자 추가
            visited[i]=true;//방문처리
            makeCombination(nums,++startNum);//다음 노드부터 찾기! -> 조합이기 때문에 체크하는 노드의 idx를 증가시켜야함 (동일한 조합 여러개 생기는거 방지)
            /**
            ex) 1 2 3 4 가 nums라 하면
            1 2 3 /  1 2 4 / 1 3 4 /2 3 4 로 만들 수 있음. 
            그런데 여기서 startNum을 지정해주지 않으면, 1 3 2 / 1 4 2 / 1 4 3 등 중복된 조합이 생길 수 있다!  */
            visited[i]=false;
            //3 자리 수 다 만들고나면 or 더이상 for문을 돌 수 없으면(모든 노드를 다 확인했으면) makeCombination 함수가 return됨
            int lastIndex=selectNum.size()-1;
            selectNum.remove(lastIndex);
        }
    }
    
    private void checkDecimal(){
        int sum=0;
        for(int i=0;i<3;i++){
            sum+=selectNum.get(i);
        }
        
        if(sum==0||sum==1)
            return;
        
        for(int i=2;i<sum;i++){
            if(sum%i==0)
                return;
        }
        answerList.add(sum);
    }
}