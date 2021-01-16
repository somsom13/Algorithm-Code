import java.util.Arrays;
class Solution {
    public long solution(long n) {
        long answer = 0;
        String s=String.valueOf(n); //long형 변수 n을 string으로 저장
        String ans="";
        int[] arr=new int[s.length()];
        for(int i=0;i<arr.length;i++)
            arr[i]=s.charAt(i)-'0'; //int형 배열 arr에 주어진 변수를 한 글자씩 나누어 저장
        Arrays.sort(arr);//배열 오름차순 정렬
        for(int i=arr.length-1;i>=0;i--) //배열의 맨 마지막부터, 즉 내림차순으로 string에 저장
            ans+=String.valueOf(arr[i]);
        answer=Long.parseLong(ans);//내림차순 정렬된 string을 long형으로 변환
        
        return answer;
    }
}