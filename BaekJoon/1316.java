import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Main {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args){
      int count=0;
      
      // 마지막으로 나타난 인덱스를 기록 -> 그거랑 1 이상 차이나면 그룹단어가 아니다! 
      int n=0;
      try{
          n=Integer.parseInt(br.readLine());
      }
      catch(IOException e){
        e.printStackTrace();
      }

    //   String[] words=new String[n];
      for(int i=0;i<n;i++){
          String word="";
          try{
              word=br.readLine();
          }
          catch(IOException e){
              e.printStackTrace();
          }
          
          int[] alphabets=new int[26];
          boolean isGroup=true;
          char a='a';
          for(int currentLocation=1;currentLocation<=word.length();currentLocation++){
              //currentLocation: 해당 알파벳의 문자열에서의 현재 location
              int idx=word.charAt(currentLocation-1)-a; //a라면 0번 idx, b라면 1번 idx ... z라면 25번 idx
              int lastLocation=alphabets[idx];
    

              if(lastLocation!=0&&currentLocation-lastLocation>1){
                  //이전에 한 번 나온적 있는 알파벳이고, 마지막 위치가 현재 위치와 1 이상 차이난다면? 
                  //얘는 그룹  문자가 될 수 없다! 
                  isGroup=false;
                  break;
              }
              else{
                  //처음 등장한 알파벳 or 최근 위치과 붙어있다면 (즉 1밖에 차이가 나지 않는다면)
                  alphabets[idx]=currentLocation;
              }
              

          }

          if(isGroup){
              count++;
          }


      }

      System.out.println(count);


    }
  
}
