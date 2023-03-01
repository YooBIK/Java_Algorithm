package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_D4_Contact_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();


    static boolean[][] contact;
    static int[] visit;

    public static void main(String[] args) throws IOException {

        for (int testCase = 1; testCase <= 10; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int commands = Integer.parseInt(stringTokenizer.nextToken());
            int start = Integer.parseInt(stringTokenizer.nextToken());

            contact = new boolean[101][101];
            visit = new int[101];

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < commands / 2; i++) {
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                contact[from][to] = true;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            visit[start] = 1;
            int maxCount = -1;

            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                maxCount = Math.max(maxCount, visit[curNode]);

                for (int i = 1; i <= 100; i++) {
                    if (contact[curNode][i] && visit[i] == 0) {
                        queue.offer(i);
                        visit[i] = visit[curNode] + 1;
                    }
                }
            }
            int result = -1;
            for (int i = 100; i >= 1; i--) {
                if (visit[i] == maxCount) {
                    result = Math.max(result, i);
                }
            }
            stringBuilder.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(stringBuilder);

    }

}
