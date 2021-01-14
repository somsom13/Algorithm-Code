class Solution {
    public int[] solution(int[] arr) {
        if(arr.length==1){
            int[] answer={-1};
            return answer;}
        else{
            int[] answer=new int[arr.length-1];
            int idx=0;
            int min=999;
            for(int i=0;i<arr.length;i++)
                if(min>arr[i]){
                    min=arr[i];
                    idx=i;
                }
            for(int i=0;i<idx;i++)
                answer[i]=arr[i];
            for(int i=idx+1;i<arr.length;i++)
                answer[i-1]=arr[i];
            return answer;
        }
    }
}