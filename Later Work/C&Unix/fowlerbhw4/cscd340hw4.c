//Brandon Fowler
//CSCD340
//Homework 4

#include "cscd340hw4.h"

double numbersOfProcessesPlaced[] = {0,0,0};//FirstFit,BestFit,WorstFit
double sumPlaceTimes[] = {0,0,0};//FirstFit,BestFit,WorstFit

/*
main()
Description: Opens input file, initializes Linked Lists,
calls functions to execute menu options.
----------------------------------------------------------------------------
*/
int main(){
	LinkedList * firstFit = linkedList();//Used for First Fit algorithm
	LinkedList * bestFit;//Used for Best Fit algorithm
	LinkedList * worstFit;//Used for Worst Fit algorithm

	FILE * fin = fopen("initialMemory.txt","r");
	if(fin == NULL){
		printf("No input file found. Exiting!");
		exit(-1);
	}

	int numLines = countLines(fin);//Number of lines in file
	constructMemoryMap(numLines, firstFit, fin);//Set up intial state

	fclose(fin);

	bestFit = duplicateLinkedList(firstFit);//copy initial state
	worstFit = duplicateLinkedList(firstFit);//copy initial state

	int option = menu();
	while(option){
		if(option == 1){
			newProc(firstFit,bestFit,worstFit);//Add new process to lists
		}
		else if(option == 2){
			terminateProcess(firstFit,bestFit,worstFit);//Terminate process in lists
		}
		else if(option == 3){
			printListMap(firstFit,bestFit,worstFit);//Print list details
		}
		else{
			printStats(firstFit,bestFit,worstFit);//Print algorithm stats
		}
		option = menu();
	}
	clearList(firstFit);
	clearList(bestFit);
	clearList(worstFit);
}

/*
menu()
Description: Offers options to user and returns choice
----------------------------------------------------------------------------
*/
int menu(){
	int option;
	printf("options:\n");
	printf("0.Exit\n");
	printf("1.Enter new process\n");
	printf("2.Terminate an existing process\n");
	printf("3.Print current lists for all algorithm\n");
	printf("4.Print current statistics for all algorithm\n");
	printf("Choose the number of your option: ");
	fscanf(stdin,"%d",&option);
	if(option < 0 || option > 4){
		printf("Invalid option!\n");
		option = menu();
	} 
	return option;
}

/*
addProcess()
Description: Places Memory Block into new Node and adds it
to the Linked List
----------------------------------------------------------------------------
*/
void addProcess(LinkedList * list, MB * memBlock){
	Node * nn = buildNode();
	nn->data = memBlock;
	addLast(list, nn);
}

/*
duplicateLinkedList()
Description: Copies a Linked List and returns the copy
----------------------------------------------------------------------------
*/
LinkedList * duplicateLinkedList(LinkedList * list){
	LinkedList * new = linkedList();
	Node * cur = list->head;
	while(cur != NULL){//Walk through list
		Node * nn = buildNode();
		nn->data = (MB*)calloc(1,sizeof(MB));
		memcpy(nn->data, &(*((MB*)cur->data)), sizeof(MB));//Copy data
		addLast(new, nn);
		cur = cur->next;
	}
	return new;	
}	

/*
countLines()
Description: Counts number of lines in file
----------------------------------------------------------------------------
*/
int countLines(FILE * fin){
	int lines = 0;
	char temp[100];
	while(!feof(fin)){
		lines++;
		fgets(temp,100,fin);
	}
	rewind(fin);
	return lines;
}

/*
constructMemoryMap()
Description: Constructs a memory map of the intial state given by
the input file.
----------------------------------------------------------------------------
*/
void constructMemoryMap(int numLines, LinkedList * firstFit, FILE * fin){
	int memorySize;//Total memory space
	int memoryStart;//Start of memory space
	fscanf(fin,"%d",&memorySize);
	fscanf(fin,"%d",&memoryStart);
	int listEndPoint = memorySize + memoryStart;//End of memory space
	int i = 2;
	int lastEndPoint = memoryStart;//Keeps track end point of last process or hole
	char c;//Holds junk characters from input
	int pid;//process id
	int segSize;//process size
	int pStart;//process start location

	for(i; i < numLines; i++){//For entire file
		fscanf(fin,"%c",&c);
		while(c == '\n'){//Remove end lines
			fscanf(fin,"%c",&c);
		}
		fscanf(fin,"%d",&pid);//Get pid
		fscanf(fin,"%d",&segSize);//Get process size
		fscanf(fin,"%d",&pStart);//Get process start
		if(c == 'p' || c == 'P'){//If is a process
			if(pStart > lastEndPoint){//Check if need to add hole
				int HSize = pStart - lastEndPoint;//Hole size

				//Make and add hole
				MB * hole = (MB*)calloc(1,sizeof(MB));
				hole->startAddress = lastEndPoint;
				hole->endAddress = lastEndPoint + HSize;
				hole->segmentSize = HSize;
				hole->processId = 0;
				addProcess(firstFit, hole);
			}
				
			//Make and add process
			lastEndPoint = pStart + segSize;
			MB * memBlock = (MB*)calloc(1,sizeof(MB));
			memBlock->startAddress = pStart;
			memBlock->endAddress = pStart + segSize;
			memBlock->segmentSize = segSize;
			memBlock->processId = pid;
			addProcess(firstFit, memBlock);
		}
	}
	
	//Add hole to end if needed
	if(lastEndPoint < listEndPoint){
		int HSize = listEndPoint - lastEndPoint;
		MB * hole = (MB*)calloc(1,sizeof(MB));
		hole->startAddress = lastEndPoint;
		hole->endAddress = lastEndPoint + HSize;
		hole->segmentSize = HSize;
		hole->processId = 0;
		addProcess(firstFit, hole);
	}
}

/*
newProc()
Description: Gets new process data from user, then calls functions to
place it.
----------------------------------------------------------------------------
*/
void newProc(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit){
	int pid;
	int segSize;
	printf("Enter process ID(Example: 81): ");
	fscanf(stdin,"%d",&pid);
	printf("Enter size(Example: 47): ");
	fscanf(stdin,"%d",&segSize);
	firstFitPlacer(pid,segSize,firstFit);//Send process to First Fit algorithm
	bestFitPlacer(pid,segSize,bestFit);//Send process to Best Fit algorithm
	worstFitPlacer(pid,segSize,worstFit);//Send process to Worst Fit algorithm
}

/*
terminateProcess()
Description: Gets process id from user, then calls functions to terminate
----------------------------------------------------------------------------
*/
void terminateProcess(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit){
	int pid;
	printf("Enter process ID(Example: 81): ");
	fscanf(stdin,"%d",&pid);
	terminateSpecified(pid,firstFit,"First Fit");//Terminate process from First Fit list
	terminateSpecified(pid,bestFit,"Best Fit");//Terminate process from Best Fit list
	terminateSpecified(pid,worstFit,"Worst Fit");//Terminate process from Worst Fit list
	printf("\n");
}

/*
printListMap()
Description: Prints the memory details of each linked list. 
----------------------------------------------------------------------------
*/
void printListMap(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit){

	//Print First Fit List
	Node * cur = firstFit->head;
	printf("\nFirst Fit List:\n");
	while(cur != NULL){
		if(((MB*)(cur->data))->processId == 0){//Is a hole
			printf("Hole   | Start: %d, Size: %d, End: %d\n",((MB*)(cur->data))->startAddress,
			((MB*)(cur->data))->segmentSize,((MB*)(cur->data))->endAddress);
		}
		else{//Is a process
			printf("Process| PID: %d, Start: %d, Size %d, End: %d\n",((MB*)(cur->data))->processId,
			((MB*)(cur->data))->startAddress,((MB*)(cur->data))->segmentSize,
			((MB*)(cur->data))->endAddress);
		}
		cur = cur->next;
	}
	
	//Print Best Fit List
	cur = bestFit->head;
	printf("\nBest Fit List:\n");
	while(cur != NULL){
		if(((MB*)(cur->data))->processId == 0){//Is a hole
			printf("Hole   | Start: %d, Size: %d, End: %d\n",
			((MB*)(cur->data))->startAddress,((MB*)(cur->data))->segmentSize,
			((MB*)(cur->data))->endAddress);
		}
		else{//Is a process
			printf("Process| PID: %d, Start: %d, Size %d, End: %d\n",((MB*)(cur->data))->processId,
			((MB*)(cur->data))->startAddress,((MB*)(cur->data))->segmentSize,
			((MB*)(cur->data))->endAddress);
		}
		cur = cur->next;
	}

	//Print Worst Fit List
	cur = worstFit->head;
	printf("\nWorst Fit List:\n");
	while(cur != NULL){
		if(((MB*)(cur->data))->processId == 0){//Is a hole
			printf("Hole   | Start: %d, Size: %d, End: %d\n",((MB*)(cur->data))->startAddress,
			((MB*)(cur->data))->segmentSize,((MB*)(cur->data))->endAddress);
		}
		else{//Is a process
			printf("Process| PID: %d, Start: %d, Size %d, End: %d\n",((MB*)(cur->data))->processId,
			((MB*)(cur->data))->startAddress,((MB*)(cur->data))->segmentSize,
			((MB*)(cur->data))->endAddress);
		}
		cur = cur->next;
	}
	printf("\n");
}

/*
printStats()
Description: Prints out the current statistics of each algorithm
----------------------------------------------------------------------------
*/
void printStats(LinkedList * firstFit, LinkedList * bestFit, LinkedList * worstFit){
	
	//Print stats for First Fit
	printf("\n");
	printFragmentDetails(firstFit,"First Fit");
	if(numbersOfProcessesPlaced[0] != 0){//Avoid divide by zero
		double firstFitAvg = sumPlaceTimes[0]/numbersOfProcessesPlaced[0];
		printf("Average placement time for First Fit: %.2f\n\n", firstFitAvg);
	}
	
	//Print stats for Best Fit
	printFragmentDetails(bestFit, "Best Fit");
	if(numbersOfProcessesPlaced[1] != 0){//Avoid divide by zero
		double bestFitAvg = sumPlaceTimes[1]/numbersOfProcessesPlaced[1];
		printf("Average placement time for Best Fit: %.2f\n\n", bestFitAvg);
	}

	//Print stats for Worst Fit
	printFragmentDetails(worstFit, "Worst Fit");
	if(numbersOfProcessesPlaced[2] != 0){//Avoid divide by zero
		double worstFitAvg = sumPlaceTimes[2]/numbersOfProcessesPlaced[2];
		printf("Average placement time for Worst Fit: %.2f\n\n", worstFitAvg);
	}
}

/*
firstFitPlacer()
Description: Places a new process into a Linked List using the First
Fit algorithm.
----------------------------------------------------------------------------
*/
void firstFitPlacer(int pid,int size, LinkedList * firstFit){
	int timeTracker = 0;//Keeps track of number of items searched during placment
	Node * cur = firstFit->head;
	while(cur != NULL){//Walk through list
		timeTracker++;
		//If a hole is big enough for process
		if((((MB*)(cur->data))->processId) == 0 && size <= (((MB*)(cur->data))->segmentSize)){
			break;
		}
		cur = cur->next;
	}

	if(cur == NULL){//Hole not found
		printf("\nThere is not enough memory for this process in First Fit list.\n");
		printf("Please terminate a process and try again.\n\n");
		return;
	}
	else{
		//Place process, and adjust hole
		MB * memBlock = (MB*)calloc(1,sizeof(MB));
		memBlock->processId = 0;
		memBlock->startAddress = size + ((MB*)(cur->data))->startAddress;
		memBlock->endAddress = ((MB*)(cur->data))->endAddress;
		memBlock->segmentSize = (((MB*)(cur->data))->segmentSize) - size;
		Node * newNode = buildNode();
		newNode->data = memBlock;
		((MB*)(cur->data))->processId = pid;
		((MB*)(cur->data))->endAddress = size + ((MB*)(cur->data))->startAddress;
		((MB*)(cur->data))->segmentSize = size;
		if(memBlock->segmentSize != 0){
			newNode->next = cur->next;
			cur->next = newNode;
		}
		else{
			free(newNode->data);
			free(newNode);
		}
		numbersOfProcessesPlaced[0]++;
		sumPlaceTimes[0] += timeTracker;
	}
	printf("\nProcess placed with First Fit algorithm.\n");
	printf("Placement time: %d (items searched)\n\n",timeTracker);
}

/*
bestFitPlacer()
Description: Places a new process into a Linked List using the Best
Fit algorithm.
----------------------------------------------------------------------------
*/
void bestFitPlacer(int pid,int size, LinkedList * bestFit){
	int timeTracker = 0;//Keeps track of number of items searched during placment
	int timeTrackerLocation = 0;//Tracks placement
	Node * cur = bestFit->head;
	Node * bestHole = NULL;
	while(cur != NULL){//Walk through list
		timeTracker++;
		if((((MB*)(cur->data))->processId) == 0 && size <= (((MB*)(cur->data))->segmentSize)){
			if(bestHole == NULL || (((MB*)(cur->data))->segmentSize) < (((MB*)(bestHole->data))->segmentSize)){
				bestHole = cur;
				timeTrackerLocation = timeTracker;
			}
		}
		cur = cur->next;
	}
	if(bestHole == NULL){//Hole not found
		printf("\nThere is not enough memory for this process in Best Fit list.\n");
		printf("Please terminate a process and try again.\n\n");
		return;
	}
	else{
		//Place process, and adjust hole
		MB * memBlock = (MB*)calloc(1,sizeof(MB));
		memBlock->processId = 0;
		memBlock->startAddress = size + ((MB*)(bestHole->data))->startAddress;
		memBlock->endAddress = ((MB*)(bestHole->data))->endAddress;
		memBlock->segmentSize = (((MB*)(bestHole->data))->segmentSize) - size;
		Node * newNode = buildNode();
		newNode->data = memBlock;
		((MB*)(bestHole->data))->processId = pid;
		((MB*)(bestHole->data))->endAddress = size + ((MB*)(bestHole->data))->startAddress;
		((MB*)(bestHole->data))->segmentSize = size;
		if(memBlock->segmentSize != 0){
			newNode->next = bestHole->next;
			bestHole->next = newNode;
		}
		else{
			free(newNode->data);
			free(newNode);
		}
		numbersOfProcessesPlaced[1]++;
		sumPlaceTimes[1] += timeTrackerLocation;
	}
	printf("\nProcess placed with Best Fit algorithm.\n");
	printf("Placement time: %d (items searched)\n\n",timeTrackerLocation);
}

/*
worstFitPlacer()
Description: Places a new process into a Linked List using the Worst
Fit algorithm.
----------------------------------------------------------------------------
*/
void worstFitPlacer(int pid,int size, LinkedList * worstFit){
	int timeTracker = 0;//Keeps track of number of items searched during placment
	int timeTrackerLocation = 0;//Tracks placement
	Node * cur = worstFit->head;
	Node * bestHole = NULL;
	while(cur != NULL){//Walk through list
		timeTracker++;
		if((((MB*)(cur->data))->processId) == 0 && size <= (((MB*)(cur->data))->segmentSize)){
			if(bestHole == NULL || (((MB*)(cur->data))->segmentSize) > (((MB*)(bestHole->data))->segmentSize)){
				bestHole = cur;
				timeTrackerLocation = timeTracker;
			}
		}
		cur = cur->next;
	}
	if(bestHole == NULL){//Hole not found
		printf("\nThere is not enough memory for this process in Worst Fit list.\n");
		printf("Please terminate a process and try again.\n\n");
		return;
	}
	else{
		//Place process, and adjust hole
		MB * memBlock = (MB*)calloc(1,sizeof(MB));
		memBlock->processId = 0;
		memBlock->startAddress = size + ((MB*)(bestHole->data))->startAddress;
		memBlock->endAddress = ((MB*)(bestHole->data))->endAddress;
		memBlock->segmentSize = (((MB*)(bestHole->data))->segmentSize) - size;
		Node * newNode = buildNode();
		newNode->data = memBlock;
		((MB*)(bestHole->data))->processId = pid;
		((MB*)(bestHole->data))->endAddress = size + ((MB*)(bestHole->data))->startAddress;
		((MB*)(bestHole->data))->segmentSize = size;
		if(memBlock->segmentSize != 0){
			newNode->next = bestHole->next;
			bestHole->next = newNode;
		}
		else{
			free(newNode->data);
			free(newNode);
		}
		numbersOfProcessesPlaced[2]++;
		sumPlaceTimes[2] += timeTrackerLocation;
	}
	printf("\nProcess placed with Worst Fit algorithm.\n");
	printf("Placement time: %d (items searched)\n\n",timeTrackerLocation);
}

/*
terminateSpecified()
Description: Terminates the process with the specified ID.
----------------------------------------------------------------------------
*/
void terminateSpecified(int pid, LinkedList * list, char alg[]){
	Node * cur = list->head;
	Node * prev = NULL;
	
	//Look for matching process ID
	while(cur != NULL){
		if(((MB*)(cur->data))->processId == pid){
			break;
		}
		prev = cur;
		cur = cur->next;
	}

	if(cur == NULL){//Process ID not found
		printf("\nProcess ID does not exist in %s list.\n",alg);
		return;
	}

	if(prev == NULL){//Process found at start of list
		if(cur->next != NULL && ((MB*)(cur->next->data))->processId == 0){//Hole to right
			((MB*)(cur->next->data))->startAddress = 
				((MB*)(cur->data))->startAddress;

			((MB*)(cur->next->data))->segmentSize = 
				((MB*)(cur->next->data))->segmentSize + 
					((MB*)(cur->data))->segmentSize;

			list->head = cur->next;
			free(cur->data);
			free(cur);
			cur = NULL;
		}
		else{//No hole to right
			((MB*)(cur->data))->processId = 0;
		}
	}
	else{//Not at begining of list
		if(cur->next != NULL && ((MB*)(cur->next->data))->processId == 0 
			&& ((MB*)(prev->data))->processId == 0){//Process surrounded by holes

			((MB*)(prev->data))->endAddress = 
				((MB*)(cur->next->data))->endAddress;

			((MB*)(prev->data))->segmentSize = 
				((MB*)(cur->next->data))->segmentSize + 
					((MB*)(cur->data))->segmentSize +
						((MB*)(prev->data))->segmentSize;
			prev->next = cur->next->next;
			free(cur->next->data);
			free(cur->next);
			cur->next = NULL;
			free(cur->data);
			free(cur);
			cur = NULL;

		}
		else if(((MB*)(prev->data))->processId == 0){//Hole to left of process
			((MB*)(prev->data))->endAddress = 
				((MB*)(cur->data))->endAddress;

			((MB*)(prev->data))->segmentSize = 
				((MB*)(prev->data))->segmentSize + 
					((MB*)(cur->data))->segmentSize;

			prev->next = cur->next;
			free(cur->data);
			free(cur);
			cur = NULL;
		}
		else if(cur->next != NULL && 
			((MB*)(cur->next->data))->processId == 0){//Hole to right of process

			((MB*)(cur->next->data))->startAddress = 
				((MB*)(cur->data))->startAddress;

			((MB*)(cur->next->data))->segmentSize = 
				((MB*)(cur->next->data))->segmentSize + 
					((MB*)(cur->data))->segmentSize;

			prev->next = cur->next;
			free(cur->data);
			free(cur);
			cur = NULL;
		}
		else{//No holes around process
			((MB*)(cur->data))->processId = 0;
		}
	}
	printf("\nProcess %d has been terminated from the %s list\n",pid,alg);
}

/*
printFragmentDetails()
Description: Scans a Linked List and prints information
relating to fragments of the current memory map.
----------------------------------------------------------------------------
*/
void printFragmentDetails(LinkedList * list, char alg[]){
	Node * cur = list->head;
	double fragments = 0;
	double sumOfFragmentSizes = 0;
	double avgSize = 0;
	
	//Walk through list and count fragments
	while(cur != NULL){
		if((((MB*)(cur->data))->processId) == 0){
			fragments++;
			sumOfFragmentSizes = sumOfFragmentSizes + 
				(((MB*)(cur->data))->segmentSize);
			
		}
		cur = cur->next;
	}

	if(fragments != 0){//Avoid divide by zero
		avgSize = sumOfFragmentSizes/fragments;
		printf("Number of frags in %s list is %.2f\n",alg,fragments);
		printf("Average frags size in %s is %.2f\n",alg,avgSize);
	}
	else{
		printf("Number of frags in %s list is %.2f\n",alg,fragments);
		printf("Average frags size in %s is %.2f\n",alg,avgSize);
	}
}
