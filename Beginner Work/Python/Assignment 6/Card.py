import random
class Card():

    def __init__(self):
        # a whole bunch of constants
        self.NOSUIT = 0
        self.SPADES = 1
        self.HEARTS = 2
        self.DIAMONDS= 3
        self.CLUBS = 4
        self.JACK = 11
        self.QUEEN = 12
        self.KING = 13
        self.ACE = 1
        self.SUITNAMES =["Selected", "Spades", "Hearts", "Diamonds", "Clubs"]
        self.SHORTSUITNAMES = ["-","S","H","D","C"]
        self.RANKNAMES = ["None", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King","Ace+"]
        self.SHORTRANKNAMES = ["-", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K","A+"]

        # our actual properties that make up card
        self.suit=random.randint(1,4)
        self.rank=random.randint(1,13)

    # following methods allow card to card comparison and hand sorting
    def __eq__(self, other):
        return (self.suit == other.suit) and (self.rank == other.rank)
    def __lt__(self,other):
        return self.rank == other.rank
    def __gt__(self, other):
        return self.rank > other.rank
    def __le__(self, other):
        return self.__lt__(self, other) or self.__eq__(self, other)
    def __ge__(self,other):
        return self.__gt__(self, other) or self.__eq__(self, other)
    def isSameSuit (self, other):
        return self.suit == other.suit
    def isSameRank (self, other):
        return self.rank == other.rank
    def sortKey(self):
        return self.rank, self.suit

    # long name if print indivual card
    def __str__(self):
        return self.RANKNAMES[self.rank]+" "+self.SUITNAMES[self.suit]
    # Short name if print in list
    def __repr__(self):
        return self.SHORTRANKNAMES[self.rank]+""+self.SHORTSUITNAMES[self.suit]