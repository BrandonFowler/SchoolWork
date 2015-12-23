
#Assignment 6-Poker Hands
#Writen by Brandon A Fowler

from Card import *

def main():
    #Main Function, Loops through hands, checks for scoring type,
    #Keeps track of scoring hands, Prints out, and writes to disk amount of
    #each type, and percentages of each type.

    NumHands=intInput()

    StraightFlush=0
    FourOfAKind=0
    FullHouse=0
    Flush=0
    Straight=0
    ThreeOfAKind=0
    TwoPair=0
    OnePair=0
    HighCard=0

    for check in range(NumHands):

        Hand=DrawHand()

        if isStraightFlush(Hand):
            StraightFlush+=1
        elif isFourOfAKind(Hand):
            FourOfAKind+=1
        elif isFullHouse(Hand):
            FullHouse+=1
        elif isFlush(Hand):
            Flush+=1
        elif isStraight(Hand):
            Straight+=1
        elif isThreeOfAKind(Hand):
            ThreeOfAKind+=1
        elif isTwoPair(Hand):
            TwoPair+=1
        elif isOnePair(Hand):
            OnePair+=1
        else:
            HighCard+=1


    print()
    print ("Total Number of Hands Drawn: ", NumHands)
    print("----------------------------------------")
    print()
    print ("Straight Flush: ", StraightFlush,\
    " Straight Flushes Dealt,  ", ((StraightFlush)/NumHands)*100,"%")
    print()
    print ("Four Of A Kind: ", FourOfAKind,\
    " Four of a Kinds Dealt,  ", ((FourOfAKind)/NumHands)*100,"%")
    print()
    print ("Full House: ", FullHouse,\
    " Full Houses Dealt,  ", ((FullHouse)/NumHands)*100,"%")
    print()
    print ("Flush: ", Flush,\
    " Flushes Dealt,  ", ((Flush)/NumHands)*100,"%")
    print()
    print ("Straight: ",Straight,\
    " Straights Dealt  ", ((Straight)/NumHands)*100,"%")
    print()
    print ("Three Of A Kind: ",ThreeOfAKind,\
    " Three of a Kinds Dealt,  ", ((ThreeOfAKind)/NumHands)*100,"%")
    print()
    print ("Two Pair: ", TwoPair,\
    " Two Pairs Dealt,  ", ((TwoPair)/NumHands)*100,"%")
    print()
    print ("One Pair: ", OnePair,\
    " One Pairs Dealt,  ", ((OnePair)/NumHands)*100,"%")
    print()
    print ("High Card: ", HighCard,\
    " High Cards Dealt,  ", ((HighCard)/NumHands)*100,"%")


    NH=["Total Number of Hands Drawn: ", str(NumHands)]
    UL="----------------------------------------"
    SF=["Straight Flush: ", str(StraightFlush),\
     " Straight Flushes Dealt,  ", str(((StraightFlush)/NumHands)*100),"%"]
    FK=["Four Of A Kind: ", str(FourOfAKind),\
     " Four of a Kinds Dealt,  ", str(((FourOfAKind)/NumHands)*100),"%"]
    FH=["Full House: ", str(FullHouse),\
     " Full Houses Dealt,  ", str(((FullHouse)/NumHands)*100),"%"]
    F=["Flush: ", str(Flush),\
     " Flushes Dealt,  ", str(((Flush)/NumHands)*100),"%"]
    S=["Straight: ",str(Straight),\
     " Straights Dealt  ", str(((Straight)/NumHands)*100),"%"]
    TK=["Three Of A Kind: ",str(ThreeOfAKind),\
     " Three of a Kinds Dealt,  ", str(((ThreeOfAKind)/NumHands)*100),"%"]
    TP=["Two Pair: ", str(TwoPair),\
     " Two Pairs Dealt,  ", str(((TwoPair)/NumHands)*100),"%"]
    OP=["One Pair: ", str(OnePair),\
     " One Pairs Dealt,  ", str(((OnePair)/NumHands)*100),"%"]
    HC=["High Card: ", str(HighCard),\
     " High Cards Dealt,  ", str(((HighCard)/NumHands)*100),"%"]



    NH2=""
    SF2=""
    FK2=""
    FH2=""
    F2=""
    S2=""
    TK2=""
    TP2=""
    OP2=""
    HC2=""


    for V in NH:
        NH2+=V

    for V in SF:
        SF2+=V

    for V in FK:
        FK2+=V

    for V in FH:
        FH2+=V

    for V in F:
        F2+=V

    for V in S:
        S2+=V

    for V in TK:
        TK2+=V

    for V in TP:
        TP2+=V

    for V in OP:
        OP2+=V

    for V in HC:
        HC2+=V



    writeResults(NH2,UL,SF2,FK2,FH2,F2,S2,TK2,TP2,OP2,HC2)


def DrawHand():
    # randomly draws hand, insures ther are no duplicates, returns hand to main

    hnd = []
    for i in range(5):
        potential = Card()
        while potential in hnd:
            potential = Card()
        hnd.append(potential)

    hnd.sort(key = Card.sortKey)

    return hnd


def isStraightFlush(hand):
    #isStraightFlush FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Straight Flush.

    if isFlush(hand) and isStraight(hand):
        return True



def isFourOfAKind(hand):
    #isFourOFAKind FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Four of a kind.

    if hand[0].rank==hand[1].rank and hand[0].rank==hand[2].rank\
     and hand[0].rank==hand[3].rank:
        return True
    elif hand[1].rank==hand[2].rank and hand[1].rank==hand[3].rank\
     and hand[1].rank==hand[4].rank:
        return True




def isFullHouse(hand):
    #isFullHouse FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Full House.
	
    if hand[0].rank==hand[1].rank and hand[0].rank==hand[2].rank\
     and hand[3].rank==hand[4].rank:
        return True
    elif hand[0].rank==hand[1].rank and hand[2].rank==hand[3].rank\
     and hand[2].rank==hand[4].rank:
        return True




def isFlush(hand):
    #isFlush FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Flush.

    if hand[0].suit==hand[1].suit and hand[0].suit==hand[2].suit\
     and hand[0].suit==hand[3].suit and hand[0].suit==hand[4].suit:
        return True




def isStraight(hand):
    #isStraight FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Straight.

    x=0
    while x<1:
        if hand[0].rank==hand[1].rank-1 and hand[0].rank==hand[2].rank-2\
         and hand[0].rank==hand[3].rank-3 and hand[0].rank==hand[4].rank-4:
            return True
        Card.ACE=14
        if hand[0].rank==hand[1].rank-1 and hand[0].rank==hand[2].rank-2\
         and hand[0].rank==hand[3].rank-3 and hand[0].rank==hand[4].rank-4:
            return True
        Card.Ace=1
        x=+1




def isThreeOfAKind(hand):
    #isThreeOfAKind FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Three of a kind.

    if hand[0].rank==hand[1].rank and hand[0].rank==hand[2].rank:
        return True
    elif hand[1].rank==hand[2].rank and hand[1].rank==hand[3].rank:
        return True
    elif hand[2].rank==hand[3].rank and hand[2].rank==hand[4].rank:
        return True




def isTwoPair(hand):
    #isTwoPair FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a Two Pair.
	
    if hand[0].rank==hand[1].rank and hand[2].rank==hand[3].rank:
        return True
    elif hand[1].rank==hand[2].rank and hand[3].rank==hand[4].rank:
        return True
    elif hand[0].rank==hand[1].rank and hand[3].rank==hand[4].rank:
        return True





def isOnePair(hand):
    #isOnePair FunctionChecks, to see if the list of cards passed into
    #the function match the pattern of a One Pair.

    if hand[0].rank==hand[1].rank:
        return True
    elif hand[1].rank==hand[2].rank:
        return True
    elif hand[2].rank==hand[3].rank:
        return True
    elif hand[3].rank==hand[4].rank:
        return True

def writeResults(P_NH,P_UL,P_SF,P_FK,P_FH,P_F,P_S,P_TK,P_TP,P_OP,P_HC):
    # writes the results to the disk file "results.txt"

    try:
        fh=open("results.txt","w")
        fh.write(P_NH)
        fh.write("\n")
        fh.write(P_UL)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_SF)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_FK)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_FH)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_F)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_S)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_TK)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_TP)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_OP)
        fh.write("\n")
        fh.write("\n")
        fh.write(P_HC)
        fh.close
    except ValueError:
        print ("Program Stopping!")
    except:
        print ("Issue opening file!")

def intInput():
    #intInput Function, Gets integer input from user then returns it
	
    try:
        UI=int(input("Enter Amount of Hands to Deal: "))
        return UI
    except KeyboardInterrupt:
        print ("Program Succesfully Terminated")
    except:
        print ("Ya did something wrong silly! Try again!")

#Main Program

main()
