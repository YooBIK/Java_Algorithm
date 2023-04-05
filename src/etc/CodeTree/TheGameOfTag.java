package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TheGameOfTag {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, M, H, K;

	static boolean[][] taggerMap;
	static boolean[][] treeMap;
	static int[][] runnerMap;

	static boolean[][] visit;

	static int taggerRow;
	static int taggerCol;
	static int taggerDir;
	static boolean moveFlag = false; // false -> 시계방향 , true -> 반시계방향

	// 상 우 하 좌
	static int[] dirRow = { -1, 0, 1, 0 };
	static int[] dirCol = { 0, 1, 0, -1 };

	static Queue<int[]> runnerQueue = new ArrayDeque<>();
	static int answer = 0;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken()); // N*N 사이즈
		M = Integer.parseInt(stringTokenizer.nextToken()); // 도망자 수
		H = Integer.parseInt(stringTokenizer.nextToken()); // 나무 갯수
		K = Integer.parseInt(stringTokenizer.nextToken()); // 게임 진행 시간

		taggerMap = new boolean[N][N];
		treeMap = new boolean[N][N];
		runnerMap = new int[N][N];

		taggerMap[N / 2][N / 2] = true;

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int dir = Integer.parseInt(stringTokenizer.nextToken()); // 1 이면 좌우(우), 2이면 상하(하)

			runnerMap[row][col]++;
			runnerQueue.offer(new int[] { row, col, dir });
		}

		for (int i = 0; i < H; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int treeRow = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int treeCol = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			treeMap[treeRow][treeCol] = true;
		}
		playGame();
		System.out.println(answer);
	}

	private static void playGame() {
		taggerRow = N / 2;
		taggerCol = N / 2;
		taggerDir = 0;
		visit = new boolean[N][N];
		visit[taggerRow][taggerCol] = true;

		for (int i = 1; i <= K; i++) {
			moveRunner();
			moveTagger();
			catchRunner(i);
		}
	}

	private static void moveTagger() {
		// 일단 보는 방향으로 이동
		taggerMap[taggerRow][taggerCol] = false;
		taggerRow += dirRow[taggerDir];
		taggerCol += dirCol[taggerDir];
		taggerMap[taggerRow][taggerCol] = true;
		visit[taggerRow][taggerCol] = !moveFlag;

		// 끝점이면 180도 회전 & 회전 방향 변경
		// false -> 시계방향 , true -> 반시계방향
		if ((taggerRow == 0 && taggerCol == 0) || (taggerRow == N / 2 && taggerCol == N / 2)) {
			taggerDir = (taggerDir + 2) % 4;
			moveFlag = !moveFlag;
			visit[taggerRow][taggerCol] = !moveFlag;
			return;
		}

		int nextRow = taggerRow;
		int nextCol = taggerCol;
		int nextDir = taggerDir;
		if (!moveFlag) { // 시계방향
			nextDir = (taggerDir + 1) % 4;
			nextRow += dirRow[nextDir];
			nextCol += dirCol[nextDir];
			if (visit[nextRow][nextCol] == moveFlag) {
				taggerDir = nextDir;
			}

		} else { // 반시계 방향
			nextRow += dirRow[nextDir];
			nextCol += dirCol[nextDir];
			if (!boundaryCheck(nextRow, nextCol) || visit[nextRow][nextCol] != moveFlag) {
				taggerDir = (taggerDir + 3) % 4;
			}
		}
	}

	private static boolean boundaryCheck(int nextRow, int nextCol) {
		return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N;
	}

	private static void moveRunner() {

		int size = runnerQueue.size();
		for (int i = 0; i < size; i++) {
			int[] curRunnerInfo = runnerQueue.poll();
			int curRow = curRunnerInfo[0];
			int curCol = curRunnerInfo[1];
			int curDir = curRunnerInfo[2];

			if (runnerMap[curRow][curCol] == 0) {
				continue;
			}

			int dist = Math.abs(curRow - taggerRow) + Math.abs(curCol - taggerCol);
			if (dist > 3) {
				runnerQueue.offer(curRunnerInfo);
				continue;
			}
			int nextRow = curRow + dirRow[curDir];
			int nextCol = curCol + dirCol[curDir];
			int nextDir = curDir;
			if (boundaryCheck(nextRow, nextCol)) { // 범위 안에 있음
				if (nextRow == taggerRow && nextCol == taggerCol) { // 해당 칸에 술래 있음
					nextRow = curRow;
					nextCol = curCol;
				}
			} else { // 범위 밖임
				nextDir = (curDir + 2) % 4;
				nextRow = curRow + dirRow[nextDir];
				nextCol = curCol + dirCol[nextDir];
				if (nextRow == taggerRow && nextCol == taggerCol) {
					nextRow = curRow;
					nextCol = curCol;
				}
			}

			curRunnerInfo[0] = nextRow;
			curRunnerInfo[1] = nextCol;
			curRunnerInfo[2] = nextDir;

			runnerMap[curRow][curCol]--;
			runnerMap[curRunnerInfo[0]][curRunnerInfo[1]]++;
			runnerQueue.offer(curRunnerInfo);
		}
	}

	private static void catchRunner(int curTime) {
		int count = 0;

		for (int i = 0; i < 3; i++) {
			int catchRow = taggerRow + i * dirRow[taggerDir];
			int catchCol = taggerCol + i * dirCol[taggerDir];

			if (boundaryCheck(catchRow, catchCol)) {
				if (runnerMap[catchRow][catchCol] > 0 && !treeMap[catchRow][catchCol]) {
					count += runnerMap[catchRow][catchCol];
					runnerMap[catchRow][catchCol] = 0;
				}
			}
		}
		answer += count * curTime;
	}
}
