import java.util.stream.*;
import java.util.*;
import java.lang.*;

//차 번호, 주차 시간 (분), IN/OUT 상태 저장 클래스
class CarRecord {
    private int number;
    private int timeMinute;
    private String status;
    
    public CarRecord(String record){
        String[] res = record.split(" ");
        this.number=Integer.parseInt(res[1]);
        String[] time=res[0].split(":");
        this.timeMinute = Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
        this.status=res[2];
    }
    
    public int getNumber(){return this.number;}
    public int getTime(){return this.timeMinute;}
    public String getStatus(){return this.status;}
}

class Solution {
    private static HashMap<Integer, Integer> totalTime = new HashMap<>();
    public int[] solution(int[] fees, String[] records) {    
        int lastTime=23*60+59;
        
        List<CarRecord> cars = Arrays.stream(records)
            .map(CarRecord::new)
            .sorted(Comparator.comparing(CarRecord::getNumber)) //시간순&차 번호순 정렬
            .collect(Collectors.toList());
        
        for(int i=0;i<cars.size();i++){
            
            CarRecord nowCar=cars.get(i);
            if(nowCar.getStatus().equals("IN")&&(i==cars.size()-1||cars.get(i+1).getNumber()!=nowCar.getNumber())){
                //IN인데 마지막 기록이거나 다음 차량이 OUT 경력이 아니라면? (차번호순 정렬이므로 다음 번호가 원래는 OUT 된 차여야함)
                int passedTime=lastTime-nowCar.getTime(); //23:59에 OUT으로 연산
                addCarTime(nowCar.getNumber(),passedTime);
                continue;
            }

            //바로 다음 CarRecord: 이번 IN record에 대한 OUT 기록 (1.차 번호순, 2.시간 순으로 정렬했으므로)
            CarRecord nextCar=cars.get(++i);
            int passedTime=nextCar.getTime()-nowCar.getTime();
            addCarTime(nowCar.getNumber(),passedTime);
        }
        
        int[] answer={};
        List<Integer> keyList = new ArrayList<>(totalTime.keySet()); //totalTime HashMap의 key (=차 번호) 순으로 정렬
        Collections.sort(keyList);
        answer=keyList.stream().map(k->calcRate(totalTime.get(k),fees[0],fees[1],fees[2],fees[3])).mapToInt(i->i).toArray(); //정렬된 key 순서대로 차의 누적 주차시간->주차요금으로 변환
        return answer;
    }
    
    //누적 시간으로 주차 요금 계산
    private int calcRate(int time, int baseT, int baseR, int unitT, int unitR){
        if(time<baseT){
            return baseR;
        }
        return baseR+(int)Math.ceil((time-baseT)/(double)unitT)*unitR; //주차 시간이 단위시간으로 나누어 떨어지지 않는 경우, 올림처리
    }
    
    //HashMap에 동일 차량이 저장된 기록이 있는지 확인
    //저장된 기록이 없다면 새롭게 레코드 추가 / 기록이 있다면 누적 주차시간 업데이트
    private void addCarTime(int number, int time){
        Integer check = totalTime.get(number);
        if(check==null){
            totalTime.put(number,time);
        }else{
            totalTime.put(number,check+time);
        }
        
    }
}