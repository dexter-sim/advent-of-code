from queue import Queue


def day12_part1():
    file = open("day12-input.txt", "r")
    grid = []
    steps = []
    start_coordinates = [-1, -1]
    end_coordinates = [-1, -1]
    for line in file:
        line = line.strip()
        row = []
        for i in range(0, len(line)):
            if line[i].__eq__("S"):
                start_coordinates = [len(grid), i]
                row.append("a")
            elif line[i].__eq__("E"):
                end_coordinates = [len(grid), i]
                row.append("z")
            else:
                row.append(line[i])
        grid.append(row)
        steps.append([-1 for _ in range(len(line))])

    queue = Queue()
    steps[start_coordinates[0]][start_coordinates[1]] = 0
    queue.put(start_coordinates)
    while queue.qsize() > 0:
        [x, y] = queue.get()

        if x - 1 >= 0 and ord(grid[x - 1][y]) - ord(grid[x][y]) <= 1 and steps[x - 1][y] == -1:
            steps[x - 1][y] = steps[x][y] + 1
            queue.put([x - 1, y])

        if x + 1 < len(grid) and ord(grid[x + 1][y]) - ord(grid[x][y]) <= 1 and steps[x + 1][y] == -1:
            steps[x + 1][y] = steps[x][y] + 1
            queue.put([x + 1, y])

        if y - 1 >= 0 and ord(grid[x][y - 1]) - ord(grid[x][y]) <= 1 and steps[x][y - 1] == -1:
            steps[x][y - 1] = steps[x][y] + 1
            queue.put([x, y - 1])

        if y + 1 < len(grid[x]) and ord(grid[x][y + 1]) - ord(grid[x][y]) <= 1 and steps[x][y + 1] == -1:
            steps[x][y + 1] = steps[x][y] + 1
            queue.put([x, y + 1])

    print(steps[end_coordinates[0]][end_coordinates[1]])
    file.close()


def day12_part2():
    file = open("day12-input.txt", "r")
    grid = []
    steps = []
    end_coordinates = [-1, -1]
    for line in file:
        line = line.strip()
        row = []
        for i in range(0, len(line)):
            if line[i].__eq__("S"):
                row.append("a")
            elif line[i].__eq__("E"):
                end_coordinates = [len(grid), i]
                row.append("z")
            else:
                row.append(line[i])
        grid.append(row)
        steps.append([-1 for _ in range(len(line))])

    queue = Queue()
    steps[end_coordinates[0]][end_coordinates[1]] = 0
    queue.put(end_coordinates)
    while queue.qsize() > 0:
        [x, y] = queue.get()
        if grid[x][y].__eq__("a"):
            print(steps[x][y])
            break

        if x - 1 >= 0 and ord(grid[x - 1][y]) - ord(grid[x][y]) >= -1 and steps[x - 1][y] == -1:
            steps[x - 1][y] = steps[x][y] + 1
            queue.put([x - 1, y])

        if x + 1 < len(grid) and ord(grid[x + 1][y]) - ord(grid[x][y]) >= -1 and steps[x + 1][y] == -1:
            steps[x + 1][y] = steps[x][y] + 1
            queue.put([x + 1, y])

        if y - 1 >= 0 and ord(grid[x][y - 1]) - ord(grid[x][y]) >= -1 and steps[x][y - 1] == -1:
            steps[x][y - 1] = steps[x][y] + 1
            queue.put([x, y - 1])

        if y + 1 < len(grid[x]) and ord(grid[x][y + 1]) - ord(grid[x][y]) >= -1 and steps[x][y + 1] == -1:
            steps[x][y + 1] = steps[x][y] + 1
            queue.put([x, y + 1])

    file.close()


day12_part1()
day12_part2()
