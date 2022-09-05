import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        //N명의 성적을 내림차순 정렬

        String[] input=br.readLine().split(" ");
        int n=Integer.parseInt(input[0]);
        int k=Integer.parseInt(input[1]);
        String[] scoreInput=br.readLine().split(" ");

        List<Integer> scores=new ArrayList<Integer>();

        for(int i=0;i<n;i++){
            scores.add(Integer.parseInt(scoreInput[i]));
        }

        //Collections.sort : Timesort(삽입정렬과 합병정렬을 결합) => 평균/최악 시간복잡도: O(nlogn)
        Collections.sort(scores,Collections.reverseOrder());
        int minScore=scores.get(k-1);
        bw.write(String.valueOf(minScore));


        bw.flush();
        bw.close();
    }
 
}