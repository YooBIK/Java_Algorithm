package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA_8382_D4_방향전환_유병익 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int[] dirRow = {0, 1, 0, -1};
    static int[] dirCol = {1, 0, -1, 0};

    static int targetRow;
    static int targetCol;

    static int answer;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());


            int startRow = Integer.parseInt(stringTokenizer.nextToken()) + 100;
            int startCol = Integer.parseInt(stringTokenizer.nextToken()) + 100;

            targetRow = Integer.parseInt(stringTokenizer.nextToken()) + 100;
            targetCol = Integer.parseInt(stringTokenizer.nextToken()) + 100;

            answer = Integer.MAX_VALUE;
            boolean[][] visit = new boolean[201][201];
            Queue<int[]> queue = new ArrayDeque<>();
            visit[startRow][startCol] = true;
            queue.offer(new int[]{startRow, startCol, 0, 0});
            bfs(visit, queue);

            visit = new boolean[201][201];
            queue = new ArrayDeque<>();
            visit[startRow][startCol] = true;
            queue.offer(new int[]{startRow, startCol, 1, 0});
            bfs(visit, queue);


            stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void bfs(boolean[][] visit, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();

            int curRow = curPos[0];
            int curCol = curPos[1];
            int beforeDir = curPos[2];
            int curDist = curPos[3];

            if (curRow == targetRow && curCol == targetCol) {
                answer = Math.min(answer, curDist);
                break;
            }

            for (int i = 1; i <= 3; i += 2) {
                int curDir = (beforeDir + i) % 4;
                int newRow = curRow + dirRow[curDir];
                int newCol = curCol + dirCol[curDir];
                if (boundaryCheck(newRow, newCol) && !visit[newRow][newCol]) {
                    visit[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, curDir, curDist + 1});
                }
            }
        }
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row <= 200 && col >= 0 && col <= 200;
    }

}
