import java.util.Queue;
import java.util.LinkedList;

class Truck {
    int weight;
    int time;
    
    public Truck(int weight, int time) {
        this.weight=weight;
        this.time=time;
    }
}

class Solution {
    private static Queue<Truck> queue = new LinkedList<>();
    private static int totalWeight=0;
    private static int bridgeLen;
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        //다리를 건너는데 걸리는 시간: bridge_length 만큼
        int answer = 0;
        bridgeLen=bridge_length;
        //각 트럭별로 다리에 올라간 후 경과 시간을 관리해주어야함

        for(int i=0;i<truck_weights.length;i++) {
            answer++;   
            if(queue.isEmpty()){
                queue.offer(new Truck(truck_weights[i],1));
                totalWeight+=truck_weights[i];
                continue;
            }
            
            Truck truck = new Truck(truck_weights[i],1);
            queue=updateTruckTime();
            if(totalWeight+truck.weight<=weight&&queue.size()<bridge_length) {

                queue.offer(truck);
                totalWeight+=truck.weight;
            }else{
                i--;
            }
        }
        
        while(!queue.isEmpty()){
            queue=updateTruckTime();
            answer++; 
        }
        
        return answer;
    }
    
    private static Queue<Truck> updateTruckTime() {
        Queue<Truck> temp=new LinkedList<>();
        while(!queue.isEmpty()) {
            Truck truck=queue.poll();
            truck.time++;
            if(truck.time==(bridgeLen+1)) {
                totalWeight-=truck.weight;
                continue;
            }
            temp.offer(truck);
        }
        return temp;
    }

}