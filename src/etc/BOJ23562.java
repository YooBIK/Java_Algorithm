package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23562 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int R, C;
    static int whiteToBlack, blackToWhite;

    static char[][] map;

    public static void main(String[] args) throws IOException {

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        whiteToBlack = Integer.parseInt(stringTokenizer.nextToken());
        blackToWhite = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }
        int answer = Integer.MAX_VALUE;
        for (int k = Math.min(R, C) / 3; k >= 1; k--) {
            int size = k * 3;
            for (int i = 0; i <= R - size; i++) {
                for (int j = 0; j <= C - size; j++) {
                    answer = Math.min(answer, calculate(i, j, size));
                }
            }
        }
        System.out.println(answer);
    }

    public static int calculate(int startRow, int startCol, int size) {
        int cost = 0;
        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                if (i >= startRow + (size / 3) && i < startRow + 2 * (size / 3) && j >= startCol + (size / 3) && j < startCol + size) {
                    if (map[i][j] == '#') {
                        cost += blackToWhite;
                    }
                } else {
                    if (map[i][j] == '.') {
                        cost += whiteToBlack;
                    }
                }
            }
        }

        for (int i = 0; i < startRow; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '#') {
                    cost += blackToWhite;
                }
            }
        }

        for (int i = startRow + size; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '#') {
                    cost += blackToWhite;
                }
            }
        }

        for (int i = 0; i < startCol; i++) {
            for (int j = startRow; j < startRow+size; j++) {
                if (map[j][i] == '#') {
                    cost += blackToWhite;
                }
            }
        }

        for (int i = startCol + size; i < C; i++) {
            for (int j = startRow; j < startRow+size; j++) {
                if (map[j][i] == '#') {
                    cost += blackToWhite;
                }
            }
        }
        return cost;
    }


}
