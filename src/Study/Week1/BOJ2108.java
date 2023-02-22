package Study.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class BOJ2108 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[N];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        double sum = 0;
        for (int i = 0; i < N; i++) {
            int curValue = Integer.parseInt(bufferedReader.readLine());
            numbers[i] = curValue;
            if (!hashMap.containsKey(curValue)) {
                hashMap.put(curValue, 0);
            } else {
                hashMap.put(curValue, hashMap.get(curValue) + 1);
            }
            sum += curValue;
        }
        Arrays.sort(numbers);

        long avg = Math.round(sum / N);
        int mid = numbers[N / 2];

        Set<Entry<Integer, Integer>> es = hashMap.entrySet();
        int maxCount = -1;
        for (Entry<Integer, Integer> e : es) {
            maxCount = Math.max(maxCount, e.getValue());
        }
        List<Integer> list = new ArrayList<>();
        for (Entry<Integer, Integer> e : es) {
            if (e.getValue() == maxCount) {
                list.add(e.getKey());
            }
        }
        Collections.sort(list);
        int thirdValue;
        if (list.size() > 1) {
            thirdValue = list.get(1);
        } else {
            thirdValue = list.get(0);
        }

        int range = numbers[numbers.length - 1] - numbers[0];
        stringBuilder.append(avg).append("\n");
        stringBuilder.append(mid).append("\n");
        stringBuilder.append(thirdValue).append("\n");
        stringBuilder.append(range).append("\n");
        System.out.println(stringBuilder);
    }

}
