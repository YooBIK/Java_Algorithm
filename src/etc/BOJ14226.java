package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ14226 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static boolean[][] visit = new boolean[2001][2001];

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		int answer = Integer.MAX_VALUE;

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { 1, 0, 0 }); // 개수 , 시간
		visit[1][0] = true;

		while (!queue.isEmpty()) {
			int[] curInfo = queue.poll();

			if (curInfo[0] == N) {
				answer = Math.min(answer, curInfo[2]);
				break;
			}

			// 붙혀 넣기
			if (curInfo[1] > 0 && curInfo[0] + curInfo[1] <= 2000 && !visit[curInfo[0] + curInfo[1]][curInfo[1]]) {
				queue.offer(new int[] { curInfo[0] + curInfo[1], curInfo[1], curInfo[2] + 1 });
				visit[curInfo[0] + curInfo[1]][curInfo[1]] = true;
			}

			// 복사
			if (curInfo[0] > 0 && !visit[curInfo[0]][curInfo[0]]) {
				visit[curInfo[0]][curInfo[0]] = true;
				queue.offer(new int[] { curInfo[0], curInfo[0], curInfo[2] + 1 });
			}

			if (curInfo[0] - 1 > 0 && !visit[curInfo[0] - 1][curInfo[1]]) {
				visit[curInfo[0] - 1][curInfo[1]] = true;
				queue.offer(new int[] { curInfo[0] - 1, curInfo[1], curInfo[2] + 1 });
			}
		}

		System.out.println(answer);
	}
}
