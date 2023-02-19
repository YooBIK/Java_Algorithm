package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7662 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            int K = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < K; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                String command = stringTokenizer.nextToken();
                int number = Integer.parseInt(stringTokenizer.nextToken());

                if (command.equals("I")) {
                    treeMap.put(number, treeMap.getOrDefault(number, 0) + 1);

                } else if (command.equals("D")) {
                    if (treeMap.isEmpty()) continue;

                    if (number == -1) {
                        int firstKey = treeMap.firstKey();
                        if (treeMap.get(firstKey) == 1) {
                            treeMap.remove(firstKey);
                        } else {
                            treeMap.put(firstKey, treeMap.get(firstKey) - 1);
                        }
                    }

                    if (number == 1) {
                        int lastKey = treeMap.lastKey();
                        if (treeMap.get(lastKey) == 1) {
                            treeMap.remove(lastKey);
                        } else {
                            treeMap.put(lastKey, treeMap.get(lastKey) - 1);
                        }
                    }
                }
            }
            if (treeMap.isEmpty()) {
                stringBuilder.append("EMPTY").append("\n");
            } else {
                int maxValue = treeMap.lastKey();
                int minValue = treeMap.firstKey();
                stringBuilder.append(maxValue).append(" ").append(minValue).append("\n");

            }
        }
        System.out.println(stringBuilder);
    }
}
