import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static Queue<int[]> queue=new LinkedList<>();
    private static int count=-1;
    public int solution(int[][] maps) {
        //막힌 칸과 뚫린 칸을 생각해서 이동하는 문제 + 최단경로 => BFS (같은 거리의 칸을 모두 서치 -> 다음 거리의 칸으로! 
        //그렇게 하다가 최종적으로 가장 먼저 종점에 도착했을 때의 경로가 최단경로임)
        //모든 경로를 다 갔는데 목적지 도달 X라면? -1 return
        //1이 있는 칸만을 이동할 수 있음! 방문한 칸이라면 0으로 설정해둘 수 있음
        //나: [0][0], 상대: [n-1][m-1]
        

        bfs(maps);
        return count;
    }
    
    private static void bfs(int[][] maps){
        int n=maps.length-1;
        int m=maps[0].length-1;
        
        int[] me={0,0,1};
        queue.offer(me); //x,y,지금까지의 이동 칸 수
        
        while(!queue.isEmpty()){
            int[] nowLoc=queue.poll();
            int x=nowLoc[0];
            int y=nowLoc[1];
            int moveCount=nowLoc[2];
            
            //이번 칸에 방문하려 했는데!? 좌표를 벗어나거나 / 이미 방문했던 칸이라면? 다음 큐로!
            if(x<0||y<0||x>n||y>m||maps[x][y]==0){
                continue;
            }
            
            //방문할 수 있는 칸을 탐색 시작할 것이므로 방문 처리
            maps[x][y]=0;
            
            if(x==n&&y==m){
                count=moveCount;
                break;
            }
            
            //이동 가능 위치: 상,하,좌,우
            int[] b1={x,y+1,moveCount+1};
            int[] b2={x,y-1,moveCount+1};
            int[] b3={x-1,y,moveCount+1};
            int[] b4={x+1,y,moveCount+1};
            
            //bfs이므로 현재 칸에서 갈 수 있는 다음 모든 칸을 다 큐에 넣는다!
            queue.offer(b1);
            queue.offer(b2);
            queue.offer(b3);
            queue.offer(b4);
            
        }
    }
}