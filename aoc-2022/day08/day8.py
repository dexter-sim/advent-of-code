def create_grid_of_trees():
    file = open("day8-input.txt", "r")
    grid = []
    index = 0

    for line in file:
        line = line.strip()
        grid.append([])
        for i, value in enumerate(line):
            grid[index].append(int(value))
        index += 1

    file.close()
    return grid


def day8_part1():
    grid = create_grid_of_trees()
    trees_counted = []
    for i in range(0, len(grid)):
        trees_counted.append([])
        for j in range(0, len(grid[i])):
            trees_counted[i].append(False)

    count = 0
    for i in range(0, len(grid)):
        max_height = -1
        for j in range(0, len(grid[i])):
            if grid[i][j] > max_height:
                max_height = grid[i][j]
                if not trees_counted[i][j]:
                    count += 1
                    trees_counted[i][j] = True

        max_height = -1
        for j in range(len(grid[i]) - 1, -1, -1):
            if grid[i][j] > max_height:
                max_height = grid[i][j]
                if not trees_counted[i][j]:
                    count += 1
                    trees_counted[i][j] = True

    for j in range(0, len(grid[0])):
        max_height = -1
        for i in range(0, len(grid)):
            if grid[i][j] > max_height:
                max_height = grid[i][j]
                if not trees_counted[i][j]:
                    count += 1
                    trees_counted[i][j] = True

        max_height = -1
        for i in range(len(grid) - 1, -1, -1):
            if grid[i][j] > max_height:
                max_height = grid[i][j]
                if not trees_counted[i][j]:
                    count += 1
                    trees_counted[i][j] = True

    print(count)


def day8_part2():
    grid = create_grid_of_trees()
    max_scenic_score = 0
    for i in range(0, len(grid)):
        for j in range(0, len(grid[i])):
            left = 0
            right = 0
            up = 0
            down = 0

            for k in range(j + 1, len(grid[i])):
                right += 1
                if grid[i][k] >= grid[i][j]:
                    break

            for k in range(j - 1, -1, -1):
                left += 1
                if grid[i][k] >= grid[i][j]:
                    break

            for k in range(i + 1, len(grid)):
                down += 1
                if grid[k][j] >= grid[i][j]:
                    break

            for k in range(i - 1, -1, -1):
                up += 1
                if grid[k][j] >= grid[i][j]:
                    break

            max_scenic_score = max(max_scenic_score, left * right * up * down)

    print(max_scenic_score)


day8_part1()
day8_part2()
