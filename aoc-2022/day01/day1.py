def day1_part1():
    file = open("day1-input.txt", "r")
    count = 0
    max_count = 0

    for line in file:
        if line == "\n":
            count = 0
        else:
            count += int(line)
            max_count = max(max_count, count)

    print(max_count)
    file.close()


def day1_part2():
    file = open("day1-input.txt", "r")
    count = 0
    array = []

    for line in file:
        if line == '\n':
            array.append(count)
            count = 0
        else:
            count += int(line)

    array.sort(reverse=True)
    print(array[0] + array[1] + array[2])
    file.close()


day1_part1()
day1_part2()
