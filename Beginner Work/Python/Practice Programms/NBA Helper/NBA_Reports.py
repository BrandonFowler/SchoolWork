class Reports():
    def __init__(self,p_name, p_lineLabel, p_count, p_sort):
        self.repName = p_name
        self.lineLabel = p_lineLabel
        self.itemCount = p_count
        self.sort = p_sort
    def __str__(self):
        return self.repName
    def __repr__(self):
        return self.__str__()

