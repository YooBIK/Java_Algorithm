package Study.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class ScoreInfo {
    int first;
    int second;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreInfo scoreInfo = (ScoreInfo) o;
        if (this.first > scoreInfo.first && this.second > scoreInfo.second) {
            return false;
        }

        if (this.first < scoreInfo.first && this.second < scoreInfo.second) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public ScoreInfo(int first, int second) {
        this.first = first;
        this.second = second;


    }
}

public class BOJ1946_신입사원 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            Set<ScoreInfo> set = new HashSet<>(N);
            List<ScoreInfo> list = new ArrayList<>(N);
            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int first = Integer.parseInt(stringTokenizer.nextToken());
                int second = Integer.parseInt(stringTokenizer.nextToken());
                list.add(new ScoreInfo(first, second));
            }
            Collections.sort(list, (o1, o2) -> Integer.compare(o1.first, o2.first));
            int result = 0;
            int minVal = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                if (list.get(i).second < minVal) {
                    minVal = list.get(i).second;
                    result++;
                }
            }
            stringBuilder.append(result).append("\n");
        }
        System.out.println(stringBuilder);

    }
}
