import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main {
    private static int[][] connection;
    private static int[] ableToConnect;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());

        connection = new int[n][2];
        ableToConnect = new int[n];

        //n은 100이하, 가장 뒤의 번호도 500이하

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            connection[i][0] = Integer.parseInt(st.nextToken());
            connection[i][1] = Integer.parseInt(st.nextToken());
        }

        sortByAAsc();

        int max = 0;

        for (int i = 0; i < n; i++) { // A의 맨 위 전깃줄 부터 돌면서 연결 가능한 전깃줄의 최대 수를 찾는다
            //모든 경우의 수에 대해서 다 찾아야함
            //맨 위에서 ~ N 번째 까지 다 첫 전선이 될 수 있음
            //그 다음부터는 그이후의 전선부터 전부 탐색!  그래서 N번째 연결 선이 첫 번째 일 때, 최대 연결 횟수를 찾는다
            max = Math.max(max, findMaxConnection(i));
        }

        System.out.println(n - max);
    }

    private static int findMaxConnection(int nowConnectionNumber) {
        if (ableToConnect[nowConnectionNumber] > 0) { 
            // 이미 이번 전선의 최대 연결 가능 횟수를 찾았다면, 그보다 더 큰 연결 가능 수는 나오지 않으므로 바로 이 값을 return => DP! 
            return ableToConnect[nowConnectionNumber];
        }

        ableToConnect[nowConnectionNumber]++;

        int nowConnectionB = connection[nowConnectionNumber][1];
        int nextConnectionB;
        int maxConnectionCountAfterNow;

        for (int nextConnectionNumber = nowConnectionNumber + 1; nextConnectionNumber < ableToConnect.length; nextConnectionNumber++) {
            //이번 전선의 다음 것들 부터 최대 연결 가능 횟수를 찾는다
            nextConnectionB = connection[nextConnectionNumber][1];
            if (nowConnectionB < nextConnectionB) {
                // 연결 가능 하므로, 이 뒤부터의 최대 연결 가능 횟수를 찾아간다!
                // 만약 이미 찾았던 최대 연결 가능 횟수가 더 크다면? 업데이트 안함
                maxConnectionCountAfterNow = findMaxConnection(nextConnectionNumber) + 1;

                if (ableToConnect[nowConnectionNumber] < maxConnectionCountAfterNow) {
                    ableToConnect[nowConnectionNumber] = maxConnectionCountAfterNow;
                }
                
            }
        }

        return ableToConnect[nowConnectionNumber];
    }

    private static void sortByAAsc() {
        Arrays.sort(connection, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 첫 번째 수 기준 오름차순 정렬
            }
        });
    }
}
