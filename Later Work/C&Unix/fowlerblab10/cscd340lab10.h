//Brandon Fowler
//CSCD340
//Lab10

struct Page_Table_Entry {
	int frame;
	int reference;
	int modified;
}; typedef struct Page_Table_Entry PTE;

FILE * openInput();
int lineCount(FILE * fin);
void conversion(FILE * fin, int lCount, LinkedList * pageTable, int vas, int frameSize, int pas);
void clearPage(Node * page);
