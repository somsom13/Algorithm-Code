import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        //arr에 담긴 모든 수들의 최소공배수: arr의 배수들 중 공통이 되는 가장 작은 수 
        //2,6,8,14 -> 가장 큰 수를 1부터 n배 시켜나감 -> 작은 수들로 나눴을 때 나머지==0이면 break
        int answer = 0;
        int multiply=1;
        Boolean check=false;
        
        Arrays.sort(arr);
        while(!check){
            check=true;
            answer=arr[arr.length-1]*multiply++;
            for(int i=0;i<arr.length-1;i++){
                if(answer%arr[i]!=0){
                    check=false;
                    continue;
                }
            }
        }
        return answer;
    }
}