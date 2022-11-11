import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedWriter;

class Main {
    private static int[][] data;
    private static Queue<String> queue = new LinkedList<>();
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        data = new int[n][n];
        String input;
        
        for (int i = 0; i < n; i++) {
            input = br.readLine();
            for (int j = 0; j < n; j++) {
                data[i][j] = input.charAt(j) - '0';
            }
        }

        //분할, 정복 -> 일단 나누고 나중에 합친다! 
        //4 영역으로 나눈다. -> 나눈 곳에서 또 나눈다
        //왼위, 오위, 왼아래, 오아래 순서
        int[] start = {0, 0};
        int size = n;
        partition(start, size);
        //그 결과를 맨 마지막에 합친다

        while (!queue.isEmpty()) {
            bw.write(queue.poll());
        }
        bw.flush();
	}

    private static void partition(int[] start, int size) {
        if (checkGroup(start, size)) {
            queue.offer(String.valueOf(data[start[0]][start[1]]));
            return;
        }

        // int[] leftTop = start;
        // int[] rightTop = {start[0], start[1] + size};
        // int[] leftBottom = {start[0] + size, start[1]};
        // int[] rightBottom = {start[0] + size, start[1] + size};

        size /= 2;
        int[] startX = {start[0], start[0], start[0] + size, start[0] + size};
        int[] startY = {start[1], start[1] + size, start[1], start[1] + size};


        queue.offer("(");
        for (int i = 0; i < 4; i++) {
            partition(new int[] {startX[i], startY[i]}, size);
        }
        queue.offer(")");
    }

    private static boolean checkGroup(int[] start, int size) {
        int x = start[0];
        int y = start[1];
        int number = data[x][y];
        int endX = x + size;
        int endY = y + size;
        for (int i = x; i < endX; i++) {
            for (int j = y; j < endY; j++) {
                if (data[i][j] != number) {
                    return false;
                }
            }
        }
        return true;
    }
}

    

