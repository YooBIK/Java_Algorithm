package Study.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 1. 회의를 시작이간이 빠른 순서로 정렬한다.
 * 2. 회의를 배정한다.
 *  2.1 진행중인 회의가 하나도 없다면 회의실을 배정한다.
 *  2.2 진행중인 회의가 있다면 진행중인 회의 중 끝나는 시간이 시작시간보다 작은지 비교한다.
 *      -> 이 떄 존재한다면, 그 회의실을 선택한다.
 *      -> 하나도 존재하지 않는다면 새로운 회의실을 배정한다.
 */
public class BOJ19598 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        List<long[]> meetingList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            long start = Long.parseLong(stringTokenizer.nextToken());
            long end = Long.parseLong(stringTokenizer.nextToken());
            meetingList.add(new long[]{start, end});
        }
        Collections.sort(meetingList, (o1, o2) -> o1[0] == o2[0] ? Long.compare(o1[1], o2[1]) : Long.compare(o1[0], o2[0]));

        List<Long> usingMeetingRooms = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long[] curMeeting = meetingList.get(i);
            if (usingMeetingRooms.isEmpty()) {
                usingMeetingRooms.add(curMeeting[1]);
            } else {
                if (!isExist(usingMeetingRooms, curMeeting)) {
                    usingMeetingRooms.add(curMeeting[1]);
                }
            }
        }
        System.out.println(usingMeetingRooms.size());
    }

    private static boolean isExist(List<Long> usingMeetingRooms, long[] curMeeting) {
        for (int i = 0; i < usingMeetingRooms.size(); i++) {
            if (usingMeetingRooms.get(i) <= curMeeting[0]) {
                usingMeetingRooms.remove(i);
                usingMeetingRooms.add(curMeeting[1]);
                return true;
            }
        }
        return false;
    }

}
