package Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1991 {

	static int[][] tree = new int[26][2];
	static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stringTokenizer;
	static StringBuilder stringBuilder = new StringBuilder();

	public static void printTree(int current, int mode) {

		if (mode == 0) {
			stringBuilder.append((char) (current + 'A'));
			if (tree[current][0] != -1) {
				printTree(tree[current][0], 0);
			}
			if (tree[current][1] != -1) {
				printTree(tree[current][1], 0);
			}
		} else if (mode == 1) {
			if (tree[current][0] != -1) {
				printTree(tree[current][0], 1);
			}
			stringBuilder.append((char) (current + 'A'));
			if (tree[current][1] != -1) {
				printTree(tree[current][1], 1);
			}
		} else if (mode == 2) {
			if (tree[current][0] != -1) {
				printTree(tree[current][0], 2);
			}
			if (tree[current][1] != -1) {
				printTree(tree[current][1], 2);
			}
			stringBuilder.append((char) (current + 'A'));
		}

        if (mode == 0) {
            stringBuilder.append((char) (current + 'A'));
            if (tree[current][0] != -1) {
                printTree(tree[current][0], 0);
            }
            if (tree[current][1] != -1) {
                printTree(tree[current][1], 0);
            }
        } else if (mode == 1) {
            if (tree[current][0] != -1) {
                printTree(tree[current][0], 1);
            }
            stringBuilder.append((char) (current + 'A'));
            if (tree[current][1] != -1) {
                printTree(tree[current][1], 1);
            }
        } else if (mode == 2) {
            if (tree[current][0] != -1) {
                printTree(tree[current][0], 2);
            }
            if (tree[current][1] != -1) {
                printTree(tree[current][1], 2);
            }
            stringBuilder.append((char) (current + 'A'));
        }

	public static void main(String[] args) throws IOException {
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int nodes = Integer.parseInt(stringTokenizer.nextToken());
		for (int[] arr : tree) {
			Arrays.fill(arr, -1);
		}
		for (int i = 0; i < nodes; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int currentNode = stringTokenizer.nextToken().charAt(0) - 'A';
			int leftChild = stringTokenizer.nextToken().charAt(0) - 'A';
			int rightChild = stringTokenizer.nextToken().charAt(0) - 'A';
			if (leftChild >= 0 && leftChild < nodes) {
				tree[currentNode][0] = leftChild;
			}
			if (rightChild >= 0 && rightChild < nodes) {
				tree[currentNode][1] = rightChild;
			}

		}

		for (int i = 0; i < 3; i++) {
			printTree(0, i);
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);
	}

}
