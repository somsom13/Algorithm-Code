import java.util.*;

/* 문서의 우선순위와 위치를 저장하는 class */
class Document{
    int loc;
    int priority;
    
    Document(int idx,int p){
        loc=idx;
        priority=p;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        
        List<Document> docsList=new ArrayList<Document>();
        int count=0;//현재까지 출력한 문서 갯수 저장
        for(int i=0;i<priorities.length;i++){
            docsList.add(new Document(i,priorities[i]));
        }
        
        int checkLocation=-1;//내가 찾고자 하는 위치의 문서를 출력완료했는지 체크! 찾으려는 문서를 찾았다면, 더이상 list를 돌면서 검사할 필요 없음
        while(true){
            if(checkLocation==location){//찾고자 하는 문서를 찾았다면 while break
                break;
            }
            Document thisDoc=docsList.get(0);
            docsList.remove(0);//맨 앞의 문서 꺼내오고 list에서 삭제
            if(docsList.size()==0){
                //이번에 체크하는 문서가 리스트의 마지막 문서라면? 다른 문서들은 모두 이미 출력이 되었고, 내가 찾는 문서가 가장 마지막에 출력된다는 의미 => while문 break
                count++;
                break;
            }
            
            for(int i=0;i<docsList.size();i++){//list의 다른 문서들 우선순위 비교하기
                //thisDoc을 list에서 꺼내왔으므로, 현재 list에는 가장 앞의 문서를 제외한 나머지 문서들이 들어있다.
                Document nextDoc=docsList.get(i);//0번부터~list끝까지 list 상의 모든 문서 우선순위 확인
                if(thisDoc.priority<nextDoc.priority){
                    //현재 문서보다 우선순위가 높은 문서가 하나라도 발견되면 현재 문서를 다시 리스트의 맨 끝에 추가하고, 
                    docsList.add(thisDoc);
                    break;//for문을 빠져나가서 갱신된 맨 앞 문서를 확인한다. (더 이상 다음 문서들의 우선순위를 확인할 필요 없음)
                }else if(i==(docsList.size()-1)){
                    //만약 문서목록의 맨 마지막까지 살펴보았고, 현재 문서(thisDoc)의 우선순위가 제일 높다면? => 출력!
                    count++;//출력한 문서 갯수 증가
                    if(thisDoc.loc==location){
                        //만약 이번에 출력한 문서의 location이 내가 찾고자 하는 문서의 위치와 같다면
                        checkLocation=location;
                        break;//for문을 빠져나가고, while문도 빠져나가기 위해서 checkLocation값 갱신
                    }
                }
            }
        }
        
        return count;
    }
}