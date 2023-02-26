package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * #풀이 방법
 * - 비가 오는 높이를 '1 ~ 지역 최대 높이' 까지 증가시키면서 각 경우마다 bfs를 한다.
 * - bfs를 하면서 영역의 개수를 Count
 * - 영역 개수를 최대값으로 갱신
 */
public class BOJ2468 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N;
    static int MAX_HEIGHT = 0;
    static int[][] map;
    static boolean[][] visit;

    static int[] dirRow = {0, 0, 1, -1};
    static int[] dirCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                MAX_HEIGHT = Math.max(MAX_HEIGHT, map[i][j]);
            }
        }

        int result = 1;                         // 하나도 안잠겼다면 1이니까 1보다 작은 경우는 없음
        for (int i = 1; i < MAX_HEIGHT; i++) {
            result = Math.max(result, getAreas(i));
        }
        System.out.println(result);


    }

    // 지도를 전부 보면서, 잠기지 않았으면서, 방문한적 없으면 bfs 실행
    private static int getAreas(int height) {
        int areaCount = 0;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > height && !visit[i][j]) {
                    areaCount++;
                    bfs(i, j, height);
                }
            }
        }

        return areaCount;
    }

    private static void bfs(int startRow, int startCol, int height) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});
        visit[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];

            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dirRow[i];
                int newCol = curCol + dirCol[i];
                if (check(newRow, newCol) && map[newRow][newCol] > height) {
                    queue.offer(new int[]{newRow, newCol});
                    visit[newRow][newCol] = true;
                }
            }
        }
    }

    private static boolean check(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N && !visit[row][col];
    }
}
