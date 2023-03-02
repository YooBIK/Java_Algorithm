package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5656_벽돌깨기_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int[][] map;
    static int N;
    static int W, H;

    static int[] dirRow = {0, 0, 1, -1};
    static int[] dirCol = {1, -1, 0, 0};

    static int blocks = 0;
    static int result = 0;
    static int answer = Integer.MIN_VALUE;

    static List<Integer> orderList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            W = Integer.parseInt(stringTokenizer.nextToken());
            H = Integer.parseInt(stringTokenizer.nextToken());

            blocks = 0;
            answer = Integer.MIN_VALUE;

            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if(map[i][j]!=0){
                        blocks++;
                    }
                }
            }

            permutation(0);
            stringBuilder.append("#").append(testCase).append(" ").append(blocks-answer).append("\n");
        }
        System.out.println(stringBuilder);



    }

    public static void permutation(int depth) {
        if (depth == N) {
            result = 0;
            down(copyMap());
            answer = Math.max(answer, result);
            return;
        }

        for (int i = 0; i < W; i++) {
            orderList.add(i);
            permutation(depth + 1);
            orderList.remove(orderList.size() - 1);
        }
    }

    private static void down(int[][] copyMap) {
        for (int i = 0; i < orderList.size(); i++) {
            int curCol = orderList.get(i);
            for (int j = 0; j < H; j++) {
                if (copyMap[j][curCol] != 0) {
                    remove(j, curCol, copyMap, 1, -1, copyMap[j][curCol]);
                    cleanUp(copyMap);
                    break;
                }
            }
        }
    }

    private static void cleanUp(int[][] copyMap) {
        for (int i = 0; i < W; i++) {
            ArrayDeque<Integer> numQueue = new ArrayDeque<>();
            for (int j = 0; j < H; j++) {
                if (copyMap[j][i] == 0) {
                    numQueue.addFirst(copyMap[j][i]);
                } else {
                    numQueue.addLast(copyMap[j][i]);
                }
            }
            for (int j = 0; j < H; j++) {
                copyMap[j][i] = numQueue.pollFirst();
            }
        }
    }

    private static void remove(int curRow, int curCol, int[][] copyMap, int curDepth, int dir, int targetDepth) {
        if (copyMap[curRow][curCol] != 0) {
            copyMap[curRow][curCol] = 0;
            result++;
        }


        if (curDepth >= targetDepth) {
            return;
        }

        if (dir == -1) {
            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dirRow[i];
                int newCol = curCol + dirCol[i];
                if (newRow >= 0 && newRow < H && newCol >= 0 && newCol < W) {
                    if (map[newRow][newCol] != 0) {
                        remove(newRow, newCol, copyMap, 1, -1, copyMap[newRow][newCol]);
                    }
                    remove(newRow, newCol, copyMap, curDepth + 1, i, targetDepth);
                }
            }
        } else {
            int newRow = curRow + dirRow[dir];
            int newCol = curCol + dirCol[dir];
            if (newRow >= 0 && newRow < H && newCol >= 0 && newCol < W) {
                if (map[newRow][newCol] != 0) {
                    remove(newRow, newCol, copyMap, 1, -1, copyMap[newRow][newCol]);
                }
                remove(newRow, newCol, copyMap, curDepth + 1, dir, targetDepth);
            }
        }
    }


    public static int[][] copyMap() {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }

}
