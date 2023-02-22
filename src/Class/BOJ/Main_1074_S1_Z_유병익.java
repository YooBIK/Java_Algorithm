package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_S1_Z_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static long N;
	static long targetRow;
	static long targetCol;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		N = Integer.parseInt(stringTokenizer.nextToken());
		targetRow = Integer.parseInt(stringTokenizer.nextToken());
		targetCol = Integer.parseInt(stringTokenizer.nextToken());

		long size = (long) Math.pow(2, N);
		recursive(0, 0, 0, size * size);
	}

	private static void recursive(long startRow, long startCol, long min, long max) {

		if (startRow == targetRow && startCol == targetCol) {
			System.out.println(min);
			return;
		}

		long offset = (int) Math.sqrt(max - min) / 2;
		int count = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				long newRow = startRow + (i * offset);
				long newCol = startCol + (j * offset);
				if (targetRow >= newRow && targetRow < newRow + offset && targetCol >= newCol
						&& targetCol < newCol + offset) {
					long minValue = min + count * (max - min) / 4;
					long maxValue = min + (count + 1) * (max - min) / 4;
					recursive(newRow, newCol, minValue, maxValue);
				}
				count++;
			}
		}
	}

}