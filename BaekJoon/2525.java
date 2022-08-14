import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //요리 시작시간, 조리시간을 고려하여 종료시간 계산하기
        String[] start=br.readLine().split(" ");
        int sHour=Integer.parseInt(start[0]);
        int sMin=Integer.parseInt(start[1]);
        int time=Integer.parseInt(br.readLine());

        if(time==0){
            System.out.println(sHour+" "+sMin);
            return;
        }

        int spendHour=time/60;
        int spendMin=time-spendHour*60;

        int endHour=sHour+spendHour;
        int endMin=sMin+spendMin;

        if(endMin>59){
            endHour++;
            endMin-=60;
        }
        if(endHour>23){
            endHour-=24;
        }

        System.out.println(endHour+" "+endMin);
    }
     
}
