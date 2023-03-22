package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9011 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int N;
    static int count = 0;
    static List<Integer> numbers;
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            N = Integer.parseInt(bufferedReader.readLine());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            numbers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                numbers.add(Integer.parseInt(stringTokenizer.nextToken()));
            }

            isUsed = new boolean[N + 1];

            ArrayDeque<Integer> result = new ArrayDeque<>();
            boolean flag = false;
            for (int i = N - 1; i >= 0; i--) {
                int curNum = numbers.get(i);
                if (curNum > i) {
                    stringBuilder.append("IMPOSSIBLE").append("\n");
                    flag = true;
                    break;
                }

                for (int j = N; j >= 1 ; j--) {
                    if (!isUsed[j]) {
                        int count = 0;
                        for (int k = 1; k <= N; k++) {
                            if (!isUsed[k] && k < j) {
                                count++;
                            }
                        }
                        if (count == curNum) {
                            isUsed[j] = true;
                            result.addFirst(j);
                            break;
                        }
                    }
                }
            }
            if (flag) {
                continue;
            }

            while (!result.isEmpty()) {
                stringBuilder.append(result.pollFirst()).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
