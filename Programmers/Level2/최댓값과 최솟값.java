import java.util.Arrays;
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] Sarr=s.split(" "); //" "기준으로 잘라서 string 배열에 저장
        int[] arr=new int[Sarr.length];
        for(int i=0;i<arr.length;i++)
            arr[i]=Integer.parseInt(Sarr[i]); //string 형태의 배열 Sarr의 값들을 int로 변환하여 저장
        Arrays.sort(arr); //오름차순 정렬 
        answer=answer+Integer.toString(arr[0])+" "+Integer.toString(arr[arr.length-1]); //answer에 최솟값, 최댓값 저장
        return answer;
    }
}