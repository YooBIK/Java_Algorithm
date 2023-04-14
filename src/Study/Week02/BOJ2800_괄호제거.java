package Study.Week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ2800_괄호제거 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();
    static HashMap<Integer, Integer> pairMap = new HashMap<>();
    static List<String> resultList = new ArrayList<>();
    static List<Integer> keyList;
    static List<Integer> valueList;
    static String inputString;

    public static void main(String[] args) throws IOException {

        inputString = bufferedReader.readLine();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '(') {
                stack.push(i);
            } else if(inputString.charAt(i) == ')'){
                pairMap.put(stack.poll(), i);
            }
        }

        keyList = new ArrayList<>(pairMap.keySet());
        for (int i = 1; i <= pairMap.size(); i++) {
            List<Integer> selectedKey = new ArrayList<>();
            List<Integer> selectedValue = new ArrayList<>();
            printExpression(i, 0, selectedKey, selectedValue);
        }
        Collections.sort(resultList);
        for (String s : resultList) {
            stringBuilder.append(s).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static void printExpression(int depth, int startIndex, List<Integer> selectedKey, List<Integer> selectedValue) {

        if (depth == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputString.length(); i++) {
                if (!selectedKey.contains(i) && !selectedValue.contains(i)) {
                    sb.append(inputString.charAt(i));
                }
            }
            if(!resultList.contains(sb.toString())){
                resultList.add(sb.toString());
            }
        }

        for (int i = startIndex; i < keyList.size(); i++) {
            selectedKey.add(keyList.get(i));
            selectedValue.add(pairMap.get(keyList.get(i)));
            printExpression(depth - 1, i + 1, selectedKey, selectedValue);
            selectedKey.remove(selectedKey.size() - 1);
            selectedValue.remove(selectedValue.size() - 1);
        }

    }
}
