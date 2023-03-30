package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2239_G4_스도쿠_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();
    static int[][] map;
    static int zeroCount = 0;
    static boolean flag = false;

    static boolean[][] rowCheck = new boolean[9][10];
    static boolean[][] colCheck = new boolean[9][10];
    static boolean[][] boxCheck = new boolean[9][10];


    public static void main(String[] args) throws IOException {

        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String str = bufferedReader.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j) - '0';

                int curValue = map[i][j];
                if (curValue != 0) {
                    rowCheck[i][curValue] = true;
                    colCheck[j][curValue] = true;
                    boxCheck[(i / 3) * 3 + j / 3][curValue] = true;
                } else {
                    zeroCount++;
                }
            }
        }

        dfs();
    }

    private static void dfs() {

        if(flag){
            return;
        }

        if (zeroCount == 0) {
            flag = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (check(i, j, k)) {
                            rowCheck[i][k] = true;
                            colCheck[j][k] = true;
                            boxCheck[(i / 3) * 3 + j / 3][k] = true;
                            map[i][j] = k;
                            zeroCount--;
                            dfs();
                            zeroCount++;
                            map[i][j] = 0;
                            rowCheck[i][k] = false;
                            colCheck[j][k] = false;
                            boxCheck[(i / 3) * 3 + j / 3][k] = false;
                        }
                    }
                    return;
                }
            }
        }
    }

    private static boolean check(int row, int col, int value) {
        return !rowCheck[row][value] && !colCheck[col][value] && !boxCheck[(row / 3) * 3 + col / 3][value];
    }
}
