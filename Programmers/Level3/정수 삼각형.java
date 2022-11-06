class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        //거쳐간 숫자의 합이 큰 경우 -> 위에서 부터 누적합!
        //대각선 방향 왼 1칸 또는 오른쪽 1칸 -> [X+1][y] or [x+1][y+1]
        
        //각 칸에 위에서 부터의 최대 연산 결과를 저장해두자
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == triangle[i].length - 1) { //맨 마지막 일 때 -> [x][y-1] -> [x][y] 로만 내려옴
                    triangle[i][j] += triangle[i-1][j-1];
                } else if (j == 0) { //맨 처음 일 때 -> [x-1][y] -> [x][y] 로만 내려옴
                    triangle[i][j] += triangle[i-1][j];
                } else{
                    triangle[i][j] += Math.max(triangle[i-1][j-1],triangle[i-1][j]);
                }
                if (i == triangle.length - 1) {
                    if (triangle[i][j] > answer) {
                        answer = triangle[i][j];
                    }
                }
            }

        }
        
        return answer;
    }
}