package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CodeTreeBread {
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, M;
	static int[][] map;
	static boolean[][][] visit;

	static int[] dirRow = { -1, 0, 0, 1 };
	static int[] dirCol = { 0, -1, 1, 0 };

	static List<int[]> baseCampList = new ArrayList<>();
	static HashMap<Integer, int[]> storeMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N][M];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 1) {
					baseCampList.add(new int[] { i, j });
				}
			}
		}

		for (int i = 1; i <= M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			storeMap.put(i, new int[] { row, col });
		}
		start();

	}

	private static void start() {

		int count = 0;
		int time = 0;

		List<Queue<int[]>> queueList = new ArrayList<>();

		while (count < M) {
			time++;
			for (int i = 0; i < queueList.size(); i++) {
				Queue<int[]> curQueue = queueList.get(i);
				int[] curTarget = storeMap.get(i + 1);
				int size = curQueue.size();

				for (int j = 0; j < size; j++) {
					int[] curPos = curQueue.poll();

					boolean flag = false;
					for (int k = 0; k < 4; k++) {
						int newRow = curPos[0] + dirRow[k];
						int newCol = curPos[1] + dirCol[k];
						if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && map[newRow][newCol] != -1
								&& !visit[newRow][newCol][i]) {
							if (newRow == curTarget[0] && newCol == curTarget[1]) {
								count++;
								map[newRow][newCol] = -1;
								curQueue.clear();
								flag = true;
								break;
							}
							visit[newRow][newCol][i] = true;
							curQueue.offer(new int[] { newRow, newCol });
						}
					}
					if (flag)
						break;

				}
			}
			if (storeMap.containsKey(time)) {
				int[] storePosition = storeMap.get(time); // 현재 시간의 출발하는 사람의 목표 편의점 위치

				PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0]
						? (o1[1] == o2[1] ? Integer.compare(o1[2], o2[2]) : Integer.compare(o1[1], o1[1]))
						: Integer.compare(o1[0], o2[0]));

				boolean[][] tempVisit = new boolean[N][N];
				Queue<int[]> tempQueue = new ArrayDeque<>();
				tempQueue.offer(storePosition);
				tempVisit[storePosition[0]][storePosition[1]] = true;
				int[] startPosition = null;// 현재 시간의 사람이 출발할 베이스 캠프

				while (!tempQueue.isEmpty()) {
					int[] curPos = tempQueue.poll();
					if (map[curPos[0]][curPos[1]] == 1) {
						startPosition = curPos;
						break;
					}
					for (int k = 0; k < 4; k++) {
						int newRow = curPos[0] + dirRow[k];
						int newCol = curPos[1] + dirCol[k];
						if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && map[newRow][newCol] != -1
								&& !tempVisit[newRow][newCol]) {
							tempVisit[newRow][newCol] = true;
							tempQueue.offer(new int[] { newRow, newCol });
						}
					}
				}

				map[startPosition[0]][startPosition[1]] = -1; // 이제 못감
				visit[startPosition[0]][startPosition[1]][time - 1] = true;
				Queue<int[]> queue = new ArrayDeque<>();
				queue.offer(new int[] { startPosition[0], startPosition[1] });
				queueList.add(queue);
			}
		}
		System.out.println(time);

	}
}
