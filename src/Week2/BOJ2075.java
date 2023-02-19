package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2075 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(bufferedReader.readLine());
        List<Long> numberList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                numberList.add(Long.parseLong(stringTokenizer.nextToken()));
            }
        }
        Collections.sort(numberList, (o1, o2) -> Long.compare(o2, o1));
        System.out.println(numberList.get(N-1));
    }

}
