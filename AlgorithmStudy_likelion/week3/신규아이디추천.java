class Solution {
    public String solution(String new_id) {
        String answer = "";

        //각 문자에 대해 비교하는 경우: 1~3단계
        for(int i=0;i<new_id.length();i++){
            char c=new_id.charAt(i);
            if(c>='A'&&c<='Z'){//1단계
                new_id=(new_id.substring(0,i)+(char)(c+0x20)+new_id.substring(i+1,new_id.length()));
                //substring 사용
            }else if(!((c>='a'&&c<='z')||(c>=0x30&&c<=0x39)||(c==0x2d)||c==0x2e||c==0x5f)){//2단계
                new_id=(new_id.substring(0,i)+new_id.substring(i+1,new_id.length()));
                i--;//한 값을 지운 상태가 되기 때문에 i를 1 감소시킨다
            }
            
            if(i>=1&&c==0x2e&&new_id.charAt(i-1)==0x2e){//3단계
                new_id=(new_id.substring(0,i)+new_id.substring(i+1,new_id.length()));
                i--;//한 값을 지웠으므로 i를 1 감소시킨다 (다음번 for문 검사를 위해)
            }
            
        }
        
        //4단계 - 문자열 맨 끝일 때
        if(new_id.charAt(new_id.length()-1)==0x2e){
            new_id=new_id.substring(0,new_id.length()-1);
        }
        
        //4단계 - 문자열 맨 앞일 때
        if(new_id.length()>=1&&new_id.charAt(0)==0x2e){
            new_id=new_id.substring(1,new_id.length());
        }
    
        //5단계
        if(new_id.equals("")){
            new_id="a";
        }else if(new_id.length()>=16){//6단계
            new_id=new_id.substring(0,15);
            if(new_id.charAt(new_id.length()-1)==0x2e){
            new_id=new_id.substring(0,14);
        }
        }
        
        //7단계
        if(new_id.length()<=2){
            while(new_id.length()<3){
            new_id+=new_id.charAt(new_id.length()-1);
            }
        }
        
        return new_id;
    }
}