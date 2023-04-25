package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ12100_2048_Easy {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int[] dirRow = {0, -1, 0, 1};
    static int[] dirCol = {-1, 0, 1, 0};

    static int N;
    static int[][] map;

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }


        playGame(0, new ArrayList<>());
        System.out.println(answer);

    }


    private static void playGame(int depth, List<Integer> directionList) {
        if (depth == 5) {
            int result = getMaxValue(getFinalStatus(directionList));
            answer = Math.max(answer, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            directionList.add(i);
            playGame(depth + 1, directionList);
            directionList.remove(directionList.size() - 1);
        }
    }

    private static int[][] getFinalStatus(List<Integer> directionList) {
        int[][] tempMap = copyMap(map);
        for (int direction : directionList) {
            moveCells(direction, tempMap);
        }
        return tempMap;
    }

    private static void moveCells(int direction, int[][] tempMap) {

        boolean[][] check = new boolean[N][N];

        switch (direction) {
            case 0:
                for (int row = 0; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        if (tempMap[row][col] > 0) {
                            moveCell(row, col, direction, tempMap, check);
                        }
                    }
                }
                break;
            case 1:
                for (int col = 0; col < N; col++) {
                    for (int row = 0; row < N; row++) {
                        if (tempMap[row][col] > 0) {
                            moveCell(row, col, direction, tempMap, check);
                        }
                    }
                }
                break;
            case 2:
                for (int row = 0; row < N; row++) {
                    for (int col = N - 1; col >= 0; col--) {
                        if (tempMap[row][col] > 0) {
                            moveCell(row, col, direction, tempMap, check);
                        }
                    }
                }
                break;
            case 3:
                for (int col = 0; col < N; col++) {
                    for (int row = N - 1; row >= 0; row--) {
                        if (tempMap[row][col] > 0) {
                            moveCell(row, col, direction, tempMap, check);
                        }
                    }
                }
                break;

        }
    }

    private static void moveCell(int row, int col, int direction, int[][] tempMap, boolean[][] check) {
        int curRow = row;
        int curCol = col;

        while (true) {

            int nextRow = curRow + dirRow[direction];
            int nextCol = curCol + dirCol[direction];

            if (!boundaryCheck(nextRow, nextCol)) break;

            if (tempMap[nextRow][nextCol] == 0) {
                tempMap[nextRow][nextCol] = tempMap[curRow][curCol];
                tempMap[curRow][curCol] = 0;
                curRow = nextRow;
                curCol = nextCol;
                continue;
            }

            if (check[nextRow][nextCol]) {
                break;
            } else {
                if (tempMap[nextRow][nextCol] == tempMap[curRow][curCol]) {
                    tempMap[nextRow][nextCol] *= 2;
                    tempMap[curRow][curCol] = 0;
                    check[nextRow][nextCol] = true;
                }
                break;
            }
        }

    }


    private static void printMap(int[][] tempMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tempMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] copyMap(int[][] targetMap) {
        int[][] nextMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nextMap[i][j] = targetMap[i][j];
            }
        }
        return nextMap;
    }

    private static int getMaxValue(int[][] tempMap) {
        int curMaxValue = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                curMaxValue = Math.max(curMaxValue, tempMap[i][j]);
            }
        }
        return curMaxValue;
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

}
