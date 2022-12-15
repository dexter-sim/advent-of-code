import functools
import math


def day15_part1():
    file = open("day15-input.txt", "r")
    intervals = []
    beacons_at_2m = set()
    for line in file:
        [sensor, closest_beacon] = line.split(": ")
        sensor_x = int(sensor[sensor.find("x=") + 2:sensor.find(",")])
        sensor_y = int(sensor[sensor.find("y=") + 2:])
        beacon_x = int(closest_beacon[closest_beacon.find("x=") + 2:closest_beacon.find(",")])
        beacon_y = int(closest_beacon[closest_beacon.find("y=") + 2:])
        if beacon_y == 2000000:
            beacons_at_2m.add(beacon_x)

        displacement = abs(sensor_x - beacon_x) + abs(sensor_y - beacon_y)
        if displacement - abs(2000000 - sensor_y) < 0:
            continue

        min_x = sensor_x - (displacement - abs(2000000 - sensor_y))
        max_x = sensor_x + (displacement - abs(2000000 - sensor_y))
        intervals.append([min_x, max_x])

    count = 0
    previous_max = -math.inf
    intervals.sort(key=functools.cmp_to_key(lambda x, y: x[0] - y[0]))
    for i in range(0, len(intervals)):
        if intervals[i][0] <= previous_max:
            if intervals[i][1] <= previous_max:
                continue
            intervals[i][0] = previous_max + 1
        for j in beacons_at_2m:
            if intervals[i][0] <= j <= intervals[i][1]:
                count -= 1
        count += intervals[i][1] - intervals[i][0] + 1
        previous_max = max(previous_max, intervals[i][1])

    print(count)
    file.close()


def day15_part2():
    file = open("day15-input.txt", "r")
    intervals = [[] for _ in range(4000001)]
    for line in file:
        [sensor, closest_beacon] = line.split(": ")
        sensor_x = int(sensor[sensor.find("x=") + 2:sensor.find(",")])
        sensor_y = int(sensor[sensor.find("y=") + 2:])
        beacon_x = int(closest_beacon[closest_beacon.find("x=") + 2:closest_beacon.find(",")])
        beacon_y = int(closest_beacon[closest_beacon.find("y=") + 2:])

        displacement = abs(sensor_x - beacon_x) + abs(sensor_y - beacon_y)
        for i in range(0, displacement + 1):
            lower_y = sensor_y - i
            upper_y = sensor_y + i
            min_x = min(4000000, max(0, sensor_x - (displacement - i)))
            max_x = min(4000000, max(0, sensor_x + (displacement - i)))
            if lower_y >= 0:
                intervals[lower_y].append([min_x, max_x])
            if upper_y < len(intervals):
                intervals[upper_y].append([min_x, max_x])

    for lst in intervals:
        lst.sort(key=functools.cmp_to_key(lambda x, y: x[0] - y[0]))

    for y_coordinate in range(0, len(intervals)):
        previous_max = -1
        for i in range(0, len(intervals[y_coordinate])):
            if intervals[y_coordinate][i][0] == previous_max + 2:
                print((previous_max + 1) * 4000000 + y_coordinate)
                break
            previous_max = max(previous_max, intervals[y_coordinate][i][1])

    file.close()


day15_part1()
day15_part2()
