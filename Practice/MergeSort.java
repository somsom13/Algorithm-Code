package Practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MergeSort {
    
    //병합정렬은 평균, 최선, 최악의 시간 복잡도가 모두 O(NlogN)인 정렬 알고리즘으로, 항상 O(NlogN)을 보장한다는 점에서 효율성이 굉장히 뛰어나며, 퀵 정렬의 단점을 보완한다.
    //해당 시간복잡도가 보장되는 이유는 pivot값에 따라 분할되는 배열의 크기가 달라지는 퀵 정렬과 다르게 병합정렬은 항상 정확히 절반으로 분할이 이루어지며, 각 분할된 배열을 하나의 정렬된 배열로 합치기 위해서는 두 개의 배열의 원소값을 하나하나 읽어서 비교하는 N의 시간복잡도만 소모되기 때문이다.
    //또한, 정확히 이등분이 이루어지므로 2^(단계 수)=N(배열의 크기) 이므로, 단계 수가 logN이 된다. 따라서 총 logN 단계에 각각 N의 정렬 시간이 필요한 것이므로 시간 복잡도가 O(NlogN)이 되는 것이다.

    //병합정렬을 할 때 항상 생각해야하는 것은,   1. 일단 분할한다.  2. 그리고 나중에 정렬한다   라는 것이다. 
    //모든 배열에 대해 분할을 하고 합치면서 정렬을 하기 때문에 재귀적으로 과정이 이루어진다. 

    //각 merge마다 정렬된 값을 임시 저장할 배열. 하나의 큰 부분 집합으로 정렬이 완료되면, 원본 배열로 sorted의 내용을 옮겨줘야 한다. 
    private static int[] sorted; //중간중간의 정렬 결과를 저장할 배열은 반드시!! 전역변수로 선언해야한다. (매 정렬마다 배열을 생성하는 메모리 낭비를 막기 위해)
    private static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        sorted=new int[n];
        arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        partition(0,n-1,arr);
        for(int i:arr){
            bw.write(i+"\n");
        }

        bw.flush();
        bw.close();
    }

    /**
     * 병합정렬 수행 메소드: 1. 일단 나누고 2. 그 다음에 정렬한다
     * @param m 이번에 분할할 (반으로 나눌) 배열의 시작 idx
     * @param n 이번에 분할할 배열의 끝 iddx
     * @param arr 분할할 전체 배열, 이 배열에서 m과 n으로 접근가능한 배열의 범위를 나누는 것
     */
    private static void partition(int m, int n, int[] arr){
        if(m>=n){
            //즉 시작 idx >= 종료 idx면 배열의 크기가 1이하 이므로 아무런 작업도 하지 않는다
            return;
        }

        int mid=(m+n)/2;

        //1. 일단 배열을 두 개로 나눈다. (재귀함수로 계속 나누다가, 길이가 1일 때 return하여 밑의 메소드를 수행) 
        partition(m,mid,arr); //앞 부분 배열
        partition(mid+1,n,arr);// 뒤 부분 배열
        merge(m,n,mid,arr); //2. m~n이고 mid를 기준으로 나뉜 두 부분배열을 하나로 합치는 메소드를 호출한다. (return된 시점부터 수행되므로 길이 2 -> 4 -> ... -> n으로 합쳐진다! )

    }

    /**
     *  
     * @param m 합친 결과물이 될 배열의 시작 idx
     * @param n 합친 결과물이 될 배열의 종료 idx
     * @param mid 배열의 중간값, 즉 mid를 기준으로 두 개의 부분배열로 나눠져 있다고 생각하면 된다. 
     * @param arr 실제로 값이 들어있는 원본 배열
     * 
     * 분할된 2개의 배열을 하나로 합치면서 정렬한다 -> 여기서 분할된 2개의 배열은 실제로는 하나의 배열에서 mid와 m, n을 통해 인덱스로 분할된 것으로 생각하면 된다. 
     * 즉 m~mid: 첫 부분 배열, mid+1 ~ n: 두 번째 부분 배열 
     */
    private static void merge(int m, int n, int mid, int[] arr){
        int i=m; //첫 부분 배열의 원소를 탐색할 idx
        int j=mid+1; //두 번째 부분 배열의 원소를 탐색할 idx
        int k=m; //정렬 완료한 값을 하나의 임시 저장 배열 (sorted)에 저장할 때, 처음 값을 저장할 시작 idx 

        //두 부분 배열에 대해서 i와 j가 가리키는 값을 각각 비교 -> 더 작은 값을 sorted에 넣어주고, 해당 idx를 증가시킨다. 둘 중 하나의 인덱스가 끝에 도달하면 (즉 i는 mid에 도달, j는 n에 도달) 종료
        while(i<=mid&&j<=n){
            if(arr[i]<arr[j]){
                sorted[k]=arr[i++]; //작은 값을 넣어준다! 
            }else{
                sorted[k]=arr[j++];
            }
            k++; //어떤 값이 더 작든, k는 항상 증가
        }

        if(i>mid){
            //즉 i를 idx로 가졌던 첫 번째 부분 배열이 먼저 다 sorted에 들어가서, j를 idx로 한 두 번째 부분배열의 데이터가 남았다면? -> 남은 값을 모두 sorted에 넣어버리자!
            for(int t=j;t<=n;t++){
                sorted[k]=arr[t];
                k++;
            }
        }else{
            for(int t=i;t<=mid;t++){
                sorted[k]=arr[t];
                k++;
            }
        }

        //이렇게 되면 sorted의 m~n 까지는 두 부분 배열이 정렬된 상태로 저장된다! 그러면 정렬된 결과를 원본 배열에 저장해주어야 한다
        for(int t=m;t<=n;t++){
            arr[t]=sorted[t];
        }
    }


}

