#Assignment 3, Brandon Fowler

x=2

Calculate=1

Factors=0

UpperBound=eval(input("Enter Upper Bound:"))

while x <= UpperBound:

    y=x/(x-1)*x/(x+1)
    
    Calculate=Calculate*y

    x+=2

    Factors+=1

Calculate=Calculate*2

print("Number of factors:",Factors)
print("Derived Pi:",Calculate)
    
