package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2022_D3_상호의배틀필드_유병익 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int R, C;
    static char[][] map;
    static int[] dirRow = {-1, 1, 0, 0};
    static int[] dirCol = {0, 0, -1, 1};
    static char[] position = {'^', 'v', '<', '>'};
    static int curRow, curCol, curDir;
    static String commands;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            R = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            map = new char[R][C];


            for (int i = 0; i < R; i++) {
                map[i] = bufferedReader.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        curRow = i;
                        curCol = j;
                        switch (map[i][j]) {
                            case '^':
                                curDir = 0;
                                break;
                            case 'v':
                                curDir = 1;
                                break;
                            case '<':
                                curDir = 2;
                                break;
                            case '>':
                                curDir = 3;
                                break;
                        }
                    }
                }
            }
            bufferedReader.readLine();
            commands = bufferedReader.readLine();
            playGame();
            stringBuilder.append("#").append(testCase).append(" ");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    stringBuilder.append(map[i][j]);
                }
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder);
    }

    public static void playGame() {
        for (int i = 0; i < commands.length(); i++) {
            if (commands.charAt(i) == 'S') {
                shoot(curRow, curCol, curDir);
            } else {
                switch (commands.charAt(i)) {
                    case 'U':
                        curDir = 0;
                        move();
                        break;
                    case 'D':
                        curDir = 1;
                        move();
                        break;
                    case 'L':
                        curDir = 2;
                        move();
                        break;
                    case 'R':
                        curDir = 3;
                        move();
                        break;
                }
            }
        }
    }

    private static void move() {
        map[curRow][curCol] = position[curDir];
        int newRow = curRow + dirRow[curDir];
        int newCol = curCol + dirCol[curDir];

        if (newRow < 0 || newRow >= R || newCol < 0 || newCol >= C) {
            return;
        }

        if (map[newRow][newCol] == '.') {
            map[curRow][curCol] = '.';
            map[newRow][newCol] = position[curDir];
            curRow = newRow;
            curCol = newCol;
        }
    }

    public static void shoot(int row, int col, int dir) {

        if (row < 0 || row >= R || col < 0 || col >= C || map[row][col] == '#') {
            return;
        }

        if (map[row][col] == '*') {
            map[row][col] = '.';
            return;
        }

        shoot(row + dirRow[dir], col + dirCol[dir], dir);
    }

}
