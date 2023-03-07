package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Shark {
	int curRow;
	int curCol;
	int speed;
	int dirRow;
	int dirCol;
	int size;

	public Shark(int curRow, int curCol, int speed, int dirRow, int dirCol, int size) {
		super();
		this.curRow = curRow;
		this.curCol = curCol;
		this.speed = speed;
		this.dirRow = dirRow;
		this.dirCol = dirCol;
		this.size = size;
	}
}

public class Main_17143_G1_낚시왕_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C, N;
	static Shark[][] map;
	static List<Shark> sharkList = new ArrayList<>();

	static int[] dirRow = { -1, 1, 0, 0 };
	static int[] dirCol = { 0, 0, 1, -1 };

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new Shark[R][C];

		N = Integer.parseInt(stringTokenizer.nextToken());
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int speed = Integer.parseInt(stringTokenizer.nextToken());
			int dir = Integer.parseInt(stringTokenizer.nextToken()) - 1;
			int size = Integer.parseInt(stringTokenizer.nextToken());
			Shark shark = new Shark(row, col, speed, dirRow[dir], dirCol[dir], size);
			map[row][col] = shark;
		}

		hunt();
		System.out.println(answer);
	}

	private static void hunt() {
		int position = -1;
		while (true) {
			if (++position >= C) {
				return;
			}

			for (int i = 0; i < R; i++) {
				if (map[i][position] != null) {
					answer += map[i][position].size;
					map[i][position] = null;
					break;
				}
			}

			Shark[][] nextMap = new Shark[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] != null) {
						moveShark(nextMap, map[i][j]);
					}
				}
			}
			map = nextMap;

		}
	}

	private static void moveShark(Shark[][] nextMap, Shark shark) {

		int nextRow = shark.curRow;
		int nextCol = shark.curCol;

		for (int i = 0; i < shark.speed; i++) {
			nextRow += shark.dirRow;
			nextCol += shark.dirCol;
			if (nextRow < 0) {
				shark.dirRow = -shark.dirRow;
				nextRow = 1;
			}
			if (nextRow >= R) {
				shark.dirRow = -shark.dirRow;
				nextRow = R - 2;
			}

			if (nextCol < 0) {
				shark.dirCol = -shark.dirCol;
				nextCol = 1;
			}

			if (nextCol >= C) {
				shark.dirCol = -shark.dirCol;
				nextCol = C - 2;
			}
		}

		shark.curRow = nextRow;
		shark.curCol = nextCol;
		if (nextMap[nextRow][nextCol] == null) {
			nextMap[nextRow][nextCol] = shark;

		} else {
			Shark before = nextMap[nextRow][nextCol];
			if (shark.size > before.size) {
				nextMap[nextRow][nextCol] = shark;
			}
		}
	}
}
