class Solution {
    public int solution(int n) {
        //2진수 변환: Integer.toBinaryString
        int count=check1Count(n);
        int foundCount=-1;
        //n보다 크고 2진수 변환 시 1의 개수가 같으려면? -> n보다 큰 수를 모두 2진수로 변환, 1의 개수가 동일한 첫 번째 수!
        while(foundCount!=count){
            foundCount=check1Count(++n);
        }
        return n;
    }
    
    private int check1Count(int n){
        String binary=Integer.toBinaryString(n);
        char[] chars=binary.toCharArray();
        int count=0;
        for(char c:chars){
            if(c=='1'){
                count++;
            }
        }
        return count;
    }
}