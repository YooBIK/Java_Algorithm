package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2383_모의_점심식사시간_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static int[][] map;

	static boolean[] isArrive;

	static int[] dirRow = { 0, 0, -1, 1 };
	static int[] dirCol = { -1, 1, 0, 0 };

	static HashMap<Integer, int[]> stairMap = new HashMap<>();

	static class Person implements Cloneable {

		int personNumber;

		int row;
		int col;
		int target;
		int arriveTime;

		public Person(int personNumber, int row, int col, int target, int arriveTime) {
			super();
			this.row = row;
			this.col = col;
			this.personNumber = personNumber;
			this.target = target;
			this.arriveTime = arriveTime;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return new Person(this.personNumber, this.row, this.col, this.target, this.arriveTime);
		}

	}

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		int T = Integer.parseInt(bufferedReader.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[N][N];

			List<Person> personList = new ArrayList<>();
			int personNumber = 0;
			int stairNumber = 0;
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					int curVal = Integer.parseInt(stringTokenizer.nextToken());
					if (curVal == 1) {
						personList.add(new Person(++personNumber, i, j, -1, 0));
					}

					if (curVal > 1) {
						stairMap.put(++stairNumber, new int[] { i, j });
					}
				}
			}

			play(personList);
		}

	}

	private static void play(List<Person> personList) throws CloneNotSupportedException {

		dfs(0, personList);

	}

	private static void dfs(int depth, List<Person> personList) throws CloneNotSupportedException {

		if (depth == personList.size()) {
			startBfs(personList);
		}

		personList.get(depth).target = 1;
		personList.get(depth).target = 2;

		dfs(depth + 1, personList);

	}

	private static void startBfs(List<Person> personList) throws CloneNotSupportedException {
		boolean[][][] visit = new boolean[N][N][personList.size() + 1];

		int curReulst = Integer.MIN_VALUE;
		Queue<Person> queue = new ArrayDeque<>();
		for (int i = 0; i < personList.size(); i++) {
			Person curPerson = (Person) personList.get(i).clone();
			visit[curPerson.row][curPerson.col][curPerson.personNumber] = true;
			queue.offer(curPerson);
		}

		int curTime = 0;

		Queue<Person> firstStair = new ArrayDeque<>();
		Queue<Person> secondStair = new ArrayDeque<>();

		Queue<Person> firstWaitingQueue = new ArrayDeque<>();
		Queue<Person> secondWaitingQueue = new ArrayDeque<>();

		while (true) {
			
			while(curTime <= firstStair.peek().arriveTime+3) {
				firstStair.poll();
			}
			
			
			
			int size = queue.size();
			for (int i = 0; i < N; i++) {
				Person curPerson = queue.poll();

				if (isArrive[curPerson.personNumber]) {
					continue;
				}

				int[] target = stairMap.get(curPerson.target);
				if (target[0] == curPerson.row && target[1] == curPerson.col) {
					if(firstStair.size() < 3) {
						
					}else {
						wai
					}
				}

				for (int j = 0; j < 4; j++) {
					int newRow = curPerson.row + dirRow[j];
					int newCol = curPerson.col + dirCol[j];
					if (boundaryCheck(newRow, newCol) && !visit[newRow][newCol][curPerson.personNumber]) {
						curPerson.row = newRow;
						curPerson.col = newCol;
						curPerson.arriveTime += 1;
						visit[newRow][newCol][curPerson.personNumber] = true;
						queue.offer(curPerson);
					}
				}
			}
			curTime++;
		}

	}

	private static boolean boundaryCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}
