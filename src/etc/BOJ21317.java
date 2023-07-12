package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21317 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, K;
    static List<Integer> jumpList = new ArrayList<>();
    static List<Integer> bigJumpList = new ArrayList<>();

    static int[][] resultArray;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(bufferedReader.readLine());

        resultArray = new int[2][N];
        Arrays.fill(resultArray[0], 987654321);
        Arrays.fill(resultArray[1], 987654321);

        for (int i = 0; i < N - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            jumpList.add(Integer.parseInt(stringTokenizer.nextToken()));
            bigJumpList.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        K = Integer.parseInt(bufferedReader.readLine());

        resultArray[0][0] = 0;

        for (int i = 1; i < N; i++) {
            resultArray[0][i] = Math.min(resultArray[0][i], resultArray[0][i - 1] + jumpList.get(i - 1));
            if (i > 1) {
                resultArray[0][i] = Math.min(resultArray[0][i], resultArray[0][i - 2] + bigJumpList.get(i - 2));
            }

            resultArray[1][i] = Math.min(resultArray[1][i], resultArray[1][i - 1] + jumpList.get(i - 1));
            if (i > 1) {
                resultArray[1][i] = Math.min(resultArray[1][i], resultArray[1][i - 2] + bigJumpList.get(i - 2));
            }
            if (i > 2) {
                resultArray[1][i] = Math.min(resultArray[1][i], resultArray[0][i - 3] + K);
            }
        }
        System.out.println(Math.min(resultArray[0][N-1], resultArray[1][N-1]));
    }
}
