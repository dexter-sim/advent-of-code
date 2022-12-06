# Stacks puzzle input
# [N]     [C]                 [Q]
# [W]     [J] [L]             [J] [V]
# [F]     [N] [D]     [L]     [S] [W]
# [R] [S] [F] [G]     [R]     [V] [Z]
# [Z] [G] [Q] [C]     [W] [C] [F] [G]
# [S] [Q] [V] [P] [S] [F] [D] [R] [S]
# [M] [P] [R] [Z] [P] [D] [N] [N] [M]
# [D] [W] [W] [F] [T] [H] [Z] [W] [R]
#  1   2   3   4   5   6   7   8   9

def create_stack_input():
    stack1 = ["D", "M", "S", "Z", "R", "F", "W", "N"]
    stack2 = ["W", "P", "Q", "G", "S"]
    stack3 = ["W", "R", "V", "Q", "F", "N", "J", "C"]
    stack4 = ["F", "Z", "P", "C", "G", "D", "L"]
    stack5 = ["T", "P", "S"]
    stack6 = ["H", "D", "F", "W", "R", "L"]
    stack7 = ["Z", "N", "D", "C"]
    stack8 = ["W", "N", "R", "F", "V", "S", "J", "Q"]
    stack9 = ["R", "M", "S", "G", "Z", "W", "V"]
    lst = [stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9]
    return lst


def day5_part1():
    file = open("day5-input.txt", "r")
    lst = create_stack_input()

    for line in file:
        split = line.split(" ")
        num = int(split[1])
        stack_from = int(split[3]) - 1
        stack_to = int(split[5]) - 1

        while num > 0:
            lst[stack_to].append(lst[stack_from].pop())
            num -= 1

    res = ""
    for stack in lst:
        res += stack[-1] if len(stack) > 0 else " "
    print(res)
    file.close()


def day5_part2():
    file = open("day5-input.txt", "r")
    lst = create_stack_input()

    for line in file:
        split = line.split(" ")
        num = int(split[1])
        stack_from = int(split[3]) - 1
        stack_to = int(split[5]) - 1

        temp_stack = []
        while num > 0:
            temp_stack.append(lst[stack_from].pop())
            num -= 1

        while len(temp_stack) > 0:
            lst[stack_to].append(temp_stack.pop())

    res = ""
    for stack in lst:
        res += stack[-1] if len(stack) > 0 else " "
    print(res)
    file.close()


day5_part1()
day5_part2()
