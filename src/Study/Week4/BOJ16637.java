package Study.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 풀이
 * - 연산자들만 따로 담아서 부분집합을 돌린다.
 * 이 때, 이전 선택한 연산자에 괄호가 쳐져있으면 다음 연산자에는 치면 안된다. 겹치는 괄호는 없기 때문
 * - 수식을 순회하면서 괄호가 쳐져있으면 연산한 결과를 담고, 아니면 그대로 담는다.
 * 연산자 이전 숫자, 연산자, 연산자 이후 숫자 를 함수에 전달 -> 연산 결과 반환
 * - 위 과정으로 괄호를 처리한 수식을 순회하면서 결과를 생성하고, 최대 값을 answer 변수에 업데이트 해준다.
 */
public class BOJ16637 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static char[] input;

    static final List<Character> OPERATOR_LIST = Arrays.asList('+', '-', '*');
    static boolean[] selected;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(bufferedReader.readLine());
        input = bufferedReader.readLine().toCharArray();

        if (N == 1) {   // 길이가 1이면 그대로 리턴
            System.out.println(input[0]);
            return;
        }

        selected = new boolean[N / 2];

        subset(0, true);

        System.out.println(answer);
    }

    private static void subset(int depth, boolean before) {

        if (depth == (N / 2)) {
            List<Integer> numbers = new ArrayList<>();
            List<Character> operators = new ArrayList<>();
            for (int i = 0; i < input.length; i++) {    // 괄호가 쳐진 연산자의 경우
                if (i % 2 == 1 && selected[(i - 1) / 2]) {
                    numbers.remove(numbers.size() - 1); // 숫자가 이미 담겨있기 때문에 지워준다.
                    numbers.add(apply(input[i - 1] - '0', input[i + 1] - '0', input[i])); // 연산 결과를 넣는다.
                    i++;    // 다음 숫자가 담기지 않도록 i를 하나 더 증가시켜준다.
                } else {
                    if (OPERATOR_LIST.contains(input[i])) { // 연산자라면 그대로 담고
                        operators.add(input[i]);
                    } else {                                // 아니면 숫자를 담는다.
                        numbers.add((input[i]) - '0');
                    }
                }
            }
            int result = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {            // 이후 숫자들과 연산자를 계산한다.
                result = apply(result, numbers.get(i + 1), operators.get(i));
            }
            answer = Math.max(answer, result);
            return;
        }

        if (depth == 0) {       // 부분집합을 생성할 때, 이전에 선택했다면 다음은 선택하지 않는다.
            selected[depth] = false;
            subset(depth + 1, false);
            selected[depth] = true;
            subset(depth + 1, true);
        } else {
            if (before) {
                selected[depth] = false;
                subset(depth + 1, false);
            } else {
                selected[depth] = false;
                subset(depth + 1, false);
                selected[depth] = true;
                subset(depth + 1, true);
            }
        }


    }


    private static int apply(int left, int right, char operator) {

        int result = Integer.MAX_VALUE;
        switch (operator) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            case '*':
                result = left * right;
                break;
        }
        return result;
    }

}
