class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int sum=0;
        String num=Integer.toString(x);
        int[] arr=new int[num.length()];
        for(int i=0;i<arr.length;i++)
            arr[i]=num.charAt(i)-'0';
        for(int i:arr)
            sum+=i;
        if(x%sum==0)
            answer=true;
        else
            answer=false;
        return answer;
    }
}