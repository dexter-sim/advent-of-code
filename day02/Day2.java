import java.util.*;
import java.io.*;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        File file = new File("day2-input.txt");
        Scanner scanner = new Scanner(file);
        int horizontal = 0;
        int depth = 0;
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            String[] arr = s.split(" ");
            int value = Integer.parseInt(arr[1]);
            if (arr[0].equals("forward")){
                horizontal += value;
            } else if (arr[0].equals("up")){
                depth -= value;
            } else {
                depth += value;
            }
        }
        System.out.println(horizontal * depth);
        scanner.close();
    }

    public static void part2() throws FileNotFoundException {
        File file = new File("day2-input.txt");
        Scanner scanner = new Scanner(file);
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            String[] arr = s.split(" ");
            int value = Integer.parseInt(arr[1]);
            if (arr[0].equals("forward")){
                horizontal += value;
                depth += aim * value;
            } else if (arr[0].equals("up")){
                aim -= value;
            } else {
                aim += value;
            }
        }
        System.out.println(horizontal * depth);
        scanner.close();
    }
}
