class Solution {
    public int solution(long num) { //주어진 조건에서 num은 1이상, 8000000 미만의 수이므로 long형으로 취급해주어야 한다!!
        int answer = 0;
        for(int i=0;i<501;i++)
            if(num==1){
                answer=i;
                break;
            }
            else if(i==500)
                answer=-1;
            else if(num%2==0)
                num=(long)(num/2);
            else 
                num=num*3+1;
                
        return answer;
    }
   
}