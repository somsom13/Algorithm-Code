import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedWriter;

class Cheese {
    public boolean isCheese;
    public boolean visited;
    public int count; 
    public int x;
    public int y;

    public Cheese(boolean isCheese, int x, int y) {
        this.isCheese = isCheese;
        this.count = 0;
        this.visited = false;
        this.x = x;
        this.y = y;
    }
}

class Main {
    private static Cheese[][] board;
    private static int N, M;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Queue<Cheese> queue = new LinkedList<>();

	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //두 면 이상이 외부에 노출 (즉 다른 치즈와 맞닿아 있지 않다면) 1시간 만에 녹아버린다.
        //단, 치즈 내부에 있는 곳 (그러니까 사방이 치즈에 둘러쌓인 곳) 은 외부 노출이 아님!
        //맨 바깥쪽 부터 탐색 시작 -> 치즈가 있는 곳은 방문하지 못함 => 자동으로 치즈 내부에 있는 곳은 건너뛰게 됨
        //그러면서 바깥 쪽에서 접근할 수 있는 면 중, 2면을 가지는 치즈 조각을 찾으면 된다!
        //현재 위치에서 상,하,좌,우 로 갈 수 있음. 치즈가 있는 곳은 갈 수 없음, 치즈를 찾으면 찾은 면의 정보를 업데이트

        board = new Cheese[N][M];

        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            int num;
            while(st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
                board[i][j] = new Cheese(num == 1, i, j);
                count += num; //초기 치즈 개수 카운트
                j++;
            }
        }

        int time = 0;
        while (count > 0) {
            time++;
            bfs(board[0][0]);
            count = deleteCheeseAndCountLeftOver();
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    //bfs 한 차례 다 돌았으면, 현재 상태에서 방문할 수 있는 모든 치즈를 방문했다는 것 ->  count 가 2인 치즈를 제거하고 
    //visited 를 다 false 로 초기화 한 후 탐색 시작!
    private static void bfs(Cheese start) {
        queue.offer(start);
        while (!queue.isEmpty()) {
            Cheese nowBoard = queue.poll();
            int x = nowBoard.x;
            int y = nowBoard.y;
            if (board[x][y].visited) {
                continue;
            }
            board[x][y].visited = true;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                //다음 칸 (즉 맞닿은 칸)이 치즈라면? -> 외부에 한 면이 노출된 것이므로 count 추가
                if (board[nextX][nextY].isCheese) {
                    board[nextX][nextY].count++;
                    continue;
                }

                //방문 안 한 치즈가 없는 칸이라면, 방문 목록에 추가
                if (!board[nextX][nextY].visited) {
                    queue.offer(board[nextX][nextY]);
                    continue;
                }
            }
        }
    }

    private static int deleteCheeseAndCountLeftOver() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j].visited = false; //방문 안 한 걸로 초기화
                if (board[i][j].isCheese) {
                    if (board[i][j].count >= 2) { //맞닿은 면이 둘 이상인 치즈라면, 이제 치즈가 사라짐
                        board[i][j].isCheese = false;
                    } else {
                        board[i][j].count = 0; //사라지지 않은 치즈라면 맞닿은 면 개수 초기화
                        count++; //남은 치즈 개수 증가
                    }
                }
            }
        }
        return count;
    }
}