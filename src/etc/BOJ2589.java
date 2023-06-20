package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2589 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int answer = 0;
    static int R, C;
    static char[][] map;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {-1, 1, 0, 0};

    static List<int[]> landList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    landList.add(new int[]{i, j});
                }
            }
        }

        for (int[] position : landList) {
            bfs(position[0], position[1]);
        }
        System.out.println(answer);
    }

    public static void bfs(int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[R][C];
        visit[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol, 0});

        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();

            int curRow = curInfo[0];
            int curCol = curInfo[1];
            int curDist = curInfo[2];

            answer = Math.max(answer, curDist);

            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dirRow[i];
                int newCol = curCol + dirCol[i];
                if (isValid(newRow, newCol) && !visit[newRow][newCol]) {
                    visit[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, curDist + 1});
                }
            }
        }
    }

    public static boolean isValid(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C && map[row][col] =='L';
    }
}
