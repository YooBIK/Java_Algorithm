package Study.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1891_사분면 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static int D;
	static long dx, dy;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		D = Integer.parseInt(stringTokenizer.nextToken());
		String number = stringTokenizer.nextToken();

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		dx = Long.parseLong(stringTokenizer.nextToken());
		dy = Long.parseLong(stringTokenizer.nextToken());

		// 번호 -> 좌표 , 좌표 -> 번호
		numberToPosition(0, number, (double) 0, Math.pow(2, D), (double) 0, Math.pow(2, D));
		System.out.println(stringBuilder);
	}

	private static void numberToPosition(int depth, String number, double minX, double maxX, double minY, double maxY) {
		if (depth == D) {
			if (isValid(minX, minY)) {
				double nextX = minX + dx;
				double nextY = minY + dy;
				positionToNumber(nextX, nextY, (double) 0, Math.pow(2, D), (double) 0, Math.pow(2, D));
			} else {
				stringBuilder.append(-1);
			}
			return;
		}

		switch (number.charAt(depth)) {
		case '1':
			numberToPosition(depth + 1, number, (minX + maxX) / 2, maxX, (minY + maxY) / 2, maxY);
			break;
		case '2':
			numberToPosition(depth + 1, number, minX, (minX + maxX) / 2, (minY + maxY) / 2, maxY);
			break;
		case '3':
			numberToPosition(depth + 1, number, minX, (minX + maxX) / 2, minY, (minY + maxY) / 2);
			break;
		case '4':
			numberToPosition(depth + 1, number, (minX + maxX) / 2, maxX, minY, (minY + maxY) / 2);
			break;

		default:
			break;
		}
	}

	private static void positionToNumber(double nextX, double nextY, double minX, double maxX, double minY,
			double maxY) {

		if (maxX - minX <= 1 && maxY - minY <= 1) {
			return;
		}

		double midX = (minX + maxX) / 2;
		double midY = (minY + maxY) / 2;

		// 1사분면
		if (nextX >= midX && nextX < maxX && nextY >= midY && nextY < maxY) {
			stringBuilder.append(1);
			positionToNumber(nextX, nextY, midX, maxX, midY, maxY);
		}

		// 2사분면
		if (nextX >= minX && nextX < midX && nextY >= midY && nextY < maxY) {
			stringBuilder.append(2);
			positionToNumber(nextX, nextY, minX, midX, midY, maxY);
		}

		// 3사분면
		if (nextX >= minX && nextX < midX && nextY >= minY && nextY < midY) {
			stringBuilder.append(3);
			positionToNumber(nextX, nextY, minX, midX, minY, midY);
		}

		// 4사분면
		if (nextX >= midX && nextX < maxX && nextY >= minY && nextY < midY) {
			stringBuilder.append(4);
			positionToNumber(nextX, nextY, midX, maxX, minY, midY);
		}
	}

	private static boolean isValid(double x, double y) {
		return x + dx >= 0 && x + dx < Math.pow(2, D) && y + dy >= 0 && y + dy < Math.pow(2, D);
	}

}
