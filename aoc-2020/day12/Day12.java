import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day12/day12-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        int direction = 90;
        int ns = 0, ew = 0;
        for (String input : inputList) {
            char action = input.charAt(0);
            int value = Integer.parseInt(input.substring(1));
            switch (action) {
                case 'N':
                    ns += value;
                    break;
                case 'S':
                    ns -= value;
                    break;
                case 'E':
                    ew += value;
                    break;
                case 'W':
                    ew -= value;
                    break;
                case 'L':
                    direction -= value;
                    direction %= 360;
                    if (direction < 0) {
                        direction += 360;
                    }
                    break;
                case 'R':
                    direction += value;
                    direction %= 360;
                    break;
                case 'F':
                    if (direction == 0) {
                        ns += value;
                    } else if (direction == 90) {
                        ew += value;
                    } else if (direction == 180) {
                        ns -= value;
                    } else if (direction == 270) {
                        ew -= value;
                    }
                    break;
            }
        }
        System.out.println(Math.abs(ew) + Math.abs(ns));
    }

    public static void part2(List<String> inputList) {
        int nsWaypoint = 1;
        int ewWaypoint = 10;
        int ns = 0, ew = 0;
        for (String input : inputList) {
            char action = input.charAt(0);
            int value = Integer.parseInt(input.substring(1));
            switch (action) {
                case 'N':
                    nsWaypoint += value;
                    break;
                case 'S':
                    nsWaypoint -= value;
                    break;
                case 'E':
                    ewWaypoint += value;
                    break;
                case 'W':
                    ewWaypoint -= value;
                    break;
                case 'L':
                    for (int i = 0; i < value % 360; i += 90) {
                        int temp = nsWaypoint;
                        nsWaypoint = ewWaypoint;
                        ewWaypoint = temp * -1;
                    }
                    break;
                case 'R':
                    for (int i = 0; i < value % 360; i += 90) {
                        int temp = ewWaypoint;
                        ewWaypoint = nsWaypoint;
                        nsWaypoint = temp * -1;
                    }
                    break;
                case 'F':
                    ns += nsWaypoint * value;
                    ew += ewWaypoint * value;
                    break;
            }
        }
        System.out.println(Math.abs(ew) + Math.abs(ns));
    }
}
