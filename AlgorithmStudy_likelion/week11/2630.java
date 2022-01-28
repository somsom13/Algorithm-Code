import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;


//각 케이스마다 해당 케이스 내 문서들의 우선순위가 담겨있어야함
public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[][] squares;
    private static int whiteCount=0;
    private static int blueCount=0;

    public static void main(String[] args) throws IOException, NumberFormatException{
        int n=Integer.parseInt(br.readLine());
        squares=new int[n][n];

        StringTokenizer st;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<n;j++){
                squares[j][i]=Integer.parseInt(st.nextToken()); // 좌표평면처럼 생각하기 위해 i와 j 순서 바꿈
            }
        }
        

        check(n,0,0); //크기, 시작 좌표, 배열
        bw.write(String.valueOf(whiteCount)+"\n"+String.valueOf(blueCount));
        bw.flush();
    }

    private static void check(int size,int x,int y){
        boolean allSame=true;
        if(size==1){
            //색 확인하고 1이면 파란색, 아니면 흰색 증가
            if(squares[x][y]==1){
                blueCount++;
            }else{
                whiteCount++;
            }
            
            return;
        }
        else{
            // 더 쪼갤 수 있다면 색 비교한 후 재귀함수 호출
            int firstColor=squares[x][y]; 
            // System.out.println("x: "+x+", y: "+y+ ", firstColor: "+squares[x][y]+", size: "+size);
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(squares[x+i][y+j]!=firstColor){
                        allSame=false; // 하나라도 다르면 false로 바꾸고 break
                        break;
                    }
                    
                }
            }

            if(allSame){ //다 같은 색이면 첫 번째 색상 기준으로 count증가
                if(firstColor==1){
                    blueCount++;
                }
                else{
                    whiteCount++;
                }
                return;
            }
            else{
                //색이 같지 않다면 또 쪼개줘야함
                int newSize=size/2;
                check(newSize,x,y); //1사분면
                check(newSize,x+newSize,y);//2사분면
                check(newSize,x,y+newSize);
                check(newSize,x+newSize,y+newSize);
            }
        }
    }
    
}