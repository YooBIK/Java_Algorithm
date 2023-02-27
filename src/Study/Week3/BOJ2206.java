package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * # 조건
 * - 입력 지도의 좌상단에서 우하단으로 이동
 * - 인접한 칸으로만 이동 가능
 * - 1로 표시된 칸으로만 이동 가능
 * <p>
 * # 해결 로직 - BFS를 수행할 때, 좌표값과 함께 벽을 부술 수 있는지 함께 저장한다. - 벽을 만났을 때, 벽을 부술 수 있다면
 * 부수고 전진해본다. - 이를 반복하면서 최단 경로를 출력한다.
 */
public class BOJ2206 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int[] dirRow = {1, 0, -1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    static int R, C;
    static char[][] map;
    static int[][][] visit;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[R][C];
        visit = new int[R][C][2];

        for (int i = 0; i < R; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        bfs(0, 0);
        
        System.out.println(result);
    }

    private static void bfs(int startRow, int startCol) {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol, 1});
        visit[startRow][startCol][1]++;
        visit[startRow][startCol][0]++;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];
            int isPossible = curPos[2];

            if (curRow == R - 1 && curCol == C - 1) {
                result = visit[curRow][curCol][isPossible];
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dirRow[i];
                int newCol = curCol + dirCol[i];
                if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C) {
                    if (map[newRow][newCol] == '1') {     // 벽일 때,
                        if (isPossible == 1) {             // 부술 수 있으면
                            queue.offer(new int[]{newRow, newCol, 0});  // 부쉈음을 표시
                            visit[newRow][newCol][0] = visit[curRow][curCol][isPossible] + 1; // 부수고 진행하는 bfs에 부수기 전 값 +1
                        }
                    } else {                          //벽이 아닐 때,
                        if (visit[newRow][newCol][isPossible] == 0) { // 방문한 적 없다면,
                            queue.offer(new int[]{newRow, newCol, isPossible}); // 현재 상태 + 새로운 좌표 큐에 push
                            visit[newRow][newCol][isPossible] = visit[curRow][curCol][isPossible] + 1;  // 값 업데이트
                        }
                    }

                }
            }

        }


    }

}
