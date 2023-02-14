package Week2.공통문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
	int index;
	int value;

	public Pair(int index, int value) {
		this.index = index;
		this.value = value;
	}
}

public class BOJ2346 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(bufferedReader.readLine());
		Deque<Pair> deque = new ArrayDeque<>();
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= N; i++) {
			deque.addLast(new Pair(i, Integer.parseInt(stringTokenizer.nextToken())));
		}

		while (!deque.isEmpty()) {
			Pair curPair = deque.pollFirst();
			stringBuilder.append(curPair.index).append(" ");

			if (deque.isEmpty())
				break;

			if (curPair.value > 0) {
				for (int j = 0; j < curPair.value - 1; j++) {
					deque.addLast(deque.pollFirst());
				}
			} else {
				for (int j = 0; j < Math.abs(curPair.value); j++) {
					deque.addFirst(deque.pollLast());
				}
			}

		}

		System.out.println(stringBuilder);

	}

}
