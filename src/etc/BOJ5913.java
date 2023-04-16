package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5913 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {1, -1, 0, 0};

    static int[][] map = new int[5][5];
    static int K;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
       K = Integer.parseInt(bufferedReader.readLine());


        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            map[row][col] = -1;
        }


        dfs(0, 0, 1, new boolean[5][5], 1);


        System.out.println(answer);
    }

    private static void dfs(int row, int col, int depth, boolean[][] visit, int appleCount) {

        visit[row][col] = true;

        if (appleCount == 25-K && ROW) {
            leftDfs(row, col, 1, visit);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5 && map[newRow][newCol] != -1 && !visit[newRow][newCol]) {
                dfs(newRow, newCol, depth + 1, visit);
            }
        }
        visit[row][col] = false;
    }

    private static void leftDfs(int row, int col, int depth, boolean[][] visit) {

        visit[row][col] = true;

        if (row == 4 && col == 4 && depth == appleCount / 2 + 1) {
            answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5 && map[newRow][newCol] != -1 && !visit[newRow][newCol]) {
                leftDfs(newRow, newCol, depth + 1, visit);
            }
        }
        visit[row][col] = false;
    }

}
