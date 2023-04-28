package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18428 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;


    static boolean answer = false;
    static int N;
    static char[][] map;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {-1, 1, 0, 0};

    static List<int[]> teacherList = new ArrayList<>();
    static List<int[]> emptyList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stringTokenizer.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teacherList.add(new int[]{i, j});
                }

                if (map[i][j] == 'X') {
                    emptyList.add(new int[]{i, j});
                }
            }
        }

        combination(0, 0);
        if(answer){
            System.out.println("YES");
        } else{
            System.out.println("NO");
        }
    }

    private static void combination(int depth, int startIndex) {

        if (depth == 3) {
            if(search() == 0){
                answer = true;
            }
            return;
        }

        for (int i = startIndex; i < emptyList.size(); i++) {
            int[] curPos = emptyList.get(i);
            map[curPos[0]][curPos[1]] = 'O';
            combination(depth + 1, i + 1);
            map[curPos[0]][curPos[1]] = 'X';
        }
    }

    private static int search() {

        int count = 0;
        for (int i = 0; i < teacherList.size(); i++) {
            int[] teacherPosition = teacherList.get(i);

            for (int j = 0; j < 4; j++) {
                count += searchByDirection(teacherPosition, j);
            }
        }
        return count;
    }

    private static int searchByDirection(int[] teacherPosition, int direciton) {

        int newRow = teacherPosition[0] + dirRow[direciton];
        int newCol = teacherPosition[1] + dirCol[direciton];

        int count = 0;

        while (boundaryCheck(newRow, newCol) && map[newRow][newCol] != 'O') {
            if (map[newRow][newCol] == 'S') {
                count++;
            }
            newRow += dirRow[direciton];
            newCol += dirCol[direciton];
        }
        return count;
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }


}
