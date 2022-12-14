def day14_part1():
    file = open("day14-input.txt", "r")
    grid = [["." for _ in range(1000)] for _ in range(1000)]
    rock_paths = []
    for line in file:
        coordinates = line.split("->")
        points = []
        for i in range(0, len(coordinates)):
            x = int(coordinates[i].split(",")[0])
            y = int(coordinates[i].split(",")[1])
            points.append([x, y])
        rock_paths.append(points)

    for path in rock_paths:
        x = path[0][0]
        y = path[0][1]
        for i in range(1, len(path)):
            while x > path[i][0]:
                grid[y][x] = "#"
                x -= 1

            while x < path[i][0]:
                grid[y][x] = "#"
                x += 1

            while y > path[i][1]:
                grid[y][x] = "#"
                y -= 1

            while y < path[i][1]:
                grid[y][x] = "#"
                y += 1
            grid[y][x] = "#"

    count = 0
    while True:
        sand_x = 500
        sand_y = 0
        while sand_y < len(grid[sand_x]) - 1:
            if grid[sand_y + 1][sand_x].__eq__("."):
                sand_y += 1
            elif grid[sand_y + 1][sand_x - 1].__eq__("."):
                sand_y += 1
                sand_x -= 1
            elif grid[sand_y + 1][sand_x + 1].__eq__("."):
                sand_y += 1
                sand_x += 1
            else:
                count += 1
                grid[sand_y][sand_x] = "o"
                break

        if sand_y == len(grid[sand_x]) - 1:
            break

    print(count)
    file.close()


def day14_part2():
    file = open("day14-input.txt", "r")
    grid = [["." for _ in range(1000)] for _ in range(1000)]
    rock_paths = []
    for line in file:
        coordinates = line.split("->")
        points = []
        for i in range(0, len(coordinates)):
            x = int(coordinates[i].split(",")[0])
            y = int(coordinates[i].split(",")[1])
            points.append([x, y])
        rock_paths.append(points)

    highest_y_coordinate = 0
    for path in rock_paths:
        x = path[0][0]
        y = path[0][1]
        highest_y_coordinate = max(highest_y_coordinate, y)
        for i in range(1, len(path)):
            while x > path[i][0]:
                grid[y][x] = "#"
                x -= 1

            while x < path[i][0]:
                grid[y][x] = "#"
                x += 1

            while y > path[i][1]:
                grid[y][x] = "#"
                y -= 1

            while y < path[i][1]:
                grid[y][x] = "#"
                y += 1
            grid[y][x] = "#"
            highest_y_coordinate = max(highest_y_coordinate, y)

    for i in range(len(grid[highest_y_coordinate + 2])):
        grid[highest_y_coordinate + 2][i] = "#"

    count = 0
    while True:
        sand_x = 500
        sand_y = 0
        while True:
            if grid[sand_y + 1][sand_x].__eq__("."):
                sand_y += 1
            elif grid[sand_y + 1][sand_x - 1].__eq__("."):
                sand_y += 1
                sand_x -= 1
            elif grid[sand_y + 1][sand_x + 1].__eq__("."):
                sand_y += 1
                sand_x += 1
            else:
                count += 1
                grid[sand_y][sand_x] = "o"
                break

        if sand_x == 500 and sand_y == 0:
            break

    print(count)
    file.close()


day14_part1()
day14_part2()
