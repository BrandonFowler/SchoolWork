import random

class Coin():
    def __init__(self):
        self.HEADS = 0
        self.TAILS = 1
        self.flipStatus = random.randint(self.HEADS,self.TAILS)
    def __str__(self):
        if self.flipStatus == self.HEADS:
            return "Heads"
        else:
            return "Tails"
    def __repr__(self):
        return self.__str__()
    def getStatus(self):
        return self.flipStatus


coins = []
heads = 0
tails = 0
for i in range(1000000):
    thisCoin = Coin()
    if thisCoin.flipStatus == thisCoin.HEADS:
        heads += 1
    else:
        tails += 1

print(heads, tails)




