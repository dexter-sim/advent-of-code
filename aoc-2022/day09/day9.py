def day9_part1():
    file = open("day9-input.txt", "r")
    head = [0, 0]
    tail = [0, 0]
    hash_set = set()
    hash_set.add(str(tail))

    for line in file:
        direction = line[0]
        magnitude = int(line[2:].strip())
        while magnitude > 0:
            if direction == "U":
                head[1] += 1
            elif direction == "D":
                head[1] -= 1
            elif direction == "R":
                head[0] += 1
            elif direction == "L":
                head[0] -= 1

            if abs(head[0] - tail[0]) > 1 or abs(head[1] - tail[1]) > 1:
                if head[0] - tail[0] > 0:
                    tail[0] += 1
                elif head[0] - tail[0] < 0:
                    tail[0] -= 1

                if head[1] - tail[1] > 0:
                    tail[1] += 1
                elif head[1] - tail[1] < 0:
                    tail[1] -= 1

            hash_set.add(str(tail))
            magnitude -= 1

    print(len(hash_set))
    file.close()


def day9_part2():
    file = open("day9-input.txt", "r")
    knots = []
    for i in range(0, 10):
        knots.append([0, 0])

    hash_set = set()
    hash_set.add(str(knots[9]))

    for line in file:
        direction = line[0]
        magnitude = int(line[2:].strip())
        while magnitude > 0:
            if direction == "U":
                knots[0][1] += 1
            elif direction == "D":
                knots[0][1] -= 1
            elif direction == "R":
                knots[0][0] += 1
            elif direction == "L":
                knots[0][0] -= 1

            for i in range(1, 10):
                if abs(knots[i - 1][0] - knots[i][0]) > 1 or abs(knots[i - 1][1] - knots[i][1]) > 1:
                    if knots[i - 1][0] - knots[i][0] > 0:
                        knots[i][0] += 1
                    elif knots[i - 1][0] - knots[i][0] < 0:
                        knots[i][0] -= 1

                    if knots[i - 1][1] - knots[i][1] > 0:
                        knots[i][1] += 1
                    elif knots[i - 1][1] - knots[i][1] < 0:
                        knots[i][1] -= 1

            hash_set.add(str(knots[9]))
            magnitude -= 1

    print(len(hash_set))
    file.close()


day9_part1()
day9_part2()
