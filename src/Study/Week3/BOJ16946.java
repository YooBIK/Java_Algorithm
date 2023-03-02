package Study.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 - 처음에는 모든 벽에서 bfs를 돌리려고 했으나 시간초과 - 그래서 0인 곳을 먼저 탐색하고 , 영역별로 해당 영역의 넓이를
 * (영역번호, 넓이)로 HashMap에 저장 - 이후 모든 벽에 대해서, 주변에 영역이 있는지, Set을 사용해 중복을 제거하며 확인하고,
 * - 근접한 영역의 넓이의 합을 해당 위치에 저장
 */

public class BOJ16946 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static char[][] map;
	static int[][] visit;
	static int[] dirRow = { 0, 0, 1, -1 };
	static int[] dirCol = { 1, -1, 0, 0 };

	static HashMap<Integer, Integer> hashMap = new HashMap<>();
	static List<int[]> wallList = new ArrayList<>();
	static int R, C;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new char[R][C];
		visit = new int[R][C];

		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			map[i] = stringTokenizer.nextToken().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '1') { // 입력받으면서 벽의 위치를 저장
					wallList.add(new int[] { i, j });
				}
			}
		}
		int areaNum = 1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) { // 빈 곳에 대해 bfs 진행 각 영역에 넘버링
				if (map[i][j] == '0' && visit[i][j] == 0) {
					bfs(i, j, areaNum++);
				}
			}
		}

		for (int i = 0; i < wallList.size(); i++) {
			int[] curPosition = wallList.get(i);
			int curRow = curPosition[0];
			int curCol = curPosition[1];
			HashSet<Integer> hashSet = new HashSet<>();
			for (int j = 0; j < 4; j++) { // 해당 영역에서 4방 탐색 진행 + 경계 검사
				int newRow = curRow + dirRow[j];
				int newCol = curCol + dirCol[j];
				if (check(newRow, newCol) && visit[newRow][newCol] > 0) { // 만약 벽이 아닌 곳이면
					hashSet.add(visit[newRow][newCol]); // set에 저장
				}
			}
			int count = 1;
			for (Integer num : hashSet) { // set에 저장된 영역번호들의 넓이를 모두 더함
				count += hashMap.get(num);
			}
			map[curRow][curCol] = (char) ('0' + count % 10); // 값 갱신
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				stringBuilder.append(map[i][j]);
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);
	}

	private static void bfs(int startRow, int startCol, int areaNum) {
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(new int[] { startRow, startCol });
		visit[startRow][startCol] = areaNum;
		int count = 1;
		while (!queue.isEmpty()) {
			int[] curPosition = queue.poll();

			int curRow = curPosition[0];
			int curCol = curPosition[1];

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (check(newRow, newCol) && visit[newRow][newCol] == 0 && map[newRow][newCol] == '0') {
					queue.offer(new int[] { newRow, newCol });
					visit[newRow][newCol] = areaNum;
					count++;
				}
			}
		}

		hashMap.put(areaNum, count); // HashMap에 영역번호 + 넓이 페어로 저장
	}

	private static boolean check(int newRow, int newCol) {
		return newRow >= 0 && newRow < R && newCol >= 0 && newCol < C;
	}

}
