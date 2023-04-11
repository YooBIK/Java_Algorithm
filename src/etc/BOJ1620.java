package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1620 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N, M;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		HashMap<Integer, String> ISMap = new HashMap<>();
		HashMap<String, Integer> SIMap = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			String input = bufferedReader.readLine();
			ISMap.put(i, input);
			SIMap.put(input, i);
		}

		for (int i = 0; i < M; i++) {
			String input = bufferedReader.readLine();
			try {
				int index = Integer.parseInt(input);
				stringBuilder.append(ISMap.get(index)).append("\n");
			} catch (Exception e) {
				stringBuilder.append(SIMap.get(input)).append("\n");
			}
		}
		System.out.println(stringBuilder);
	}

}
