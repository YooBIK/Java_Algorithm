package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_G1_다리만들기2_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C;

	static int answer = Integer.MAX_VALUE;

	static int[] dirRow = { 0, 0, -1, 1 };
	static int[] dirCol = { -1, 1, 0, 0 };

	static int[][] map;
	static int[][] islandMap;

	static int[] parents;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[R][C];
		islandMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		makeIslandMap();
		dfs(0, 0);
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	private static void makeIslandMap() {
		int islandNumber = 1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 1 && islandMap[i][j] == 0) {
					Queue<int[]> queue = new ArrayDeque<>();
					queue.offer(new int[] { i, j });
					islandMap[i][j] = islandNumber++;

					while (!queue.isEmpty()) {
						int[] curPos = queue.poll();
						for (int k = 0; k < 4; k++) {
							int newRow = curPos[0] + dirRow[k];
							int newCol = curPos[1] + dirCol[k];
							if (boundaryCheck(newRow, newCol) && map[newRow][newCol] == 1
									&& islandMap[newRow][newCol] == 0) {
								islandMap[newRow][newCol] = islandMap[curPos[0]][curPos[1]];
								queue.offer(new int[] { newRow, newCol });
							}
						}
					}
				}
			}
		}

		parents = new int[islandNumber];
		for (int i = 1; i < islandNumber; i++) {
			parents[i] = i;
		}
	}

	private static void dfs(int startRow, int startCol) {
		if (checkParents()) {
			int cost = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] < 0) {
						cost += map[i][j];
					}
				}
			}
			answer = Math.min(answer, Math.abs(cost));
			return;
		}

		for (int i = startRow; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] <= 0) {
					int[] rowCheck = canBuildRow(i, j);
					int[] colCheck = canBuildCol(i, j);
					if (rowCheck != null) {
						buildBridgeRow(i, j, rowCheck[0], rowCheck[1]);
						dfs(i, j);
						recoverRow(i, j, rowCheck[0], rowCheck[1]);
					}
					if (colCheck != null) {
						buildBridgeCol(i, j, colCheck[0], colCheck[1]);
						dfs(i, j);
						recoverCol(i, j, colCheck[0], colCheck[1]);
					}
				}

			}
		}
	}

	private static int[] canBuildRow(int curRow, int curCol) {
		int length = 0;
		int upIsland = 0;
		int downIsland = 0;
		for (int i = curRow; i >= 0; i--) {
			if (map[i][curCol] == 1) {
				downIsland = islandMap[i][curCol];
				break;
			}
			length++;
		}

		for (int i = curRow + 1; i < R; i++) {
			if (map[i][curCol] == 1) {
				upIsland = islandMap[i][curCol];
				break;
			}
			length++;
		}

		int upRoot = findRoot(upIsland);
		int downRoot = findRoot(downIsland);

		if (upIsland != 0 && downIsland != 0 && upRoot != downRoot && length > 1) {
			return new int[] { upRoot, downRoot };
		}
		return null;
	}

	private static int[] canBuildCol(int curRow, int curCol) {
		int length = 0;

		int leftIsland = 0;
		int rightIsland = 0;

		for (int i = curCol; i >= 0; i--) {
			if (map[curRow][i] == 1) {
				leftIsland = islandMap[curRow][i];
				break;
			}
			length++;
		}

		for (int i = curCol + 1; i < C; i++) {
			if (map[curRow][i] == 1) {
				rightIsland = islandMap[curRow][i];
				break;
			}
			length++;
		}

		int leftRoot = findRoot(leftIsland);
		int rightRoot = findRoot(rightIsland);

		if (leftIsland != 0 && rightIsland != 0 && leftRoot != rightRoot && length > 1) {
			return new int[] { leftRoot, rightRoot };
		}
		return null;
	}

	private static void buildBridgeRow(int curRow, int curCol, int upRoot, int downRoot) {
		for (int i = curRow; i >= 0; i--) {
			if (map[i][curCol] == 1) {
				break;
			} else {
				map[i][curCol]--;
			}

		}

		for (int i = curRow + 1; i < R; i++) {
			if (map[i][curCol] == 1) {
				break;
			} else {
				map[i][curCol]--;
			}
		}

		parents[upRoot] = downRoot;
	}

	private static void buildBridgeCol(int curRow, int curCol, int leftRoot, int rightRoot) {
		for (int i = curCol; i >= 0; i--) {
			if (map[curRow][i] == 1) {
				break;
			} else {
				map[curRow][i]--;
			}
		}

		for (int i = curCol + 1; i < C; i++) {
			if (map[curRow][i] == 1) {
				break;
			} else {
				map[curRow][i]--;
			}
		}

		parents[leftRoot] = rightRoot;
	}

	private static void recoverRow(int curRow, int curCol, int upRoot, int downRoot) {
		for (int i = curRow; i >= 0; i--) {
			if (map[i][curCol] == 1) {
				break;
			} else {
				map[i][curCol]++;
			}
		}

		for (int i = curRow + 1; i < R; i++) {
			if (map[i][curCol] == 1) {
				break;
			} else {
				map[i][curCol]++;
			}
		}

		parents[upRoot] = upRoot;
	}

	private static void recoverCol(int curRow, int curCol, int leftRoot, int rightRoot) {
		for (int i = curCol; i >= 0; i--) {
			if (map[curRow][i] == 1) {
				break;
			} else {
				map[curRow][i]++;
			}
		}

		for (int i = curCol + 1; i < C; i++) {
			if (map[curRow][i] == 1) {
				break;
			} else {
				map[curRow][i]++;
			}
		}

		parents[leftRoot] = leftRoot;
	}

	private static boolean checkParents() {
		int firstRoot = findRoot(1);
		for (int i = 2; i < parents.length; i++) {
			if (firstRoot != findRoot(i)) {
				return false;
			}
		}
		return true;
	}

	private static int findRoot(int island) {
		if (parents[island] == island) {
			return island;
		}
		return findRoot(parents[island]);
	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C;
	}

}
