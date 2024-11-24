import static java.util.Map.entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day7 {
    public static Map<Character, Integer> part1Map = Map.ofEntries(
            entry('2', 2),
            entry('3', 3),
            entry('4', 4),
            entry('5', 5),
            entry('6', 6),
            entry('7', 7),
            entry('8', 8),
            entry('9', 9),
            entry('T', 10),
            entry('J', 11),
            entry('Q', 12),
            entry('K', 13),
            entry('A', 14)
    );

    public static Map<Character, Integer> part2Map = Map.ofEntries(
            entry('2', 2),
            entry('3', 3),
            entry('4', 4),
            entry('5', 5),
            entry('6', 6),
            entry('7', 7),
            entry('8', 8),
            entry('9', 9),
            entry('T', 10),
            entry('J', 1),
            entry('Q', 12),
            entry('K', 13),
            entry('A', 14)
    );

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2023/day07/day7-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        Map<String, Integer> handBetMap= new HashMap<>();
        List<List<String>> hands = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            hands.add(new ArrayList<>());
        }

        for (String s : inputList) {
            String[] split = s.split(" ");
            String hand = split[0];
            Integer bet = Integer.parseInt(split[1]);
            handBetMap.put(hand, bet);

            Map<Character, Integer> characterCountsMap = new HashMap<>();
            for (char c : hand.toCharArray()) {
                characterCountsMap.put(c, characterCountsMap.getOrDefault(c, 0) + 1);
            }

            int maxCount = 0;
            for (char c : characterCountsMap.keySet()) {
                maxCount = Math.max(maxCount, characterCountsMap.get(c));
            }

            if (maxCount == 5) {
                hands.get(0).add(hand);
            } else if (maxCount == 4) {
                hands.get(1).add(hand);
            } else if (maxCount == 3 && characterCountsMap.size() == 2) {
                hands.get(2).add(hand);
            } else if (maxCount == 3) {
                hands.get(3).add(hand);
            } else if (maxCount == 2 && characterCountsMap.size() == 3) {
                hands.get(4).add(hand);
            } else if (maxCount == 2) {
                hands.get(5).add(hand);
            } else {
                hands.get(6).add(hand);
            }

            for (List<String> list : hands) {
                Collections.sort(list, (x, y) -> {
                    for (int i = 0; i < x.length(); i++) {
                        int compare = Integer.compare(part1Map.get(x.charAt(i)), part1Map.get(y.charAt(i)));
                        if (compare != 0) {
                            return compare;
                        }
                    }
                    return 0;
                });
            }
        }

        long totalWinnings = 0L;
        int rank = 1;
        for (int i = hands.size() - 1; i >= 0; i--) {
            for (String hand : hands.get(i)) {
                totalWinnings += (long) handBetMap.get(hand) * rank;
                rank++;
            }
        }

        System.out.println(totalWinnings);
    }

    public static void part2(List<String> inputList) {
        Map<String, Integer> handBetMap= new HashMap<>();
        List<List<String>> hands = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            hands.add(new ArrayList<>());
        }

        for (String s : inputList) {
            String[] split = s.split(" ");
            String hand = split[0];
            Integer bet = Integer.parseInt(split[1]);
            handBetMap.put(hand, bet);

            Map<Character, Integer> characterCountsMap = new HashMap<>();
            for (char c : hand.toCharArray()) {
                characterCountsMap.put(c, characterCountsMap.getOrDefault(c, 0) + 1);
            }

            int jCount = characterCountsMap.getOrDefault('J', 0);
            characterCountsMap.remove('J');

            int maxCount = 0;
            for (char c : characterCountsMap.keySet()) {
                maxCount = Math.max(maxCount, characterCountsMap.get(c));
            }
            maxCount += jCount;

            if (maxCount == 5) {
                hands.get(0).add(hand);
            } else if (maxCount == 4) {
                hands.get(1).add(hand);
            } else if (maxCount == 3 && characterCountsMap.size() == 2) {
                hands.get(2).add(hand);
            } else if (maxCount == 3) {
                hands.get(3).add(hand);
            } else if (maxCount == 2 && characterCountsMap.size() == 3) {
                hands.get(4).add(hand);
            } else if (maxCount == 2) {
                hands.get(5).add(hand);
            } else {
                hands.get(6).add(hand);
            }

            for (List<String> list : hands) {
                Collections.sort(list, (x, y) -> {
                    for (int i = 0; i < x.length(); i++) {
                        int compare = Integer.compare(part2Map.get(x.charAt(i)), part2Map.get(y.charAt(i)));
                        if (compare != 0) {
                            return compare;
                        }
                    }
                    return 0;
                });
            }
        }

        long totalWinnings = 0L;
        int rank = 1;
        for (int i = hands.size() - 1; i >= 0; i--) {
            for (String hand : hands.get(i)) {
                totalWinnings += (long) handBetMap.get(hand) * rank;
                rank++;
            }
        }

        System.out.println(totalWinnings);
    }

}
