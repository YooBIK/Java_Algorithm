package Class.Live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Live0404 {

	static int N;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
//		for (int i = 0; i < N; i++) {
//			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
//		}
//
//		do {
//			System.out.println(Arrays.toString(numbers));
//		} while (nextPermutation());

	}
	
	
	

//	private static boolean nextPermutation() {
//
//		int i = N - 1;
//
//		// 꼭대기 찾기
//		while (i > 0 && numbers[i] <= numbers[i - 1]) {
//			i--;
//		}
//		if (i == 0) {
//			return false;
//		}
//
//		int j = N - 1;
//
//		while (numbers[i - 1] >= numbers[j]) {
//			j--;
//		}
//		
//		
//
//	}

}
