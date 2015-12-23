#Brandon Fowler, Assignment 2

def CalculatePrice(p_A,p_S,p_C):
    PriceAdults = p_A*7
    PriceSenior = p_S*5
    if p_C <4:
        PriceChildren = p_C*4
    else:
        PriceChildren = p_C*3.5
    TotalPrice = PriceAdults + PriceSenior + PriceChildren
    return TotalPrice
    




#Main

Adults = eval(input("Enter Number of Adults:"))

Seniors = eval(input("Enter Number of Seniors:"))

Children = eval(input("Enter Number of Children:"))


Result = CalculatePrice(Adults,Seniors,Children)


print("Aduts:",Adults,  "Seniors:",Seniors,"Children:",Children)

print("Overall price:",Result,"Dollars")
