import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day3 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("day3-input.txt"));
		List<List<Character>> grid = new ArrayList<>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			List<Character> tempList = new ArrayList<>();
			for (char c : line.toCharArray()) {
				tempList.add(c);
			}
			grid.add(tempList);
		}
		scanner.close();

		part1(grid);
		part2(grid);
	}

	public static void part1(List<List<Character>> grid) {
		int result = 0;
		for (int i = 0; i < grid.size(); i++) {
			int value = 0;
			boolean isAdjacent = false;
			for (int j = 0; j < grid.get(i).size(); j++) {
				char current = grid.get(i).get(j);
				if (Character.isDigit(current)) {
					value *= 10;
					value += Character.getNumericValue(current);
					for (int k = Math.max(0, i - 1); k < Math.min(grid.size(), i + 2); k++) {
						for (int l = Math.max(0, j - 1); l < Math.min(grid.get(k).size(), j + 2); l++) {
							char adjacent = grid.get(k).get(l);
							if (!Character.isDigit(adjacent) && adjacent != '.') {
								isAdjacent = true;
								break;
							}
						}
					}
				}

				if (!Character.isDigit(current) || j == grid.get(i).size() - 1) {
					if (isAdjacent) {
						result += value;
					}
					value = 0;
					isAdjacent = false;
				}
			}
		}
		System.out.println(result);
	}

	public static void part2(List<List<Character>> grid) {
		int result = 0;
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < grid.size(); i++) {
			int value = 0;
			String coordinates = "";
			for (int j = 0; j < grid.get(i).size(); j++) {
				char current = grid.get(i).get(j);
				if (Character.isDigit(current)) {
					value *= 10;
					value += Character.getNumericValue(current);
					for (int k = Math.max(0, i - 1); k < Math.min(grid.size(), i + 2); k++) {
						for (int l = Math.max(0, j - 1); l < Math.min(grid.get(k).size(), j + 2); l++) {
							char adjacent = grid.get(k).get(l);
                            if (adjacent == '*') {
                                coordinates = k + "," + l;
                                break;
                            }
						}
					}
				}

				if (!Character.isDigit(current) || j == grid.get(i).size() - 1) {
					if (!coordinates.isEmpty()) {
						result += map.getOrDefault(coordinates, 0) * value;
						map.put(coordinates, value);
					}
					value = 0;
					coordinates = "";
				}
			}
		}
		System.out.println(result);
	}

}