#-------------------------------------------------------------------------------
# Name:        Brandon's Word Solver
# Purpose:
#
# Author:      Brandon Fowler
#
# Created:     06/11/2011
# Copyright:   (c) Brandon Fowler 2011
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

import sys

def main():
    print("Please be patient! These things take timke silly!")
    File= GetDictFile("File")
    if File:
        print("Dictionary Loaded, Nice one!")
    else:
        print("Error. Whatcha do dimwit?")
        sys.exit("Program Dead")
    DisplayMenu()
    UserChoice=UserIntInput("Enter the number of your choice!!!!")
    while UserChoice !=0:
        if UserChoice==1:
            Anagram(File)
        elif UserChoice==2:
            Crossword(File)
        elif UserChoice==3:
            Palindrome(File)
        DisplayMenu()
        UserChoice=UserIntInput("Enter the number of your choice!!!!")





def GetDictFile(P_File):
    try:
        OpenFile=open(P_File)
        PrepFile=OpenFile.readlines()
        OpenFile.close()
        for Counter in range(len(PrepFile)):
            PrepFile[Counter]=PrepFile[Counter].strip()
        print("File has",len(PrepFile),"Lines")
        return PrepFile
    except:
        return None




def DisplayMenu():
    print()
    print()
    print("Please Select a VALID Choice From Below!")
    print()
    print()
    print("For Anagram Enter: 1")
    print("For Crossword Enter: 2")
    print("For Palindrome Enter: 3")
    print("To leave this silly program Enter: 0")
    print()
    print()




def UserIntInput(UI):
    try:
        UI=int(input("Enter The Number"))
        return UI
    except ValueError:
        print("You Must Enter an Integer")
        return None
    except:
        print("You did something stupid. Try again!")
        return None























if __name__ == '__main__':
    main()
