import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day08/day8-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        System.out.println(runInstructions(inputList, false));
    }

    public static void part2(List<String> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            String instruction = inputList.get(i);
            if (instruction.startsWith("acc")) {
                continue;
            }

            String modifiedInstruction = instruction.startsWith("jmp") ?
                    instruction.replace("jmp", "nop") :
                    instruction.replace("nop", "jmp");
            inputList.set(i, modifiedInstruction);

            Integer sum = runInstructions(inputList, true);
            if (sum != null) {
                System.out.println(sum);
                return;
            }
            inputList.set(i, instruction);
        }
    }

    /**
     * Executes the sequence of instructions and returns the final accumulator value.
     *
     * @param inputList A list where each entry is an instruction.
     * @param isPart2   If true, returns null if an infinite loop is detected
     *                  instead of the current sum. If false, returns the sum
     *                  accumulated right before the loop occurs.
     * @return The accumulated sum if the program reaches the end (or if Part 1 detects a loop).
     * Returns null if isPart2 is true and an infinite loop is encountered.
     */
    public static Integer runInstructions(List<String> inputList, boolean isPart2) {
        Set<Integer> visited = new HashSet<>();
        int index = 0;
        int sum = 0;
        while (index < inputList.size()) {
            if (!visited.add(index)) {
                return isPart2 ? null : sum;
            }

            String instruction = inputList.get(index);
            int value = Integer.parseInt(instruction.substring(4));
            if (instruction.startsWith("acc")) {
                sum += value;
                index++;
            } else if (instruction.startsWith("jmp")) {
                index += value;
            } else {
                index++;
            }
        }
        return sum;
    }
}
