import NBAPlayer
import NBA_Reports
def main():
    pstats = readCommaDelimitedFileIntoList("player_career.txt")
    if not pstats:
        print("File not read correctly...")
        return False
    reps = loadReports()
    choice = displayMenu(reps,"Enter your choice:" )
    while choice != 0:
        if choice > 0 and choice < len(reps)+1:
            printReport(pstats, reps[choice-1])
        choice = displayMenu(reps,"Enter your choice:" )

def loadReports():
    reps = [NBA_Reports.Reports("Best Players", "Rating", 5, lambda player: player.playerRating),NBA_Reports.Reports("Best Offensive Players", "Rating", 5, lambda player: player.offensiveRating),NBA_Reports.Reports("Best Defensive Players", "Rating", 50, lambda player: player.defensiveRating),NBA_Reports.Reports("Best Scorers", "Points", 50, lambda player: player.pts),NBA_Reports.Reports("Most Assists", "Assists", 5, lambda player: player.asts),NBA_Reports.Reports("Most Steals", "Steals", 5, lambda player: player.stl),NBA_Reports.Reports("Most Rebounds", "Rebounds", 5, lambda player: player.rebs),NBA_Reports.Reports("Most Blocked Shots", "Blocked Shots", 5, lambda player: player.blk),NBA_Reports.Reports("Best Shooting", "Shooting Rating", 5, lambda player: player.shooterRating),NBA_Reports.Reports("Best 3 Point Shooters", "3 Pt Shooting Rating", 5, lambda player: player.threePointShootingRating)]
    return reps
def printReport(stats,report):
    stats.sort(key=report.sort)
    stats.reverse()
    print("\n"+report.repName + "\n" + "="*len(report.repName))
    for ix in range(report.itemCount):
        print(ix+1,"-",stats[ix]," - "+report.lineLabel+": ", report.sort(stats[ix]))
    print()
def displayMenu(reps, pmpt):
    for cntr in range(len(reps)):
        print(cntr+1,reps[cntr].repName + " Report")
    print("0. Exit")
    try:
        return int(input(pmpt))
    except:
        return None
def readCommaDelimitedFileIntoList(fn):
    try:
        fh = open(fn, "r", encoding="utf-8")
        fc = fh.readlines()
        fh.close()
        players = []
        fc.pop(0)  # get rid of titles
        for i in range(len(fc)):   # get rid of white space horizontal
            fc[i] = fc[i].strip()
        while "" in fc:  # get rid of blank lines
            fc.remove("")
        for i in range(len(fc)):  # split each line into a list of fields
            pf = fc[i].split(",")
            players.append(NBAPlayer.NBAPlayer(pf[0], pf[1], pf[2], pf[3], int(pf[4]), int(pf[5]), int(pf[6]), int(pf[7]),int(pf[8]), int(pf[9]), int(pf[10]), int(pf[11]), int(pf[12]), int(pf[13]), int(pf[14]),int(pf[15]), int(pf[16]), int(pf[17]), int(pf[18]), int(pf[19]), int(pf[20])))  # create a player object and add to players list
        return players
    except:
        return None  # None if error
main()