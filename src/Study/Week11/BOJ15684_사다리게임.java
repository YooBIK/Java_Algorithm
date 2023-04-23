package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        map = new boolean[H + 2][N * 2 - 1];

        for (int i = 0; i < 2 * N - 1; i += 2) {
            for (int j = 0; j <= H + 1; j++) {
                map[j][i] = true;
            }
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int row = (Integer.parseInt(stringTokenizer.nextToken()));
            int col = (Integer.parseInt(stringTokenizer.nextToken()) - 1) * 2 + 1;
            map[row][col] = true;
        }

        List<int[]> positionList = new ArrayList<>();
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < 2 * N - 1; j += 2) {
                if (!map[i][j]) {
                    positionList.add(new int[]{i, j});
                }
            }
        }

        int answer = -1;
        for (int i = 0; i <= 3; i++) {
            if (combination(0, 0, i, positionList)) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);

    }

    private static boolean combination(int startIndex, int depth, int targetDepth, List<int[]> positionList) {

        if (targetDepth == depth) {
            return play();
        }


        for (int i = startIndex; i < positionList.size(); i++) {
            int[] curPosition = positionList.get(i);
            if (boundaryCheck(curPosition[0], curPosition[1] - 2) && map[curPosition[0]][curPosition[1] - 2]) {
                continue;
            }
            if (boundaryCheck(curPosition[0], curPosition[1] + 2) && map[curPosition[0]][curPosition[1] + 2]) {
                continue;
            }

            map[curPosition[0]][curPosition[1]] = true;
            if (combination(i + 1, depth + 1, targetDepth, positionList)) {
                return true;
            }
            map[curPosition[0]][curPosition[1]] = false;
        }
        return false;
    }

    private static boolean play() {
        for (int i = 0; i < N * 2 - 1; i += 2) {
            if (!bfs(i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean bfs(int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, col});

        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();

            if (curInfo[0] == H + 1) {
                return curInfo[1] == col;
            }

            boolean flag = false;
            for (int i = 0; i < 2; i++) {
                int newRow = curInfo[0] + dirRow[i];
                int newCol = curInfo[1] + dirCol[i];

                if (boundaryCheck(newRow, newCol) && map[newRow][newCol]) {
                    queue.offer(new int[]{newRow + 1, newCol + dirCol[i]});
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                queue.offer(new int[]{curInfo[0] + 1, curInfo[1]});
            }
        }
        return false;
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row <= H + 1 && col >= 0 && col < N * 2 - 1;
    }
}
