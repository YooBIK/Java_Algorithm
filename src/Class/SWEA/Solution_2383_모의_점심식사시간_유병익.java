package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2383_모의_점심식사시간_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static int N;
    static int[][] map;

    static boolean[] isArrive;

    static int[] dirRow = {0, 0, -1, 1};
    static int[] dirCol = {-1, 1, 0, 0};

    static HashMap<Integer, int[]> stairMap = new HashMap<>();

    static int answer;

    static class Person implements Cloneable {

        int personNumber;

        int row;
        int col;
        int target;
        int startTime;


        public Person(int personNumber, int row, int col, int target, int startTime) {
            super();
            this.row = row;
            this.col = col;
            this.personNumber = personNumber;
            this.target = target;
            this.startTime = startTime;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Person{");
            sb.append("personNumber=").append(personNumber);
            sb.append(", row=").append(row);
            sb.append(", col=").append(col);
            sb.append(", target=").append(target);
            sb.append(", arriveTime=").append(startTime);
            sb.append('}');
            return sb.toString();
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Person(this.personNumber, this.row, this.col, this.target, this.startTime);
        }

    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {

        int T = Integer.parseInt(bufferedReader.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[N][N];

            List<Person> personList = new ArrayList<>();
            int personNumber = 0;
            int stairNumber = 0;
            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < N; j++) {
                    int curVal = Integer.parseInt(stringTokenizer.nextToken());
                    if (curVal == 1) {
                        personList.add(new Person(++personNumber, i, j, -1, 0));
                    }

                    if (curVal > 1) {
                        stairMap.put(++stairNumber, new int[]{i, j,curVal});
                    }
                }
            }
            answer = Integer.MAX_VALUE;
            dfs(0, personList);
            stringBuilder.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.println(stringBuilder);

    }


    private static void dfs(int depth, List<Person> personList) throws CloneNotSupportedException {

        if (depth == personList.size()) {
            startBfs(personList);
            return;
        }

        personList.get(depth).target = 1;
        dfs(depth + 1, personList);
        personList.get(depth).target = 2;
        dfs(depth + 1, personList);

    }

    private static void startBfs(List<Person> personList) throws CloneNotSupportedException {
        boolean[][][] visit = new boolean[N][N][personList.size() + 1];
        isArrive = new boolean[personList.size() + 1];

        Queue<Person> queue = new ArrayDeque<>();
        for (int i = 0; i < personList.size(); i++) {
            Person curPerson = (Person) personList.get(i).clone();
            visit[curPerson.row][curPerson.col][curPerson.personNumber] = true;
            queue.offer(curPerson);
        }

        int curTime = 0;

        int firstValue = stairMap.get(1)[2];
        int secondValue = stairMap.get(2)[2];

        Queue<Person> firstStair = new ArrayDeque<>();
        Queue<Person> secondStair = new ArrayDeque<>();

        Queue<Person> firstWaitingQueue = new ArrayDeque<>();
        Queue<Person> secondWaitingQueue = new ArrayDeque<>();

        while (true) {

            while (!firstStair.isEmpty() && curTime >= firstStair.peek().startTime + firstValue) {
                firstStair.poll();
            }

            while (!firstWaitingQueue.isEmpty() && firstStair.size() < 3) {
                Person person = firstWaitingQueue.poll();
                person.startTime = curTime;
                firstStair.offer(person);
            }

            while (!secondStair.isEmpty() && curTime >= secondStair.peek().startTime + secondValue) {
                secondStair.poll();
            }

            while (!secondWaitingQueue.isEmpty() && secondStair.size() < 3) {
                Person person = secondWaitingQueue.poll();
                person.startTime = curTime;
                secondStair.offer(person);
            }

            if(firstWaitingQueue.isEmpty() && firstStair.isEmpty() && secondWaitingQueue.isEmpty() && secondStair.isEmpty() && queue.isEmpty()){
                break;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Person curPerson = queue.poll();

                if (isArrive[curPerson.personNumber]) {
                    continue;
                }

                int[] target = stairMap.get(curPerson.target);
                if (target[0] == curPerson.row && target[1] == curPerson.col) {
                    if (curPerson.target == 1) {
                        firstWaitingQueue.offer(curPerson);
                    } else {
                        secondWaitingQueue.offer(curPerson);
                    }
                    isArrive[curPerson.personNumber] = true;
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    int newRow = curPerson.row + dirRow[j];
                    int newCol = curPerson.col + dirCol[j];
                    if (boundaryCheck(newRow, newCol) && !visit[newRow][newCol][curPerson.personNumber]) {
                        visit[newRow][newCol][curPerson.personNumber] = true;
                        queue.offer(new Person(curPerson.personNumber, newRow, newCol, curPerson.target, curTime));
                    }
                }
            }

            curTime++;
        }
        answer = Math.min(answer, curTime);
    }

    private static boolean boundaryCheck(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

}
