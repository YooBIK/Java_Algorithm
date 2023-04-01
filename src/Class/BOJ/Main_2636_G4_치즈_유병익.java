package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2636_G4_치즈_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();
    static StringTokenizer stringTokenizer;

    static int R, C;
    static int[][] map;
    static boolean[][] isMelted;
    static int cheeseCount = 0;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[R][C];
        isMelted = new boolean[R][C];


        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }

        int curTime = 0;
        int beforeCheeseCount = 0;
        while (cheeseCount > 0) {
            beforeCheeseCount = cheeseCount;

            boolean[][] isVisit = new boolean[R][C];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0});
            isVisit[0][0] = true;

            while (!queue.isEmpty()) {
                int[] curPos = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int newRow = curPos[0] + dirRow[i];
                    int newCol = curPos[1] + dirCol[i];
                    if (boundaryCheck(newRow, newCol) && !isVisit[newRow][newCol]) {
                        if (map[newRow][newCol] == 0) {
                            isVisit[newRow][newCol] = true;
                            queue.offer(new int[]{newRow, newCol});
                        } else {
                            map[newRow][newCol] = 0;
                            isVisit[newRow][newCol] = true;
                            cheeseCount--;
                        }
                    }
                }
            }
            curTime++;
        }


        System.out.println(curTime);
        System.out.println(beforeCheeseCount);
    }

    private static boolean boundaryCheck(int newRow, int newCol) {
        return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C;
    }

}
