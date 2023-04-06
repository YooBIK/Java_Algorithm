package Study.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 방법 
 * - 현재 좌표에서 퍼져나간 먼지들을 그대로 더하면 값이 이상하게 나온다.
 * 	-> 퍼져나간 먼지들을 현재 맵과 관계없는 새로운 맵에 더해준다. (현재 맵의 먼지는 감소한 먼지량만 반영한다.)
 *  -> 모든 먼지가 확산되었으면 두 맵을 더해서 다음 상태를 만든다.
 *  -> 이후 회전시키고 이전맵을 갱신한 맵으로 표시해준다.
 */
public class BOJ17144_미세먼지안녕 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C, T;
	static int[][] map;

	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static List<int[]> acPos = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		T = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

				if (map[i][j] == -1) { // 에어컨 위치 저장
					acPos.add(new int[] { i, j });
				}
			}
		}

		int curTime = 0;
		while (curTime < T) {
			int[][] temp = new int[R][C]; // 매 초마다 빈 맵을 생성한다.
			for (int i = 0; i < acPos.size(); i++) { // 새로운 맵에 에어컨 위치 표시
				temp[acPos.get(i)[0]][acPos.get(i)[1]] = -1;
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) { // 이전 맵에 좌표를 순회
					if (map[i][j] > 0) { // 이전 맵에 먼지가 있던 곳인지 체크
						int count = 0;
						for (int dir = 0; dir < 4; dir++) { // 4방향 탐색을 하며 퍼져나갈 수 있는 칸의 개수를 센다.
							int newRow = i + dirRow[dir];
							int newCol = j + dirCol[dir];
							if (boundaryCheck(newRow, newCol)) {
								count++;
							}
						}
						int diffusion = map[i][j] / 5;
						map[i][j] -= diffusion * count; // 총 퍼져나갈 양만큼 이전 맵의 먼지를 감소시킨다.

						for (int dir = 0; dir < 4; dir++) { // 새로운 맵에 퍼져나간 먼지를 더한다.
							int newRow = i + dirRow[dir];
							int newCol = j + dirCol[dir];
							if (boundaryCheck(newRow, newCol)) {
								temp[newRow][newCol] += diffusion;
							}
						}
					}
				}
			}

			for (int i = 0; i < R; i++) { // 이전맵이랑 새로운 맵을 합친다.
				for (int j = 0; j < C; j++) {
					if (temp[i][j] != -1) {
						temp[i][j] = map[i][j] + temp[i][j];
					}
				}
				map[i] = temp[i]; // 이전 맵을 새로운 맵으로 갱신
			}
			rotateDown(); // 주어진 조건에 맞춰 회전시킨다.
			rotateTop();
			curTime++;

		}
		System.out.println(getResult());

	}

	/**
	 * 총 먼지량을 구하는 함수
	 */
	private static int getResult() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		return result;
	}

	private static void rotateTop() {

		for (int i = acPos.get(0)[0] - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}

		for (int i = 0; i < acPos.get(0)[0]; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[acPos.get(0)[0]][i] = map[acPos.get(0)[0]][i - 1];
		}
		map[acPos.get(0)[0]][1] = 0;
	}

	private static void rotateDown() {

		for (int i = acPos.get(1)[0] + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}

		for (int i = R - 1; i > acPos.get(1)[0]; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[acPos.get(1)[0]][i] = map[acPos.get(1)[0]][i - 1];
		}
		map[acPos.get(1)[0]][1] = 0;
	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && map[newRow][newCol] != -1;
	}

}