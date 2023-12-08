import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day4 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("day4-input.txt"));
		List<String> inputList = new ArrayList<>();
		while (scanner.hasNext()) {
			inputList.add(scanner.nextLine());
		}
		scanner.close();

		part1(inputList);
		part2(inputList);
	}

	public static void part1(List<String> inputList) {
		int result = 0;
		for (String s : inputList) {
			Set<Integer> winningNumbers = new HashSet<>();
			String left = s.split("\\|")[0].substring(9);
			String right = s.split("\\|")[1];
			int points = 0;
			for (String element : left.trim().split("\\s+")) {
				winningNumbers.add(Integer.parseInt(element));
			}
			for (String element : right.trim().split("\\s+")) {
				if (winningNumbers.contains(Integer.parseInt(element))) {
					if (points == 0) {
						points = 1;
					} else {
						points *= 2;
					}
				}
			}
			result += points;
		}
		System.out.println(result);
	}

	public static void part2(List<String> inputList) {
		int[] copies = new int[inputList.size()];
		Arrays.fill(copies, 1);
		for (int i = 0; i < inputList.size(); i++) {
			String s = inputList.get(i);
			Set<Integer> winningNumbers = new HashSet<>();
			String left = s.split("\\|")[0].substring(9);
			String right = s.split("\\|")[1];
			int matches = 0;
			for (String element : left.trim().split("\\s+")) {
				winningNumbers.add(Integer.parseInt(element));
			}
			for (String element : right.trim().split("\\s+")) {
				if (winningNumbers.contains(Integer.parseInt(element))) {
					matches++;
				}
			}
			for (int j = i + 1; j < Math.min(copies.length, i + 1 + matches); j++) {
				copies[j] += copies[i];
			}
		}
		int result = Arrays.stream(copies).sum();
		System.out.println(result);
	}

}
