import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        //누적된 값이 최소이기 위해서는
        //가장 큰 값 x 가장 작은 값 + 그 다음으로 큰 값 x 그 다음으로 작은값 .....
        //2,3,4,5 > 6+20 = 26. 10+12 = 22 작은값과 큰 값을 매칭해야 최종 결과가 작아짐
        
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int len=A.length;
        
        
        //B를 역순으로 정렬하지 않는 이유
        //Arrays.sort(B, Collections.reverseOrder()) 를 쓰기 위해서는 B배열이 Integer[](primitive type) 이어야함 (int[]는 불가)
        //Integer[]로 변환하기 위해서는 stream.boxed().toArray(Integer[]::new) 로 wrap 해주어야한다. 그런데, stream API는 기본적으로 일반 for문보다 효율이 떨어짐 
        //-> 이 방법을 사용하면 효율성 테스트를 통과하지 못한다!
        //따라서, B를 그냥 오름차순 정렬한 후 인덱스를 역순으로 접근해야 한다. 
        
        for(int i=0;i<len;i++){
            answer+=A[i]*B[len-1-i];
        }

        return answer;
    }
}