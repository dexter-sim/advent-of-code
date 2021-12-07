import java.io.*;
import java.util.*;

public class Day6 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day6-input.txt"));
        ArrayList<Integer> list = new ArrayList<>();
        String[] arr = scanner.nextLine().split(",");
        for (String s : arr){
            list.add(Integer.parseInt(s));
        }
        for (int i = 0; i < 80; i++){
            int len = list.size();
            for (int j = 0; j < len; j++){
                list.set(j, list.get(j) - 1);
                if (list.get(j) < 0){
                    list.set(j, 6);
                    list.add(8);
                }
            }
        }
        System.out.println(list.size());
        scanner.close();
    }

    public static void part2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day6-input.txt"));
        String[] arr = scanner.nextLine().split(",");
        long[] counters = new long[9];
        for (String s : arr){
            counters[Integer.parseInt(s)]++;
        }
        for (int i = 0; i < 256; i++){
            long temp = counters[0];
            for (int j = 0; j < counters.length - 1; j++){
                counters[j] = counters[j + 1];
            }
            counters[counters.length - 1] = temp;
            counters[6] += temp;
        }
        long total = 0;
        for (long n : counters){
            total += n;
        }
        System.out.println(total);
        scanner.close();
    }

}