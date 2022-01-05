import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class SizeandID{
    public int weight;
    public int height;
    public int _id;
    public int rank;

    public SizeandID(int w,int h,int i){
        this.weight=w;
        this.height=h;
        this._id=i;
        this.rank=0;
    }

}

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args){
        int n=0;
        try{
            n=Integer.parseInt(br.readLine());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        SizeandID[] size=new SizeandID[n];

        for(int i=0;i<n;i++){
            String val="";
            try{
                val=br.readLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            String[] values=val.split(" ");
            int w=Integer.parseInt(values[0]);
            int h=Integer.parseInt(values[1]);

            size[i]=new SizeandID(w,h,i);
        }

       
        printRank(size);

    }

    private static void printRank(SizeandID[] size){
        //우선 몸무게 기준으로 내림차순 정렬
        for(int i=0;i<size.length;i++){
            for(int j=0;j<size.length;j++){
                if(size[i].weight<size[j].weight && size[i].height<size[j].height){
                    size[i].rank+=1;
                }
            }
        }

        for(int i=0;i<size.length;i++){
            System.out.print(size[i].rank+1+" ");
        }
    }
}