package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_3190_G4_뱀_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[][] map;

	static int[] dirRow = { 0, 1, 0, -1 };
	static int[] dirCol = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N][N];

		int K = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < K; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			map[row][col] = -1;
		}

		int L = Integer.parseInt(bufferedReader.readLine());
		HashMap<Integer, Character> commandMap = new HashMap<>();
		for (int i = 0; i < L; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			int time = Integer.parseInt(stringTokenizer.nextToken());
			char direction = stringTokenizer.nextToken().charAt(0);
			commandMap.put(time, direction);
		}

		Deque<int[]> deque = new ArrayDeque<>();

		map[0][0] = 1;
		deque.addFirst(new int[] { 0, 0 });

		int direction = 0;
		int time = 0;

		while (true) {
			time++;

			int[] curHead = deque.peek();
			int newRow = curHead[0] + dirRow[direction];
			int newCol = curHead[1] + dirCol[direction];

			if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
				if (map[newRow][newCol] == -1) {
					map[newRow][newCol] = 1;
					deque.addFirst(new int[] { newRow, newCol });
				} else if (map[newRow][newCol] == 0) {
					map[newRow][newCol] = 1;
					deque.addFirst(new int[] { newRow, newCol });
					int[] tail = deque.pollLast();
					map[tail[0]][tail[1]] = 0;
				} else if (map[newRow][newCol] == 1) {
					break;
				}
			} else {
				break;
			}

			if (commandMap.get(time) != null) {
				if (commandMap.get(time) == 'L') {
					direction--;
				} else {
					direction++;
				}
				if (direction < 0) {
					direction = 3;
				} else {
					direction = direction % 4;
				}
			}
		}

		System.out.println(time);
	}

}
