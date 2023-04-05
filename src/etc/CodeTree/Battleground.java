package etc.CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Battleground {

	static class Player {
		int playerNum;

		int curRow;
		int curCol;
		int curDir;

		int power;
		int gun;

		public Player(int playerNum, int curRow, int curCol, int curDir, int power, int gun) {
			super();
			this.playerNum = playerNum;
			this.curRow = curRow;
			this.curCol = curCol;
			this.curDir = curDir;
			this.power = power;
			this.gun = gun;
		}

		public int getMaxPower() {
			return power + gun;
		}

		@Override
		public String toString() {
			return "playerNum=" + playerNum + ", curRow=" + curRow + ", curCol=" + curCol + ", curDir=" + curDir
					+ ", power=" + power + ", gun=" + gun;
		}

	}

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int N, M, K;

	static int[] dirRow = { -1, 0, 1, 0 };
	static int[] dirCol = { 0, 1, 0, -1 };

	static PriorityQueue<Integer>[][] gunMap;
	static Player[][] playerMap;
	static Queue<Player> playerQueue = new ArrayDeque<>();

	static int[] score;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		playerMap = new Player[N][N];
		gunMap = new PriorityQueue[N][N];
		score = new int[M];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				gunMap[i][j] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

				int curGun = Integer.parseInt(stringTokenizer.nextToken());
				if (curGun > 0) {
					gunMap[i][j].offer(curGun);
				}
			}
		}

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int dir = Integer.parseInt(stringTokenizer.nextToken());
			int power = Integer.parseInt(stringTokenizer.nextToken());
			Player curPlayer = new Player(i, row, col, dir, power, 0);
			playerMap[row][col] = curPlayer;
			playerQueue.offer(curPlayer);
		}

		playGame();
		for (int i = 0; i < score.length; i++) {
			System.out.print(score[i] + " ");
		}
	}

	private static void playGame() {

		for (int round = 1; round <= K; round++) {
			System.out.println(round);
			Queue<Player> nextQueue = new ArrayDeque<>();
			
			while(!playerQueue.isEmpty()) {
				
			}

			movePlayers();

		}

	}

	private static void movePlayers() {
		boolean[] isMove = new boolean[M];

		int size = playerQueue.size();

		for (int a = 0; a < size; a++) {
			Player curPlayer = playerQueue.poll();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (playerMap[i][j] != null) {
						System.out.print(playerMap[i][j].getMaxPower() + " ");
					} else {
						System.out.print("- ");
					}
				}
				System.out.println();
			}
			System.out.println();

			if (isMove[curPlayer.playerNum]) { // 패배자임 !! 안 움직인다.
				playerQueue.offer(curPlayer);
			}

			int curRow = curPlayer.curRow;
			int curCol = curPlayer.curCol;
			int curDir = curPlayer.curDir;

			int newRow = curRow + dirRow[curDir];
			int newCol = curCol + dirCol[curDir];
			int newDir = curDir;

			if (!boundaryCheck(newRow, newCol)) {
				newDir = (curDir + 2) % 4;
				newRow = curRow + dirRow[newDir];
				newCol = curCol + dirCol[newDir];
			}
			playerMap[curRow][curCol] = null;
			curPlayer.curRow = newRow;
			curPlayer.curCol = newCol;
			curPlayer.curDir = newDir;

			isMove[curPlayer.playerNum] = true;
			if (playerMap[newRow][newCol] == null) {
				playerMap[newRow][newCol] = curPlayer;

				PriorityQueue<Integer> pq = gunMap[newRow][newCol];
				pq.add(curPlayer.gun);
				curPlayer.gun = pq.poll();
				playerQueue.add(curPlayer);

			} else {
				Player first = curPlayer;
				Player second = playerMap[newRow][newCol];

				Player winner = null;
				Player loser = null;

				if (first.getMaxPower() == second.getMaxPower()) {
					if (first.power < second.power) {
						winner = second;
						loser = first;
					} else {
						winner = first;
						loser = second;
					}
				} else {
					if (first.getMaxPower() < second.getMaxPower()) {
						winner = second;
						loser = first;
					} else {
						winner = first;
						loser = second;
					}
				}
				score[winner.playerNum] += winner.getMaxPower() - loser.getMaxPower();

				moveLoser(loser);
				isMove[loser.playerNum] = true;

				if (!gunMap[winner.curRow][winner.curCol].isEmpty()
						&& gunMap[winner.curRow][winner.curCol].peek() > winner.gun) {
					gunMap[winner.curRow][winner.curCol].add(winner.gun);
					winner.gun = gunMap[winner.curRow][winner.curCol].poll();
				}

				winner.curRow = newRow;
				winner.curCol = newCol;
				if (!playerQueue.contains(winner)) {
					playerQueue.offer(winner);
				}

				if (!playerQueue.contains(loser)) {
					playerQueue.offer(loser);
				}

			}

		}

	}

	private static void moveLoser(Player loser) {
		int curRow = loser.curRow;
		int curCol = loser.curCol;
		int curDir = loser.curDir;

		if (loser.gun > 0) {
			gunMap[curRow][curCol].add(loser.gun);
			loser.gun = 0;
		}

		int newRow = curRow + dirRow[curDir];
		int newCol = curCol + dirCol[curDir];

		while (true) {
			if (!boundaryCheck(newRow, newCol) || playerMap[newRow][newCol] != null) {
				curDir = (curDir + 1) % 4;
				newRow = curRow + dirRow[curDir];
				newCol = curCol + dirCol[curDir];
			} else {
				break;
			}
		}
		loser.curRow = newRow;
		loser.curCol = newCol;
		loser.curDir = curDir;
		playerMap[newRow][newCol] = loser;

		if (!gunMap[loser.curRow][loser.curCol].isEmpty()) {
			loser.gun = gunMap[loser.curRow][loser.curCol].poll();
		}

		playerQueue.offer(loser);
	}

	private static boolean boundaryCheck(int newRow, int newCol) {
		return newRow >= 0 && newRow < N && newCol >= 0 && newCol < N;
	}

}
