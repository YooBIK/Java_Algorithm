package Study.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6603_로또 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int K;
    static int[] numbers;
    static int[] result = new int[6];


    private static void combination(int depth, int start) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                stringBuilder.append(result[i]).append(" ");
            }
            stringBuilder.append("\n");
            return;
        }

        for (int i = start; i < K; i++) {
            result[depth] = numbers[i];
            combination(depth + 1, i + 1);
        }
    }


    public static void main(String[] args) throws IOException {
        while (true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            K = Integer.parseInt(stringTokenizer.nextToken());
            if (K == 0) break;
            numbers = new int[K];
            for (int i = 0; i < K; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            combination(0, 0);
            stringBuilder.append("\n");

        }
        System.out.println(stringBuilder);
    }
}
