package Study.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992_쿼드트리 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();

    static char[][] map;

    private static int check(int depth, int row, int col) {
        char start = map[row][col];
        for (int i = row; i < row + depth; i++) {
            for (int j = col; j < col + depth; j++) {
                if (map[i][j] != start) return -1;
            }
        }
        return start - '0';
    }

    public static void quadTree(int depth, int row, int col) {
        int checkResult = check(depth, row, col);
        if (checkResult != -1) {
            stringBuilder.append(checkResult);
            return;
        }

        stringBuilder.append("(");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                quadTree(depth / 2, row + i * (depth / 2), col + j * (depth / 2));
            }
        }
        stringBuilder.append(")");
    }


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] chars = bufferedReader.readLine().toCharArray();
            map[i] = chars;
        }
        quadTree(N, 0, 0);
        System.out.println(stringBuilder);
    }
}
