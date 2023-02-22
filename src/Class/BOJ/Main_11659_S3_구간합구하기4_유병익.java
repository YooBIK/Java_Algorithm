package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659_S3_구간합구하기4_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int n;
	static int m;
	static int[] array;

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		array = new int[n + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= n; i++) {
			array[i] = array[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
		}

		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int startIndex = Integer.parseInt(stringTokenizer.nextToken());
			int endIndex = Integer.parseInt(stringTokenizer.nextToken());
			stringBuilder.append(array[endIndex] - array[startIndex - 1]).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

}
