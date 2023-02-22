package Class.BOJ;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main_2164_S4_카드2_유병익 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.addLast(i);
		}

		while (queue.size() > 1) {
			queue.removeFirst();
			queue.add(queue.poll());
		}
		System.out.println(queue.poll());
	}
}
