package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15829 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;

	static final int MOD = 1234567891;

	public static void main(String[] args) throws IOException {

		int len = Integer.parseInt(bufferedReader.readLine());

		long hashVal = 0;

		String input = bufferedReader.readLine();
		for (int i = 0; i < len; i++) {
			hashVal = hashVal % MOD + (power(31, i) % MOD * (input.charAt(i) - 'a' + 1)) % MOD;
		}
		System.out.println(hashVal % MOD);
	}

	private static long power(int number, int power) {

		if (power == 0)
			return 1;

		if (power % 2 == 0) {
			return ((power(number, power / 2) % MOD) * (power(number, power / 2) % MOD)) % MOD;
		} else {
			return ((number * power(number, power / 2) % MOD) * (power(number, power / 2) % MOD)) % MOD;
		}

	}

}
