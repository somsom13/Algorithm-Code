class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        //대각선을 방정식으로 두고, ax+by=c x,y에 좌표 대입 -> 그 결과 <=c라면 ok. >c라면 no
        double a=(double)h/w;
        double b=1;
        double c=h;
        System.out.println("식: "+a+"x"+"+y="+c);

        //맨 오른쪽 꼭지점 : 사각형 1개
        for(int i=1;i<w;i++){ //x좌표를 1부터 돌면서 체크
            int maxY=(int)(c-a*i);//x좌표 하나를 대각선 식에 대입 -> 그 때의 대각선 상의 y좌표 구해짐 -> 그 보다 작거나 같은 정수라면 그게 최대 갯수!
            //int로 강제치환 -> 소수점은 버려지므로, 최대 정수 y좌표 갯수 구해짐
            //예) x=1을 대입했을 때의 대각선 상의 y=10.1 이라면, (1,1),(1,2)~(1,10) 까지가 멀쩡한 사각형 => maxY만큼이 x=1일때 멀쩡한 사각형의 갯수! 
            answer+=maxY;
        }
        //대각선 기준 생기는 사각형 값 같으므로 2배
        return answer*2;
    }
}