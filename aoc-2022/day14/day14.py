def day14_part1():
    file = open("day14-input.txt", "r")
    grid = [["." for _ in range(1000)] for _ in range(1000)]
    for line in file:
        coordinates = line.split("->")
        points = []
        for i in range(0, len(coordinates)):
            [x, y] = coordinates[i].split(",")
            points.append([int(x), int(y)])

        x = points[0][0]
        y = points[0][1]
        for i in range(1, len(points)):
            while x > points[i][0]:
                grid[y][x] = "#"
                x -= 1

            while x < points[i][0]:
                grid[y][x] = "#"
                x += 1

            while y > points[i][1]:
                grid[y][x] = "#"
                y -= 1

            while y < points[i][1]:
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
    highest_y_coordinate = 0
    for line in file:
        coordinates = line.split("->")
        points = []
        for i in range(0, len(coordinates)):
            [x, y] = coordinates[i].split(",")
            points.append([int(x), int(y)])
            highest_y_coordinate = max(highest_y_coordinate, points[-1][1])

        x = points[0][0]
        y = points[0][1]
        for i in range(1, len(points)):
            while x > points[i][0]:
                grid[y][x] = "#"
                x -= 1

            while x < points[i][0]:
                grid[y][x] = "#"
                x += 1

            while y > points[i][1]:
                grid[y][x] = "#"
                y -= 1

            while y < points[i][1]:
                grid[y][x] = "#"
                y += 1
            grid[y][x] = "#"

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
