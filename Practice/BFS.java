package Practice;

import java.util.Queue;
import java.util.LinkedList;

    /**
     * BFS (너비 우선 탐색)을 연습해보자!
     * BFS란? 이름 그대로 DFS와는 반대되게 가로축을 먼저 방문한다고 생각할 수 있음. 즉, 시작노드가 있다면 그 노드와 거리가 1밖에 떨어지지 않은 모든 노드들을 한 번에 방문. 
     * 이후, 거리가 2인 노드를 모두 한 번에 방문, 거리가 3인 노드 ... 로 순서대로 모든 노드를 방문하게 된다! 
     * 그래서 거리가 동일한 각종 경로들 중, 최적의 경로를 찾는 문제에도 BFS를 사용할 수 있음.
     * DFS와의 가장 큰 차이점은 한 번에 하나의 노드를 방문하여 그 노드를 타고타고 들어가 가장 깊은 노드까지 찍고 나서야 다시 위로 올라오는 DFS와 다르게, 현재 방문 노드와 인접한 노드들은 모오두 한 번에 방문처리 한다는 점이다
     * 기준 (ex. 낮은 번호부터 방문)에 따라 가장 먼저 탐색해야할 노드가 큐의 맨 밑에 오면, 그 큐를 다시 빼내고 그와 인접한 모든 노드들을 다시 방문한다. 
     * 그러면 큐에는 새롭게 거리가 2인 노드들이 쌓이게 되고, 큐의 맨 밑에는 거리가 1인 노드 중 방금 처리한 노드의 다음 노드가 남게 된다. 그러면 다시 거리가 1인 남은 노드와 인접한 모든 노드를 방문처리, 즉 거리가 2인 다른 노드들이 큐에 쌓이게 되는 것이다
     * 이 과정을 반복하면 거리가 1인 노드 전부 -> 거리가 2인 노드 전부 -> 거리가 n인 노드 전부를 순차적으로 방문하게 되는 것! 
     * 만약 맨 밑에 남은 노드와 인접한 노드들 중 방문하지 않은 노드가 없다면, 큐에서 제거하기만 한다. 이런식으로 남은 모든 노드들을 체크하다보면 모든 노드들을 방문한 경우에는 큐에 넣을 노드는 없고 노드들이 빠지기만 하므로 큐가 비었을 때 == 모든 노드를 방문했을 때 가 된다
     */  
        

public class BFS {
    private static Boolean[] visited;
    public static void main(String[] args) {
        int n=8; //8개의 노드가 있다고 가정, 낮은 번호 순서대로 방문
        visited=new Boolean[n+1]; //1~8번 노드만 처리, 0번 idx는 없는 취급

        for(int i=0;i<n+1;i++){
            visited[i]=false; //모두 방문 없었던 것으로 초기화
        }

        int[][] linkedNodes={{},{2,3,8},{1,7},{1,4,5},{3},{3},{7},{2,6,8},{1,7}};

        Queue<Integer> queue=new LinkedList<>(); //처리 중인 노드를 관리하는데 사용하는 큐
        bfs(1,queue,linkedNodes);

        

    }

    private static void bfs(int startNode, Queue<Integer> queue, int[][] linkedNodes){
        queue.offer(startNode); //처음 방문하는 노드를 큐에 삽입
        visited[startNode]=true;
        System.out.print(startNode+" ");
        //첫 큐와 인접한 모든 노드를 큐에 넣고, 맨 밑의 노드부터 하나씩 빼가면서 다음 거리의 노드들을 방문!

        while(!queue.isEmpty()){ //큐가 빌 때 까지 반복! (큐가 비었다는 것은 모든 노드를 방문했음을 의미)
            int checkNode=queue.poll(); //큐의 맨 앞을 꺼내온다 -> 이 노드와 인접하는 노드들을 체크!
            for(int i=0;i<linkedNodes[checkNode].length;i++){
                int nextNode=linkedNodes[checkNode][i];
                if(!visited[nextNode]){
                    System.out.print(nextNode+" ");
                    queue.offer(nextNode); //방문하지 않은 모든 노드를 큐에 삽입 후, 방문 처리
                    visited[nextNode]=true;
                }
            }
            //현재 노드와 인접한 모든 노드를 삽입한 후에는, 큐에 남은 다음 노드를 체크하러 간다 -> 거리가 동일한 노드일 수도 있고 거리가 동일한 노드를 모두 방문했다면 다음 거리의 노드가 된다!
        }

    }
    
}
