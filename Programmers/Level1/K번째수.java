import java.util.Arrays;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0;i<commands.length;i++){
            int[] arr=new int[commands[i][1]-commands[i][0]+1];
            arr=Arrays.copyOfRange(array,commands[i][0]-1,commands[i][1]);
            //copyOfRange(복사할 배열,시작index,끝index)
            Arrays.sort(arr);//오름차순 정렬
            answer[i]=arr[commands[i][2]-1];
            }
        return answer;
    }
}