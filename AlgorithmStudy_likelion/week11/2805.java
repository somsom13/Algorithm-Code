import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int[] heights=new int[n];
        st=new StringTokenizer(br.readLine()," ");

        for(int i=0;i<n;i++){
            heights[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(heights);
        searchMax(m,heights);
    }

    private static void searchMax(int needHeight,int[] heights){
        // 절단기 높이가 최대 -> 자른 것들의 합이 가장 m에 가까운 결과가 나올 수 있도록 잘라야함
        // 찾는 값: 필요한 나무 (=m), 비교할 값들(=배열 그 자체): 설정할 자르는 높이(=h) (needHeight/n) 부터 heights 중 최대값까지)
        // m = (heights[i]-h)의 총합 (이 때 heights[i]-h<0, 즉 톱이 나무보다 크다면? 잘리는 부분은 0) -> h를 변경해가면서 각 h에서의 m을 찾는다! 
        // 최소 h=1, 최대 h=heights중 최댓값 (가장 큰 나무의 높이)

        int totalTreeCount=heights.length;
        int maxHeight=heights[totalTreeCount-1];
        int minHeight=0;
        //1부터 max까지의 숫자를 모두 돌아가면서 최적의 자를 높이를 찾는다! 

        while(minHeight<maxHeight){
            long totalCuts=0;
            int midH=(maxHeight+minHeight)/2; //여기서

            for(int height:heights){
                int cutHeight=height-midH;
                if(cutHeight>0){
                    totalCuts+=cutHeight;
                }
            }

            //totalCuts에 자르는 높이 = h 일 때 총 자른 합이 담긴다
            if(totalCuts<needHeight){
                //더 나무가 많이 잘려야 한다 => 자르는 높이가 낮아져야한다! (그래야 잘려나가는 나무가 커짐)  
                // => 좀 더 1에 가까운 쪽으로 이동한다. 
                // max를 midH(=현재의 h)로 갱신!
                maxHeight=midH;
            }else{
                //나무가 너무 많이 잘렸다 => 자르는 높이가 높아져야 한다! (나무가 덜 잘려나가도록)
                // => 좀 더 maxH에 가까운 쪽으로 이동한다.
                // min을 midH로 갱신!
                minHeight=midH+1;
            }

        }

        System.out.println(minHeight-1);
        // 자를 나무 높이를 갱신해 가는 것.... (최소값과 최대값의 중간부터 시작해서)
    }
     
}
