class Move {
    int distance;
    int count;
    
    public Move(int distance, int count) {
        this.distance = distance;
        this.count = count;
    }
}

class Solution {
    private static final int NUM = 1000000007;
    private static Move[][] board;
    public int solution(int m, int n, int[][] puddles) {
        board = new Move[m + 1][n + 1]; // (m, n 까지 포함)
        int answer = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j =1; j <= n; j++) {
                board[i][j] = new Move(0, 0);
            }
        }
        
        board[1][1].count = 1; //집이 있는 칸은 일단 갈 수 있는 방법이 1가지라고 둔다. (다음 칸으로 이동하기 때문에)
        
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            board[x][y] = null; //물웅덩이가 있어서 갈 수 없는 곳은 null로
        }
        
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                if (board[x][y] == null) { //이번 칸이 물웅덩이라면? 이동 불가 
                    continue;
                }
                int untilNow = board[x][y].distance; //지금까지의 거리
                int prevCount = board[x][y].count; //지금까지의 최소 경우의 수
                if (prevCount == 0) { //prevCount가 0이면, 이 칸에는 도달할 수 있는 방법이 아예 없었다는 의미! 그러므로 다음 칸도 방문불가 -> 체크하지 않는다
                    continue;
                }
                //오른쪽으로 한 칸 가는 경우
                setMinDistance(untilNow + 1, prevCount, new int[] {x, y + 1}, m, n);
                //아래로 한 칸 가는 경우
                setMinDistance(untilNow + 1, prevCount, new int[] {x + 1, y}, m, n);
            }
        }
        
        answer = board[m][n].count;
        return answer;
    }
    
    private static void setMinDistance(int newDistance, int prevCount, int[] nowPos, int m, int n) {
        int x = nowPos[0];
        int y = nowPos[1];
        
        //다음 칸이 격자 밖이면 체크 X
        if (x > m || y > n) {
            return;
        }
        
        //다음칸에 물웅덩이가 있으면 체크 X
        if (board[x][y] == null) {
            return;
        }   
        
        //이번 칸에 처음 도달하는 거라면? 새롭게 세팅
        if (board[x][y].distance == 0) {
            board[x][y].distance = newDistance;
            board[x][y].count = prevCount;
            return;
        }
        
        //기록이 있었는데, 지금 온 방법이 더 최단거리라면? 갱신
        if (board[x][y].distance > newDistance) {
            board[x][y].distance = newDistance;
            board[x][y].count = prevCount;
            return;
        }
        
        //같은 거리라면? 이 직전칸까지 온 경우의 수를 더해주어야한다
        if (board[x][y].distance == newDistance) {
            board[x][y].count = (board[x][y].count + prevCount) % NUM;
            return;
        }
    }
}