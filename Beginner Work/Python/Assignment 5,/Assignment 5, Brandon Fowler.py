1#-------------------------------------------------------------------------------
# Name:        Assignment 5
# Purpose:     Show NBA Stats
#
# Author:      Brandon Fowler
#
# Created:     07/11/2011
# Copyright:   (c) Brandon Fowler 2011
# Licence:     N/A
#-------------------------------------------------------------------------------
#!/usr/bin/env python

import sys

def main():
#Main Function- Contains Main Program- Executes Program Functions

    pass

    print("Loading NBA Stats")

    player_career=ReadFile("player_career.txt")

    if player_career:
        print("Stats Loaded")
    else:
        print("Something went wrong!")
        sys.exit("Program Dead!")

    player_career=parseList(player_career)


    ShowMenu()
    choice=GetIntInput("Enter Your Choice: ")

    while choice !=0:
        if choice==1:
            BestPlayers(player_career)
        elif choice==2:
            BestOffensivePlayers(player_career)
        elif choice==3:
            BestDefensivePlayers(player_career)
        elif choice==4:
            TopScorers(player_career)
        elif choice==5:
            TopAssist(player_career)
        elif choice==6:
            TopSteals(player_career)
        elif choice==7:
            TopRebounds(player_career)
        elif choice==8:
            TopBlockers(player_career)
        elif choice==9:
            TopShooters(player_career)
        elif choice==10:
            TopThreePointers(player_career)

        ShowMenu()
        choice=GetIntInput("Enter Choice: ")



def ReadFile(P_File):

# ReadFile Function- Reads passed in file- populates list- strips whitespace- Returns

    try:
        OpenFile=open(P_File,"r")
        PrepFile=OpenFile.readlines()
        OpenFile.close()

        for L in range(len(PrepFile)-1, -1, -1):
            if PrepFile[L].strip() == "":
                PrepFile.pop(L)

        return PrepFile
    except:
        return None



def ShowMenu():

#ShowMenu Function- prints menu of choices

    print()
    print()
    print("To List 50 Top Players Input # 1")
    print("To List 50 Best Offensive Players Input # 2")
    print("To List 50 Best Defensive Players Input # 3")
    print("To List Top 50 Scorers Input # 4")
    print ("To List Top 50 Assists Input # 5")
    print("To List Top 50 Steals Input # 6")
    print("To List Top 50 Rebounds Input # 7")
    print("To List Top 50 Blocked Shots Input # 8")
    print("To List Top 50 Shooters Input # 9")
    print("To List Top 50 Three Point Shooters Input # 10 ")
    print("To Exit Program Input # 0")
    print()
    print()


def GetIntInput(Prompt):

#GetIntInput Function- Gets Integer Input From User- Returns

    try:
        UI=int(input(Prompt))
        return UI
    except ValueError:
        print("Enter a Valid Choice Silly!!")
        return None
    except KeyboardInterrupt:
        print("Stopping Program")
        sys.exit("Blackout!")
    except:
        print("Uh Oh, Something is Wrong")
        return None


def parseList(P_lst):

#parseList Function- Strips White Spaces- Splits List- Pops First Line- Converts
#Stings within List to Integers- Returns

    for L in range(len(P_lst)):
        P_lst[L] = P_lst[L].strip()
        P_lst[L] = P_lst[L].split(",")

    P_lst.pop(0)

    for L in range(len(P_lst)):
        P_lst[L][4] = int(P_lst[L][4])
    for L in range(len(P_lst)):
        P_lst[L][5] = int(P_lst[L][5])
    for L in range(len(P_lst)):
        P_lst[L][6] = int(P_lst[L][6])
    for L in range(len(P_lst)):
        P_lst[L][7] = int(P_lst[L][7])
    for L in range(len(P_lst)):
        P_lst[L][8] = int(P_lst[L][8])
    for L in range(len(P_lst)):
        P_lst[L][9] = int(P_lst[L][9])
    for L in range(len(P_lst)):
        P_lst[L][10] = int(P_lst[L][10])
    for L in range(len(P_lst)):
        P_lst[L][11] = int(P_lst[L][11])
    for L in range(len(P_lst)):
        P_lst[L][12] = int(P_lst[L][12])
    for L in range(len(P_lst)):
        P_lst[L][13] = int(P_lst[L][13])
    for L in range(len(P_lst)):
        P_lst[L][14] = int(P_lst[L][14])
    for L in range(len(P_lst)):
        P_lst[L][15] = int(P_lst[L][15])
    for L in range(len(P_lst)):
        P_lst[L][16] = int(P_lst[L][16])
    for L in range(len(P_lst)):
        P_lst[L][17] = int(P_lst[L][17])
    for L in range(len(P_lst)):
        P_lst[L][18] = int(P_lst[L][18])
    for L in range(len(P_lst)):
        P_lst[L][19] = int(P_lst[L][19])
    for L in range(len(P_lst)):
        P_lst[L][20] = int(P_lst[L][20])

    return P_lst





def BestPlayers(P_BK):

#BestPlayer Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50


    try:
        Stack=[]

        for L in range(len(P_BK)):

            BPres=[((P_BK[L][6]+P_BK[L][9]+P_BK[L][10]+P_BK[L][11]+P_BK[L][12])-((P_BK[L][15]-P_BK[L][16])- (P_BK[L][17]-P_BK[L][18]) + P_BK[L][13]))/ P_BK[L][4],str(P_BK[L][1]),str(P_BK[L][2])]

            Stack.append(BPres)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            BPres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(BPres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0




def BestOffensivePlayers(P_BOK):

#BestOffensivePlayer Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_BOK)):

            if P_BOK[L][15]==0:
                pass
            else:
                BOPres=[((P_BOK[L][6]+P_BOK[L][10]) - (P_BOK[L][13] * 4)) * (P_BOK[L][16] / P_BOK[L][15]),str(P_BOK[L][1]),str(P_BOK[L][2])]

                Stack.append(BOPres)

                Stack=sorted(Stack)

        Stack.reverse()


        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            BOPres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(BOPres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])





    except:
        return 0





def BestDefensivePlayers(P_BDK):

#BestDefensivePlayer Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_BDK)):

            BDPres=[(P_BDK[L][8] + (P_BDK[L][11] * 1.5) + (P_BDK[L][12]*2)),str(P_BDK[L][1]),str(P_BDK[L][2])]

            Stack.append(BDPres)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            BDPres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(BDPres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0



def TopScorers(P_BSK):

#TopScorers Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_BSK)):

            BSres=[(P_BSK[L][6]),str(P_BSK[L][1]),str(P_BSK[L][2])]

            Stack.append(BSres)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            BSres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(BSres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0


def TopAssist(P_AK):

#TopAssist Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_AK)):

            Ares=[(P_AK[L][10]),str(P_AK[L][1]),str(P_AK[L][2])]

            Stack.append(Ares)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            Ares2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(Ares2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0





def TopSteals(P_SK):

#TopSteals Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_SK)):

            Sres=[(P_SK[L][11]),str(P_SK[L][1]),str(P_SK[L][2])]

            Stack.append(Sres)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            Sres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(Sres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0




def TopRebounds(P_RK):

#TopRebounds Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_RK)):

            Rres=[(P_RK[L][9]),str(P_RK[L][1]),str(P_RK[L][2])]

            Stack.append(Rres)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            Rres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(Rres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0






def TopBlockers(P_BLK):

#TopBlockers Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_BLK)):

            Bres=[(P_BLK[L][12]),str(P_BLK[L][1]),str(P_BLK[L][2])]

            Stack.append(Bres)

            Stack=sorted(Stack)

        Stack.reverse()

        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            Bres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(Bres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])

    except:
        return 0





def TopShooters(P_SHK):

#TopShooters Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_SHK)):

            if P_SHK[L][15]==0 or P_SHK[L][17]==0 or P_SHK[L][19]==0:
                pass
            else:
                SHres=[((P_SHK[L][16]/P_SHK[L][15])*2) + (P_SHK[L][18]/P_SHK[L][17]) + ((P_SHK[L][20]/P_SHK[L][19])*3),str(P_SHK[L][1]),str(P_SHK[L][2])]

                Stack.append(SHres)

                Stack=sorted(Stack)

        Stack.reverse()


        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            SHres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(SHres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])





    except:
        return 0






def TopThreePointers(P_TPK):

#TopThreePointers Function- Uses Data From Passed in List to Calculate and Sort
#Players in Order From Best to Worst- Prints Top 50

    try:
        Stack=[]

        for L in range(len(P_TPK)):

            if P_TPK[L][19]==0:
                pass
            else:
                TPres=[(P_TPK[L][20]/P_TPK[L][19]),str(P_TPK[L][1]),str(P_TPK[L][2])]

                Stack.append(TPres)

                Stack=sorted(Stack)

        Stack.reverse()


        Stack2=[]
        cntr=1
        for L2 in range(len(Stack)):
            TPres2=str(cntr)+". "+Stack[L2][1]+" "+Stack[L2][2]+" - "+str(Stack[L2][0])
            Stack2.append(TPres2)
            cntr+=1


        print (Stack2[0])
        print (Stack2[1])
        print (Stack2[2])
        print (Stack2[3])
        print (Stack2[4])
        print (Stack2[5])
        print (Stack2[6])
        print (Stack2[7])
        print (Stack2[8])
        print (Stack2[9])
        print (Stack2[10])
        print (Stack2[11])
        print (Stack2[12])
        print (Stack2[13])
        print (Stack2[14])
        print (Stack2[15])
        print (Stack2[16])
        print (Stack2[17])
        print (Stack2[18])
        print (Stack2[19])
        print (Stack2[20])
        print (Stack2[21])
        print (Stack2[22])
        print (Stack2[23])
        print (Stack2[24])
        print (Stack2[25])
        print (Stack2[26])
        print (Stack2[27])
        print (Stack2[28])
        print (Stack2[29])
        print (Stack2[30])
        print (Stack2[31])
        print (Stack2[32])
        print (Stack2[33])
        print (Stack2[34])
        print (Stack2[35])
        print (Stack2[36])
        print (Stack2[37])
        print (Stack2[38])
        print (Stack2[39])
        print (Stack2[40])
        print (Stack2[41])
        print (Stack2[42])
        print (Stack2[43])
        print (Stack2[44])
        print (Stack2[45])
        print (Stack2[46])
        print (Stack2[47])
        print (Stack2[48])
        print (Stack2[49])


    except:
        return 0







if __name__ == '__main__':
    main()
