package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2629 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();


    static int N;
    static List<Integer> weightList = new ArrayList<>();
    static List<Integer> beadsList = new ArrayList<>();

    static boolean[] dp = new boolean[40001];


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            weightList.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(stringTokenizer.nextToken());
            beadsList.add(target);
        }

        for (int i = 0; i < weightList.size(); i++) {
            int curWeight = weightList.get(i);
            List<Integer> curTrue = new ArrayList<>();
            for (int j = 40000; j >= 1; j--) {
                if (dp[j]) {
                    curTrue.add(j);
                }
            }
            for (int curTrueWeight : curTrue) {
                if (curTrueWeight + curWeight <= 40000) {
                    dp[curTrueWeight + curWeight] = true;
                }
                dp[Math.abs(curTrueWeight - curWeight)] = true;
            }
            dp[curWeight] = true;
        }


        for (int i = 0; i < beadsList.size(); i++) {
            if (dp[beadsList.get(i)]) {
                stringBuilder.append("Y ");
            } else {
                stringBuilder.append("N ");
            }
        }
        System.out.println(stringBuilder);

    }
}
