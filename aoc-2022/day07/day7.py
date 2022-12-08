def day7():
    file = open("day7-input.txt", "r")
    file_size_map = dict()
    files_counted = set()
    stack = []

    # Create filesystem
    for line in file:
        line = line.strip()
        if line.startswith("$ cd"):
            if line.__eq__("$ cd .."):
                stack.pop()
            else:
                stack.append(str(stack) + line[5:])
        elif line.startswith("$ ls"):
            pass
        elif line.startswith("dir"):
            pass
        else:
            if str(stack) + line in files_counted:
                continue
            else:
                files_counted.add(str(stack) + line)

            for dir_name in stack:
                file_size_map[dir_name] = file_size_map.get(dir_name, 0) + int(line.split(" ")[0])

    # Part 1
    total = 0
    for dir_name, dir_size in file_size_map.items():
        if dir_size <= 100000:
            total += dir_size
    print(total)

    # Part 2
    root_size = int(file_size_map[str([]) + "/"])
    additional_space_req = 30000000 - (70000000 - root_size)
    min_size = float('inf')
    for dir_name, dir_size in file_size_map.items():
        if dir_size >= additional_space_req:
            min_size = min(min_size, dir_size)
    print(min_size)
    file.close()


day7()
