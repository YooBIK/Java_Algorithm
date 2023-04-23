package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15683_감시 {

    static class CCTV {
        int row;
        int col;
        int curDir;
        int number;

        public CCTV(int row, int col, int number, int curDir) {
            this.row = row;
            this.col = col;
            this.curDir = curDir;
            this.number = number;
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int R, C;

    static int[][] map;

    // 우 하 좌 상
    static int[] dirRow = {0, 1, 0, -1};
    static int[] dirCol = {1, 0, -1, 0};

    static List<CCTV> CCTVList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) {
                    CCTVList.add(new CCTV(i, j, map[i][j], 0)); // Row, Col, Dir, 종류;
                }
            }
        }

        subSet(0);
        System.out.println(answer);

    }

    private static void subSet(int depth) {
        if (depth == CCTVList.size()) {
            answer = Math.min(answer, getZeroCount());
            //printMap();
            return;
        }

        for (int i = 0; i < 4; i++) {
            CCTVList.get(depth).curDir = i;
            locate(CCTVList.get(depth), -1);
            subSet(depth + 1);
            locate(CCTVList.get(depth), 1);
        }

    }

    private static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int getZeroCount() {
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    private static void locate(CCTV cctv, int value) {

        List<Integer> directionList = new ArrayList<>();
        directionList.add(cctv.curDir);

        switch (cctv.number) {
            case 2:
                directionList.add((cctv.curDir + 2) % 4);
                break;
            case 3:
                directionList.add((cctv.curDir + 3) % 4);
                break;
            case 4:
                directionList.add((cctv.curDir + 2) % 4);
                directionList.add((cctv.curDir + 3) % 4);
                break;
            case 5:
                directionList.add((cctv.curDir + 1) % 4);
                directionList.add((cctv.curDir + 2) % 4);
                directionList.add((cctv.curDir + 3) % 4);
                break;
            default:
                break;
        }

        for (int curDir : directionList) {
            int newRow = cctv.row + dirRow[curDir];
            int newCol = cctv.col + dirCol[curDir];

            while (boundaryCheck(newRow, newCol)) {
                if (map[newRow][newCol] == 6) break;

                if (map[newRow][newCol] > 0 && map[newRow][newCol] < 6) {
                    newRow += dirRow[curDir];
                    newCol += dirCol[curDir];
                    continue;
                }
                map[newRow][newCol] += value;
                newRow += dirRow[curDir];
                newCol += dirCol[curDir];
            }
        }
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }

}
