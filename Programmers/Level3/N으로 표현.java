import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class Solution {
    private static HashMap<Integer, HashSet<Integer>> calculateResult = new HashMap<>();
    private static Boolean found = false;
    public int solution(int N, int number) {
        
        int answer = 0;
        //N을 한 번 사용 -> number = N
        //N을 두 번 사용 -> number = N * / + - N or NN => 즉 1번의 결과에 사칙연산
        //N을 세 번 사용 -> number = ( N * / + - N -> N 두 번 사용의 결과) * / + - N => 즉 N을 두 번 사용하는 케이스의 결과에 다시 N을 두 번 사용하는 케이스를 이중적용 (NN *+/- N) 도 이 케이스에 포함된다! (NN이 두 번 사용의 결과 중 하나이므로) or NNN 
        // 10 / 5 와 5 / 10 의 결과가 다르기 때문에 연산 결과를 양방향에 적용시켜야한다!
        //N을 네 번 사용 -> number = (N 두 번 사용 ) */+- (N 두 번 사용 ) or NNNN or 세번의 결과에 N 사칙연산 또는 NN 끼리 사칙연산,,,, -> 1번의 연산에 3번의 연산의 경우도 생각해주어야함
        //그러니까 지금 보다 -1 인 hashSet ~ 지금 / 2 인 hashSet
        //N을 다섯 번 사용 -> number = N 네번의 결과에 
        int result = 0;
        int count = 0;
        for (int i = 1; i <= 8; i++) {
            HashSet<Integer> set = new HashSet<>();
            count++;
            
            if (i == 1) {
                result = N;
                set.add(result);
                calculateResult.put(i, set);

                if (result == number) {
                    found = true;

                    break;
                }
                continue;
            }

            set = getNextHashSet(i, number);
            set.add(createContinuousN(i, N));
            
            if (!found) {
                if (set.contains(number)) {
                    found = true;
                    break;
                }
            }
            
            calculateResult.put(i, set);
            
            if (found) {
                break;
            }
        }
        
        if (!found) {
            return -1;
        }
        return count;
    }

    private static int calculate(int n1, int n2, int caseNumber) {
        switch (caseNumber) {
            case 1:
                return n1 + n2;
            case 2:
                return n1 - n2;
            case 3:
                return n1 * n2;
            case 4:
                if (n2 == 0) {
                    return 0;
                }
                return n1 / n2;
            default:
                return 0;
        }
    }
    
    private static HashSet<Integer> getNextHashSet(int nowCount, int number) {
        HashSet<Integer> nowSet = new HashSet<>();
        
        HashSet<Integer> firstSet;
        HashSet<Integer> secondSet;
        
        for (int i = 1; i <= nowCount/2; i++) {
            int matchIdx = nowCount - i;
            firstSet = calculateResult.get(i);
            secondSet = calculateResult.get(matchIdx);
            
            Iterator itr = firstSet.iterator();
            
            int result;
            
            while (itr.hasNext()) {
                int n1 = (int) itr.next();
                Iterator secondItr = secondSet.iterator();
                while (secondItr.hasNext()) {
                    int n2 = (int) secondItr.next();
                    for (int j = 1; j <= 4; j++) {
                        result = calculate(n1, n2, j);
                        if (result != 0 && !nowSet.contains(result)) {
                            nowSet.add(result);
                        }
                        result = calculate(n2, n1, j);
                        if (result != 0 && !nowSet.contains(result)) {
                            nowSet.add(result);
                        }
                        
                        if (result == number) {
                            found = true;
                            return nowSet;
                        }
                    }
                }
            }
        }
        return nowSet;

      
    }

    
    private static int createContinuousN(int nowCount, int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nowCount; i++) {
            sb.append("1");
        }
        
        return Integer.parseInt(sb.toString()) * N ;
    }
}