package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ2665 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static int N, wallCount;

    static char[][] map;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = bufferedReader.readLine();
            map[i] = input.toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '0') wallCount++;
            }

        }

        System.out.println(getResult());

    }

    private static int getResult() {
        boolean[][][] visit = new boolean[N][N][wallCount + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});
        visit[0][0][0] = true;

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();

            if (curInfo[2] > answer) continue;

            if (curInfo[0] == N - 1 && curInfo[1] == N - 1) {
                answer = curInfo[2];
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int newRow = curInfo[0] + dirRow[i];
                int newCol = curInfo[1] + dirCol[i];

                if (boundaryCheck(newRow, newCol)) {
                    if (map[newRow][newCol] == '1') {
                        if (!visit[newRow][newCol][curInfo[2]]) {
                            queue.offer(new int[]{newRow, newCol, curInfo[2]});
                            visit[newRow][newCol][curInfo[2]] = true;
                        }
                    } else {
                        if (curInfo[2] + 1 <= wallCount && !visit[newRow][newCol][curInfo[2] + 1]) {
                            queue.offer(new int[]{newRow, newCol, curInfo[2] + 1});
                            visit[newRow][newCol][curInfo[2] + 1] = true;
                        }
                    }
                }
            }
        }
        return answer;
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

}
