package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ23253 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        List<LinkedList<Integer>> stackList = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int dummySize = Integer.parseInt(stringTokenizer.nextToken());

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            LinkedList<Integer> curStack = new LinkedList<>();
            for (int j = 0; j < dummySize; j++) {
                curStack.push(Integer.parseInt(stringTokenizer.nextToken()));
            }
            stackList.add(curStack);
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            boolean flag = false;
            for (LinkedList<Integer> curStack : stackList) {
                if (!curStack.isEmpty() && curStack.peek() == i) {
                    resultList.add(curStack.pop());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        if(resultList.size() == N){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}
