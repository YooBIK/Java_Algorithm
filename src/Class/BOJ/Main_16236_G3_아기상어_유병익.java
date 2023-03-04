package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_16236_G3_아기상어_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N;
	static int[][] map;
	static boolean[][] visit;

	static int[] dirRow = { -1, 0, 0, 1 };
	static int[] dirCol = { 0, -1, 1, 0 };

	static int startRow, startCol, curSize;
	static int distance = 0;
	static int eatCount = 0;

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(bufferedReader.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 9) {
					startRow = i;
					startCol = j;
					curSize = 2;
					map[i][j] = 0;
				}
			}
		}

		bfs();
		System.out.println(distance);
	}

	private static void bfs() {
		visit = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { startRow, startCol, 0 });

		visit[startRow][startCol] = true;
		int beforeDist = 0;
		TreeSet<int[]> treeSet = new TreeSet<>( // treeSet에는 해당 깊이에 물고기들의 (좌표 + 깊이)를 담는다.
				(o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

		while (!queue.isEmpty()) {

			int[] curInfo = queue.poll();
			int curRow = curInfo[0];
			int curCol = curInfo[1];
			int curDist = curInfo[2];

			if (beforeDist < curDist) { // 거리가 바뀌면 트리셋을 확인한다.
				if (!treeSet.isEmpty()) { // 비어있지 않으면 최단거리의 물고기들의 좌표가 담겨있음
					int[] nextInfo = treeSet.first(); // 그중 가장 위, 가장 왼쪽인 녀석을 꺼냄
					map[nextInfo[0]][nextInfo[1]] = 0; // 먹었다고 처리
					distance += nextInfo[2]; // 거리를 더해줌

					if (curSize == ++eatCount) { // 먹은 횟수 더해주고, 현재 사이즈랑 먹은 횟수가 같으면 사이즈 증가 , 먹은 횟수 초기화
						curSize++;
						eatCount = 0;
					}
					visit = new boolean[N][N]; // visit배열 초기화
					queue.clear(); // 큐랑 트리셋 모두 비워줌
					treeSet.clear();
					queue.offer(new int[] { nextInfo[0], nextInfo[1], 0 }); // 큐에 먹은 물고기 위치 넣음 -> 다음 시작위치
					visit[nextInfo[0]][nextInfo[1]] = true;
					continue;
				}
			}

			// 깊이가 바뀌지 않거나, 바뀌었어도 트리셋이 비어있다면(해당 거리까지 먹을 물고기가 없다는 뜻) 해당 위치에서 4방 탐색한다.
			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dirRow[i];
				int newCol = curCol + dirCol[i];
				if (check(newRow, newCol)) {
					if (map[newRow][newCol] == 0 || map[newRow][newCol] == curSize) { // 0이거나 크기가 같은 물고기 자리라면 방문처리 +
						queue.add(new int[] { newRow, newCol, curDist + 1 });
						visit[newRow][newCol] = true;
					} else if (map[newRow][newCol] > 0 && map[newRow][newCol] < curSize) { // 크기가 작은 물고기라면 해당 위치를
																							// treeset에 저장
						treeSet.add(new int[] { newRow, newCol, curDist + 1 });
						queue.add(new int[] { newRow, newCol, curDist + 1 });
						visit[newRow][newCol] = true;
					}
				}
			}
			beforeDist = curDist;
		}

	}

	private static boolean check(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N && !visit[row][col];
	}

}
