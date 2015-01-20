def choose(round, players, args):
    if len(args) == 0:
        return 10
    last = args[-1].split();

# next line from http://stackoverflow.com/a/7368801/3148067
    last = map(int, last)

    dist = 0
    for i in range(1, 999):
        if i in last:
            dist = 0
        else:
            dist = dist + 1
            if dist == 10:
                return i
    return 500
