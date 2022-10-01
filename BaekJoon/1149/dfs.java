import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
    char color;
    int price;
    int idx;
    int priceCalculation;

    public Node(char color, int price, int idx, int calc){
        this.color=color;
        this.price=price;
        this.idx=idx;
        this.priceCalculation=calc;
    }
}

class Main {
    private static int n;
    private static ArrayList<Node> red=new ArrayList<>();
    private static ArrayList<Node> green=new ArrayList<>();
    private static ArrayList<Node> blue=new ArrayList<>();
    private static int cheapPrice=Integer.MAX_VALUE;
    //N번 집까지 색칠, 이전 집과 다음 집의 색이 같으면 안된다.  -> 그러면 3개의 수가 연달아 빨,파,초 여야함
    //비용의 최솟값을 구해야 한다!!! -> 나라면 dfs로 생각할듯?  (모든 경로 탐색하면서 최저비용 찾기)
    //5개까지 칠해보자 
    //1번집의 색칠 비용 + 2번 집의 색칠 비용 + 3번 집의 색칠 비용 ... 
    //즉 1~n까지의 최소 비용 + 현재 비용 = 나까지의 최종 비용  => 점화식이 나왔으면 이걸 코드로 짜야한다!
    //blue, green, red 일 때 각각의 각 단계별 최소비용 식을 세울 수 있어야한다
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            red.add(new Node("R".charAt(0),Integer.parseInt(st.nextToken()),i,0));
            green.add(new Node("G".charAt(0),Integer.parseInt(st.nextToken()),i,0));
            blue.add(new Node("B".charAt(0),Integer.parseInt(st.nextToken()),i,0));
        }

        dfs(red.get(0));
        dfs(blue.get(0));
        dfs(green.get(0));

        bw.write(String.valueOf(cheapPrice));
        bw.flush();
        bw.close();
    }

    //재귀를 사용한 dfs!!!! (시간 초과)  -> 이걸 DP로 변환한다? 
    private static void dfs(Node nowNode){
        nowNode.priceCalculation+=nowNode.price;
        int nextIdx=nowNode.idx+1;

        if(nextIdx>=n){ //종료! 다음 노드 탐색 불가임
            if(nowNode.priceCalculation<cheapPrice){
                cheapPrice=nowNode.priceCalculation;
            }
            nowNode.priceCalculation-=nowNode.price; //다음 노드 탐색할 때 위해 이번의 합을 빼줌 -> 지금까지의 결과만 남겨줌
            return;
        }
        //연결된  바로 다음 노드들 (idx+1 집의 R,G,B 3가지 노드가 있음) 중 하나 선택 
        //다음 노드들을 모두 한 번씩 감 (근데 dfs이므로 먼저 간 노드 -> 그 라인의 끝까지 -> 다시 백)

        Node redNode=red.get(nextIdx);
        Node blueNode=blue.get(nextIdx);
        Node greenNode=green.get(nextIdx);

        redNode.priceCalculation=nowNode.priceCalculation; //일단 지금까지의 가격 합을 반영시켜줌 (그리고 거기로 dfs 호출됐을 때 값 합침)
        blueNode.priceCalculation=nowNode.priceCalculation;
        greenNode.priceCalculation=nowNode.priceCalculation;

        if(redNode.color!=nowNode.color){
            dfs(redNode);
        }
        if(blueNode.color!=nowNode.color){
            dfs(blueNode);
        }
        if(greenNode.color!=nowNode.color){
            dfs(greenNode);
        }

        //자기 뿌리 끝까지 다 탐색했으면 여기로 넘어올 것임 -> return되면 내 바로 부모 노드로 돌아가서 다음 노드를 탐색할 것이므로 내 가격을 여기서 빼준다!
        nowNode.priceCalculation-=nowNode.price;
    }
}