import functools


def parse_input():
    file = open("day11-input.txt", "r")
    items = []
    operation_operators = []
    operation_operands = []
    test_values = []
    test_true = []
    test_false = []

    for i in range(0, 8):
        file.readline()
        line = file.readline()
        starting_items = line[line.find(":") + 1:].split(",")
        items.append([])
        for item in starting_items:
            items[i].append(int(item.strip()))

        line = file.readline()
        if line.__contains__("+"):
            operation_operators.append("+")
            operation_operands.append(line[line.find("+") + 1:].strip())
        else:
            operation_operators.append("*")
            operation_operands.append(line[line.find("*") + 1:].strip())

        line = file.readline()
        test_values.append(int(line.split(" ")[-1]))

        line = file.readline()
        test_true.append(int(line.split(" ")[-1]))

        line = file.readline()
        test_false.append(int(line.split(" ")[-1]))

        file.readline()

    file.close()
    return [items, operation_operators, operation_operands, test_values, test_true, test_false]


def day11_part1():
    [items, operation_operators, operation_operands, test_values, test_true, test_false] = parse_input()
    inspection_counts = [0, 0, 0, 0, 0, 0, 0, 0]
    for rounds in range(0, 20):
        for i in range(0, 8):
            for item in items[i]:
                inspection_counts[i] += 1
                worry = item
                if operation_operands[i].__eq__("old"):
                    if operation_operators[i].__eq__("+"):
                        worry += worry
                    else:
                        worry *= worry
                else:
                    if operation_operators[i].__eq__("+"):
                        worry += int(operation_operands[i])
                    else:
                        worry *= int(operation_operands[i])

                worry = int(worry / 3)
                if worry % test_values[i] == 0:
                    items[test_true[i]].append(worry)
                else:
                    items[test_false[i]].append(worry)
            items[i] = []

    inspection_counts.sort(reverse=True)
    result = inspection_counts[0] * inspection_counts[1]
    print(result)


def day11_part2():
    [items, operation_operators, operation_operands, test_values, test_true, test_false] = parse_input()
    lowest_common_multiple = functools.reduce(lambda x, y: x * y, test_values, 1)
    inspection_counts = [0, 0, 0, 0, 0, 0, 0, 0]
    for rounds in range(0, 10000):
        for i in range(0, 8):
            for item in items[i]:
                inspection_counts[i] += 1
                worry = item
                if operation_operands[i].__eq__("old"):
                    if operation_operators[i].__eq__("+"):
                        worry += worry
                    else:
                        worry *= worry
                else:
                    if operation_operators[i].__eq__("+"):
                        worry += int(operation_operands[i])
                    else:
                        worry *= int(operation_operands[i])

                worry %= lowest_common_multiple
                if worry % test_values[i] == 0:
                    items[test_true[i]].append(worry)
                else:
                    items[test_false[i]].append(worry)
            items[i] = []

    inspection_counts.sort(reverse=True)
    result = inspection_counts[0] * inspection_counts[1]
    print(result)


day11_part1()
day11_part2()
