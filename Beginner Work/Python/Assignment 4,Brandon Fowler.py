#Assignment 4, Brandon Fowler

def Main():
    #Main programm function, executes functions in main program
    
    List=[]

    SortList(List)

    x=0
    
    while x!=9:

        DisplayMenu()
    
        ExecuteChoice(List)

        
def DisplayMenu():
    #DisplayMenu function, Displays the menu

    print()
    print("Welcome! Please Create, Modify, and Derive statistics from your list!")
    print("=====================================================================")
    print()
    print("1. Add New Number")
    print("2. Delete a Number")
    print("3. Purge All Numbers From List")
    print("4. Display List")
    print("5. Display Average of List")
    print("6. Display the Mid-Range Value of the List")
    print("7. Display the Median Value of the List")
    print("8. Display the Mode of the List")
    print("9. Exit Program")
    print()
    print()


def SortList(P_Sort):
    #SortList function, sorts the list passed into it through parameters

    P_Sort.sort()
    return P_Sort



def AddNumber(P_AddList):
    #AddNUmber function, adds a number imputed from the user, to the list
    #passed into it from it's parameters.

    Add=float(input("Enter Number:"))
    P_AddList.append(Add)
    P_AddList.sort()
    return P_AddList


def RemoveNumber(P_Remove):
    #RemoveNumber function, removes a number of the users choice, via it's
    #index in the given list

    print()
    print()
    print(P_Remove)
    print()
    print()
    Remove=float(input("Enter Number to Remove:"))
    P_Remove.remove(Remove)
    return P_Remove


def PurgeList(P_Purge):
    #PurgeList function is used to remove all numbers from the given list
    #passed in through parameters
    
    for ClearList in range (0,len(P_Purge)):
        P_Purge.pop()
    return ClearList
    

def AverageList(P_Avg):
    #AverageList function, is used to compute the average of the list passed
    #into it, via parameters

    tot=0
    if len(P_Avg) !=0:
        for val in P_Avg:
            tot+=val
        return tot/len(P_Avg)
    else:
        print ("Cannot Calculate Empty list")
        return
    

def MidRange(P_ListMR):
    #MidRange function is used to find the mid-range of a given list, passed
    #into it through parameters

    if len(P_ListMR) !=0:
        Min=P_ListMR[0]
        Max=P_ListMR[-1]
        return (Min+Max)/2
    else:
        print ("Cannot Calculate Empty list")
        return
    

def Median(P_ListMedian):
    #Median function, is used to find the median of a list passed into it
    #through parameters

    if len(P_ListMedian) !=0:
        if len(P_ListMedian)%2==0:
            Median1=len(P_ListMedian)//2
            Median2=(len(P_ListMedian)//2)-1
            return (P_ListMedian[Median1]+P_ListMedian[Median2])/2 
        else:
            median= len(P_ListMedian)//2
            return (P_ListMedian[median])
    else:
         print ("Cannot Calculate Empty list")
         return

    

def Mode(P_ListMode):
    #Mode function, is used to find the mode of a list, passed in through
    #parameters

    if len(P_ListMode) !=0:
        Accumulator=0
        AmountNum={}
        for val in P_ListMode:
            AmountNum[val]=AmountNum.get(val,0)+1
            Accumulator+=1
        return max(AmountNum,key=AmountNum.__getitem__)
    else:
         print ("Cannot Calculate Empty list")
         return


    

def ExecuteChoice(P_Output):
    #ExecuteChoice function, Gets choice from user and
    #returns it, then executes the choice

    Choice=float(input("Enter Your Choice:"))
    
    while Choice is not 0:
        if Choice==1:
            AddedList=AddNumber(P_Output)
            return AddedList

        if Choice==2:
            RemovedList=RemoveNumber(P_Output)
            return RemovedList

        if Choice==3:
            PurgedList=PurgeList(P_Output)
            return PurgedList

        if Choice==4:
            print()
            print("Numbers in List:",P_Output)
            print()
            return
             
        if Choice==5:
            print()
            print ("Average of List:",AverageList(P_Output))
            print()
            return

        if Choice==6:
            print()
            print("Mid-Range of List:",MidRange(P_Output))
            print()
            return

        if Choice==7:
            print()
            print("Median Index value of List",Median(P_Output))
            print()
            return

        if Choice==8:
            print()
            print("Mode of List",Mode(P_Output))
            print()
            return

        if Choice==9:
            exit()
            

        else:
            print("Entry Not Valid, Please Provide Valid Entry")
            return


#Main Program

Main()
          
    
















