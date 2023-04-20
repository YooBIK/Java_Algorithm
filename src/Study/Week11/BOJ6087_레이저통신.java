package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6087_레이저통신 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int R, C;
    static char[][] map;

    static int[] dirRow = {1, -1, 0, 0};
    static int[] dirCol = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        C = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[R][C];

        List<int[]> CPosition = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'C') {
                    CPosition.add(new int[]{i, j});
                }
            }
        }

        int[] startPosition = CPosition.get(0);
        int[][][] rotateMap = new int[R][C][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    rotateMap[j][k][i] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            rotateMap[startPosition[0]][startPosition[1]][i] = 0;
        }


        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            int newRow = startPosition[0] + dirRow[i];
            int newCol = startPosition[1] + dirCol[i];
            if (boundaryCheck(newRow, newCol) && map[newRow][newCol] != '*') {
                queue.offer(new int[]{newRow, newCol, i});
                rotateMap[newRow][newCol][i] = 0;
            }
        }

        while (!queue.isEmpty()) {
            int[] curInfo = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newRow = curInfo[0] + dirRow[i];
                int newCol = curInfo[1] + dirCol[i];
                if (boundaryCheck(newRow, newCol) && map[newRow][newCol] != '*') {
                    int curRotate = rotateMap[curInfo[0]][curInfo[1]][curInfo[2]];
                    if (curInfo[2] != i) {
                        curRotate++;
                    }

                    if (rotateMap[newRow][newCol][i] > curRotate) {
                        rotateMap[newRow][newCol][i] = curRotate;
                        queue.offer(new int[]{newRow, newCol, i});
                    }


                }
            }

        }

//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                if (rotateMap[i][j] == Integer.MAX_VALUE) {
//                    System.out.print("X ");
//                } else {
//                    System.out.print(rotateMap[i][j] + " ");
//                }
//            }
//            System.out.println();
//        }


        int[] endPosition = CPosition.get(1);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            result = Math.min(result, rotateMap[endPosition[0]][endPosition[1]][i]);
        }
        System.out.println(result);
    }


    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }
}