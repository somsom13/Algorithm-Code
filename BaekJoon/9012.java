import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        
        //stack에 ( 가 나오면 쌓아두고, )가 나오면 pop
        //만약에 모든 string이 끝났는데 (가 남아있거나 )가 나왔는데 스택에 (가 없으면 VPS가 아니다

        Stack<Character> st=new Stack<>();
        Boolean flag=true;

        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            flag=true;
            String input=br.readLine();
            for(int j=0;j<input.length();j++){
                if(input.charAt(j)=='('){
                    st.push('(');
                }else{
                    if(st.isEmpty()){
                        bw.write("NO\n");
                        st.clear();
                        flag=false;
                        break;
                    }
                    st.pop();
                }
            }

            if(!flag){ //flag가 false면 앞에서 이미 체크가 끝남
                continue;
            }

            if(!st.isEmpty()){
                //다 돌았는데 스택에 '(' 가 남아있다면, 닫히지 않은 괄호가 존재한다는 의미
                bw.write("NO\n");
            }else{
                bw.write("YES\n");
            }
            st.clear();
        }

        bw.flush();
        bw.close();

    }
 
}