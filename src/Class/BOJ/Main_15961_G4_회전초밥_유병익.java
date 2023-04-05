package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961_G4_회전초밥_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder stringBuilder = new StringBuilder();
	static StringTokenizer stringTokenizer;

	public static void main(String[] args) throws IOException {

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int D = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());
		int C = Integer.parseInt(stringTokenizer.nextToken()) - 1;

		int[] sushiArray = new int[N];
		int[] isEat = new int[D];
		for (int i = 0; i < N; i++) {
			sushiArray[i] = Integer.parseInt(bufferedReader.readLine()) - 1;
		}

		int answer = Integer.MIN_VALUE;
		int count = 1;
		isEat[C]++;
		for (int i = 0; i < K; i++) {
			if (isEat[sushiArray[i]] == 0) {
				count++;
			}
			isEat[sushiArray[i]]++;
		}
		answer = Math.max(answer, count);

		for (int i = 1; i < N; i++) {
			isEat[sushiArray[i - 1]]--;
			if (isEat[sushiArray[i - 1]] == 0) {
				count--;
			}

			if (isEat[sushiArray[(i + K - 1) % N]] == 0) {
				count++;
			}
			isEat[sushiArray[(i + K - 1) % N]]++;
			answer = Math.max(answer, count);
		}
		System.out.println(answer);

	}

}
