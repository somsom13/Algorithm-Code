import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
class Word{
    String word;
    int idx;
    int count;
    Boolean visited;
    
    public Word(String word,int idx, int count, Boolean visited){
        this.word=word;
        this.idx=idx;
        this.count=count;
        this.visited=visited;
    }
}

class Solution {
    private static Queue<Word> queue=new LinkedList<>(); //{word,인덱스}
    private static int len;
    private static ArrayList<Word> wordList=new ArrayList<>();
    private static int count;
    public int solution(String begin, String target, String[] words) {
        //target이 words내에 있는 단어여야 변환 가능
        //target까지의 모든 words에 있는 단어를 다 거쳐야함
        //한 글자 씩만 바꿀 수 있으며, 그 단어는 words 내에 있는 단어여야 한다!
        len=begin.length();
        for(int i=0;i<words.length;i++){
            wordList.add(new Word(words[i],i,0,false));
        }
        
        for(int i=0;i<words.length;i++){
            if(words[i].equals(target)){
                break;
            }
            if(i==words.length-1){
                return 0;
            }
        }
        bfs(new Word(begin,-1,0,false),target);
        //가장 짧은 변환 과정 -> bfs 사용
        return count;
    }
    
    private static void bfs(Word w, String target){
        ArrayList<Word> list;
        queue.offer(w);
        Queue<Word> temp=new LinkedList<>();
        while(!queue.isEmpty()){

            Word nowWord=queue.poll();

            if(nowWord.word.equals(target)){
                count=nowWord.count;
                return;
            }
            
            //이번 단어의 다음 idx에 있는 단어들 중, 한 글자만 다른 단어를 모두 방문!
            list=checkWordInArray(nowWord, target);
            if(list.isEmpty()){
                nowWord.visited=false; //이 루트에서 더 이상 탐색 못하면, 방문 false로 하고 돌아감
                continue;
            }
            //이 단어에서 한 글자를 바꾼게 words에 있다면? 그 단어를 방문! 그리고 그 words는 null 처리
            
            for(Word word:list){
                word.visited=true;
                wordList.set(word.idx,word);
                queue.offer(new Word(word.word, word.idx, nowWord.count+1,true));
            }
        }
    }
    
    private static ArrayList<Word> checkWordInArray(Word nowWord,String target){
        int count;
        ArrayList<Word> list=new ArrayList<>();
        for(Word w:wordList){ //순서랑 상관없이 찾을 수 있어야함
            count=0;//서로 다른 문자의 갯수
            if(w.visited){
                continue;
            }

            for(int i=0;i<len;i++){
                if(w.word.charAt(i)!=nowWord.word.charAt(i)){
                    count++;
                }
                if(count>=2){
                    break;
                }
            }
            if(count==1){
                list.add(w);
            }
        }
            return list;
        }
}