package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1541_S2_잃어버린괄호_유병익 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static List<Integer> numberList = new ArrayList<>();
	static List<Character> operatorList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		String str = bufferedReader.readLine();
		boolean findFlag = false;
		int result = 0;
		int startIndex = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '-' || str.charAt(i) == '+') {
				numberList.add(Integer.parseInt(str.substring(startIndex, i)));
				startIndex = i + 1;
				if (str.charAt(i) == '-') {
					findFlag = true;
					operatorList.add('-');
				} else {
					if (findFlag) {
						operatorList.add('-');
					} else {
						operatorList.add('+');
					}
				}
			}
		}
		numberList.add(Integer.parseInt(str.substring(startIndex)));

		System.out.println(getResult());
	}

	private static int getResult() {
		int result = numberList.get(0);
		for (int i = 0; i < operatorList.size(); i++) {
			if (operatorList.get(i) == '+') {
				result += numberList.get(i + 1);
			} else {
				result -= numberList.get(i + 1);
			}
		}
		return result;
	}

}
