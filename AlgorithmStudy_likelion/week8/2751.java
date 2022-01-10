import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
      int n=0;
      int[] nums;
      try{
          n=Integer.parseInt(br.readLine());
      }
      catch(IOException e){
          e.printStackTrace();
      }
      nums=new int[n];

      for(int i=0;i<n;i++){
          int num=0;
          try{
              num=Integer.parseInt(br.readLine());
          }
          catch(IOException e){
            e.printStackTrace();
          }
          nums[i]=num;
      }

      int[] sorted=mergeSort(nums,n);
      for(int i=0;i<sorted.length;i++){
        bw.write(String.valueOf(sorted[i])+"\n");
      }
        bw.flush();
    }



      //합병 정렬 (Merge Sort) 사용 -> 시간 복잡도를 O(NlogN)으로 낮추기 위해
      // 합병 정렬이란? 가운데 값 (mid)를 기준으로 좌측, 우측의 크기를 비교하여 정렬하는 것

    

    private static int[] mergeSort(int[] nums,int n){
        
        int mid=0;

        if(n>1){
            // 더 쪼개야 한다면 (남은 배열의 크기가 2 이상, 즉 반으로 다시 쪼갤 수 있다면)
            mid=n/2;
            int[] left=new int[mid];
            int[] right=new int[n-mid];

            // 왼쪽 배열, 오른쪽 배열 초기화
            for(int i=0;i<left.length;i++){
                left[i]=nums[i];
            }
            for(int i=0;i<right.length;i++){
                right[i]=nums[mid+i];
            }

            // 왼쪽 배열, 오른쪽 배열 각각에 대해 다시 mergeSort (쪼개고 합치고)
            mergeSort(left,mid);
            mergeSort(right,n-mid);
            //mergeSort내에 두 배열을 다시 합치는 과정이 존재해야한다! 
            int[] sorted=merge(left,right,nums);
            return sorted;


        }
        return nums;
    }

    private static int[] merge(int[] left, int[] right,int[] nums){

        //두 배열 left와 right의 각 원소를 비교해서, 하나로 합병한다!
        int leftLen=left.length;
        int rightLen=right.length;

        int currentLeft;
        int currentRight;
        int currentNum=0;


        for(currentLeft=0,currentRight=0;currentLeft<leftLen&&currentRight<rightLen;currentLeft++,currentRight++){ //왼쪽배열, 오른쪽 배열 둘 중 하나라도 다 list에 들어갔다? -> 종료! 남은 부분은 다 한 번에 이어 붙인다. 
            if(left[currentLeft]<right[currentRight]){
                nums[currentNum]=left[currentLeft];
                currentNum++;
                //왼쪽 배열의 값만 정렬 -> 오른쪽 값은 여전히 재비교 되어야 하므로, index가 증가하지 않게 -- 해준다.
                currentRight--;
            }
            else{
                nums[currentNum]=right[currentRight];
                currentNum++;
                currentLeft--;
            }
        }

        if(currentLeft==leftLen){ //왼쪽 배열을 끝까지 다 돌았다면?
            //오른쪽 배열의 나머지를 전부 sortedList에 add한다. 
            while(currentRight<rightLen){
                nums[currentNum]=right[currentRight];
                currentNum++;
                currentRight++;
            }
        }else{
            while(currentLeft<leftLen){
                nums[currentNum]=left[currentLeft];
                currentNum++;
                currentLeft++;
            }
        }

        return nums;
    }

   
}