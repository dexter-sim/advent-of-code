import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("day2-input.txt"));
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
			String game = s.split(":")[0];
			StringBuilder sb = new StringBuilder();
			for (char c : game.toCharArray()) {
				if (Character.isDigit(c)) {
					sb.append(c);
				}
			}
			int id = Integer.parseInt(sb.toString());

			boolean possible = true;
			String[] rounds = s.split(":")[1].split(";");
			for (String round : rounds) {
				int blue = 0;
				int green = 0;
				int red = 0;
				String[] picks = round.split(",");
				for (String pick : picks) {
					sb = new StringBuilder();
					for (char c : pick.toCharArray()) {
						if (Character.isDigit(c)) {
							sb.append(c);
						}
					}
					int value = Integer.parseInt(sb.toString());
					if (pick.contains("blue")) {
						blue += value;
					} else if (pick.contains("green")) {
						green += value;
					} else {
						red += value;
					}
				}
				if (blue > 14 || green > 13 || red > 12) {
					possible = false;
					break;
				}
			}

			if (possible) {
				result += id;
			}
		}
		System.out.println(result);
	}

	public static void part2(List<String> inputList) {
		long result = 0L;
		for (String s : inputList) {
			String[] rounds = s.split(":")[1].split(";");
			long minBlue = 0L;
			long minGreen = 0L;
			long minRed = 0L;
			for (String round : rounds) {
				long blue = 0L;
				long green = 0L;
				long red = 0L;
				String[] picks = round.split(",");
				for (String pick : picks) {
					StringBuilder sb = new StringBuilder();
					for (char c : pick.toCharArray()) {
						if (Character.isDigit(c)) {
							sb.append(c);
						}
					}
					long value = Long.parseLong(sb.toString());
					if (pick.contains("blue")) {
						blue += value;
					} else if (pick.contains("green")) {
						green += value;
					} else {
						red += value;
					}
				}
				minBlue = Math.max(minBlue, blue);
				minGreen = Math.max(minGreen, green);
				minRed = Math.max(minRed, red);
			}
			result += minBlue * minGreen * minRed;
		}
		System.out.println(result);
	}

}