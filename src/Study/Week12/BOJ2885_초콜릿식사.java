package Study.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2885_초콜릿식사 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(bufferedReader.readLine());


        int max = 1 << 20;


        int firstMax = -1;
        int temp = K;

        boolean isValid = true;
        while (true) {
            if (temp == 1 || temp == 0) break;
            if (temp % 2 == 1) {
                isValid = false;
                break;
            }


            temp /= 2;
        }

        if (isValid) {
            System.out.println(K + " " + 0);
            return;
        }

        temp = K;
        int count = 0;
        boolean flag = false;
        while (temp > 0) {
            if (temp < max) {
                max /= 2;
                continue;
            }

            if (!flag) {
                firstMax = max * 2;
                flag = true;
            }


            temp -= max;
            count++;
        }

        System.out.println(firstMax + " " + count);


    }


}
