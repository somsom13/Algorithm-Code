import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        List<Integer> list=new ArrayList<>();

        /**
        2차원 배열: 행*열의 구조!!!!!!!!!!
        따라서 크레인의 경우, 맨 윗줄: board[0], 맨 아랫줄: board[board.length] 가 되는것임
        => moves가 1인 경우, 1번열의 위에서 부터 순서대로 집어나가기 시작하는 형태!

        thisMove=moves[value]는 크레인의 입장에서 "어떤 열로 집게를 넣었는지"를 알려준다.
        즉 thisMove=col

        그래서 board의 값의 접근할 때 board[j(0~board.length(=세로 한 줄의 크기, 즉 행의 갯수)),moves[value]]
        로 접근해야 한다. 
        열은 고정된 값을 사용하지만 (집게는 하나의 열 안에서 이동하므로), 행은 0번부터 board.length-1(맨 마지막 칸) 까지 돌아야하기 때문! 
        
        board[j][thisMove]==0 이면 해당 칸은 비어있기 때문에 집게게임의 원리에 따라 집게는 인형이 있는 곳 까지 계속 내려간다
        그래서 board==0이면 아무런 동작없이 for문을 돌고, 0이 아닌 값을 찾았을 때 list에 추가/삭제하고 해당 값을 0으로 바꿔주는것
         */
        
        for(int i=0;i<moves.length;i++){
            int thisMove=moves[i]-1;
            for(int j=0;j<board.length;j++){
                
                if(board[j][thisMove]!=0){
                    if(list.size()==0){
                        list.add(board[j][thisMove]);
                        board[j][thisMove]=0;
                    }else{
                        int lastIndex=list.size()-1;
                        if(list.get(lastIndex)==board[j][thisMove]){
                            list.remove(lastIndex);
                            answer+=2;
                            board[j][thisMove]=0;
                        }
                        else{
                            list.add(board[j][thisMove]);
                            board[j][thisMove]=0;
                        }
                    }
                    break;
                }
            }
        }
        return answer;
    }
}