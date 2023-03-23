package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23562 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int R, C;
	static int whiteToBlack, blackToWhite;

	static char[][] map;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		whiteToBlack = Integer.parseInt(stringTokenizer.nextToken());
		blackToWhite = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}

		for (int k = Math.min(R, C) / 3; k >= 1; k--) {

		}

	}
}
