import java.io.*;
import java.util.*;

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day5-input.txt"));
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> inputList = new ArrayList<>();
        while (scanner.hasNext()){
            inputList.add(scanner.nextLine());
        }
        for (String s : inputList){
            String[] curr = s.split(" -> ");
            int x1 = Integer.parseInt(curr[0].substring(0, curr[0].indexOf(",")));
            int y1 = Integer.parseInt(curr[0].substring(curr[0].indexOf(",") + 1));
            int x2 = Integer.parseInt(curr[1].substring(0, curr[1].indexOf(",")));
            int y2 = Integer.parseInt(curr[1].substring(curr[1].indexOf(",") + 1));

            if (x1 == x2){
                for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++){
                    if (map.containsKey(x1 + "," + i)){
                        map.replace(x1 + "," + i, map.get(x1 + "," + i) + 1);
                    } else {
                        map.put(x1 + "," + i, 1);
                    }
                }
            } else if (y1 == y2){
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++){
                    if (map.containsKey(i + "," + y1)){
                        map.replace(i + "," + y1, map.get(i + "," + y1) + 1);
                    } else {
                        map.put(i + "," + y1, 1);
                    }
                }
            }
        }
        int count = 0;
        for (String s : map.keySet()){
            if (map.get(s) >= 2){
                count++;
            }
        }
        System.out.println(count); // Part 1 Answer

        for (String s : inputList){
            String[] curr = s.split(" -> ");
            int x1 = Integer.parseInt(curr[0].substring(0, curr[0].indexOf(",")));
            int y1 = Integer.parseInt(curr[0].substring(curr[0].indexOf(",") + 1));
            int x2 = Integer.parseInt(curr[1].substring(0, curr[1].indexOf(",")));
            int y2 = Integer.parseInt(curr[1].substring(curr[1].indexOf(",") + 1));
            int index = 0;
            if (Math.abs(x1 - x2) == Math.abs(y1 - y2)){
                for (int i = x1; i <= x2; i++) {
                    if (y1 < y2) {
                        int y = y1 + index;
                        if (map.containsKey(i + "," + y)) {
                            map.replace(i + "," + y, map.get(i + "," + y) + 1);
                        } else {
                            map.put(i + "," + y, 1);
                        }
                    } else {
                        int y = y1 - index;
                        if (map.containsKey(i + "," + y)) {
                            map.replace(i + "," + y, map.get(i + "," + y) + 1);
                        } else {
                            map.put(i + "," + y, 1);
                        }
                    }
                    index++;
                }
                for (int i = x1; i >= x2; i--) {
                    if (y1 < y2) {
                        int y = y1 + index;
                        if (map.containsKey(i + "," + y)) {
                            map.replace(i + "," + y, map.get(i + "," + y) + 1);
                        } else {
                            map.put(i + "," + y, 1);
                        }
                    } else {
                        int y = y1 - index;
                        if (map.containsKey(i + "," + y)) {
                            map.replace(i + "," + y, map.get(i + "," + y) + 1);
                        } else {
                            map.put(i + "," + y, 1);
                        }
                    }
                    index++;
                }
            }
        }

        count = 0;
        for (String s : map.keySet()){
            if (map.get(s) >= 2){
                count++;
            }
        }
        System.out.println(count); // Part 2 Answer
        scanner.close();
    }
}