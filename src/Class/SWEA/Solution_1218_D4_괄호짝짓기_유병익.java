package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_D4_괄호짝짓기_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();

	static char[] left = { '(', '[', '{', '<' };
	static char[] right = { ')', ']', '}', '>' };

	public static void main(String[] args) throws IOException {

		for (int testCase = 1; testCase <= 10; testCase++) {
			int inputLength = Integer.parseInt(bufferedReader.readLine());
			String input = bufferedReader.readLine();
			Stack<Character> stack = new Stack<>();
			boolean flag = true;
			for (int i = 0; i < inputLength; i++) {
				for (int j = 0; j < 4; j++) {
					if (input.charAt(i) == left[j]) {
						stack.push(input.charAt(i));
						break;
					}
					if (input.charAt(i) == right[j]) {
						if (!stack.isEmpty() && stack.peek() == left[j]) {
							stack.pop();
						} else {
							flag = false;
						}
						break;
					}
				}
				if (!flag) {
					break;
				}
			}
			if (stack.isEmpty()) {
				builder.append("#").append(testCase).append(" ").append(1).append("\n");
			} else {
				builder.append("#").append(testCase).append(" ").append(0).append("\n");
			}
		}
		System.out.println(builder);

	}

}
