package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ17298 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringbuilder = new StringBuilder();

    static int N;

    static ArrayDeque<Integer> stack = new ArrayDeque<>();

    static int[] numberArray;
    static int[] answerArray;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        numberArray = new int[N];
        answerArray = new int[N];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            numberArray[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && numberArray[stack.peekFirst()] < numberArray[i]) {
                answerArray[stack.pollFirst()] = numberArray[i];
            }
            stack.addFirst(i);
        }


        for (int i = 0; i < N; i++) {
            if (answerArray[i] == 0) {
                stringbuilder.append(-1);
                stringbuilder.append(" ");
            } else {
                stringbuilder.append(answerArray[i]);
                stringbuilder.append(" ");
            }
        }
        System.out.println(stringbuilder);
    }

}