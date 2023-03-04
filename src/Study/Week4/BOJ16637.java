package Study.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16637 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static char[] input;

	static final List<Character> OPERATOR_LIST = Arrays.asList('+', '-', '*');
	static boolean[] selected;
	static ArrayDeque<Integer> stack;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());
		input = bufferedReader.readLine().toCharArray();

		if (N == 1) {
			System.out.println(input[0]);
			return;
		}

		selected = new boolean[N / 2];

		subset(0, true);
		subset(0, false);
		System.out.println(answer);
	}

	private static void subset(int depth, boolean selectFlag) {
		if (depth == (N / 2)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < input.length; i++) {
				if (i % 2 == 1 && selected[(i - 1) / 2]) {
					sb.append(apply(input[i - 1], input[i + 1], input[i]));
					i++;
				} else {
					sb.append(input[i]);
				}
			}
			System.out.println(sb.toString());
			return;
		}

		if (selectFlag) {
			selected[depth] = true;
			subset(depth + 1, false);
		} else {
			selected[depth] = false;
			subset(depth + 1, true);
			subset(depth + 1, false);
		}
	}

	private static void removeBracket() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length; i++) {
			if (i % 2 == 1 && selected[(i - 1) / 2]) {
				sb.append(apply(input[i - 1], input[i + 1], input[i]));
				i++;
			} else {
				sb.append(input[i]);
			}
		}
		calculate(sb.toString());
	}

	private static void calculate(String str) {
		int result = (int) (str.charAt(0) - '0');
		char operator = str.charAt(1);
		for (int i = 1; i < str.length(); i++) {
			if (OPERATOR_LIST.contains(str.charAt(i))) {
				operator = str.charAt(i);
			} else {
				result = apply((char) (result + '0'), str.charAt(i), operator);
			}
		}
		answer = Math.max(result, answer);

	}

	private static int apply(char left, char right, char operator) {

		int leftVal = (int) (left - '0');
		int rightVal = (int) (right - '0');
		int result = Integer.MAX_VALUE;
		switch (operator) {
		case '+':
			result = leftVal + rightVal;
			break;
		case '-':
			result = leftVal - rightVal;
			break;
		case '*':
			result = leftVal * rightVal;
			break;
		}
		return result;
	}

}
