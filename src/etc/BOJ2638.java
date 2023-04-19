package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int R, C;
    static int[][] map;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {-1, 1, 0, 0};

    static int cheeseCount = 0;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }

        System.out.println(getResult());
    }

    private static int getResult() {

        int time = 0;
        while (cheeseCount > 0) {
            time++;
            removeCheese();
        }

        return time;
    }

    private static void removeCheese() {

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] visit = new int[R][C];
        queue.offer(new int[]{0, 0});
        visit[0][0]++;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = curPos[0] + dirRow[i];
                int newCol = curPos[1] + dirCol[i];

                if (boundaryCheck(newRow, newCol)) {
                    if (map[newRow][newCol] == 1) {
                        visit[newRow][newCol]++;
                    } else {
                        if (visit[newRow][newCol] == 0) {
                            visit[newRow][newCol]++;
                            queue.offer(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1 && visit[i][j] > 1) {
                    map[i][j] = 0;
                    cheeseCount--;
                }
            }
        }
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }
}
