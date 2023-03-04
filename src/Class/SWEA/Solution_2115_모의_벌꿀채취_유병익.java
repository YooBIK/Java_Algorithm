package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2115_모의_벌꿀채취_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int N, M, C;
    static int[][] map;

    static int selectMaxValue = Integer.MIN_VALUE;
    static int answer = Integer.MIN_VALUE;

    static List<Integer> selectedRow = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            answer = Integer.MIN_VALUE;
            select(0, 0);

            stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static void select(int depth, int startIndex) {

        if (depth == 2) {
            int firstStartPosition = selectedRow.get(0);
            int secondStartPosition = selectedRow.get(1);

            if (canCollect(firstStartPosition, secondStartPosition)) {
                int result = 0;
                result += collect(firstStartPosition);
                result += collect(secondStartPosition);
                answer = Math.max(answer, result);
            }
            return;
        }

        for (int i = startIndex; i < N * N; i++) {
            selectedRow.add(i);
            select(depth + 1, i + 1);
            selectedRow.remove(selectedRow.size() - 1);
        }

    }

    private static boolean canCollect(int first, int second) {
        int firstRow = first / N;
        int firstCol = first % N;
        int secondRow = second / N;
        int secondCol = second % N;

        if (firstCol + M - 1 < N && secondCol + M - 1 < N) {
            if (firstRow != secondRow) {
                return true;
            } else {
                if (firstCol + M - 1 < secondCol) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private static int collect(int startPosition) {

        int startRow = startPosition / N;
        int startCol = startPosition % N;
        List<Integer> list = new ArrayList<>();

        int sum = 0;
        for (int i = startCol; i <= startCol + M - 1; i++) {
            sum += map[startRow][i];
            list.add(map[startRow][i]);
        }
        if (sum > C) {
            selectMaxValue = Integer.MIN_VALUE;
            for (int i = list.size() - 1; i >= 1; i--) {
                carry(0, 0, i, list, 0, 0);
            }
            return selectMaxValue;
        } else {
            int result = 0;
            for (Integer i : list) {
                result += Math.pow(i, 2);
            }
            return result;
        }
    }

    private static void carry(int depth, int startIndex, int targetDepth, List<Integer> list, int curValue, int curSum) {

        if (curSum > C) {
            return;
        }

        if (depth == targetDepth) {
            selectMaxValue = Math.max(selectMaxValue, curValue);
            return;
        }

        for (int i = startIndex; i < list.size(); i++) {
            int temp = list.get(i) * list.get(i);
            carry(depth + 1, i + 1, targetDepth, list, curValue + temp, curSum + list.get(i));
        }
    }

}
