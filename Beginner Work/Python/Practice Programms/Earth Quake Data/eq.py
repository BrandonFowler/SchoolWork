

def main():
    eqs = readFile("eqs7day-M2.5.txt")
    if not eqs:
        print("Couldn't read file...")
        return


    eqs = parseList(eqs)
    eqs.sort()
    print(eqs)


def parseList(lst):
    for ix in range(len(lst)):
        lst[ix] = lst[ix].strip()
        lst[ix] = lst[ix].split(",")

    lst.pop(0)

    for ix in range(len(lst)):
        lst[ix][8] = float(lst[ix][8])

    return lst


def readFile(fn):
    try:
        fh = open(fn,"r")
        retlist = fh.readlines()
        return retlist
    except:
        return None



if __name__ == '__main__':
    main()
