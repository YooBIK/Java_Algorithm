package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 방법
 * - 단순히 bfs를 돌면 되는 문제 , 조금 특이하게 8방향 탐색을 해야한다.
 * - 탐색하지 않은 땅(1)을 찾을 때 마다 섬의 개수를 증가
 *      -> 이후 bfs를 진행 (이어져 있는 부분은 전부 방문처리)
 */
public class BOJ4963 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int[][] map;
    static boolean[][] visit;

    static int W, H;

    static int[] dirRow = {-1, -1, -1, 0, 0, 1, 1, 1,};
    static int[] dirCol = {1, 0, -1, 1, -1, 1, 0, -1};


    public static void main(String[] args) throws IOException {

        while (true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            W = Integer.parseInt(stringTokenizer.nextToken());
            H = Integer.parseInt(stringTokenizer.nextToken());
            if (W == 0 && H == 0) {
                break;
            }

            int result = 0;

            map = new int[H][W];
            visit = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        result++;
                        bfs(map, visit, i, j);
                    }
                }
            }

            stringBuilder.append(result).append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void bfs(int[][] map, boolean[][] visit, int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});
        visit[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];

            for (int i = 0; i < 8; i++) {
                int newRow = curRow + dirRow[i];
                int newCol = curCol + dirCol[i];

                if (newRow >= 0 && newRow < H && newCol >= 0 && newCol < W && map[newRow][newCol] == 1 && !visit[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visit[newRow][newCol] = true;
                }
            }

        }

    }

}
