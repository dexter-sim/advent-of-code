def day10_part1():
    file = open("day10-input.txt", "r")

    signal_cycles = {20, 60, 100, 140, 180, 220}
    signal_strength = 0
    value = 1
    cycle = 1
    for line in file:
        if line.startswith("noop"):
            if signal_cycles.__contains__(cycle):
                signal_strength += cycle * value
            cycle += 1

        if line.startswith("addx"):
            addend = int(line.split(" ")[1].rstrip())
            if signal_cycles.__contains__(cycle):
                signal_strength += cycle * value
            cycle += 1
            if signal_cycles.__contains__(cycle):
                signal_strength += cycle * value
            cycle += 1
            value += addend

    print(signal_strength)
    file.close()


def day10_part2():
    file = open("day10-input.txt", "r")

    display = []
    for i in range(0, 6):
        display.append([])
        for j in range(0, 40):
            display[i].append(" ")

    sprite_position = 1
    cycle = 1
    for line in file:
        if sprite_position <= (cycle - 1) % 40 + 1 <= sprite_position + 2:
            display[int((cycle - 1) / 40)][(cycle - 1) % 40] = "#"
        cycle += 1

        if line.startswith("addx"):
            addend = int(line.split(" ")[1].rstrip())
            if sprite_position <= (cycle - 1) % 40 + 1 <= sprite_position + 2:
                display[int((cycle - 1) / 40)][(cycle - 1) % 40] = "#"
            cycle += 1
            sprite_position += addend

    for i in range(0, len(display)):
        print("  ".join(display[i]))
    file.close()


day10_part1()
day10_part2()
