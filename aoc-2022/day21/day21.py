import copy


def day21_part1():
    file = open("day21-input.txt", "r")
    operations_map = dict()
    result_map = dict()
    for line in file:
        monkey = line[0:4]
        operation = line[6:].strip()
        if operation.isnumeric():
            result_map[monkey] = int(operation)
        else:
            operations_map[monkey] = operation

    while len(operations_map) > 0:
        monkeys_to_be_deleted = []
        for monkey, operation in operations_map.items():
            left = operation[0:4]
            right = operation[-4:]
            if left not in result_map or right not in result_map:
                continue

            left_value = result_map.get(left)
            right_value = result_map.get(right)
            if operation.__contains__("+"):
                result_map[monkey] = left_value + right_value
            elif operation.count("-"):
                result_map[monkey] = left_value - right_value
            elif operation.count("*"):
                result_map[monkey] = left_value * right_value
            elif operation.count("/"):
                result_map[monkey] = int(left_value / right_value)
            monkeys_to_be_deleted.append(monkey)

        for monkey in monkeys_to_be_deleted:
            operations_map.pop(monkey)

    print(result_map.get("root"))
    file.close()


def day21_part2():
    file = open("day21-input.txt", "r")
    me = "humn"
    operations_map = dict()
    result_map = dict()
    for line in file:
        monkey = line[0:4]
        if monkey.__eq__(me):
            continue
        operation = line[6:].strip()
        if operation.isnumeric():
            result_map[monkey] = float(operation)
        else:
            operations_map[monkey] = operation

    minimum = 1
    maximum = 232974643455000
    equality_check = False
    while not equality_check:
        copy_operations_map = copy.deepcopy(operations_map)
        copy_result_map = copy.deepcopy(result_map)
        value = int((minimum + maximum) / 2)
        copy_result_map[me] = value
        while len(copy_operations_map) > 0:
            monkeys_to_be_deleted = []
            for monkey, operation in copy_operations_map.items():
                left = operation[0:4]
                right = operation[-4:]
                if left not in copy_result_map or right not in copy_result_map:
                    continue

                left_value = copy_result_map.get(left)
                right_value = copy_result_map.get(right)

                if monkey.__eq__("root"):
                    if abs(left_value - right_value) < 10 ** -6:
                        equality_check = True
                        break
                    elif left_value > right_value:
                        minimum = value + 1
                    else:
                        maximum = value - 1
                elif operation.__contains__("+"):
                    copy_result_map[monkey] = left_value + right_value
                elif operation.count("-"):
                    copy_result_map[monkey] = left_value - right_value
                elif operation.count("*"):
                    copy_result_map[monkey] = left_value * right_value
                elif operation.count("/"):
                    copy_result_map[monkey] = left_value / right_value

                monkeys_to_be_deleted.append(monkey)

            if equality_check:
                print(value)
                break

            for monkey in monkeys_to_be_deleted:
                copy_operations_map.pop(monkey)

    file.close()


day21_part1()
day21_part2()
