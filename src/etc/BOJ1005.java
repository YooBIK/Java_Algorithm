package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1005 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int N = Integer.parseInt(stringTokenizer.nextToken());
			int K = Integer.parseInt(stringTokenizer.nextToken());
			int[] buildTimeArray = new int[N];

			List<Integer>[] needToBuild = new ArrayList[N];

			for (int i = 0; i < N; i++) {
				needToBuild[i] = new ArrayList<>();
			}

			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < N; i++) {
				buildTimeArray[i] = Integer.parseInt(stringTokenizer.nextToken());
			}

			for (int i = 0; i < K; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int first = Integer.parseInt(stringTokenizer.nextToken());
				int second = Integer.parseInt(stringTokenizer.nextToken());
				needToBuild[second].add(first);
			}
			
			

		}

	}

}
