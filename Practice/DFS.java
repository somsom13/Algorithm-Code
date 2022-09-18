package Practice;

public class DFS {
    private static Boolean[] visited;
    public static void main(String[] args) throws Exception{
        // BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int n=9; //총 8개의 노드가 있다 가정, 0번 node는 없으므로 비워둠
        visited=new Boolean[n];
        for(int i=0;i<n;i++){
            visited[i]=false; //모두 방문 없었던 것으로 초기화
        }
        // int[][] linkedNodes=new int[9][]; //1~8번 노드와 인접한 노드를 1~8번 idx에 저장하기 위한 2차원 배열

        int[][] linkedNodes={{},{2,3,8},{1,7},{1,4,5},{3},{3},{7},{2,6,8},{1,7}};

        dfs(1,linkedNodes); //1번 노드부터 방문 시작!
        //ex) 1번 node는 2,3,8 node와 인점. 낮은 번호부터 방문하는 상황이라 가정 -> 인접한 노드도 낮은 번호부터 순서대로 저장


    }

    private static void dfs(int nowNode, int[][] linkedNodes){
        //현재 노드와 인접한 노드 중, 방문 한 적이 없는 노드를 낮은 번호부터 찾아서 방문한다.
        visited[nowNode]=true;
        System.out.print(nowNode+" ");
        for(int i=0;i<linkedNodes[nowNode].length;i++){
            //현재 방문한 노드와 인접한 노드들을 체크!
            int nextNode=linkedNodes[nowNode][i];
            if(!visited[nextNode]){
                //방문한 적 없는 인접 노드를 찾았다면? 
                dfs(nextNode, linkedNodes); //새롭게 방문한다
            }
        }
        //방문한 적 없는 인접노드가 없다면 for문을 빠져나오고 return 하게 된다. 
        //그러면 이 노드를 방문하기 직전 노드로 다시 되돌아가게 되고, 다시 되돌아간 해당 노드에서 이어서 다른 방문한 적 없는 인접 노드를 찾게 된다! --> 그래서 위에서 방문X인 노드를 찾고 break하지 않는 것!@
        //return되고 난 다음 노드들 중 방문하지 않은 것도 찾아야 하므로

        //위의 과정을 반복하여 nowNode가 시작 노드 (1번 노드) 인 곳으로 돌아오고, 1번 노드에서도 for문을 모두 돌고 정상적으로 빠져나왔다면 인접 노드들을 모두 방문했다는 의미! 
        //DFS 종료
    }

    
    
}
