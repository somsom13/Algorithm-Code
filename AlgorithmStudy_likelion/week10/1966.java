import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;


//각 케이스마다 해당 케이스 내 문서들의 우선순위가 담겨있어야함
public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int n=Integer.parseInt(br.readLine());
        Queue<int[]> queue;
        PriorityQueue<Integer> priorities;


        for(int i=0;i<n;i++){
            String[] numsAndTarget=br.readLine().split(" "); // 문서 개수와 체크해야할 대상 문서의 인덱스
            int len=Integer.parseInt(numsAndTarget[0]); //문서 개수
            int needIdx=Integer.parseInt(numsAndTarget[1]); //체크 대상 문서 인덱스

            queue=new LinkedList<>();  //[각 문서의 원래 인덱스, 우선순위] 가 들어간 큐
            priorities=new PriorityQueue<>(Collections.reverseOrder()); //우선순위를 내림차순으로 저장할 큐

           
            int[] s;
            String[] inputs=br.readLine().split(" "); // inputs에 우선순위 순서대로 입력받음
            for(int j=0;j<len;j++){
                s=new int[2]; // [원래 인덱스, 우선순위]
                s[0]=j;
                s[1]=Integer.parseInt(inputs[j]);
                priorities.add(s[1]); //우선순위를 내림차순으로 저장
                queue.add(s);
            }


            int count=0; //지금까지 문서가 몇개나 출력되었는가
            int maxVal=0; //가장 우선순위가 큰 거 저장할 변수
            if(!priorities.isEmpty()){
                maxVal=priorities.peek(); //일단 맨 처음에 가장 큰 우선순위 세팅
            }
            
            while(!queue.isEmpty()){
                int[] current=queue.poll();
                if(current[1]==maxVal){ //우선순위가 가장 크면? -> 바로 출력
                    count++;
                    if(current[0]==needIdx){ //출력한 애가 원하는 애 (체크 대상)랑 같으면? -> 비교 멈춤
                        break;
                    }

                    //출력한 애가 체크 대상이 아니라면 (더 출력해야 한다면)
                    priorities.poll(); // 최고 우선순위를 갱신하기 위해 방금 뽑은 애의 우선순위를 제거
                    if(!priorities.isEmpty()){   
                        maxVal=priorities.peek(); //최고 우선순위 갱신
                    }
                    continue;
                }else{
                    queue.add(current); //현재 문서 우선순위 < 대기열 모든 문서 중 최고 우선순위 -> 다음 문서 확인
                }

            }
            bw.write(String.valueOf(count)+"\n");
        }

        bw.flush();
    }
}