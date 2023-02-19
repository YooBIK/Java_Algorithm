package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11286 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)
                -> Math.abs(o1) == Math.abs(o2) ? Integer.compare(o1, o2) : Integer.compare(Math.abs(o1), Math.abs(o2)));
        for (int i = 0; i < N; i++) {
            int curVal = Integer.parseInt(bufferedReader.readLine());
            if(curVal == 0 ){
                if(pq.isEmpty()){
                    stringBuilder.append(0).append("\n");
                }else{
                    stringBuilder.append(pq.poll()).append("\n");
                }
            }else{
                pq.offer(curVal);
            }
        }
        System.out.println(stringBuilder);

    }

}
