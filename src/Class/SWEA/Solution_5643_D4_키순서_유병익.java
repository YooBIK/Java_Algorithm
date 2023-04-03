package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5643_D4_키순서_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            int M = Integer.parseInt(bufferedReader.readLine());


            List<Integer>[] isSmall = new ArrayList[N];
            List<Integer>[] isTall = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                isSmall[i] = new ArrayList<>();
                isTall[i] = new ArrayList<>();
            }


            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int less = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int greater = Integer.parseInt(stringTokenizer.nextToken()) - 1;

                isSmall[greater].add(less);
                isTall[less].add(greater);
            }

            int answer = 0;
            for (int i = 0; i < N; i++) {
                boolean[] check = new boolean[N];
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                check[i] = true;
                int count = 0;

                while (!queue.isEmpty()) {
                    int curNode = queue.poll();
                    List<Integer> curList = isSmall[curNode];
                    for (int j = 0; j < curList.size(); j++) {
                        if (!check[curList.get(j)]) {
                            check[curList.get(j)] = true;
                            queue.offer(curList.get(j));
                            count++;
                        }
                    }
                }

                check = new boolean[N];
                queue.offer(i);
                check[i] = true;
                while (!queue.isEmpty()) {
                    int curNode = queue.poll();
                    List<Integer> curList = isTall[curNode];
                    for (int j = 0; j < curList.size(); j++) {
                        if (!check[curList.get(j)]) {
                            check[curList.get(j)] = true;
                            queue.offer(curList.get(j));
                            count++;
                        }
                    }
                }
                if (count == N - 1) {
                    answer++;
                }
            }
            stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.println(stringBuilder);
    }
}

