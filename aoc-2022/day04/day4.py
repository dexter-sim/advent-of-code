def day4_part1():
    file = open("day4-input.txt", "r")
    count = 0

    for line in file:
        split = line.split(",")
        first = split[0]
        second = split[1]
        first_lower = int(first[:first.find("-")])
        first_upper = int(first[first.find("-") + 1:])
        second_lower = int(second[:second.find("-")])
        second_upper = int(second[second.find("-") + 1:])
        if ((second_lower >= first_lower and second_upper <= first_upper)
                or (first_lower >= second_lower and first_upper <= second_upper)):
            count += 1

    print(count)
    file.close()


def day4_part2():
    file = open("day4-input.txt", "r")
    count = 0

    for line in file:
        split = line.split(",")
        first = split[0]
        second = split[1]
        first_lower = int(first[:first.find("-")])
        first_upper = int(first[first.find("-") + 1:])
        second_lower = int(second[:second.find("-")])
        second_upper = int(second[second.find("-") + 1:])
        if ((second_lower <= first_upper and second_lower >= first_lower)
                or (first_lower <= second_upper and first_lower >= second_lower)):
            count += 1

    print(count)
    file.close()


day4_part1()
day4_part2()
