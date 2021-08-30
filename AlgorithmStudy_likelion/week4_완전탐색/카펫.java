class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int max=(brown-2)/2;//최대 가로 길이: 세로길이가 3일 때. 즉, 양 옆의 블록 2개를 뺀 값 / 2
        //가로*2+세로*2-4=brown ---1번식
        //(가로-2)*(세로-2)=yellow  ---2번식
        //가로, 세로 모두 최소 3 이상, 최대 (brown-2)/2  -- max
        
        for(int w=3;w<=max;w++){//w 기준으로 찾기 (최소3, 최대 max)
            int h=(brown+4-2*w)/2;//1번식으로 세로 길이 찾고
            if((h-2)*(w-2)==yellow){//2번식에 대입 (이차방정식)
                if(h<=w){
                    //w>=h 라면, 가로=w, 세로=h (가로는 세로보다 크거나 같으므로)
                    answer[0]=w;
                    answer[1]=h;
                }else{
                    //h>w 라면, 가로=h, 세로=w
                    answer[0]=h;
                    answer[1]=w;
                }
                break;
            }
        }
        
        return answer;
    }
}