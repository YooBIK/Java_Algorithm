package Study.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2885_초콜릿식사 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int K = Integer.parseInt(bufferedReader.readLine());

		int size = 1;
		while (size < K) {
			size *= 2;
		}

		int curSize = size;
		int temp = K;
		int count = 0;

		while (temp > 0) {
			if (curSize <= temp) {
				temp -= curSize;
			} else {
				curSize /= 2;
				count++;
			}
		}
		System.out.println(size + " " + count);
	}
}
