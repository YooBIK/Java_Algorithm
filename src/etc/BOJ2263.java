package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2263 {

	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	static int N;
	static String[] inOrder, postOrder;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bufferedReader.readLine());

		inOrder = bufferedReader.readLine().split(" ");
		postOrder = bufferedReader.readLine().split(" ");

		findPreOrder(0, N, 0, N);
		System.out.println(stringBuilder);
	}

	private static void findPreOrder(int inOrderStart, int inOrderEnd, int postOrderStart, int postOrderEnd) {

		if (inOrderStart == inOrderEnd)
			return;

		String root = postOrder[postOrderEnd - 1];
		stringBuilder.append(root).append(" ");

		int inOrderRootPosition = -1;
		int offset = 0;
		for (int i = inOrderStart; i < inOrderEnd; i++) {
			if (inOrder[i].equals(root)) {
				offset = i - inOrderStart;
				inOrderRootPosition = i;
				break;
			}
		}

		findPreOrder(inOrderStart, inOrderRootPosition, postOrderStart, postOrderStart + offset);
		findPreOrder(inOrderRootPosition + 1, inOrderEnd, postOrderStart + offset, postOrderEnd - 1);

	}

}
