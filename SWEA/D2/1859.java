package SWEA.D2;

import java.util.Scanner;

class Solution
{
    private static Scanner sc;
	public static void main(String args[]) throws Exception
    {
        // System.setIn(new FileInputStream("input.txt"));

		sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
            System.out.printf("#%d %d\n", test_case, code());
		}
	}

    private static long code() {
        int N = sc.nextInt();

        int[] value = new int[N];

        //뒤에 자기 보다 큰 값이 있으면 거기에 판다 , 근데 뒤에 더 큰 값이 있다면? 그 쪽에 판다!
        //즉 맨 뒤에서부터 비교한다고 보면 될 듯
        //맨 뒤에서 부터 비교 -> 가장 큰 값의 idx를 찾고 

        //1) 맨 뒤에서 부터 가장 큰 값의 idx -> max
        //2) 0 ~ max -1 까지 하나씩 사서max에 판다
        //3) 다시 max + 1 ~ 끝까지 중에서 가장 큰 값 찾음 -> 
        //4) max + 1 ~ 그 값까지 사서 max 에 판다
        //찾기 시작하는 인덱스가 끝일 때 까지 반복

        for (int i = 0; i < N; i++) {
            value[i] = sc.nextInt();
        }

        int maxIdx = -1;
        int max = -1;
        int prevIdx = 0;
        long sum = 0;
        while (prevIdx < N) {
            max = -1;
            maxIdx = -1;
            for (int i = N - 1; i >= prevIdx; i--) {
                if (value[i] > max) {
                    maxIdx = i;
                    max = value[i];
                }
            }

            if (maxIdx == 0) {
                break;
            }

            for (int i = prevIdx; i <= maxIdx - 1; i++) {
                sum += max - value[i]; 
            }

            prevIdx = maxIdx + 1;
        }

        return sum;
    }
}
