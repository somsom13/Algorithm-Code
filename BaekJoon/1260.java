import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;


class Main {
    private static Boolean[] visited;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static Queue<Integer> queue = new LinkedList<>(); //방문한 노드들을 모두 저장하고 순서대로 인접한 노드를 처리하기 위한 큐
    public static void main(String[] args) throws Exception{
        br=new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        
        //무방향 그래프, 간선간의 가중치 동일, 오름차순 (낮은 번호부터 방문)
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int r=Integer.parseInt(st.nextToken());

        visited=new Boolean[n+1]; //1~n 번까지 정점 방문여부 저장

        for(int i=0;i<n+1;i++){
            visited[i]=false;
        }
        
        //ArrayList<Integer> 타입의 배열!
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

        //오름차순으로 방문하므로 각 노드와 인접한 접점들의 번호를 오름차순으로 정렬
        for(int i=1;i<=n;i++){
            Collections.sort(linkedNodes[i]);
        }

        dfs(r,linkedNodes);
        for(int i=0;i<=n;i++){
            visited[i]=false;
        }
        bw.write("\n");
        bfs(r,linkedNodes);
        
        bw.flush();
        bw.close();

    }

    private static void bfs(int startNode, ArrayList<Integer>[] linkedNodes) throws IOException{
        queue.offer(startNode);
        bw.write(startNode+" "); //방문한 순서대로 정점 번호 출력
        visited[startNode]=true;
        while(!queue.isEmpty()){ //큐가 비었다는것은 모든 노드들을 방문했다는 의미 (더 이상 방문할 다음 거리의 노드가 없음 -> BFS 종료)
            int checkNode=queue.poll(); //큐의 가장 하단에 있는 노드를 꺼내고, 이와 인접한 (즉 거리가 현재 거리 +1 인) 노드들을 모두 찾아 queue에 넣고 방문처리!
            for(int i=0;i<linkedNodes[checkNode].size();i++){
                int nextLevelNode=linkedNodes[checkNode].get(i);
                if(!visited[nextLevelNode]){
                    queue.offer(nextLevelNode);
                    visited[nextLevelNode]=true;
                    bw.write(nextLevelNode+" ");
                }
                
            } 
        }
    }

    private static void dfs(int nowNode, ArrayList<Integer>[] linkedNodes) throws IOException{
        //현재 노드와 인접한 노드 중, 방문 한 적이 없는 노드를 낮은 번호부터 찾아서 방문한다.
        visited[nowNode]=true;
        bw.write(nowNode+" ");
        for(int i=0;i<linkedNodes[nowNode].size();i++){
            //현재 방문한 노드와 인접한 노드들을 체크!
            int nextNode=linkedNodes[nowNode].get(i);
            if(!visited[nextNode]){
                //방문한 적 없는 인접 노드를 찾았다면? 
                dfs(nextNode, linkedNodes); //새롭게 방문한다
            }
        }
    }

}
