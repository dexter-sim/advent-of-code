import java.util.*;
import java.io.*;

public class Day12 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day12-input.txt"));
        Map<String, List<String>> map = new HashMap<>();
        while (scanner.hasNext()) {
            String[] curr = scanner.nextLine().split("-");
            if (map.containsKey(curr[0])) {
                List<String> temp = map.get(curr[0]);
                temp.add(curr[1]);
                map.replace(curr[0], temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(curr[1]);
                map.put(curr[0], temp);
            }

            if (map.containsKey(curr[1])) {
                List<String> temp = map.get(curr[1]);
                temp.add(curr[0]);
                map.replace(curr[1], temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(curr[0]);
                map.put(curr[1], temp);
            }
        }
        scanner.close();
        System.out.println(part1(map, "start", new ArrayList<String>()));                 // Part 1 Answer
        System.out.println(part2(map, "start", new ArrayList<String>(), false));    // Part 2 Answer
    }

    public static int part1(Map<String, List<String>> map, String current, List<String> visited) {
        if (current.equals("end")) {
            return 1;
        } else if (current.equals(current.toLowerCase()) && visited.contains(current)) {
            return 0;
        } else {
            visited.add(current);
            int total = 0;
            for (String s : map.get(current)) {
                total += part1(map, s, new ArrayList<>(visited));
            }
            return total;
        }
    }

    public static int part2(Map<String, List<String>> map, String current, List<String> visited, boolean twice) {
        if (current.equals("end")) {
            return 1;
        } else if (current.equals(current.toLowerCase()) && visited.contains(current)) {
            if (twice || current.equals("start")){
                return 0;
            } else {
                twice = true;
            }
        }
        visited.add(current);
        int total = 0;
        for (String s : map.get(current)) {
            total += part2(map, s, new ArrayList<>(visited), twice);
        }
        return total;
    }

}