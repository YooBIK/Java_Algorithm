package Study.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 방법
 *  1. 모든 물고기들의 (번호 : 좌표)를 해쉬 맵에 저장해둔다.
 *  2. 3차원 맵을 사용해 [x][y][0] 에는 물고기 번호, [x][y][1] 에는 방향 을 넣어둔다.
 *  3. 해쉬맵 Key(물고기 번호) - Set을 정렬해서 순서대로 문제 조건대로 이동시킨다. 
 *  4. 상어를 이동시킨다. 재귀를 통해 먹을 수 있는 모든 경우를 탐색한다. (이동할 수 있는 칸이 없으면 종료)
 *  3,4 반복
 */

public class BOJ19236_청소년상어 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    static int[] dirRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dirCol = {0, -1, -1, -1, 0, 1, 1, 1};

    static final int SHARK = -1;

    static int[][][] map = new int[4][4][2];
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        HashMap<Integer, int[]> positionMap = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(stringTokenizer.nextToken());
                int direction = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                map[i][j][0] = fishNum;
                map[i][j][1] = direction;
                positionMap.put(fishNum, new int[]{i, j});
            }
        }

        int curValue = map[0][0][0];		// 시작 점수는 0,0에 있는 물고기의 번호
        positionMap.remove(map[0][0][0]);	// 0,0에 있는 물고기를 해시맵에서 제거한다.
        map[0][0][0] = SHARK;				// 0,0을 상어로 표시
        int[] sharkInfo = new int[]{0, 0};	// 현재 상어 정보 저장

        play(copyMap(map), sharkInfo, curValue, positionMap);
        System.out.println(answer);
    }

    private static void play(int[][][] curMap, int[] sharkInfo, int curValue, HashMap<Integer, int[]> positionMap) {
        answer = Math.max(answer, curValue);
        List<Integer> fishList = new ArrayList<>(positionMap.keySet());
        Collections.sort(fishList);


        for (Integer fishNumber : fishList) {
            moveFish(curMap, fishNumber, positionMap);	// 물고기 이동!
        }

        int sharkRow = sharkInfo[0];
        int sharkCol = sharkInfo[1];
        int sharkDir = curMap[sharkRow][sharkCol][1];
        int newSharkRow = sharkRow + dirRow[sharkDir];
        int newSharkCol = sharkCol + dirCol[sharkDir];
        while (isValidShark(newSharkRow, newSharkCol)) {
            if (curMap[newSharkRow][newSharkCol][0] != 0) {
                HashMap<Integer, int[]> tempFishMap = new HashMap<>(positionMap);
                int[][][] tempMap = copyMap(curMap);
                int fishNumber = tempMap[newSharkRow][newSharkCol][0];
                int fishDir = tempMap[newSharkRow][newSharkCol][1];
                tempMap[newSharkRow][newSharkCol][0] = SHARK;
                tempMap[newSharkRow][newSharkCol][1] = fishDir;

                tempMap[sharkRow][sharkCol][0] = 0;
                tempMap[sharkRow][sharkCol][1] = 0;
                tempFishMap.remove(fishNumber);
                play(tempMap, new int[]{newSharkRow, newSharkCol}, curValue + fishNumber, tempFishMap);
            }
            newSharkRow += dirRow[sharkDir];
            newSharkCol += dirCol[sharkDir];
        }
    }


    private static int[][][] copyMap(int[][][] curMap) {
        int[][][] temp = new int[4][4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 2; k++) {
                    temp[i][j][k] = curMap[i][j][k];
                }
            }
        }
        return temp;
    }


    private static boolean isValidShark(int newSharkRow, int newSharkCol) {
        return newSharkRow >= 0 && newSharkRow < 4 && newSharkCol >= 0 && newSharkCol < 4;
    }

    private static void moveFish(int[][][] curMap, int fishNumber, HashMap<Integer, int[]> positionMap) {
        int[] curFishInfo = positionMap.get(fishNumber);
        int curRow = curFishInfo[0];
        int curCol = curFishInfo[1];
        int curDir = curMap[curRow][curCol][1];

        for (int i = curDir; i < curDir + 8; i++) {	// 8방 체크 
            int newRow = curRow + dirRow[i % 8];	// 
            int newCol = curCol + dirCol[i % 8];
            if (isValid(curMap, newRow, newCol)) {
                if (curMap[newRow][newCol][0] == 0) {
                    positionMap.put(fishNumber, new int[]{newRow, newCol});
                    curMap[newRow][newCol][0] = fishNumber;
                    curMap[newRow][newCol][1] = i % 8;

                    curMap[curRow][curCol][0] = 0;
                    curMap[curRow][curCol][1] = 0;
                } else {
                    int anotherFishNumber = curMap[newRow][newCol][0];
                    int anotherFishDir = curMap[newRow][newCol][1];


                    positionMap.put(fishNumber, new int[]{newRow, newCol});
                    positionMap.put(anotherFishNumber, new int[]{curRow, curCol});
                    curMap[newRow][newCol][0] = fishNumber;
                    curMap[newRow][newCol][1] = i % 8;

                    curMap[curRow][curCol][0] = anotherFishNumber;
                    curMap[curRow][curCol][1] = anotherFishDir;

                }
                break;
            }
        }
    }

    private static boolean isValid(int[][][] curMap, int newRow, int newCol) {
        return newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4 && curMap[newRow][newCol][0] != SHARK;
    }


}
