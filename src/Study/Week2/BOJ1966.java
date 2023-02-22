package Study.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1966 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 0; testCase < T; testCase++) {

            List<Integer> numberList = new ArrayList<>();
            Queue<int[]> queue = new ArrayDeque<>();

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int target = Integer.parseInt(stringTokenizer.nextToken());

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < N; i++) {
                int curValue = Integer.parseInt(stringTokenizer.nextToken());
                numberList.add(curValue);
                queue.offer(new int[]{i, curValue});
            }
            Collections.sort(numberList);
            int result = 0;
            while (true) {
                if (queue.peek()[1] >= numberList.get(numberList.size() - 1)) {
                    result++;
                    if(queue.peek()[0] == target){
                        break;
                    }
                    queue.poll();
                    numberList.remove(numberList.size() - 1);
                } else {
                    queue.offer(queue.poll());
                }
            }
            stringBuilder.append(result).append("\n");
        }
        System.out.println(stringBuilder);
    }

}
