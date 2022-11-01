import java.util.Stack;

class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int top = stack.peek();
            int nowNum = arr[i];
            if (top == nowNum) {
                continue;
            }
            
            stack.push(nowNum);
        }

        int[] answer = new int[stack.size()];
        int idx = stack.size() - 1;
        
        while (!stack.isEmpty()) {
            answer[idx--] = stack.pop();
        }    
        return answer;
    }
}