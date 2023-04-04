package Class.Live.String;

/**
 * 정수로 이루어진 배열에서 길이가 4인 sub 배열의 합계가 가장 큰 서브 배열 구하기 ex) {2,4,7,10,8,4,5,6,7,1}
 */
public class SlidingWindowTest {

	static int[] numbers = { 2, 4, 7, 10, 8, 4, 5, 6, 7, 1 };

	public static void main(String[] args) {

		int max = 0;
		int sum = 0;
		int len = 4;

		for (int i = 0; i < len; i++) {
			sum += numbers[i];
		}
		max = sum;

		for (int i = 0; i < numbers.length - len; i++) {
			sum -= numbers[i];
			sum += numbers[i+len];
			max = Math.max(max, sum);
		}


		System.out.println(max);

	}

}
