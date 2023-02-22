package Study.Week2.공통문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23253 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		for (int i = 0; i < M; i++) {
			int k = Integer.parseInt(bufferedReader.readLine());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			Deque<Integer> curStack = new ArrayDeque<>();
			for (int j = 0; j < k; j++) {
				int inputValue = Integer.parseInt(stringTokenizer.nextToken());
				if (curStack.isEmpty()) {
					curStack.addFirst(inputValue);
				} else {
					if (curStack.peek() > inputValue) {
						curStack.addFirst(inputValue);
					} else {
						break;
					}
				}
			}
			if (curStack.size() != k) {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");

	}

}
