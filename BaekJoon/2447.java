import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static char[][] star;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n=Integer.parseInt(br.readLine()); //3의 거듭제곱, nxn 크기의 정사각형
        star=new char[n][n];

        writeStar(0, 0, n);
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                bw.write(star[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
       
        //n이 무엇이든 가운데는 항상 공백
        //그 주위를 (n/3)*(n/3) 사각형이 둘러쌈

    }

    /**
     * 
     * @param a 입력 시작할 배열의 가로 행 번호
     * @param b 입력 시작할 배열의 세로 열 번호
     * @param n 현재 그리는 정사각형의 크기
     */
    private static void writeStar(int a, int b, int n){
        //n==3 이어도 이 부분이 크기가 더 큰 사각형에서 빈 칸이어야 하는 경우
        //가장 큰 사각형 부터 가운데 제외시키는 규칙 적용 -> 크기가 작은 사각형일 때 이미 빈칸인 곳 제외하고 *랑 ' ' 으로 다시 채우기
    


        for(int i=a;i<a+n;i++){
            for(int j=b;j<b+n;j++){
                if(star[i][j]==' '){
                    continue;
                }
                if(i>=n/3+a&&i<n/3*2+a&&j>=n/3+b&&j<n/3*2+b){ 
                    //가운데 위치면 빈칸으로 두어야함
                    // a ~ a+n 을 3등분
                    // 1/3 지점 ~ 2/3 지점이 빈칸
                    star[i][j]=' ';
                    continue;
                }
                star[i][j]='*'; //그 외는 * 찍기
            }
        }

        if(n==3){
            return;
        }
        
        int size=n/3;
        for(int i=a;i<a+n;i+=size){
            for(int j=b;j<b+n;j+=size){
                writeStar(i,j,size);
            }
        }

    }
 
}
