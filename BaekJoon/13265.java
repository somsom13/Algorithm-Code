import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.BufferedWriter;

class Main {
    private static int[] tempColors;
    private static Set<Integer>[] connection; //연결된 동그라미 번호 저장
    private static int[] colors; //각 동그라미들의 색 (1 또는 2) 저장
    private static boolean able = true;

	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            able = true;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            connection = new HashSet[n + 1];
            colors = new int[n + 1];
            tempColors = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                connection[i] = new HashSet<Integer>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                connection[x].add(y); //중복 자동 제거
                connection[y].add(x);
                //x와 y가 연결 -> 둘이 색이 달라야한다!

                //모든 연결 관계를 표시 -> 타고 타고 가면서 색 바꿈 (dfs) => 다른 색이 안되는 순간, impossible!

                //1이랑 연결된거 - >쭈르륵 표시 
                //2랑 연결된거 -> 쭈르륵 표시
                /// 끝까지 
            }

            for (int i = 1; i <= n; i++) {
                if (colors[i] == 0) {
                    //색칠할 수 있는 경우 2가지 -> 다 색칠한다!
                    for (int j = 1; j <= n; j++) {
                        tempColors[j] = colors[j];
                    }
                    colorize(i, 1);
                    if (able) {
                        continue;
                    }
                    //실패라면, 다른 케이스를 확인하기 위해 colorize 하기 전으로 배열 초기화 시킨다!
                    for (int j = 1; j <= n; j++) {
                        colors[j] = tempColors[j];
                    }
                    colorize(i, -1);
                    if (able) {
                        continue;
                    }
                    //둘 다 실패했다면, impossible
                    break;
                }
                //이미 색칠되어 있다면, 한 번 탐색 다 되었을 거임 -> 패스!
            }
            if (!able) {
                bw.write("impossible\n");
            } else {
                bw.write("possible\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void colorize(int nowCheckCircle, int nowColor) {
        if (!able) {
            return;
        }

        Set<Integer> connected = connection[nowCheckCircle];
        colors[nowCheckCircle] = nowColor;

        Iterator itr = connected.iterator();
        while (itr.hasNext()) {
            int nextCircle = (int) itr.next();
            if (colors[nextCircle] == 0) {
                //한 번도 칠해진 적 없었다면 다른 색으로 색칠하고 탐색시킨다.
                colorize(nextCircle, -nowColor);
                if (!able) {
                    return;
                }
            }
            if (colors[nextCircle] == nowColor) {
                able = false;
                return; //이미 연결관계에서 실패를 발견함!
            }
            //이미 다른 색상으로 칠해졌다면, 탐색할 필요 없이 continue
            if (!able) {
                return;
            }
            
        }
    }

}
