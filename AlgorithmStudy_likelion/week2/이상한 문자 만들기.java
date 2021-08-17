class Solution {
    public String solution(String s) {
        String answer = "";
        
        //공백 기준으로 문자열 나누기, split 사용 -> word배열에 0번 index부터 잘린 단어 순서대로 담김

        //왜 split(" ",-1) -1이 왜 붙는지??????????????????????????????/
        String[] word=s.split(" ",-1);

        //잘린 각 단어에 대하여
        for(int i=0;i<word.length;i++){
            //각 단어의 문자에 대해
            for(int j=0;j<word[i].length();j++){
                char ch=word[i].charAt(j);
                if(j%2==0)
                    if('a'<=ch&&ch<='z'){
                        answer+=(char)(ch-32);
                    }
                    else
                        answer+=ch;
                else
                    if('A'<=ch&&ch<='Z')
                        answer+=(char)(ch+32);
                    else
                        answer+=ch;
            }
            //맨 마지막 단어가 아니라면, 뒤에 공백 붙이기
            if(i!=word.length-1)
                answer+=" ";
            }

        return answer;
    }
}