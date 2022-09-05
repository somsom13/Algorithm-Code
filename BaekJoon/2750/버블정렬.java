import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        //선택, 버블 정렬: 최고/평균/최악 시간복잡도 -> O(n^2)
        //O(n^2)으로 풀 수 있는 문제

        int n=Integer.parseInt(br.readLine());
        int[] numbers=new int[n];

        for(int i=0;i<n;i++){
            numbers[i]=Integer.parseInt(br.readLine());
        }

        //버블정렬: 뒤부터 큰 순서대로 정렬 (한 턴에 현재 남은 배열에서 가장 큰 수가 가장 뒤로 감 -> N개의 수를 모두 정렬하려면 N번 매번 큰 수를 맨 뒤로 보내야함
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1-i;j++){ //n-1-i 인 이유: 1턴을 돌았다면 -> [n-1]은 이미 현재 배열에서 가장 큰 수가 들어감 -> [n-2]와 [n-1]은 비교할 필요 없음
                //2턴 째라면 [n-2]에도 이미 두 번째로 큰 수 들어감 -> [n-3]과 [n-2]를 비교할 필요도 없음
                if(numbers[j]>numbers[j+1]){
                    int tmp=numbers[j+1];
                    numbers[j+1]=numbers[j];
                    numbers[j]=tmp;
                }
            }
        }

        for(int i=0;i<n;i++){
            bw.write(numbers[i]+"\n");
        }

        bw.flush();
        bw.close();
    }
 
}
