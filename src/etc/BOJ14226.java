package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ14226 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] visit = new int[1001];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        int answer = Integer.MAX_VALUE;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0, 0}); // 개수 , 시간
        Arrays.fill(visit, Integer.MAX_VALUE);
        visit[1] = 0;
        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();

            if (curInfo[0] == N) {
                answer = curInfo[2];
                break;
            }


            if (curInfo[1] != 0 && curInfo[0] + curInfo[1] <= 1000 && curInfo[0] + curInfo[1] > 0 && curInfo[2] + 1 < visit[curInfo[0] + curInfo[1]]) {
                visit[curInfo[0] + curInfo[1]] = curInfo[2] + 1;
                queue.offer(new int[]{curInfo[0] + curInfo[1], curInfo[1], curInfo[2] + 1});
            }

            if (curInfo[0] > 0)
                queue.offer(new int[]{curInfo[0], curInfo[0], curInfo[2] + 1});

            if (curInfo[0] > 0 && curInfo[0] - 1 <= 1000 && curInfo[0] - 1 > 0 && curInfo[2] + 1 < visit[curInfo[0] - 1]) {
                visit[curInfo[0] - 1] = curInfo[2] + 1;
                queue.offer(new int[]{curInfo[0] - 1, curInfo[1], curInfo[2] + 1});
            }

        }

        System.out.println(answer);
    }
}
