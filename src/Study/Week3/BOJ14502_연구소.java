package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 *  #풀이 방법 - 시작 컴퓨터는 1번 컴퓨터임\
 *  - 조합으로 빈 칸(0)에 벽을 세우는 경우의 수를 모두 구한다.
 *  - 각 경우마다 BFS를 시행해서 바이러스가 모두 퍼지는데 걸리는 시간을 구한다.
 *  - 각 경우마다 지도의 0이 남아있는 개수를 세고, 최대값을 갱신하며 저장한다.
 *  - 연결 정보를 2차원 배열로 변환해서 연결 여부를 확인
 *  - bfs 사용, queue에 offer될 때 마다 결과값 증가
 *
 */
public class BOJ14502_연구소 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int R, C;
	static int[][] map;
	static boolean[][] visit;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> safePositions = new ArrayList<>();	// 0이 있는 위치 정보를 저장
	static List<int[]> virusPositions = new ArrayList<>();	// 2가 있는 위치 정보를 저장

	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException, InterruptedException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				int curVal = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = curVal;
				if (curVal == 0) {
					safePositions.add(new int[] { i, j });
				}
				if (curVal == 2) {
					virusPositions.add(new int[] { i, j });
				}
			}
		}
		buildWall(0, 0);
		System.out.println(result);
	}

	/**
	 *
	 * @param depth : 현재 세운 벽의 개수
	 * @param startIndex : 조합 구현
	 * @throws InterruptedException
	 */
	private static void buildWall(int depth, int startIndex) throws InterruptedException {
		if (depth == 3) {					// 벽을 3개 세우면
			int[][] temp = new int[R][C];	// 현재 지도 정보를 temp에 복사
			for (int i = 0; i < R; i++) {
				temp[i] = map[i].clone();
			}
			visit = new boolean[R][C];		// visit 배열 초기화
			bfs(temp);						// bfs 시행
			updateResult(temp);				// temp를 돌면서 0의 개수를 카운트
			return;
		}
		for (int i = startIndex; i < safePositions.size(); i++) {
			int[] curPos = safePositions.get(i);
			map[curPos[0]][curPos[1]] = 1;
			buildWall(depth + 1, i + 1);
			map[curPos[0]][curPos[1]] = 0;
		}
	}

	/**
	 * @param temp : bfs 시행 후 안전지대 범위를 구하는 함수
	 */
	private static void updateResult(int[][] temp) {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (temp[i][j] == 0) {
					sum++;
				}
			}
		}
		result = Math.max(result, sum);
	}

	private static void bfs(int[][] temp) {
		Queue<int[]> queue = new ArrayDeque<>(virusPositions);
		for (int i = 0; i < virusPositions.size(); i++) {
			int[] curPos = virusPositions.get(i);
			visit[curPos[0]][curPos[1]] = true;
		}

		while (!queue.isEmpty()) {
			int[] curPos = queue.poll();
			int curRow = curPos[0];
			int curCol = curPos[1];

			for (int k = 0; k < 4; k++) {
				int newRow = curRow + dirRow[k];
				int newCol = curCol + dirCol[k];
				if (check(newRow, newCol)) {
					temp[newRow][newCol] = 2;
					visit[newRow][newCol] = true;
					queue.offer(new int[] { newRow, newCol });
				}
			}
		}
	}

	private static boolean check(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && !visit[newRow][newCol]
				&& map[newRow][newCol] == 0;
	}

}
