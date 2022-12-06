def day6_part1():
    file = open("day6-input.txt", "r")
    line = file.readline()

    for i in range(3, len(line)):
        hash_set = set()
        for j in range(i - 3, i + 1):
            hash_set.add(line[j])
        if len(hash_set) == 4:
            print(i + 1)
            break

    file.close()


def day6_part2():
    file = open("day6-input.txt", "r")
    line = file.readline()

    for i in range(13, len(line)):
        hash_set = set()
        for j in range(i - 13, i + 1):
            hash_set.add(line[j])
        if len(hash_set) == 14:
            print(i + 1)
            break

    file.close()


day6_part1()
day6_part2()
