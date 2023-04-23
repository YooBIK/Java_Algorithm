package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15684_사다리게임 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static boolean[][] map;

    static int[] dirRow = {0, 0, 1};
    static int[] dirCol = {-1, 1, 0};

    static int N, M, H;

    public static void main(String[] args) throws IOException {

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());

        map = new boolean[H + 2][N * 2 + 1];

        for (int i = 0; i <= 2 * N; i += 2) {
            for (int j = 0; j <= H + 1; j++) {
                map[j][i] = true;
            }
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int col = (Integer.parseInt(stringTokenizer.nextToken()) - 1) * 2 + 1;
            int row = Integer.parseInt(stringTokenizer.nextToken());
            map[row][col] = true;
        }


        boolean flag = false;
        for (int i = 0; i <= 3; i++) {
            if (dfs(1, 0, i)) {
                System.out.println(i);
                flag = true;

            }
        }
        if (!flag) {
            System.out.println(-1);
        }
    }

    private static boolean dfs(int row, int depth, int targetDepth) {

        if (depth == targetDepth) {
            System.out.println(depth);
            printMap();
            System.out.println(play());
            return play();
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N * 2; j += 2) {
                if (map[i][j]) continue;

                if (boundaryCheck(i, j - 2) && map[i][j - 2]) {
                    continue;
                }
                if (boundaryCheck(i, j + 2) && map[i][j + 2]) {
                    continue;
                }

                map[i][j] = true;
                if (dfs(i,depth + 1, targetDepth)) {
                    return true;
                }
                map[i][j] = false;
            }
        }
        return false;
    }

    private static void printMap() {
        for (int i = 0; i < H + 2; i++) {
            for (int j = 0; j < N * 2 + 1; j++) {
                if (map[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("o ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean play() {
        for (int i = 0; i <= N * 2; i += 2) {
            boolean[][] visit = new boolean[H + 2][N * 2 + 1];
            return bfs(i, visit);
        }
        return false;
    }

    private static boolean bfs(int col, boolean[][] visit) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, col});
        visit[0][col] = true;

        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();
            if (curInfo[0] == H + 1) {
                if (curInfo[1] == col) {
                    return true;
                } else {
                    return false;
                }
            }
            for (int i = 0; i < 3; i++) {
                int newRow = curInfo[0] + dirRow[i];
                int newCol = curInfo[1] + dirCol[i];

                if (boundaryCheck(newRow, newCol) && !visit[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visit[newRow][newCol] = true;
                    break;
                }
            }
        }
        return false;
    }


    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row <= H && col >= 0 && col < N * 2;
    }
}
