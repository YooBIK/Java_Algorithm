package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1228_D3_암호문1_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void main(String[] args) throws IOException {

		for (int testCase = 1; testCase <= 10; testCase++) {
			int length = Integer.parseInt(bufferedReader.readLine());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			List<Integer> numberList = new LinkedList<>();

			for (int i = 0; i < length; i++) {
				numberList.add(Integer.parseInt(stringTokenizer.nextToken()));
			}

			int N = Integer.parseInt(bufferedReader.readLine());

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < N; i++) {
				String command = stringTokenizer.nextToken();
				int startIndex = Integer.parseInt(stringTokenizer.nextToken());
				int number = Integer.parseInt(stringTokenizer.nextToken());
				for (int j = startIndex; j < startIndex + number; j++) {
					numberList.add(j, Integer.parseInt(stringTokenizer.nextToken()));
				}
			}
			stringBuilder.append("#").append(testCase);
			for (int i = 0; i < 10; i++) {
				stringBuilder.append(" ").append(numberList.get(i));
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);

	}

}
