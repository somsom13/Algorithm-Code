class Solution {
    public String solution(int[] numbers, String hand) {
        /* 키패드를 좌표평면으로 생각 */
        String answer = "";
        int[] Lpos={4,1};//처음 시작 위치: *
        int[] Rpos={4,3};//처음 오른손 위치: #
        //int[][] pad={{1,2,3},{4,5,6},{7,8,9},{-1,0,-2}};
        //-1: *, -2: #
        for(int num : numbers){
            if(num==1||num==4||num==7){//1,4,7: 왼손
                answer+="L";
                switch (num){
                        //각 case별로 왼손의 위치 변경
                    case 1:
                        Lpos[0]=1;
                        Lpos[1]=1;
                        break;
                    case 4:
                        Lpos[0]=2;
                        Lpos[1]=1;
                        break;
                    case 7:
                        Lpos[0]=3;
                        Lpos[1]=1;
                        break;
                }
            }else if(num==3||num==6||num==9){//3,6,9: 오른손
                answer+="R";
                switch (num){//각 case별로 오른손 위치 변경
                    case 3:
                        Rpos[0]=1;
                        Rpos[1]=3;
                        break;
                    case 6:
                        Rpos[0]=2;
                        Rpos[1]=3;
                        break;
                    case 9:
                        Rpos[0]=3;
                        Rpos[1]=3;
                        break;
                }
            }else{//2,5,8,0
                int Ldis=0;//버튼과 왼손/오른손 거리 비교 위한 변수
                int Rdis=0;
                int Lrow,Lcol,Rrow,Rcol=0;//좌표평면으로 생각 -> 가로차이+세로차이 = 총 거리
                switch (num){
                    case 2:
                        //row: 가로거리(x축), col: 세로거리(y축)
                        //2는 (1,2)에 위치 -> 손의 x축 위치(pos[0])과 1의 차이로 가로 거리 비교
                        //거리는 항상 "양수" 이므로, 절댓값을 씌워준다
                        Lrow=(1-Lpos[0]<0)?(-(1-Lpos[0])):(1-Lpos[0]);
                        Lcol=(2-Lpos[1]<0)?(-(2-Lpos[1])):(2-Lpos[1]);//y축 비교
                        Ldis=Lrow+Lcol;//x차이+y차이 = 총 거리
                        
                        //오른손 거리비교
                        Rrow=(1-Rpos[0]<0)?(-(1-Rpos[0])):(1-Rpos[0]);
                        Rcol=(2-Rpos[1]<0)?(-(2-Rpos[1])):(2-Rpos[1]);
                        Rdis=Rrow+Rcol;
                        
                        if(Ldis<Rdis){//왼손거리<오른손거리
                            answer+="L";
                            Lpos[0]=1;
                            Lpos[1]=2;//오른손 위치 변경
                        }else if(Ldis>Rdis){
                            answer+="R";
                            Rpos[0]=1;
                            Rpos[1]=2;
                        }else{
                            if(hand.equals("left")){//오른손=왼손일 경우
                                answer+="L";
                                Lpos[0]=1;
                                Lpos[1]=2;
                            }else{
                                answer+="R";
                                Rpos[0]=1;
                                Rpos[1]=2;
                            }
                        }
                        System.out.println(num+"->Ldis: "+Ldis+" Rdis: "+Rdis);
                        break;
                        
                    case 5://(2,2)
                        Lrow=(2-Lpos[0]<0)?(-(2-Lpos[0])):(2-Lpos[0]);
                        Lcol=(2-Lpos[1]<0)?(-(2-Lpos[1])):(2-Lpos[1]);
                        Ldis=Lrow+Lcol;
                        
                        Rrow=(2-Rpos[0]<0)?(-(2-Rpos[0])):(2-Rpos[0]);
                        Rcol=(2-Rpos[1]<0)?(-(2-Rpos[1])):(2-Rpos[1]);
                        Rdis=Rrow+Rcol;
                        
                        if(Ldis<Rdis){
                            answer+="L";
                            Lpos[0]=2;
                            Lpos[1]=2;
                        }else if(Ldis>Rdis){
                            answer+="R";
                            Rpos[0]=2;
                            Rpos[1]=2;
                        }else{
                            if(hand.equals("left")){
                                answer+="L";
                                Lpos[0]=2;
                                Lpos[1]=2;
                            }else{
                                answer+="R";
                                Rpos[0]=2;
                                Rpos[1]=2;
                            }
                        }
                        System.out.println(num+"->Ldis: "+Ldis+" Rdis: "+Rdis);
                        break;
                    case 8://(3,2)
                        Lrow=(3-Lpos[0]<0)?(-(3-Lpos[0])):(3-Lpos[0]);
                        Lcol=(2-Lpos[1]<0)?(-(2-Lpos[1])):(2-Lpos[1]);
                        Ldis=Lrow+Lcol;
                        
                        Rrow=(3-Rpos[0]<0)?(-(3-Rpos[0])):(3-Rpos[0]);
                        Rcol=(2-Rpos[1]<0)?(-(2-Rpos[1])):(2-Rpos[1]);
                        Rdis=Rrow+Rcol;
                        
                        if(Ldis<Rdis){
                            answer+="L";
                            Lpos[0]=3;
                            Lpos[1]=2;
                        }else if(Ldis>Rdis){
                            answer+="R";
                            Rpos[0]=3;
                            Rpos[1]=2;
                        }else{
                            if(hand.equals("left")){
                                answer+="L";
                                Lpos[0]=3;
                                Lpos[1]=2;
                            }else{
                                answer+="R";
                                Rpos[0]=3;
                                Rpos[1]=2;
                            }
                        }
                        System.out.println(num+"->Ldis: "+Ldis+" Rdis: "+Rdis);
                        break;
                    case 0://(4,2)
                        Lrow=(4-Lpos[0]<0)?(-(4-Lpos[0])):(4-Lpos[0]);
                        Lcol=(2-Lpos[1]<0)?(-(2-Lpos[1])):(2-Lpos[1]);
                        Ldis=Lrow+Lcol;
                        
                        Rrow=(4-Rpos[0]<0)?(-(4-Rpos[0])):(4-Rpos[0]);
                        Rcol=(2-Rpos[1]<0)?(-(2-Rpos[1])):(2-Rpos[1]);
                        Rdis=Rrow+Rcol;
                        
                        if(Ldis<Rdis){
                            answer+="L";
                            Lpos[0]=4;
                            Lpos[1]=2;
                        }else if(Ldis>Rdis){
                            answer+="R";
                            Rpos[0]=4;
                            Rpos[1]=2;
                        }else{
                            if(hand.equals("left")){
                                answer+="L";
                                Lpos[0]=4;
                                Lpos[1]=2;
                            }else{
                                answer+="R";
                                Rpos[0]=4;
                                Rpos[1]=2;
                            }
                        }
                        System.out.println(num+"->Ldis: "+Ldis+" Rdis: "+Rdis);
                        break;
                        
                }
            }
        }
        return answer;
    }
}