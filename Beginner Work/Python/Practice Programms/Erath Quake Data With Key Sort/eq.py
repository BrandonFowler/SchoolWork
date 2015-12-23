def main():
    eqs = readFile("eqs7day-M2.5.txt")
    if not eqs:
        print("Couldn't read file...")
        return

    eqs = parseList(eqs)
    eqs.sort(key=eqMagnitudeKey)
    eqs.reverse()



    oldMag = 0.0
    cntr = 1
    for eq in eqs:
        if cntr <= 50:
            if oldMag != eq[8]:
                print("====================")
                oldMag = eq[8]
            print(cntr," - ",eq[8],"-",eq[11])
        cntr += 1

def eqMagnitudeKey(thisEq):
    return str(thisEq[8]) + str(thisEq[11]).upper()

def eqKey(thisEq):
    return thisEq[11].upper() + str(thisEq[8])

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
        fh.close()

        for ix in range(len(retlist)-1, -1, -1):
            if retlist[ix].strip() == "":
                retlist.pop(ix)

        return retlist
    except:
        return None

if __name__ == '__main__':
    main()
