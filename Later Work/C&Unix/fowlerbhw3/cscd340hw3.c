//Brandon Fowler
//CSCD340
//Homework 3

#include "cscd340hw3.h"

/*
main()
Description: Opens file, initializes LinkedList to
hold input data, calls functions to do work
==================================================================
*/
int main(){
	LinkedList * pList = linkedList();//Hold input data
	FILE * fin = fopen("data.txt","r");//Input file
	if(fin == NULL){//Bail if file not found
		printf("Input file not found! Exiting!");
		exit(-1);
	}

	//Read through file
	int lines = countLines(fin);
	int i;
	for(i= 1; i < lines; i += 4){
		getNextProcess(fin, pList);
	}

	fclose(fin);
	runPriority(pList);//Run priority algorithm on data
	resetList(pList);//Reset data to initial state
	runSJF(pList);//Run SJF algorithm on data
	clearList(pList);
	return 0;
}

/*
getNextProcess()
Description: Reads set of process data from
input file, and adds it to the LinkedList
=================================================================
*/
void getNextProcess(FILE * fin, LinkedList * pList){
	Node * newNode = buildNode();
	PCB * newP = (PCB*)calloc(1,sizeof(PCB));
	fscanf(fin,"%d", &(newP->processID));
	fscanf(fin,"%ld", &(newP->arrivalTimeStamp));
	fscanf(fin, "%ld", &(newP->totalBurstTime));
	newP->executionStartTime = -1;//Initialize default value
	newP->executionEndTime = -1;//Initialize default value
	newP->remainingBurstTime = newP->totalBurstTime;
	fscanf(fin, "%d", &(newP->processPriority));
	newP->waitTime = 0;//Initialize default value
	newP->scheduled = 0;//Initialize default value
	newNode->data = newP;
	addLast(pList, newNode);
}

/*
runPriority()
Description: Places processes into readyQue depending on
priority values, then calculates/prints statistics on
the running of processes in that order.
=================================================================
*/
void runPriority(LinkedList * pList){
	LinkedList * readyQue = linkedList();//ready process que
	Node * cur = pList->head;
	Node * nextPr = cur;//Next process to add to readyQue
	int timeTracker = 0;//Total amount of time processes qued will take
	int totalTime = 0;//Total time of run
	long totalWaitTime = 0;
	long totalTurnaround = 0;//All end times summed together
	int runOrder[(pList->size)];//PIDs in order that they run

	int i;
	for(i = 0; i < pList->size; i++){//For the amount of processes inputed
		cur = pList->head;
		nextPr = cur;
		
		//Go through list, and find next to be scheduled
		while(cur != NULL){
			if((((PCB*)(cur->data))->processPriority > ((PCB*)(nextPr->data))->processPriority
			 && ((PCB*)(cur->data))-> arrivalTimeStamp <= timeTracker && 
			((PCB*)(cur->data))->scheduled != 1) || 
			((PCB*)(nextPr->data))->scheduled == 1){
				nextPr = cur;
			}
			cur = cur->next;
		}

		//Add process to readyQue
		timeTracker += ((PCB*)nextPr->data)->totalBurstTime;
		((PCB*)nextPr->data)->scheduled = 1;
		Node * processNode = buildNode();
		processNode->data = (PCB*)calloc(1,sizeof(PCB));
		memcpy(processNode->data, &(*((PCB*)nextPr->data)), sizeof(PCB));
		addLast(readyQue, processNode);	
	}

	i = 0;
	cur = readyQue->head;

	//Run processes, and track statistics
	while(cur != NULL){
		((PCB*)cur->data)->executionStartTime = totalTime;
		if((totalTime - ((PCB*)cur->data)->arrivalTimeStamp) >= 0){
			((PCB*)cur->data)->waitTime = totalTime - ((PCB*)cur->data)->arrivalTimeStamp;
			totalWaitTime += (totalTime - ((PCB*)cur->data)->arrivalTimeStamp);
		}
		totalTime += ((PCB*)cur->data)->totalBurstTime;
		((PCB*)cur->data)->executionEndTime = totalTime;
		totalTurnaround += totalTime;
		((PCB*)cur->data)->remainingBurstTime = 0;
		runOrder[i++] = ((PCB*)cur->data)->processID;
		cur = cur->next;
	}

	//Calculate additional statistics
	double avgTurnaround = ((double)totalTurnaround)/((double)(pList->size));
	double avgWait = ((double)totalWaitTime)/((double)(pList->size));
	double throughput = ((double)(pList->size))/((double)totalTime);

	//Print results of run
	printf("\n---------------------------------------\n");
	printf("Priority non-preemptive run completed\n");
	printf("RUN STATISTICS:\n");
	printf("Total Time: %d Time Units\n",totalTime);
	printf("Throughput: %lf Processes Per Unit Time\n",throughput);
	printf("Average Wait Time: %lf Time Units\n",avgWait);
	printf("Average Turnaround Time: %lf Time Units\n",avgTurnaround);
	printf("ORDER OF EXECUTION:\n");
	for(i = 0; i < (pList->size); i++){
		printf("Process ID: %d\n",runOrder[i]);
	}
	printf("---------------------------------------\n");
	clearList(readyQue);	
}

/*
runSJF()
Description: Places processes into readyQue depending on
totalBurstTime values, then calculates/prints statistics on
the running of processes in that order.
=================================================================
*/
void runSJF(LinkedList * pList){
	LinkedList * readyQue = linkedList();//ready process que
	Node * cur = pList->head;
	Node * nextPr = cur;//Next process to add to readyQue
	int timeTracker = 0;//Total amount of time processes qued will take
	int totalTime = 0;//Total time of run
	long totalWaitTime = 0;
	long totalTurnaround = 0;//All end times summed together
	int runOrder[(pList->size)];//PIDs in order that they run

	int i;
	for(i = 0; i < pList->size; i++){//For the amount of processes inputed
		cur = pList->head;
		nextPr = cur;
		
		//Go through list, and find next to be scheduled
		while(cur != NULL){
			if((((PCB*)(cur->data))->totalBurstTime < ((PCB*)(nextPr->data))->totalBurstTime
			 && ((PCB*)(cur->data))-> arrivalTimeStamp <= timeTracker && 
			((PCB*)(cur->data))->scheduled != 1) || 
			((PCB*)(nextPr->data))->scheduled == 1){
				nextPr = cur;
			}
			cur = cur->next;
		}

		//Add process to readyQue
		timeTracker += ((PCB*)nextPr->data)->totalBurstTime;
		((PCB*)nextPr->data)->scheduled = 1;
		Node * processNode = buildNode();
		processNode->data = (PCB*)calloc(1,sizeof(PCB));
		memcpy(processNode->data, &(*((PCB*)nextPr->data)), sizeof(PCB));
		addLast(readyQue, processNode);	
	}

	i = 0;
	cur = readyQue->head;

	//Run processes, and track statistics
	while(cur != NULL){
		((PCB*)cur->data)->executionStartTime = totalTime;
		if((totalTime - ((PCB*)cur->data)->arrivalTimeStamp) >= 0){
			((PCB*)cur->data)->waitTime = totalTime - ((PCB*)cur->data)->arrivalTimeStamp;
			totalWaitTime += (totalTime - ((PCB*)cur->data)->arrivalTimeStamp);
		}
		totalTime += ((PCB*)cur->data)->totalBurstTime;
		((PCB*)cur->data)->executionEndTime = totalTime;
		totalTurnaround += totalTime;
		((PCB*)cur->data)->remainingBurstTime = 0;
		runOrder[i++] = ((PCB*)cur->data)->processID;
		cur = cur->next;
	}

	//Calculate additional statistics
	double avgTurnaround = ((double)totalTurnaround)/((double)(pList->size));
	double avgWait = ((double)totalWaitTime)/((double)(pList->size));
	double throughput = ((double)(pList->size))/((double)totalTime);

	//Print results of run
	printf("\n---------------------------------------\n");
	printf("SJF non-preemptive run completed\n");
	printf("RUN STATISTICS:\n");
	printf("Total Time: %d Time Units\n",totalTime);
	printf("Throughput: %lf Processes Per Unit Time\n",throughput);
	printf("Average Wait Time: %lf Time Units\n",avgWait);
	printf("Average Turnaround Time: %lf Time Units\n",avgTurnaround);
	printf("ORDER OF EXECUTION:\n");
	for(i = 0; i < (pList->size); i++){
		printf("Process ID: %d\n",runOrder[i]);
	}
	printf("---------------------------------------\n");
	clearList(readyQue);
}

/*
resetList()
Description: Resets some data in the LinkedList
back to default values
=================================================================
*/
void resetList(LinkedList * pList){
	Node * cur = pList->head;
	while(cur != NULL){
		((PCB*)(cur->data))->executionStartTime = -1;
		((PCB*)(cur->data))->executionEndTime = -1;
		((PCB*)(cur->data))->remainingBurstTime = ((PCB*)(cur->data))->totalBurstTime;
		((PCB*)(cur->data))->waitTime = 0;
		((PCB*)(cur->data))->scheduled = 0;
		cur = cur->next;
	}
}

/*
countLines()
Description: Counts the number of lines in an
input file.
=================================================================
*/
int countLines(FILE * fin){
	int lines = 0;
	int temp;
	while(!feof(fin)){
		lines++;
		fscanf(fin,"%d",&temp);
	}
	rewind(fin);
	return lines;
}
