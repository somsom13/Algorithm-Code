import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;



class Main {
    private static int[][] house;
    private static int linkedHouse=0;

    private static BufferedReader br;
    private static BufferedWriter bw;
    public static void main(String[] args) throws Exception{
        br=new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int n=Integer.parseInt(br.readLine());
        StringTokenizer st;
        house=new int[n][n];

        //각 집의 존재 여부 (존재: 1, 존재X: 0)
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            String num=st.nextToken();
            for(int j=0;j<n;j++){
                house[i][j]=num.charAt(j)-'0';
            }
        }
    
        ArrayList<Integer> houseGroups=new ArrayList<>();
        for(int x=0;x<n;x++){
            for (int y=0;y<n;y++){
                dfs(x,y,n);
                if(linkedHouse!=0){
                    houseGroups.add(linkedHouse);
                    linkedHouse=0;
                }
            }
        }

        Collections.sort(houseGroups);
        bw.write(houseGroups.size()+"\n");
        for(int h:houseGroups){
            bw.write(h+"\n");
        }
        
        //존재하는 집들끼리만 연결해야 하므로 house[i][j]=1 인 곳만 방문할 수 있고, 0인 곳은 방문할 수 없다!
        //따라서 이미 방문한 집도 house[i][j]=0 으로 변경함으로써 방문 처리와 같은 효과를 낼 수 있다.
        //house[i][j]는 상하좌우에 있는 모든 집들과 거리가 1인 간선으로 연결되어있다고 생각할 수 있음 -> 현재 방문한 집의 상,하,좌,우 모두에 dfs를 호출!


        bw.flush();
        bw.close();
    }

    private static void dfs(int x, int y, int n){

        if(x<0 || y<0 || x>=n || y>=n){
            //정해진 좌표를 벗어난다면 영역을 벗어난 것 -> 더 이상 탐색 불가이므로 탐색 종료
            return;
        }

        if(house[x][y]==1){
            //현재 집이 방문 가능하다면?
            house[x][y]=0; //방문처리
            linkedHouse++; //현재 단지의 집 개수를 1 증가

            //현재 방문한 집의 상하좌우를 모두 방문 가능한지 판별해주어야 한다 -> 그리고 다음에 방문한 집에서 또 다음 방문 가능한 집을 찾는다!
            dfs(x-1, y,n);
            dfs(x,y-1,n);
            dfs(x+1,y,n);
            dfs(x,y+1,n);
        }
        return;
    }
}

    
