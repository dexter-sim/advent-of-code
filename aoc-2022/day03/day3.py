def day3_part1():
    file = open("day3-input.txt", "r")
    total = 0

    for line in file:
        first = line[:int(len(line) / 2)]
        second = line[int(len(line) / 2):]
        hash_set = set()
        for index, value in enumerate(first):
            hash_set.add(value)

        for index, value in enumerate(second):
            if hash_set.__contains__(value):
                if ord(value) >= ord('a'):
                    total += ord(value) - ord('a') + 1
                else:
                    total += ord(value) - ord('A') + 27
                break

    print(total)
    file.close()


def day3_part2():
    file = open("day3-input.txt", "r")
    total = 0

    while True:
        first = file.readline()
        second = file.readline()
        third = file.readline()
        if first == '':
            break

        hash_set_one = set()
        hash_set_two = set()
        for index, value in enumerate(first):
            hash_set_one.add(value)
        for index, value in enumerate(second):
            hash_set_two.add(value)

        for index, value in enumerate(third):
            if hash_set_one.__contains__(value) and hash_set_two.__contains__(value):
                if ord(value) >= ord('a'):
                    total += ord(value) - ord('a') + 1
                else:
                    total += ord(value) - ord('A') + 27
                break

    print(total)
    file.close()


day3_part1()
day3_part2()
