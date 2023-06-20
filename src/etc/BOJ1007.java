package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1007 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int N;
    static List<int[]> positionList;
    static boolean[] isUsed;

    static double result;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(bufferedReader.readLine());
            positionList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                positionList.add(new int[]{Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())});
            }


            result = Double.MAX_VALUE;
            isUsed = new boolean[N];
            combination(0, 0);
            stringBuilder.append(result);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void combination(int startIndex, int depth) {
        if (depth == N / 2) {
            result = Math.min(result, getVectorSize());
        }

        for (int i = startIndex; i < N; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                combination(i + 1, depth + 1);
                isUsed[i] = false;
            }
        }
    }


    private static double getVectorSize() {
        int xValue = 0;
        int yValue = 0;
        for (int i = 0; i < N; i++) {
            if (isUsed[i]) {
                xValue += positionList.get(i)[0];
                yValue += positionList.get(i)[1];
            } else {
                xValue -= positionList.get(i)[0];
                yValue -= positionList.get(i)[1];
            }
        }
        return Math.sqrt(Math.pow(xValue, 2) + Math.pow(yValue, 2));
    }


}
