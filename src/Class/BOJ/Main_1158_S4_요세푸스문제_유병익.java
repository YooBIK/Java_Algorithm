package Class.BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1158_S4_요세푸스문제_유병익 {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Scanner scanner = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		builder.append("<");
		int curValue;
		while (!queue.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				queue.add(queue.poll());
			}
			curValue = queue.poll();
			if (queue.isEmpty()) {
				builder.append(curValue).append(">");
			} else {
				builder.append(curValue).append(", ");
			}
		}
		System.out.println(builder);
	}

}
