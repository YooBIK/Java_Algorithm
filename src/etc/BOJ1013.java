package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1013 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            String input = bufferedReader.readLine();

            if (input.matches("(100+1+|01)+")) {
                stringBuilder.append("YES").append("\n");
            } else {
                stringBuilder.append("NO").append("\n");
            }
        }
        System.out.println(stringBuilder);
    }
}
