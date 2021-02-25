import java.io.*; //BufferedReader,Writer,IOException,Stream
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n=Integer.parseInt(reader.readLine());
		String number;
		String[] num;
		for(int i=0;i<n;i++) {
			number=reader.readLine();
			num=number.split(" ");
			writer.write((Integer.parseInt(num[0])+Integer.parseInt(num[1]))+"\n");	
		}
		writer.flush();
		writer.close();
	}	
}