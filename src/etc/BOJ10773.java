package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ10773 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int K = Integer.parseInt(bufferedReader.readLine());

		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < K; i++) {
			int curNum = Integer.parseInt(bufferedReader.readLine());
			if (curNum != 0) {
				stack.addFirst(curNum);
			} else {
				stack.pollFirst();
			}
		}

		int result = 0;
		for (int i : stack) {
			result += i;
		}
		System.out.println(result);

	}

}
