import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


class Main {
    private static Boolean[] visited;
    private static int[] visitOrder;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int count=1;
    public static void main(String[] args) throws Exception{
        br=new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        //무방향 그래프, 간선간의 가중치 동일, 오름차순 (낮은 번호부터 방문), 깊이 우선 탐색 (DFS)
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int r=Integer.parseInt(st.nextToken());

        visited=new Boolean[n+1]; //1~n 번까지 정점 방문여부 저장
        visitOrder=new int[n+1]; //1~n 번 노드의 방문 순서 저장 (ex. [1]에는 1번 노드를 몇 번째로 방문했는가)

        for(int i=0;i<n+1;i++){
            visited[i]=false;
        }
        ArrayList<Integer>[] linkedNodes=new ArrayList[n+1]; //간선 관리 -> 1번 행에는 1번 node와 연결된 접점들이 오름차순으로 들어감

        for(int i=0;i<n+1;i++){
            linkedNodes[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());

            linkedNodes[u].add(v);
            linkedNodes[v].add(u);
        }

        for(int i=1;i<=n;i++){
            Collections.sort(linkedNodes[i]);
        }

        dfs(r,linkedNodes);
        
        for(int i=1;i<=n;i++){

            bw.write(visitOrder[i]+"\n");

        }
        bw.flush();
        bw.close();

    }

    private static void dfs(int nowNode, ArrayList<Integer>[] linkedNodes) throws IOException{
        visitOrder[nowNode]=count++;
        visited[nowNode]=true;
        
        for(int i=0;i<linkedNodes[nowNode].size();i++){
            int nextNode=linkedNodes[nowNode].get(i);
            if(!visited[nextNode]){
                dfs(nextNode,linkedNodes);
            }
        }
    }  

}
