class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int n=phone_number.length();
        for(int i=0;i<n-4;i++)
            answer+="*";
        for(int j=n-4;j<n;j++)
            answer+=phone_number.charAt(j);
        return answer;
    }
}