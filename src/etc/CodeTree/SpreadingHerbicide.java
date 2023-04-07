package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 순서
 * 1. 나무의 성장 (인접한 나무의 개수만큼 커진다. 동시에 이루어진다.)
 * 2. 나무의 번식 (다른나무, 벽, 제초제 제외 가능, 번식한 곳의 값 = (현재크기 / 번식가능영역))
 * 3. 복구
 * 4. 제초제 살포 (가장 큰 값을 얻을 수 있는 곳에 살포(같으면 행, 같으면 열 작은순), 모든 대각선 방향으로 k만큼 제거, 해당 위치는 c년동안 나무가 자랄 수 없음)
 * 	// 벽 제외 모든 위치에 살포할 수 있음
 */

public class SpreadingHerbicide {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static final int WALL = Integer.MAX_VALUE;

	static int N, M, K, C;
	static int[][] map;

	static int[] dirRow = { 0, 0, -1, 1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> treeList = new ArrayList<>();

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] > 0) {
					treeList.add(new int[] { i, j });
				}
				if (map[i][j] == -1) {
					map[i][j] = WALL;
				}
			}
		}

		start();

	}

	private static void start() {
		int curTime = 0;
		while (curTime < M) {
			growUpTrees();
			spreadTrees();
			restore();
			kill();
			curTime++;
		}
		System.out.println(answer);
	}

	private static void restore() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] < 0)
					map[i][j]++;
			}
		}
	}

	private static void kill() {
		int[] curDropPosition = getPosition();
		if (curDropPosition != null) {
			drop(curDropPosition[1], curDropPosition[2]);
		}
	}

	private static void drop(int row, int col) {

		if(map[row][col] <= 0){
			map[row][col] = -C;
			return;
		}

		answer += map[row][col];
		map[row][col] = -C;

		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row - i, col - i) || map[row - i][col - i] == WALL) {
				break;
			}
			if (map[row - i][col - i] <= 0) {
				map[row - i][col - i] = -C;
				break;
			}

			answer += map[row - i][col - i];
			map[row - i][col - i] = -C;
		}

		// 오른쪽 위 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row - i, col + i) || map[row - i][col + i] == WALL) {
				break;
			}
			if (map[row - i][col + i] <= 0) {
				map[row - i][col + i] = -C;
				break;
			}
			answer += map[row - i][col + i];
			map[row - i][col + i] = -C;

		}

		// 오른쪽 아래 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row + i, col + i) || map[row + i][col + i] == WALL) {
				break;
			}
			if (map[row + i][col + i] <= 0) {
				map[row + i][col + i] = -C;
				break;
			}
			answer += map[row + i][col + i];
			map[row + i][col + i] = -C;

		}

		// 왼쪽 아래 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row + i, col - i) || map[row + i][col - i] == WALL) {
				break;
			}
			if (map[row + i][col - i] <= 0) {
				map[row + i][col - i] = -C;
				break;
			}
			answer += map[row + i][col - i];
			map[row + i][col - i] = -C;
		}
	}

	private static int[] getPosition() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0]
				? (o1[1] == o2[1] ? Integer.compare(o1[2], o2[2]) : Integer.compare(o1[1], o2[1]))
				: Integer.compare(o2[0], o1[0]));

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != WALL) {
					pq.offer(new int[] { getKillCount(i, j), i, j });
				}
			}
		}
		return pq.peek();
	}

	private static int getKillCount(int row, int col) {

		if(map[row][col] <= 0){
			return 0;
		}

		int count = map[row][col];
		// 왼쪽 위 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row - i, col - i) || map[row - i][col - i] == WALL || map[row - i][col - i] <= 0) {
				break;
			}
			count += map[row - i][col - i];
		}

		// 오른쪽 위 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row - i, col + i) || map[row - i][col + i] == WALL || map[row - i][col + i] <= 0) {
				break;
			}
			count += map[row - i][col + i];
		}

		// 오른쪽 아래 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row + i, col + i) || map[row + i][col + i] == WALL || map[row + i][col + i] <= 0) {
				break;
			}
			count += map[row + i][col + i];
		}

		// 왼쪽 아래 방향
		for (int i = 1; i <= K; i++) {
			if (!boundaryCheck(row + i, col - i) || map[row + i][col - i] == WALL || map[row + i][col - i] <= 0) {
				break;
			}
			count += map[row + i][col - i];
		}
		return count;
	}

	private static void spreadTrees() {
		int[][] tempMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && map[i][j] != WALL) {
					int area = countEmptySpace(i, j);
					if (area != 0) {
						int number = map[i][j] / area;
						fillTree(i, j, tempMap, number);
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += tempMap[i][j];
			}
		}
	}

	private static void growUpTrees() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && map[i][j] != WALL) {
					map[i][j] += countNearTree(i, j);
				}
			}
		}
	}

	private static int countNearTree(int row, int col) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int newRow = row + dirRow[i];
			int newCol = col + dirCol[i];
			if (boundaryCheck(newRow, newCol) && map[newRow][newCol] > 0 && map[newRow][newCol] != WALL) {
				count++;
			}
		}
		return count;
	}

	private static int countEmptySpace(int row, int col) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int newRow = row + dirRow[i];
			int newCol = col + dirCol[i];
			if (boundaryCheck(newRow, newCol) && map[newRow][newCol] == 0) {
				count++;
			}
		}
		return count;
	}

	private static void fillTree(int row, int col, int[][] tempMap, int number) {
		for (int i = 0; i < 4; i++) {
			int newRow = row + dirRow[i];
			int newCol = col + dirCol[i];
			if (boundaryCheck(newRow, newCol) && map[newRow][newCol] == 0) {
				tempMap[newRow][newCol] += number;
			}
		}
	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}
