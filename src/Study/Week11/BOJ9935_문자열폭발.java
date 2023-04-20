package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ9935_문자열폭발 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String inputString = bufferedReader.readLine();
        String target = bufferedReader.readLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < inputString.length(); i++) {
            stack.addFirst(inputString.charAt(i));
            if (!stack.isEmpty() && stack.peekFirst() == target.charAt(target.length() - 1)) {
                ArrayDeque<Character> tempStack = new ArrayDeque<>();
                for (int j = target.length() - 1; j >= 0; j--) {
                    if (!stack.isEmpty() && stack.peekFirst() == target.charAt(j)) {
                        tempStack.addFirst(stack.pollFirst());
                    } else {
                        while (!tempStack.isEmpty()) {
                            stack.addFirst(tempStack.pollFirst());
                        }
                        break;
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.isEmpty()) {
                stringBuilder.append(stack.pollLast());
            }
            System.out.println(stringBuilder);
        }

    }
}
