def day2_part1():
    file = open("day2-input.txt", "r")
    count = 0

    for line in file:
        my_shape = ord(line[2]) - ord('X') + 1
        other_shape = ord(line[0]) - ord('A') + 1

        count += my_shape
        if my_shape == other_shape:
            count += 3
        elif my_shape % 3 == (other_shape + 1) % 3:
            count += 6

    print(count)
    file.close()


def day2_part2():
    file = open("day2-input.txt", "r")
    count = 0

    for line in file:
        outcome = ord(line[2]) - ord('X') + 1
        other_shape = ord(line[0]) - ord('A') + 1

        if outcome == 1:
            count += 3 if other_shape - 1 == 0 else other_shape - 1
        elif outcome == 2:
            count += 3
            count += other_shape
        elif outcome == 3:
            count += 6
            count += other_shape % 3 + 1

    print(count)
    file.close()


day2_part1()
day2_part2()
