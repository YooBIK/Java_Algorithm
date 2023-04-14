package Study.Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 내가 가진 카드 묶음 중에서 가장 작은 크기의 카드 묶음 2개를 뽑아서 합친다.
 * 이를 반복하면서 카드 뭉치가 하나가 될 때 까지 반복한다.
 * Priority Queue 를 사용했다.
 */
public class BOJ1715_카드정렬하기 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            priorityQueue.add(Integer.parseInt(bufferedReader.readLine()));
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }
        int result = 0;
        while (true) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            result = result + first + second;
            if (priorityQueue.isEmpty()) {
                break;
            }
            priorityQueue.add(first + second);
        }

        System.out.println(result);
    }
}




