import java.util.Arrays;
class Solution {
    public int[] solution(int n, long left, long right) {
        long len=right-left+1;
        int[] answer=new int[(int)len];
        int idx=0;

        //1행 1열부터 i행 i열 까지의 영역 내 모든 칸을 채운다?
        //빈 칸을 채우는 것이므로 앞의 기록을 덮어쓰지는 않는다!
        //영역 내 이므로 그 정사각형 모양 내부에 오는걸 의미한다!
        
        //1행: 1, 2, 3, ... n -> 여기까지 n개 
        //2행: 2, 2, 3, ... n -> (n+1)번 부터 2n 번 까지
        //3행: 3, 3, 3, 4 ,... n 
        //i행: i * i번, (i+1), ... n -> 
        //-> 이어붙였을 때 

        for(long i=left;i<right+1;i++){
            // i/n -> 몫+1: 행번호 (ex. n==10 이라면, 0~9 까지는 몫이 0. 나머지: 0~9 -> 0행의 0~9번 열을 나머지+1의 값으로 채움)
            // i==10이면 (즉 2행의 시작) 10/10 -> 몫: 1, 나머지: 0~9 까지 -> 2행의 0~9번 배열을 채움
            //단, (몫+1)의 값을 처음~몫+1 열만큼 채워주고 그 뒤부터 나머지+1의 값을 채워주는 것!
            
            if(i==0){
                answer[0]=1;
                idx++;
                continue;
            }
            
            int lineNum=(int)(i/(long)n);
            int colNum=(int)(i%(long)n);
            if(colNum<=lineNum){
                answer[idx++]=lineNum+1;
                continue;
            }
            answer[idx++]=colNum+1;
        }
        //copyOfRange는 지정하는 복사 범위 idx가 int형일 때만 쓸 수 있다!
        return answer;
    }
}