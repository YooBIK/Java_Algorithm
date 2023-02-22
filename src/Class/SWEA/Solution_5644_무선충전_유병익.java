package Class.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5644_무선충전_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static List<Integer>[][] map = new ArrayList[10][10];
    static boolean[][] visit = new boolean[10][10];
    static HashMap<Integer, Integer> BCMap = new HashMap<>();

    static int[] dirRow = {0, -1, 0, 1, 0};
    static int[] dirCol = {0, 0, 1, 0, -1};

    static int M; // 이동 시간
    static int A; // BC 총 개수


    static List<Integer> userA = new ArrayList<>();
    static List<Integer> userB = new ArrayList<>();
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            init();

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            M = Integer.parseInt(stringTokenizer.nextToken());
            A = Integer.parseInt(stringTokenizer.nextToken());

            isUsed = new boolean[A];

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            userA.add(0);
            userB.add(0);

            // A의 이동 정보
            for (int i = 0; i < M; i++) {
                userA.add(Integer.parseInt(stringTokenizer.nextToken()));
            }
            // B의 이동 정보
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < M; i++) {
                userB.add(Integer.parseInt(stringTokenizer.nextToken()));
            }
            for (int i = 0; i < A; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());

                int col = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int row = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int range = Integer.parseInt(stringTokenizer.nextToken());
                int power = Integer.parseInt(stringTokenizer.nextToken());
                BCMap.put(i, power);
                for (boolean[] arr : visit) {
                    Arrays.fill(arr, false);
                }
                visit[row][col] = true;
                dfs(row, col, row, col, i, range);
            }

            for (List<Integer>[] listArr : map) {
                for (List<Integer> list : listArr) {
                    Collections.sort(list, (o1, o2) -> Integer.compare(BCMap.get(o2), BCMap.get(o1)));
                }
            }

            stringBuilder.append("#").append(testCase).append(" ").append(getResult()).append("\n");
        }
        System.out.println(stringBuilder);

    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        BCMap.clear();
        userA.clear();
        userB.clear();
    }

    private static int getResult() {
        int userARow = 0;
        int userACol = 0;

        int userBRow = 9;
        int userBCol = 9;

        int result = 0;
        int time = 0;

        while (time <= M) {

            userARow = userARow + dirRow[userA.get(time)];
            userACol = userACol + dirCol[userA.get(time)];

            userBRow = userBRow + dirRow[userB.get(time)];
            userBCol = userBCol + dirCol[userB.get(time)];

            List<Integer> curUserA = map[userARow][userACol];
            List<Integer> curUserB = map[userBRow][userBCol];

            if (curUserA.size() == 0 && curUserB.size() == 0) {
                time++;
                continue;
            }

            if (curUserA.size() == 0) {
                result += BCMap.get(curUserB.get(0));
                time++;
                continue;
            }

            if (curUserB.size() == 0) {
                result += BCMap.get(curUserA.get(0));
                time++;
                continue;
            }

            //A가 하나만 사용 가능할 때,
            if (curUserA.size() == 1) {
                if (curUserB.size() == 1) {                 // A도 하나 B도 하나만 사용 가능
                    int userAUsingBC = curUserA.get(0);
                    int userBUsingBC = curUserB.get(0);
                    if (userAUsingBC == userBUsingBC) {   // 만약 둘이 같은 BC면 하나를 같이쓴다.
                        result += BCMap.get(userAUsingBC);
                    } else {                              // 다른거면 각각 쓴다.
                        result += BCMap.get(userAUsingBC) + BCMap.get(userBUsingBC);
                    }
                } else {  //A는 1개 B는 2개 이상
                    int userAUsingBC = curUserA.get(0);
                    int userBUsingBC = curUserB.get(0);
                    if (userAUsingBC == userBUsingBC) {   // 만약 둘이 같은 BC면 B가 양보하고 다른 하나를 쓴다.
                        userBUsingBC = curUserB.get(1);
                    }
                    result += BCMap.get(userAUsingBC) + BCMap.get(userBUsingBC);
                }
            } else {
                // A는 2개 이상, B는 하나
                if (curUserB.size() == 1) {
                    int userAUsingBC = curUserA.get(0);
                    int userBUsingBC = curUserB.get(0);
                    // 만약 둘이 같은 BC면 A가 양보하고 다른 하나를 쓴다.
                    if (userAUsingBC == userBUsingBC) {
                        userAUsingBC = curUserA.get(1);
                    }
                    result += BCMap.get(userAUsingBC) + BCMap.get(userBUsingBC);
                } else {        // A도 2개 이상, B도 2개 이상 사용 가능할 때,
                    int userAUsingBC = curUserA.get(0);
                    int userBUsingBC = curUserB.get(0);
                    // 최대 출력이 BC가 같은놈이면 ?
                    if (userAUsingBC == userBUsingBC) {
                        // 두번째로 출력이 높은 BC를 가진 놈이 쓴다.
                        if (BCMap.get(curUserA.get(1)) > BCMap.get(curUserB.get(1))) {
                            userAUsingBC = curUserA.get(1);
                        } else {
                            userBUsingBC = curUserB.get(1);
                        }
                    }
                    result += BCMap.get(userAUsingBC) + BCMap.get(userBUsingBC);
                }
            }
            time++;
        }


        return result;
    }

    public static void dfs(int row, int col, int startRow, int startCol, int BCNum, int targetDepth) {

        map[row][col].add(BCNum);

        if (Math.abs(row - startRow) + Math.abs(col - startCol) == targetDepth) {
            return;
        }

        for (int i = 1; i < 5; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
                if (!visit[newRow][newCol]) {
                    visit[newRow][newCol] = true;
                    dfs(newRow, newCol, startRow, startCol, BCNum, targetDepth);
                }
            }

        }
    }

}
