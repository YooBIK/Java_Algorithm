package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1005 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int N, K;
    static int[] buildTimeArray;
    static List<Integer>[] parentListArray;

    static int[] childNumberArray;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());

            buildTimeArray = new int[N];
            childNumberArray = new int[N];
            parentListArray = new ArrayList[N];

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < N; i++) {
                buildTimeArray[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 0; i < K; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int child = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int parent = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                if (parentListArray[child] == null) {
                    parentListArray[child] = new ArrayList<>();
                }
                childNumberArray[parent]++;
                parentListArray[child].add(parent);
            }

            dp = new int[N];

            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                if (childNumberArray[i] == 0) {
                    dp[i] = buildTimeArray[i];
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int curChild = queue.poll();
                if (parentListArray[curChild] != null) {
                    List<Integer> curParentList = parentListArray[curChild];
                    for (int curParent : curParentList) {
                        dp[curParent] = Math.max(dp[curParent], dp[curChild] + buildTimeArray[curParent]);
                        childNumberArray[curParent]--;

                        if(childNumberArray[curParent] == 0){
                            queue.offer(curParent);
                        }
                    }
                }
            }

            int target = Integer.parseInt(bufferedReader.readLine()) - 1;
            stringBuilder.append(dp[target]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
