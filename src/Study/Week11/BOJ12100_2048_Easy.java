package Study.Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12100_2048_Easy {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int[] dirRow = { 0, -1, 0, 1 };
	static int[] dirCol = { -1, 0, 1, 0 };

	static int N;
	static int[][] map;

	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		playGame(copyMap(map), 0);
		System.out.println(answer);

	}

	private static void playGame(int[][] beforeMap, int depth) {
		if (depth == 5) {
			answer = Math.max(answer, getMaxValue(beforeMap));
			return;
		}
//		try {
//			Thread.sleep(50);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		for (int i = 0; i < 4; i++) {
			int[][] tempMap = copyMap(beforeMap);
			getNextStatus(tempMap, i);
//			printMap(tempMap);
			playGame(tempMap, depth + 1);
		}
	}

	private static void printMap(int[][] tempMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(tempMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void getNextStatus(int[][] tempMap, int dir) {

		boolean[][] check = new boolean[N][N];

		switch (dir) {
		case 0:
			for (int i = 0; i < N; i++) {
				moveLeft(i, tempMap, check);
			}
			break;
		case 1:
			for (int i = 0; i < N; i++) {
				moveUp(i, tempMap, check);
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				moveRight(i, tempMap, check);
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				moveDown(i, tempMap, check);
			}
			break;
		default:
			break;
		}
	}

	private static void moveDown(int col, int[][] tempMap, boolean[][] check) {
		// TODO Auto-generated method stub

	}

	private static void moveUp(int col, int[][] tempMap, boolean[][] check) {
		// TODO Auto-generated method stub

	}

	private static void moveRight(int row, int[][] tempMap, boolean[][] check) {
		// TODO Auto-generated method stub

	}

	private static void moveLeft(int row, int[][] tempMap, boolean[][] check) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		ArrayDeque<Integer> resultQueue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (tempMap[row][i] > 0) {
				if(!queue.isEmpty() && queue.peekLast() == tempMap[row][i]) {
					
				}else {
					queue.addLast(tempMap[row][i]);
				}
			}
		}


	}

	private static void moveCell(int row, int col, int dir, int[][] tempMap, boolean[][] check) {
	}

	private static int[][] copyMap(int[][] targetMap) {
		int[][] nextMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nextMap[i][j] = targetMap[i][j];
			}
		}
		return nextMap;
	}

	private static int getMaxValue(int[][] beforeMap) {
		int curMaxValue = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				curMaxValue = Math.max(curMaxValue, beforeMap[i][j]);
			}
		}
		return curMaxValue;
	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}
