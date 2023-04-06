package Study.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    int start;
    int end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.end == o.end) {
            return Integer.compare(this.start, o.start);
        }
        return Integer.compare(this.end, o.end);
    }

}

public class BOJ1931_회의실배정 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            list.add(new Pair(start, end));
        }

        Collections.sort(list);
        int curEnd = 0;
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            Pair curPair = list.get(i);
            if (curPair.start >= curEnd) {
                curEnd = curPair.end;
                result++;
            }
        }
        System.out.println(result);
    }

}
