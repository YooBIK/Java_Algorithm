package Class.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_1697_S1_숨바꼭질_유병익 {

	static int result = Integer.MAX_VALUE;
	static boolean[] visit = new boolean[100001];

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int start = scanner.nextInt();
		int target = scanner.nextInt();

		bfs(start, target);

	}

	private static void bfs(int start, int target) {

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		queue.offer(0);
		visit[start] = true;

		while (!queue.isEmpty()) {
			int curPosition = queue.poll();
			int curCount = queue.poll();
			if (curPosition == target) {
				System.out.println(curCount);
				break;
			}

			int newPosition = curPosition + 1;
			if (check(newPosition)) {
				visit[newPosition] = true;
				queue.offer(newPosition);
				queue.offer(curCount + 1);
			}

			newPosition = curPosition - 1;
			if (check(newPosition)) {
				visit[newPosition] = true;
				queue.offer(newPosition);
				queue.offer(curCount + 1);
			}

			newPosition = curPosition * 2;
			if (check(newPosition)) {
				visit[newPosition] = true;
				queue.offer(newPosition);
				queue.offer(curCount + 1);
			}

		}

	}

	private static boolean check(int newPosition) {
		
		return newPosition >= 0 && newPosition <= 100000 && !visit[newPosition];
	}

}
