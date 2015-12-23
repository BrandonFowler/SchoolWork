class NBAPlayer():
    def __init__(self, id, first, last, league, gp, mins, pts, oreb, dreb, rebs, asts, stl, blk, turnover, pf, fga, fgm, fta, ftm, tpa, tpm):
        self.id= id
        self.first = first
        self.last = last
        self.league = league
        self.gp = gp
        self.min = mins
        self.pts = pts
        self.oreb = oreb
        self.dreb = dreb
        self.rebs = rebs
        self.asts = asts
        self.stl = stl
        self.blk = blk
        self.turnover = turnover
        self.pf = pf
        self.fga = fga
        self.fgm = fgm
        self.fta = fta
        self.ftm = ftm
        self.tpa = tpa
        self.tpm = tpm
        self.playerRating = 0
        self.offensiveRating = 0
        self.defensiveRating = 0
        self.shooterRating = 0
        self.threePointShootingRating = 0

        self.setPlayerRating()
        self.setOffensiveRating()
        self.setDefensiveRating()
        self.setShooterRating()
        self.setThreePointShootingRating()



    def getPlayerRatingString(self):
        return str(self.playerRating) + " - (PTS:" + str(self.pts) + " REBS:" + str(self.rebs) + " ASTS:" + str(self.asts) + " STLS:" + str(self.stl) + " BLKS:" + str(self.blk) + ")"
    def getOffensiveRatingString(self):
        return str(self.offensiveRating) + " - (PTS:" + str(self.pts) + " ASTS:" + str(self.asts) + " FG%" + str(self.fgm) + "/" + str(self.fga) + " TO:" + str(self.turnover) + ")"
    def getDefensiveRatingString(self):
        return str(self.defensiveRating) + " - (DREBS:" + str(self.dreb) + " STLS:" + str(self.stl) + " BLKS:" + str(self.blk) + ")"
    def getShooterRatingString(self):
        return str(self.shooterRating) + " - (FG:" + str(self.fgm)+"/"+str(self.fga)+") (3PG:" + str(self.tpm) + "/" + str(self.tpa) + ") (FT:" + str(self.ftm) + "/" + str(self.fta) + ")"
    def getThreePointShootingRating(self):
        return str(self.threePointShootingRating) + " - (" + str(self.tpm) + "/" + str(self.tpa) + ")"

    def setPlayerRating(self):
        if self.gp == 0:
            self.playerRating = 0
        else:
            self.playerRating = ((self.pts+self.rebs+self.asts+self.stl+self.blk)-((self.fga-self.fgm)- (self.fta-self.ftm) + self.turnover))/ self.gp
    def setOffensiveRating(self):
        if self.fga == 0:
            self.offensiveRating = 0
        else:
            self.offensiveRating =  ((self.pts+self.asts) - (self.turnover * 4)) * (self.fgm / self.fga)
    def setDefensiveRating(self):
        self.defensiveRating = self.dreb + (self.stl*1.5) + (self.blk*2)
    def setShooterRating(self):
        self.shooterRating = self.__shootper__(self.fgm,self.fga)*2 + self.__shootper__(self.ftm,self.fta) + self.__shootper__(self.tpm,self.tpa)*3
    def setThreePointShootingRating(self):
        self.threePointShootingRating = self.__shootper__(self.tpm,self.tpa)
    def __shootper__(self,made, attempt):
        return 0 if attempt == 0 else made/attempt
    def __str__(self):
        return self.last + ", " + self.first
    def __repr__(self):
        return self.__str__()
