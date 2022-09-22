package Practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class QuickSort {
    private static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n=Integer.parseInt(br.readLine());
        arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        //퀵정렬 -> 최선, 평균: O(nlog2n), 최악: O(n^2)
        //퀵정렬: 비교정렬, 제자리 정렬

        //기본: pivot은 첫 번째 인덱스, 좌측에서는 pivot보다 큰 값을 / 우측에서는 pivot보다 작은 값을 찾아 swap 한다
        //만약 작은 값의 idx>큰 값의 idx (엇갈린다)면? pivot과 작은 값의 위치를 swap, pivot 기준으로 새로운 배열로 분할
        //큰 값은 마지막인덱스 까지 갈 수 있도록 한다 -> 만약 큰 값이 없는 경우 (즉 작은 값만 남아있는 경우) 라면, 작은값과 큰 값의 인덱스가 엇갈렸으므로 처음으로 발견한 작은값과 pivot의 위치를 바꿔서 새롭게 분할할 수 있게 함
        //작은 값은 pivot의 인덱스 까지 갈 수 있도록 한다 (즉 pivot보다 작거나 같은 값을 찾는 걸로 조건을 둔다) -> pivot==(처음으로 발견한 pivot보다 작거나 같은 값) 이라면 당연히 인덱스가 엇갈렸을 것이며, 그렇다면 작은 값과 pivot의 위치를 바꾸게 되는데
        //작은 값의 idx == pivot의 idx 이므로 그냥 제자리에 머물게 되고, 위치를 바꾼 pivot을 기준으로 분할하게 된다면 pivot만 있는 배열과 나머지 전부로 구성된 배열이 생긴다.
        //만약 배열의 크기가 1이 된다면 더 이상 분할할 수 없으므로 해당 배열에 대해서는 정렬을 종료한다. 

        quickSort(0,n-1);

        for(int i:arr){
            bw.write(i+"\n");
        }

        bw.flush();
        bw.close();

    }

    private static void quickSort(int start, int end){

        if(start>=end){
            return;
        }

        //start~end 까지의 배열만 존재한다고 생각!
        int pivotIdx=start;
        int pivot=arr[pivotIdx];
        int left=start+1; //pivot 다음 원소부터 큰 값을 찾아나감
        int right=end; //맨 마지막 원소부터 작은 값을 찾아나감

        while(left<=right){ //left>=right이라면 해당 배열 내에서는 더 이상 탐색이 필요없다.
            //left는 pivot보다 큰 값이 나올 때 까지, end+1 (마지막 인덱스를 초과한 값, 즉 pivot보다 큰 값을 찾지 못함) 이 될 때 까지 pivot보다 큰 값의 idx를 찾아나간다
            while(left<=end&&arr[left]<=pivot){ //따라서 while조건은 left가 조건에 맞는 값을 찾는 경우의 반대를 두어야함
                left++;
            }
            
            //right는 pivot보다 작거나 같은 (같은은 본인) 값이 나올 때 까지, pivot과 같은 인덱스가 될 때 까지 (같은 idx면 무조건 엇갈림, 작은 값과 swap 할 때 제자리에 머물고 분할) pivot보다 작은 값의 idx를 찾아나간다
            while(right>start&&arr[right]>pivot){
                right--;
            }

            //둘이 제대로 된 idx를 찾았거나, 범위를 초과 (즉 작은 값이 없거나 큰 값이 없을 때) while문을 빠져나오게됨
            if(left>right){
                //엇갈린 경우, 즉 이제 해당 배열에서는 더 이상 pivot 기준으로 위치 바꿀게 없는 경우 -> 분할을 통해서 다음 작은 배열을 탐색해야 한다. 분할 기준인 pivot의 위치를 만들어주기 위해 작은 값과 pivot을 swap 한다
                int tmp=pivot;
                arr[pivotIdx]=arr[right];
                arr[right]=tmp; //pivotIdx==start 이고, 만약 right==start라면 pivot과 right의 값은 변하지 않는다. (자기자신 그대로)
                pivotIdx=right;

            } else{
                //작은 값과 큰 값의 위치를 바꿀 수 있는 경우
                int tmp=arr[right];
                arr[right]=arr[left];
                arr[left]=tmp;
            }

        }
        quickSort(0,pivotIdx-1); //해당 배열에서 더 이상 위치 변경이 불가한 경우에는, 새롭게 옮겨진 pivotIdx 값을 기준으로 분할이 일어나야함 -> pivotIdx를 기준으로 start,end를 재설정해서 재귀한다
        quickSort(pivotIdx+1,end);
    }
}

