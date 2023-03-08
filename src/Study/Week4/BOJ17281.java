package Study.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17281 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[][] input;
	static int[] selected;
	static boolean[] isSelected;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		input = new int[N][10];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= 9; j++) {
				input[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		selected = new int[10];
		isSelected = new boolean[10];
		isSelected[1] = true;
		selected[4] = 1;

		permutation(1);
		System.out.println(answer);

	}

	public static void permutation(int depth) {
		if (depth == 10) {
			answer = Math.max(answer, playGame());
			return;
		}

		if (depth == 4) {
			permutation(depth + 1);
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				selected[depth] = i;
				permutation(depth + 1);
				isSelected[i] = false;
			}
		}
	}

	private static int playGame() {
		int score = 0;
		int current = 1;
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			queue.clear();
			int outCount = 0;
			while (outCount < 3) {
				switch (input[i][selected[current]]) {
				case 0:
					outCount++;
					break;
				case 1:
					queue.offer(1);
					break;
				case 2:
					queue.offer(1);
					queue.offer(0);
					break;
				case 3:
					queue.offer(1);
					queue.offer(0);
					queue.offer(0);
					break;
				case 4:
					score += 1;
					while (!queue.isEmpty()) {
						score += queue.poll();
					}
					break;
				}

				while (queue.size() > 3) {
					score += queue.poll();
				}

				current++;
				if (current >= 10) {
					current = 1;
				}
			}
		}
		return score;
	}

}
