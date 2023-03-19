package etc;

public class BOJ4673 {

    static StringBuilder stringBuilder = new StringBuilder();
    static int[] numbers= new int[10001];


    public static void main(String[] args) {


        for(int i =1;i<=10001;i++){
            int startNumber = i;
            while(true){
                int beforeNumber = startNumber;
                int temp = beforeNumber;
                int nextNumber = startNumber;
                while(temp!=0){
                    nextNumber += temp%10;
                    temp /= 10;
                }
                if(nextNumber <10001){
                    numbers[nextNumber] = beforeNumber;
                    startNumber = nextNumber;
                }else{
                    break;
                }

            }
        }
        for(int i =1;i<=10000;i++){
            if(numbers[i] == 0){
                stringBuilder.append(i).append("\n");
            }
        }
        System.out.println(stringBuilder);

    }
}
