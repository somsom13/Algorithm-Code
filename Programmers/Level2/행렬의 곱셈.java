class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = {};
        int n=arr1.length;
        int h=arr1[0].length;
        int m=arr2[0].length;
        answer=new int[n][m]; //n행 m열 크기의 행렬
        int sum=0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<h;k++){
                    answer[i][j]+=arr1[i][k]*arr2[k][j];
                }
            }
        }
        return answer;
    }
}