import functools

RIGHT_ORDER = -1
WRONG_ORDER = 1
UNKNOWN = 0


def day13_part1():
    file = open("day13-input.txt", "r")
    packets = []
    for line in file:
        if len(line.rstrip()) == 0:
            continue
        packets.append(eval(line.rstrip()))

    sum_of_indices = 0
    for i in range(0, len(packets), 2):
        first_list = packets[i]
        second_list = packets[i + 1]
        if helper(first_list, second_list) == RIGHT_ORDER:
            sum_of_indices += int(i / 2 + 1)

    print(sum_of_indices)
    file.close()


def day13_part2():
    file = open("day13-input.txt", "r")
    packets = [[[2]], [[6]]]
    for line in file:
        if len(line.rstrip()) == 0:
            continue
        packets.append(eval(line.rstrip()))

    packets.sort(key=functools.cmp_to_key(lambda x, y: helper(x, y)))
    print((packets.index([[2]]) + 1) * (packets.index([[6]]) + 1))
    file.close()


def helper(list_a, list_b):
    if len(list_a) == 0 and len(list_b) == 0:
        return UNKNOWN
    if len(list_a) == 0:
        return RIGHT_ORDER
    if len(list_b) == 0:
        return WRONG_ORDER

    left = list_a[0]
    right = list_b[0]

    if isinstance(left, int) and isinstance(right, int):
        if left < right:
            return RIGHT_ORDER
        elif left == right:
            return helper(list_a[1:], list_b[1:])
        else:
            return WRONG_ORDER

    if isinstance(left, int):
        left = [left]

    if isinstance(right, int):
        right = [right]

    result = helper(left, right)
    if result == UNKNOWN:
        return helper(list_a[1:], list_b[1:])
    else:
        return result


day13_part1()
day13_part2()
