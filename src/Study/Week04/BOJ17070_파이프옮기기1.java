package Study.Week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *  - 다음 위치로 이동할 때, 오른쪽이면 열+1, 아래면 행+1, 대각선이면  (열+1, 행+1, 열+1 행+1) 체크
 *  - 범위 안에 있는지, 벽인지 확인하고 가능하면 dfs 진행 이때, 현재 방향 정보를 함께 전달한다.
 */
public class BOJ17070_파이프옮기기1 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        dfs(0, 1, 0);
        System.out.println(answer);
    }

    private static void dfs(int curRow, int curCol, int curDir) {
        if (curRow == N - 1 && curCol == N - 1) {
            answer++;
            return;
        }

        if (checkDiag(curRow, curCol)) {
            dfs(curRow + 1, curCol + 1, 1);
        }
        switch (curDir) {
            case 0:
                if (checkRight(curRow, curCol)) {
                    dfs(curRow, curCol + 1, 0);
                }
                break;
            case 1:
                if (checkRight(curRow, curCol)) {
                    dfs(curRow, curCol + 1, 0);
                }
                if (checkDown(curRow, curCol)) {
                    dfs(curRow + 1, curCol, 2);
                }
                break;
            case 2:
                if (checkDown(curRow, curCol)) {
                    dfs(curRow + 1, curCol, 2);
                }
                break;
        }
    }

    private static boolean checkRight(int curRow, int curCol) {
        return curCol + 1 >= 0 && curCol + 1 < N && map[curRow][curCol + 1] == 0;
    }

    private static boolean checkDiag(int curRow, int curCol) {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int newRow = curRow + i;
                int newCol = curCol + j;
                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N || map[newRow][newCol] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkDown(int curRow, int curCol) {
        return curRow + 1 >= 0 && curRow + 1 < N && map[curRow + 1][curCol] == 0;
    }
}