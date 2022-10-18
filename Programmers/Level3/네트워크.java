
class Solution {
    private static Boolean[] visited;
    private static int answer=0;
    public int solution(int n, int[][] computers) {
        // int answer = 0;
        //2개의 컴퓨터 직접연결 -> 종단에 있는 두 컴퓨터도 간접연결!
        
        //네트워크 개수 -> 서로 연결되어있지 않은 것들을 찾아야함 -> dfs 로 (0,0) 부터 방문, visited 처리, 1인 곳으로 방문할 수 있음
        //연결이 반드시 인접한게 아닐 수도 있기 때문에 배열에 있는 모든 컴퓨터를 확인해주어야함!
        visited=new Boolean[n];
        for(int i=0;i<n;i++){
            visited[i]=false;
        }
        
        for(int i=0;i<n;i++){
            if(!visited[i]){ //내가 방문이력이 있다면, 이미 다른 네트워크에 속해있는 것이므로 서치 X
                dfs(computers, n, i);
                answer++; 
                //내가 다른 네트워크에 속해있지 않다면, visited가 false일 것이므로 **나를 방문할 때 answer를 추가시켜준다**
                //나를 포함한 하나의 네트워크를 찾게되기 때문에 (+나 혼자만으로도 하나의 네트워크임)
            }
            
        } 
        
        return answer;
    }
    
    private static void dfs(int[][] computers, int n, int me){
        //computers[me][0~n-1] 까지를 모두 체크해야한다! 
        visited[me]=true; //한 번 방문한 컴퓨터는 모두 한 네트워크에 속해있는 컴퓨터임 -> 다른 네트워크에서 서치할 필요 없다
        for(int i=0;i<n;i++){
            if(i!=me&&!visited[i]&&computers[me][i]==1){ //자기자신은 패스, 방문 안한 연결된 컴퓨터만
                dfs(computers,n,i);
            }
            
        }
    }
}