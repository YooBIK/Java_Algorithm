package Study.Week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1043_거짓말 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int N, M;
    static List<Integer>[] partyList;   // i번째 사람이 참석하는 파티들을 저장
    static List<Integer>[] personList;  // i번째 파티에 참석하는 사람들을 저장
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        check = new boolean[M];
        partyList = new ArrayList[N];
        personList = new ArrayList[M];


        for (int i = 0; i < N; i++) {
            partyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            personList[i] = new ArrayList<>();
        }

        List<Integer> startList = new ArrayList<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int T = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < T; i++) {
            startList.add(Integer.parseInt(stringTokenizer.nextToken()) - 1);
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            T = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < T; j++) {
                int curPerson = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                partyList[curPerson].add(i);
                personList[i].add(curPerson);
            }
        }


        bfs(startList);

        int count =0;
        for (int i = 0; i < M; i++) {
            if(!check[i]) count++;
        }
        System.out.println(count);


    }

    private static void bfs(List<Integer> trueList) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i : trueList) {
            for (int j : partyList[i]) {      // i번째 사람이 참석하는 파티들 순회
                queue.offer(j);
                check[j] = true;            // 해당 파티에서는 거짓말 불가능
            }
        }

        while (!queue.isEmpty()) {
            int curParty = queue.poll();

            for (int curPerson : personList[curParty]) {
                for (int nextParty : partyList[curPerson]) {
                    if (!check[nextParty]) {
                        queue.offer(nextParty);
                        check[nextParty] = true;
                    }
                }
            }
        }
    }
}
