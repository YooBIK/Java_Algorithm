package Study.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class BOJ2504 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	static List<Character> OPEN = Arrays.asList('(', '[');
	static List<Character> CLOSE = Arrays.asList(')', ']');

	public static void main(String[] args) throws IOException {

		String input = bufferedReader.readLine();

		if (check(input)) {
			System.out.println(recursive(input));
		} else {
			System.out.println(0);
		}

	}

	private static boolean check(String input) {
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < input.length(); i++) {
			if (OPEN.contains(input.charAt(i))) {
				stack.push(input.charAt(i));
			}

			if (CLOSE.contains(input.charAt(i))) {
				if (stack.isEmpty())
					return false;
				if ((input.charAt(i) == CLOSE.get(1) && stack.peek() == OPEN.get(1))
						|| (input.charAt(i) == CLOSE.get(0) && stack.peek() == OPEN.get(0))) {
					stack.poll();
				} else {
					return false;
				}
			}
		}

		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private static int recursive(String input) {

		if (input.length() == 2) {
			if (input.charAt(0) == '(') {
				return 2;
			} else {
				return 3;
			}
		}

		Deque<Character> stack = new ArrayDeque<>();
		List<Integer> points = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			if (stack.isEmpty()) {
				points.add(i);
			}

			if (OPEN.contains(input.charAt(i))) {
				stack.push(input.charAt(i));
			}

			if ((input.charAt(i) == CLOSE.get(1) && stack.peek() == OPEN.get(1))
					|| (input.charAt(i) == CLOSE.get(0) && stack.peek() == OPEN.get(0))) {
				stack.poll();
			}
		}
		points.add(input.length());
		if (points.size() == 2) {
			if (input.charAt(0) == '(') {
				return 2 * recursive(input.substring(1, input.length() - 1));
			} else {
				return 3 * recursive(input.substring(1, input.length() - 1));
			}
		}

		int result = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			int beginIndex = points.get(i);
			int endIndex = points.get(i + 1);
			result += recursive(input.substring(beginIndex, endIndex));
		}
		return result;

	}

}
