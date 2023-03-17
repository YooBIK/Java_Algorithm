package Study.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 경우의 수는 총 3가지다.
 * 1. 벌통은 맨 왼쪽에 있고, 꿀벌 한마리는 맨 오른쪽, 나머지 한마리는 벌통과 다른 꿀벌 사이
 * 2. 벌통은 맨 오른쪽에 있고, 꿀벌 한마리는 맨 왼쪽, 나머지 한마리는 벌통과 다른 꿀벌 사이
 * 3. 한 마리는 맨 왼쪽, 다른 한 마리는 맨 오른쪽 벌통은 그 사이
 * <p>
 * 처음 입력을 받을 때, 구간합도 같이 저장한ㄷ.
 * 이 후, 위 3가지 조건에 맞춰서 각각 최대 값을 구하고 각 최대값 중 가장 큰 값이 정답이다!!
 */
public class BOJ21758 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> numberList = new ArrayList<>();
    static List<Integer> prefixSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stringTokenizer.nextToken());
            sum += num;
            numberList.add(num);
            prefixSum.add(sum);
        }

        /*
         * 1. 벌통은 맨 왼쪽에 있고, 꿀벌 한마리는 맨 오른쪽, 나머지 한마리는 벌통과 다른 꿀벌 사이
         */
        int firstBee = 0;
        int secondBee = 1;
        int rightHoneyResult = 2 * (prefixSum.get(N - 1) - prefixSum.get(1));
        for (int i = 2; i < N; i++) {
            secondBee = i;
            rightHoneyResult = Math.max(rightHoneyResult, prefixSum.get(secondBee - 1) - prefixSum.get(firstBee) + 2 * (prefixSum.get(N - 1) - prefixSum.get(secondBee)));

        }

        /*
         * 2. 벌통은 맨 오른쪽에 있고, 꿀벌 한마리는 맨 왼쪽, 나머지 한마리는 벌통과 다른 꿀벌 사이
         */
        firstBee = N - 1;
        int leftHoneyResult = 2 * prefixSum.get(N - 3);
        for (int i = N - 3; i >= 1; i--) {
            secondBee = i;
            leftHoneyResult = Math.max(leftHoneyResult, 2 * prefixSum.get(secondBee - 1) + (prefixSum.get(firstBee - 1) - prefixSum.get(secondBee)));
        }
        int result = Math.max(rightHoneyResult, leftHoneyResult);

        /*
         * 3. 한 마리는 맨 왼쪽, 다른 한 마리는 맨 오른쪽 벌통은 그 사이
         */
        firstBee = 0;
        secondBee = N - 1;
        int centerResult = 0;
        for (int i = 1; i < N - 1; i++) {
            int curSpot = i;
            int curResult = prefixSum.get(i) - prefixSum.get(firstBee) + prefixSum.get(secondBee - 1) - prefixSum.get(curSpot - 1);
            centerResult = Math.max(centerResult, curResult);
        }

        result = Math.max(result, centerResult);
        System.out.println(result);
    }
}