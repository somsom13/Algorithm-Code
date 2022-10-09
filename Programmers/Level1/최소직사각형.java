import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int[][] sizes) {
        ArrayList<Integer> h=new ArrayList<>();
        ArrayList<Integer> w=new ArrayList<>();
        
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0]>sizes[i][1]){
                h.add(sizes[i][0]);
                w.add(sizes[i][1]);
            }else{
                h.add(sizes[i][1]);
                w.add(sizes[i][0]);
            }
        }
        Collections.sort(h,Collections.reverseOrder());
        Collections.sort(w,Collections.reverseOrder());
        
        return h.get(0)*w.get(0);
    }
}