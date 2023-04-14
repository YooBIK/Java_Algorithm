package Study.Week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ1874_스택수열 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(bufferedReader.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		int nextNumber = 1;
		for (int i = 0; i < N; i++) {
			int target = Integer.parseInt(bufferedReader.readLine());

			if (target >= nextNumber) {
				while (target >= nextNumber) {
					stack.push(nextNumber++);
					stringBuilder.append("+").append("\n");
				}
				stack.poll();
				stringBuilder.append("-").append("\n");
			} else {
				if (stack.isEmpty()) {
					System.out.println("NO");
					return;
				} else {
					if (stack.peek() == target) {
						stack.poll();
						stringBuilder.append("-").append("\n");
					} else {
						System.out.println("NO");
						return;
					}
				}
			}
		}
		System.out.println(stringBuilder);
	}
}
