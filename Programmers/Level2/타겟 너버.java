import java.util.ArrayList;

class NumberNode{
    int calcResult;
    int num;
    int idx;
    
    public NumberNode(int num, int calcResult, int idx){
        this.calcResult=calcResult;
        this.num=num;
        this.idx=idx;
    }
}

class Solution {
    private static int n;
    private static int count=0;
    public int solution(int[] numbers, int target) {
        int answer = 0;

        ////[0]의 숫자 -> -/+ 다음 숫자 -> -/+ 다음 숫자.... **숫자의 순서가 바뀌지 말아야 한다**
        //이전까지의 결과에 -/+(이번숫자)를  계속해서 탐색한다! => dfs
        //지금까지의 연산 결과, 이번 숫자, 인덱스를 저장하는 노드들을 만들어두자!
        //노드들의 연결관계: 순서가 변하지 않아야하므로 numbers 배열에 들어온 값 순서대로 1인 간선으로 주르륵 연결되었다고 생각
        //(그리고 얘네를 스택에 넣어가면서 DFS 하면 처음수~끝수까지 target 만족하는 모든 경로를 찾을 수 있음)

        n=numbers.length;

        //부호가 +일 때, -일 때의 리스트를 따로 만듬 (다른 부호에 대해서 같은 idx로 접근하기 위해)
        ArrayList<NumberNode> plusNumbers=new ArrayList<>();
        ArrayList<NumberNode> minusNumbers=new ArrayList<>();
        
        for(int i=0;i<numbers.length;i++){
            int num=numbers[i];
            plusNumbers.add(new NumberNode(num,0,i));
            minusNumbers.add(new NumberNode(-num,0,i));
        }

        //numbers[0]의 값이 +일 때와 - 일 때에 대해서 dfs 호출
        dfs(plusNumbers.get(0),target,plusNumbers, minusNumbers);
        dfs(minusNumbers.get(0),target,plusNumbers, minusNumbers);
            
        return count;
    }
    
    private static void dfs(NumberNode nowNode,int target, ArrayList<NumberNode> plusNumbers, ArrayList<NumberNode> minusNumbers){
        //"지금까지의 연산 결과"에 이번 노드의 값을 더해준다
        nowNode.calcResult+=nowNode.num;
        
        //더 이상 다음 노드가 없을 때 처리
        if(nowNode.idx==(n-1)){
            if(nowNode.calcResult==target){
                count++;
            }
            nowNode.calcResult-=nowNode.num; //leaf까지 탐색한 후에는 다시 위의 노드로 올라갈 때, 지금까지의 연산 결과에서 자기 자신을 빼주어야 한다
            return;
        }
        
        int nextIdx=nowNode.idx+1;
        
        NumberNode nextMinusNode=minusNumbers.get(nextIdx);
        NumberNode nextPlusNode=plusNumbers.get(nextIdx);
        
        //각각의 +/- 다음 숫자 노드의 현재까지의 연산 결과를 지금까지의 연산 결과로 맞춰준다
        nextMinusNode.calcResult=nowNode.calcResult;
        dfs(nextMinusNode,target,plusNumbers, minusNumbers);
        nextPlusNode.calcResult=nowNode.calcResult;
        dfs(nextPlusNode,target,plusNumbers, minusNumbers);
        
        //return 될 때, 즉 앞의 노드로 돌아갈 때 이번에 더해졌던 자신의 수를 역으로 빼주어야함
        nowNode.calcResult-=nowNode.num;
    }
    
}