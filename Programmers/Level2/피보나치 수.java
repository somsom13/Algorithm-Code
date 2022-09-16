class Solution {
    private static int answer=0;
    public int solution(int n) {
        int[] fiboRemainder=new int[n+1];
        //(a+b)%c = (a%c)+(b%c)
        //즉, 피보나치 결과를 fibo(a)+fibo(b)가 아닌 fibo(a%1234567)+fibo(b%1234567) 로 가져가야 int 범위를 초과하지 않게 배열에 저장할 수 있다!
        for(int i=0;i<n+1;i++){
            if(i<=1){
                fiboRemainder[i]=i;
            }else{
                fiboRemainder[i]=fiboRemainder[i-1]%1234567+fiboRemainder[i-2]%1234567;
            }
        }
        return fiboRemainder[n]%1234567;
    }

}