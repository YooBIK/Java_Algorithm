package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2563_S5_색종이_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();
    static StringTokenizer stringTokenizer;

    static int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 0; testCase < N; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int col = Integer.parseInt(stringTokenizer.nextToken());
            int row = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = row; i < row + 10; i++) {
                for (int j = col; j < col + 10; j++) {
                    map[i][j] = 1;
                }
            }
        }

		int result =0;
		for(int i = 0 ;i<=100;i++){
			for (int j = 0; j <= 100; j++) {
				if(map[i][j]==1) result++;
			}
		}
		System.out.println(result);

    }

}
