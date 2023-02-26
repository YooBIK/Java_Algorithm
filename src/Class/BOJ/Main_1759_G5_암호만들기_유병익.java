package Class.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1759_G5_암호만들기_유병익 {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();
    static StringTokenizer stringTokenizer;

    static int N, R;
    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
    static List<Character> vowelList = new ArrayList<>();   // 모음
    static List<Character> consonantList = new ArrayList<>();   // 자음

    static Set<List<Character>> resultSet = new HashSet<>();


    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            char curValue = stringTokenizer.nextToken().charAt(0);
            if (vowels.contains(curValue)) {
                vowelList.add(curValue);
            } else {
                consonantList.add(curValue);
            }
        }
        select(1, 2);
        List<List<Character>> resultList = new ArrayList<>(resultSet);
        for(int i=0;i<resultList.size();i++){
            Collections.sort(resultList.get(i));
        }

        Collections.sort(resultList, (o1, o2) -> o1.toString().compareTo(o2.toString()));
        for(List<Character> list : resultList){
            for(char c : list){
                stringBuilder.append(c);
            }
            stringBuilder.append("\n");
        }
        System.out.print(stringBuilder);
    }

    public static void select(int vowelCount, int consonantCount) {

        if (vowelCount + consonantCount > R) return;

        if (vowelCount <= vowelList.size() && consonantCount <= consonantList.size() && vowelCount + consonantCount == R) {
            List<Character> tempList = new ArrayList<>();
            combinationVowel(0, 0, vowelCount, tempList);
            return;
        }
        select(vowelCount + 1, consonantCount);
        select(vowelCount, consonantCount + 1);
        select(vowelCount + 1, consonantCount + 1);
    }

    public static void combinationVowel(int depth, int startIndex, int targetDepth, List<Character> tempList) {
        if (depth == targetDepth) {
            combinationConsonant(0, 0, R - targetDepth, tempList);
            return;
        }

        for (int i = startIndex; i < vowelList.size(); i++) {
            tempList.add(vowelList.get(i));
            combinationVowel(depth + 1, i + 1, targetDepth, tempList);
            tempList.remove(tempList.size() - 1);
        }


    }

    public static void combinationConsonant(int depth, int startIndex, int targetDepth, List<Character> tempList) {
        if (depth == targetDepth) {
            resultSet.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = startIndex; i < consonantList.size(); i++) {
            tempList.add(consonantList.get(i));
            combinationConsonant(depth + 1, i + 1, targetDepth, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }


}
