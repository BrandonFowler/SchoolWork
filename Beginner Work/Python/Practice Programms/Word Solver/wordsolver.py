
import sys

def main():
    # Main Program
    # words - list - contains list of dictionary words
    print("Loading Dictionary...")
    words = readFileToList("words")
    if words:
        print("Dictionary Loaded!")
    else:
        print("Error!")
        sys.exit("Program Exited.")

    displayMenu()
    choice = getIntInput("Enter your choice:")

    while choice != 0:
        if choice == 1:
            anagram(words)
        elif choice == 2:
            crossword(words)
        elif choice == 3:
            palindrome(words)



        displayMenu()
        choice = getIntInput("Enter your choice:")

def palindrome(dct):
    length = getIntInput("Enter your desired length:")

    print()
    print("Matching Words")
    print("==============")
    cntr = 0
    for wrd in dct:
        if isPalindrome(wrd, length):
            print(wrd)
            cntr += 1

    if cntr == 0:
        print("No matches found.")

    print()

def isPalindrome(chk, length):
    if len(chk) != length:
        return False

    wl = list(chk)
    wlr = list(chk)
    wlr.reverse()

    return wl == wlr

def crossword(dct):
    ptrn = input("Enter your crossword pattern:")

    print()
    print("Matching Words")
    print("==============")
    cntr = 0
    for wrd in dct:
        if isCrossWordMatch(wrd, ptrn):
            print(wrd)
            cntr += 1

    if cntr == 0:
        print("No matches found.")

    print()

def isCrossWordMatch(wrd, tmp):
    if len(wrd) != len(tmp):
        return False

    for ix in range(len(wrd)):
        if tmp[ix] != "?":
            if tmp[ix] != wrd[ix]:
                return False

    return True

def anagram(dct):
    ana = input("Enter your anagram:")

    print()
    print("Matching Anagrams")
    print("=================")
    cntr = 0
    for wrd in dct:
        if isAnagram(ana, wrd):
            print(wrd)
            cntr += 1

    if cntr == 0:
        print("No matches found.")

    print()

def isAnagram(str1, str2):
    return sorted(str1) == sorted(str2)

def displayMenu():
    print("1. Anagram Solver")
    print("2. Crossword Solver")
    print("3. Palindrome Finder")
    print()
    print("0. Exit")
    print()

def getIntInput(prompt):
    try:
        ret = int(input(prompt))
        return ret
    except ValueError:
        print("You must enter an integer...")
        return None
    except KeyboardInterrupt:
        print("System Halt!")
        sys.exit("Halt!")
    except:
        print("Unexpected error...")
        return None




def readFileToList(fn):
    # Reads passed in file, populates list, strips whitespace
   
    try:
        handle = open(fn, "r")
        il = handle.readlines()
        handle.close()

        for cntr in range(len(il)):
            il[cntr] = il[cntr].strip()
        print("File has",len(il),"lines...")

        return il
    except:
        return None

if __name__ == '__main__':
    main()
