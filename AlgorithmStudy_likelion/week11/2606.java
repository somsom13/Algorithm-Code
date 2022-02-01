import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;


//각 케이스마다 해당 케이스 내 문서들의 우선순위가 담겨있어야함
public class Main {
    static int[][] connection;
    static Queue<Integer> q;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int coms=Integer.parseInt(br.readLine());
        int directConns=Integer.parseInt(br.readLine());
        connection=new int[coms+1][coms+1]; // 모든 컴퓨터들에 대한 연결관계를 표시
        q=new LinkedList<>();
        visited=new boolean[coms+1]; // 방문한 노드 (컴퓨터) 체크용
        

        StringTokenizer st;
        for(int i=0;i<directConns;i++){
            st=new StringTokenizer(br.readLine()," ");
            int[] inputs=new int[2];
            for(int j=0;j<2;j++){
                inputs[j]=Integer.parseInt(st.nextToken());
            }
            connection[inputs[0]][inputs[1]]=1; 
            connection[inputs[1]][inputs[0]]=1;
            //ex) 1번 com과 3번 com연결 -> connection[1][3]=1, connections[3][1]=1

        }

        q.add(1);
        visited[1]=true;
        int count=checkConnections(coms);
        System.out.println(count);

    }

    private static int checkConnections(int coms){
        int count=0;
        while(!q.isEmpty()){
            int node=q.poll();
            //1번 컴퓨터 부터 시작해서 2번, 3번 ... n번 컴퓨터까지 모두 확인
            for(int i=2;i<=coms;i++){
                if(connection[node][i]==1&&visited[i]==false){
                    q.add(i);
                    count++;
                    visited[i]=true;
                }
            }
        }
        return count;
    }

     
}