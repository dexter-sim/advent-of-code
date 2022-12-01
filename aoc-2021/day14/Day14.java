import java.io.*;
import java.util.*;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day14-input.txt"));
        String s = scanner.nextLine();
        scanner.nextLine();
        Map<String, String> map = new HashMap<>();
        while (scanner.hasNext()){
            String[] temp = scanner.nextLine().split(" -> ");
            map.put(temp[0], temp[1]);
        }
        scanner.close();
        for (int i = 0; i < 10; i++){
            s = insertionPart1(s, map);
        }
        long max = 0;
        long min = Long.MAX_VALUE;
        for (int i = 65; i <= 90; i++){
            long count = 0;
            for (char ch : s.toCharArray()){
                if (ch == (char) i){
                    count++;
                }
            }
            if (count > 0) {
                max = Math.max(count, max);
                min = Math.min(count, min);
            }
        }
        System.out.println(max - min);
    }

    public static String insertionPart1(String s, Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++){
            builder.append(s.charAt(i));
            builder.append(map.get(s.substring(i, i + 2)));
        }
        builder.append(s.charAt(s.length() - 1));
        return builder.toString();
    }

    public static void part2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day14-input.txt"));
        Map<String, Long> stringMap = new HashMap<>();
        String s = scanner.nextLine();
        for (int i = 0; i < s.length() - 1; i++){
            if (stringMap.containsKey(s.substring(i, i + 2))){
                stringMap.replace(s.substring(i, i + 2), stringMap.get(s.substring(i, i + 2)) + 1);
            } else {
                stringMap.put(s.substring(i, i + 2), 1L);
            }
        }
        scanner.nextLine();
        Map<String, String> map = new HashMap<>();
        while (scanner.hasNext()){
            String[] temp = scanner.nextLine().split(" -> ");
            map.put(temp[0], temp[1]);
        }
        scanner.close();
        for (int i = 0; i < 40; i++){
            stringMap = insertionPart2(stringMap, map);
        }
        long[] arr = new long[26];
        for (String temp : stringMap.keySet()){
            long num = stringMap.get(temp);
            arr[(int) temp.charAt(0) - 65] += num;
        }
        arr[(int) s.charAt(s.length() - 1) - 65] += 1L;

        long max = 0;
        long min = Long.MAX_VALUE;
        for (long n : arr){
            if (n != 0){
                max = Math.max(n, max);
                min = Math.min(n, min);
            }
        }
        System.out.println(max - min);
    }

    public static Map<String, Long> insertionPart2(Map<String, Long> stringMap, Map<String, String> map) {
        Map<String, Long> newMap = new HashMap<>();
        for (String s : stringMap.keySet()){
            long number = stringMap.get(s);
            String first = s.substring(0, 1) + map.get(s);
            String second = map.get(s) + s.substring(1);
            if (newMap.containsKey(first)){
                newMap.replace(first, newMap.get(first) + number);
            } else {
                newMap.put(first, number);
            }
            if (newMap.containsKey(second)){
                newMap.replace(second, newMap.get(second) + number);
            } else {
                newMap.put(second, number);
            }
        }
        return newMap;
    }
}