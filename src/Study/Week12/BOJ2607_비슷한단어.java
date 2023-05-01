package Study.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2607_비슷한단어 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(bufferedReader.readLine());
        int answer = 0;

        int[] firstWordCount = new int[26];
        String firstWord = bufferedReader.readLine();

        for (int i = 0; i < firstWord.length(); i++) {
            firstWordCount[firstWord.charAt(i) - 'A']++;
        }

        for (int i = 0; i < N - 1; i++) {
            String inputString = bufferedReader.readLine();
            if (Math.abs(firstWord.length() - inputString.length()) > 1) continue;

            int[] inputStringCount = new int[26];
            for (int j = 0; j < inputString.length(); j++) {
                inputStringCount[inputString.charAt(j) - 'A']++;
            }

            int gap = 0;
            for (int j = 0; j < 26; j++) {
                gap += Math.abs(firstWordCount[j] - inputStringCount[j]);
            }

            // 길이가 같은데 다른 문자가 2 -> 한문자만 바꾸면 ㄱㅊ
            // 길이가 같은데 다른 문자가 0 -> 같은 구성임
            if (firstWord.length() == inputString.length() && (gap == 2 || gap == 0)) {
                answer++;
                // 길이가 1 차이나는데 gap이 1 -> 하나만 지우면 ㄱㅊ
            } else if (Math.abs(firstWord.length() - inputString.length()) == 1 && gap == 1) {
                answer++;
            }

        }
        System.out.println(answer);
    }

}
