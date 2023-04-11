package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4949 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static List<Character> OPEN = Arrays.asList('(', '[');
	static List<Character> CLOSE = Arrays.asList(')', ']');

	public static void main(String[] args) throws IOException {

		while (true) {
			String input = bufferedReader.readLine();

			if (input.equals("."))
				break;

			ArrayDeque<Character> stack = new ArrayDeque<>();

			boolean flag = true;
			for (int i = 0; i < input.length(); i++) {
				char curChar = input.charAt(i);

				if (OPEN.contains(curChar)) {
					stack.addFirst(curChar);
				}

				if (CLOSE.contains(curChar)) {
					if (stack.isEmpty() || stack.peekFirst() == '(' && curChar == ']'
							|| stack.peekFirst() == '[' && curChar == ')') {
						flag = false;
						break;
					} else {
						stack.pollFirst();

					}
				}
			}

			if (flag) {
				if (stack.isEmpty()) {
					stringBuilder.append("yes");
				} else {
					stringBuilder.append("no");
				}
			} else {
				stringBuilder.append("no");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);

	}

}
