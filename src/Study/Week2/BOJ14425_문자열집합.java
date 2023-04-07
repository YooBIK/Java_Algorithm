package Study.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14425_문자열집합 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        List<String> S = new ArrayList<>();
        List<String> input = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            S.add(bufferedReader.readLine());
        }

        for (int i = 0; i < M; i++) {
            input.add(bufferedReader.readLine());
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            String curStr = input.get(i);
            for (int j = 0; j < N; j++) {
                if(S.get(j).equals(curStr)){
                    result++;
                    break;
                }
            }
        }
        System.out.println(result);

    }

}
