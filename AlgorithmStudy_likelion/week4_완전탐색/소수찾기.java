import java.util.*;

class Solution {
    List<Integer> decimalList=new ArrayList<>();
    boolean[] visited=new boolean[7]; //최대 numbers 배열의 길이가 7이므로, 최대 노드 갯수도 7
    public int solution(String numbers) {
        int answer = 0;
        int totalLen=numbers.length();
        //한 자리수 ~ count 자리수 숫자 체크
        for(int i=1;i<=totalLen;i++){
            String newString="";
            makeStringNumber(i,numbers,newString); //main에서는 한 번만 호출 -> 원하는 길이(i)가 될 때 까지 makeStringNumbers 내에서 재귀호출 되고, i길이의 숫자 문자자열 모든 경우의 수를 확인한 후에 돌아온다.
            
        }
        
        return decimalList.size();
    }
    
    /* makeStringNumber : 재귀함수! */
    private void makeStringNumber(int len,String numbers,String newString){
        //makingString.length()=len 이 될 때 까지 숫자 만듬
        //모든 numbers배열을 방문하도록 -> idx순서대로, 깊이우선탐색!! dfs (재귀함수)
        //numbers의 각 원소가 하나의 노드라고 생각
        
        /* 종료조건 : 만들고자 하는 길이 (len) 만큼의 길이가 되었을 때! */
        if(newString.length()==len){
            System.out.println("End of Length, made string: "+newString);
            int newNum=Integer.parseInt(newString);
            checkDecimal(newNum);//중복체크 및 소수확인
            return;
        }
        
        for(int j=0;j<numbers.length();j++){
            if(visited[j]){
                //visited[j]==true, 즉 j번째 idx의 문자를 이미 이어 붙였다면 다음 문자(노드)로 넘어감
                continue;
            }
            newString+=numbers.charAt(j);
            visited[j]=true;//j번 노드를 방문처리
            System.out.println("now node: "+j+",  string: "+newString);
            //재귀!!!! 이어붙인 문자열에 대해서 다시 다른 문자들을 체크하며 이어붙여 나간다. 
            makeStringNumber(len,numbers,newString);
            //이 때, for은 다시 0부터 돌게 되지만 전역변수인 visited에 이미 방문한 노드들이 표시되어 있으므로 중복되지 않게 돌 수 있다.
            System.out.println("returned node number: "+j);
            //여기서 return되면, 원하는 길이의 문자열 한 가지 케이스를 만들었다는 의미이므로 직전 visited를 없애준다. (다른 경우의 수 확인하기 위함)
            visited[j]=false;//계속해서 해당 위치로 return 되기 때문에, 바로 직전의 idx만 방문안함 표시 해주면 모든 재귀함수가 return 될 때 모든 visited가 false가 된다! 
            
            newString=newString.substring(0,newString.length()-1);
            /* 왜 visited=false로 바꾼 후, 맨 마지막 문자만 빼줄까? => 깊이 우선 탐색이므로, 숫자문자열의 뒷쪽 문자들이 계속해서 바뀌어야 하므로! 
            맨 마지막 문자를 빼준 후, numbers의 길이만큼 다 돌 때 까지(즉 모든 node를 확인하여 모든 경우의 수를 다 확인할 때 까지) 다음번 node(idx)를 탐색한다. (for에서 다음번 j에 대해 이어서 탐색). */
            //이후, 다시 for문을 돌기 시작! (현재 위치에 올 수 있는 다른 경우의 수 탐색)
            //numbers길이 만큼 배열을 다 돌고나서야(for문을 모두 돌고 나서야) 해당 위치에 올 수 있는 경우의 수를 다 확인한 것이므로 return! 
        }
        
    }
    
    private void checkDecimal(int newNum){
        //소수체크
        if(newNum==0||newNum==1){
            return;
        }
        for(int i=2;i<newNum;i++){
            if(newNum%i==0){
                return;
            }
        }
        
        //중복체크
        if(decimalList.size()==0){
            decimalList.add(newNum);
        }
        if(!decimalList.contains(newNum)){
            decimalList.add(newNum);
        }
        return;
    }
}