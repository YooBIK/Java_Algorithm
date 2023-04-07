package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Artistry {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;

	static int[][] map;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static int regionCount;
	static int[][] regionMap; // 분할 정보 , 인접 구역 구하기 용도
	static HashMap<Integer, Integer> regionAreaHashMap; // 각 영역 : 넓이
	static HashMap<Integer, Integer> regionValueHashMap; // 각 영역 : 원래 숫자
	static int[][] adjMaxtix; // i와 j 의 인접한 변의 개수 저장

	static int answer = 0;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		getResult();
		System.out.println(answer);

	}

	private static void getResult() {
		for (int i = 0; i <= 3; i++) {
			divideArea();
			int result = getCurrentScore();
			answer += result;
			if (i < 3) {
				rotateMap();
			}
		}
	}

	private static void rotateMap() {
		int[][] tempMap = new int[N][N];
		rotateCenter(tempMap);
		rotateOthers(0, 0, N, tempMap);
		map = tempMap;

	}

	private static void rotateCenter(int[][] tempMap) {
		int mid = N / 2;
		for (int i = 0; i < N; i++) {
			tempMap[mid][i] = map[i][mid];
			tempMap[i][mid] = map[mid][N - i - 1];
		}
	}

	private static void rotateOthers(int startRow, int startCol, int size, int[][] tempMap) {

		if (size == N) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					rotateOthers(((N / 2) + 1) * i, ((N / 2) + 1) * j, N / 2, tempMap);
				}
			}
		} else {
			for (int round = 0; round <= size; round++) {
				int curRow = startRow + round;
				int curCol = startCol + round;

				int curSize = size - (2 * round);

				int endRow = curRow + curSize - 1;
				int endCol = curCol + curSize - 1;

				if (curRow > endRow && curRow > endRow) {
					return;
				}
				for (int i = 0; i < curSize; i++) { // 윗 변 -> 오른쪽 변
					tempMap[curRow + i][endCol] = map[curRow][curCol + i];
					tempMap[endRow][endCol - i] = map[curRow + i][endCol];
					tempMap[endRow - i][curCol] = map[endRow][endCol - i];
					tempMap[curRow][curCol + i] = map[endRow - i][curCol];
				}

			}

		}

	}

	private static int getCurrentScore() {

		adjMaxtix = new int[regionCount + 1][regionCount + 1];
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					makeAdjMaxrix(i, j, visit);
				}
			}
		}

		int currentScore = 0;
		for (int i = 1; i <= regionCount; i++) {
			for (int j = i; j <= regionCount; j++) {
				if (i == j || adjMaxtix[i][j] == 0)
					continue;
				currentScore += (regionAreaHashMap.get(i) + regionAreaHashMap.get(j)) * regionValueHashMap.get(i)
						* regionValueHashMap.get(j) * adjMaxtix[i][j];
			}
		}
		return currentScore;
	}

	private static void makeAdjMaxrix(int row, int col, boolean[][] visit) {

		int curRegion = regionMap[row][col];

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { row, col });
		visit[row][col] = true;

		while (!queue.isEmpty()) {
			int[] curPos = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newRow = curPos[0] + dirRow[i];
				int newCol = curPos[1] + dirCol[i];
				if (boundaryCheck(newRow, newCol)) {
					if (regionMap[newRow][newCol] == curRegion) {
						if (!visit[newRow][newCol]) {
							queue.offer(new int[] { newRow, newCol });
							visit[newRow][newCol] = true;
						}
					} else {
						int anotherRegion = regionMap[newRow][newCol];
						adjMaxtix[curRegion][anotherRegion]++;
					}
				}
			}
		}
	}

	private static void divideArea() {
		int curRegion = 0;
		regionMap = new int[N][N];
		regionAreaHashMap = new HashMap<>();
		regionValueHashMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (regionMap[i][j] == 0) {
					bfs(i, j, ++curRegion);
					regionValueHashMap.put(curRegion, map[i][j]);
				}
			}
		}
		regionCount = curRegion;
	}

	private static void bfs(int row, int col, int curRegion) {
		int curValue = map[row][col];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { row, col });
		regionMap[row][col] = curRegion;
		int count = 1;
		while (!queue.isEmpty()) {
			int[] curPos = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newRow = curPos[0] + dirRow[i];
				int newCol = curPos[1] + dirCol[i];
				if (boundaryCheck(newRow, newCol) && regionMap[newRow][newCol] == 0) {
					if (map[newRow][newCol] == curValue) {
						queue.offer(new int[] { newRow, newCol });
						regionMap[newRow][newCol] = curRegion;
						count++;
					}
				}
			}
		}
		regionAreaHashMap.put(curRegion, count);
	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}
