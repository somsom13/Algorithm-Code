import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedWriter;

class Main {
    private static int[][] board;
    private static boolean[][] cleaned;
    private static int N, M;
    private static int d;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        cleaned = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while(st.hasMoreTokens()) {
                board[i][j] = Integer.parseInt(st.nextToken());
                cleaned[i][j] = false;
                j++;
            }
        }

        clean(r, c);
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cleaned[i][j] == true) {
                    count++;
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    //1. 현재 위치를 청소한다
    private static void clean(int r, int c) {
        //청소가 가능하다면? 
        if (board[r][c] == 0 && cleaned[r][c] == false) {
            //방향을 시계 반대방향으로 전환
            cleaned[r][c] = true; //청소 완료로 상태 변환
            cleanNextPos(r, c); //현재 방향에서 시계 반대방향부터 체크하면서 청소할 수 있는지 확인
        }
    }

    //시계 반대방향부터 청소 가능 여부 체크
    private static void cleanNextPos(int r, int c) { //지금 있는 위치
        int count = 0;
        // System.out.print("prev direction : "+d);
        while (count < 4) {
            count++;

            d--; //왼쪽으로 이동 (d : 북, 동, 남, 서) 역순으로 시계 반대방향 (왼쪽방향)
            if (d == -1) {
                d = 3;
            }
            // System.out.print(", now dir: " + d);
            
            int nextR = r + dx[d];
            int nextC = c + dy[d];

            if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) {
                continue;
            }

            if (board[nextR][nextC] == 0 && cleaned[nextR][nextC] == false) {
                clean(nextR, nextC);
                return;
            }

        }

        //한 바퀴 다 돌았는데 청소 할 수 있는 장소가 없다면

        int nextR = r - dx[d];
        int nextC = c - dy[d];

        if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) {
            return;
        }

        if (board[nextR][nextC] == 0) {
            cleanNextPos(nextR, nextC);
        }
        return;
    }

}