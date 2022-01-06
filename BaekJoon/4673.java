public public class Main {

    public static void main(String[] args){
        // 생성자는 항 상 그 수보다 작다! (ex. 39의 생성자는 33 => 33 < 39)
        // => 10000까지 중에서 "생성자가 없는 애를 찾는다? "-> 10000까지만 for문을 돌아도 찾을 수 있다.

        boolean[] check=new boolean[10000];
        for(int i=1;i<=10000;i++){
            int calc=d(i);
            //calc: i를 생성자로 가지는 수

            if(calc<=10000){
                check[calc-1]=true;  // calc는 생성자가 있음 -> 셀프넘버가 아니다! 
            }
        }

        for(int i=0;i<10000;i++){
            if(check[i]==false){
                System.out.println(i+1);
            }
        }
    }

    private static int d(int n){
        int calc=n;

        while(n>0){
            int a=n%10;
            calc+=a;
            n/=10;
        }

        return calc;
    }
}
