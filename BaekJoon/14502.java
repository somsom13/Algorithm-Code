import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedWriter;

class Main {
    private static int[][] board;
    private static int[][] tmp;
    private static int N, M;
    private static Queue<int[]> queue = new LinkedList<>();
    private static List<int[]> virus = new ArrayList<>();
    private static int max = 0;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        tmp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while(st.hasMoreTokens()) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    virus.add(new int[] {i, j});
                }
                j++;
            }
        }

        setWall();
        bw.write(String.valueOf(max));
        bw.flush();

        //벽은 반드시 3개를 세워야한다. 
        //(0, 0) 부터 (N - 1, M - 1) 까지 돌면서 벽을 무조건 3개 세운다
        //벽을 3개 세운 후, 각 바이러스의 위치에서 bfs -> 갈 수 있는 최대 영역을 계산하고 합해서 기억한다. 
    }

    //벽을 세우는 모든 경우의 수를 세는 방법 생각해보기
    
    private static void setWall() {
        for (int firstX = 0; firstX < N; firstX++) {
            for (int firstY = 0; firstY < M; firstY++) {
                if (board[firstX][firstY] != 0) {
                    continue;
                }
                board[firstX][firstY] = 1;
                for (int secondX = 0; secondX < N; secondX++) {
                    for (int secondY = 0; secondY < M; secondY++) {
                        if (board[secondX][secondY] != 0) {
                            continue;
                        }
                        board[secondX][secondY] = 1;
                        for (int thirdX = 0; thirdX < N; thirdX++) {
                            for (int thirdY = 0; thirdY < M; thirdY++) {

                                if (board[thirdX][thirdY] != 0) {
                                    continue;
                                }

                                board[thirdX][thirdY] = 1;

                                //벽 3개를 모두 세운 후 바이러스가 퍼지기 이전의 값을 기억
                                for (int i = 0; i < N; i++) {
                                    for (int j = 0; j < M; j++) {
                                        tmp[i][j] = board[i][j];
                                    }
                                }

                                //bfs 로 바이러스가 퍼질 수 있는 모든 위치 탐색
                                for (int[] virusPos : virus) {
                                    bfs(virusPos);
                                }

                                //최대 안전영역 계산
                                max = Math.max(max, calculateSafeArea());
                                
                                //바이러스가 퍼지기 직전으로 세팅
                                for (int i = 0; i < N; i++) {
                                    for (int j = 0; j < M; j++) {
                                        board[i][j] = tmp[i][j];
                                    }
                                }

                                //다시 board 벽 세우기 전으로 초기화

                                board[thirdX][thirdY] = 0;
                            }
                        }
                        board[secondX][secondY] = 0;
                    }
                }
                board[firstX][firstY] = 0;
            }
        }
    }

    private static void bfs(int[] position) {
        queue.offer(position);
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];

            int nextX;
            int nextY;
            for (int i = 0; i < 4; i++) {
                nextX = x + dx[i];
                nextY = y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N | nextY >= M) {
                    continue;
                }

                if (board[nextX][nextY] == 0) {
                    board[nextX][nextY] = 2;
                    queue.offer(new int[] {nextX, nextY});
                }
            }
        }
    }

    private static int calculateSafeArea() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
