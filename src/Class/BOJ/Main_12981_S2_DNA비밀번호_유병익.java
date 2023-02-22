package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12981_S2_DNA비밀번호_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static int N;
	static int M;
	static int result = 0;
	static int[] countArray = new int[4];

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		String inputString = bufferedReader.readLine();
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < 4; i++) {
			countArray[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int[][] array = new int[N - M + 1][4];

		for (int i = 0; i < M; i++) {
			if (inputString.charAt(i) == 'A') {
				array[0][0]++;
			} else if (inputString.charAt(i) == 'C') {
				array[0][1]++;
			} else if (inputString.charAt(i) == 'G') {
				array[0][2]++;
			} else if (inputString.charAt(i) == 'T') {
				array[0][3]++;
			}
		}
		boolean startflag = true;
		for (int i = 0; i < 4; i++) {
			if (array[0][i] < countArray[i]) {
				startflag = false;
				break;
			}
		}
		if (startflag)
			result++;

		for (int i = 1; i <= N - M; i++) {
			for (int j = 0; j < 4; j++) {
				array[i][j] = array[i - 1][j];
			}
			if (inputString.charAt(i - 1) == 'A') {
				array[i][0]--;
			} else if (inputString.charAt(i - 1) == 'C') {
				array[i][1]--;
			} else if (inputString.charAt(i - 1) == 'G') {
				array[i][2]--;
			} else if (inputString.charAt(i - 1) == 'T') {
				array[i][3]--;
			}

			if (inputString.charAt(i + M - 1) == 'A') {
				array[i][0]++;
			} else if (inputString.charAt(i + M - 1) == 'C') {
				array[i][1]++;
			} else if (inputString.charAt(i + M - 1) == 'G') {
				array[i][2]++;
			} else if (inputString.charAt(i + M - 1) == 'T') {
				array[i][3]++;
			}
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (array[i][j] < countArray[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result++;
			}
		}
		System.out.println(result);
	}

}
