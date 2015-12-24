//Brandon Fowler
//CSCD340
//Lab10

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "linkedList.h"
#include "cscd340lab10.h"

/*
main()
Description: Simple main that opens setup input file,
reads it, then passes the values out to be used by the
conversion function.
===============================================================
*/
int main(){

	int vas = 0;//Virtual Address Space
	int frameSize = 0;//Page Frame Size
	int pas = 0;//Physical Address Space
	LinkedList * pageTable = linkedList();

	//Read setup file
	FILE * setup = fopen("setup.txt","r");
	if(setup == NULL){
		printf("Cannot find setup.txt, Exiting!:");
		exit(-1);
	}
	fscanf(setup,"%d",&vas);
	fscanf(setup,"%d",&frameSize);
	fscanf(setup,"%d",&pas);
	fclose(setup);

	
	FILE * fin = openInput();//Open input file with addresses
	int lCount = lineCount(fin);//Count input file lines
	conversion(fin, lCount, pageTable, vas, frameSize, pas);//Process addresses
	
	fclose(fin);
	clearList(pageTable);
}

/*
openInput()
Description: Opens a user specified input file.
===============================================================
*/
FILE * openInput(){
	char fileName[100];
	printf("Enter input file name(file with addresses): ");
	fscanf(stdin,"%s",fileName);
	FILE * fin = fopen(fileName,"r");
	if(fin == NULL){
		printf("Invalid file name! Try again!\n");
		fin = openInput();
	}
	return fin;
}

/*
lineCount()
Description: Counts lines in input file(One integer per line assumed)
===============================================================
*/
int lineCount(FILE * fin){
	int count = 0;
	int temp;
	while(!feof(fin)){
		count++;
		fscanf(fin,"%d",&temp);
	}
	rewind(fin);
	return count;
}

/*
conversion()
Description: Reads through input file of virtual addresses and
translates them to physical addresses based on the page table
and values passes into the function. Also, prints data on
each translated address, and final statistics.
===============================================================
*/
void conversion(FILE * fin, int lCount, LinkedList * pageTable, int vas, int frameSize, int pas){			   	   
	int framesInUse = 0;//Number of page frames used so far
	int nextEviction = 0;//Next page frame to evict
	int numOfHits = 0;//Counts number of hits		  	  
	int numOfMiss = 0;//Counts number of addresses		  
	int numOfEviction = 0;//Counts number of evictions		   
	int evictionRate = 0;//Stores eviction rate 		   			   
	int virtAddress = 0;//Holds each virtual address 		   
	int numOfFrames = pas/frameSize;//Total number of page frames in PAS 		   
	int offsetBitNum = log2(frameSize);//Number of bits for offset		   
	int numOfPages = vas/frameSize;//Total number of pages in VAS 	   	   
	int check = numOfHits;//Use to determine if a hit or miss

	//Print out useful starting information
	printf("\nPhysical Address Space Size: %d\n", pas);
	printf("Page Frame Size: %d\n", frameSize);
	printf("Number of Page Frames: %d\n", numOfFrames);
	printf("Virtual Address Space Size: %d\n", vas);
	printf("Number of Pages: %d\n", numOfPages);
	printf("Offset: %d Bits\n", offsetBitNum);
	printf("=====================================\n\n");

	//Initialize page table
	int i = 0;
	for(i; i < numOfPages; i++){
		PTE * newEntry = (PTE*)calloc(1,sizeof(PTE));
		newEntry->frame = -1;
		newEntry->reference = 0;
		newEntry->modified = 0;
		Node * newNode = buildNode();
		newNode->data = newEntry;
		addLast(pageTable,newNode);
	}
  
	for (i = 1; i < lCount; i++){//For legth of input file
		fscanf(fin,"%d",&virtAddress);
		int physicalAddressBin[32]={0};//Hold binary rep. of physical address
		int physicalAddressDec=0;//Hold decimal rep. of physical address
		int virtualAddressBin[32]={0};//Hold binary rep. of virtual address
		int frame = 0;//Page frame being mapped to
		int tempFrame;//Used to translate page frame to binary
		int page = 0;//Page being mapped
		int tempVirtualAddress = virtAddress;//Save original virtual address
		int isEvicted = 0;//Boolean to determine if eviction happened

		//Translate virtual address to binary
		int j = 0;
		while(virtAddress>0)
      		{
          		 virtualAddressBin[j] = virtAddress%2;
          		 j++;
           		 virtAddress = virtAddress/2;
      		}

		//Build offset into physical address
		int y = 0;
		for(y; y < offsetBitNum; y++){
			physicalAddressBin[y] = virtualAddressBin[y];
		}

		//Translate page into decimal
		int v = 0;
		for(y; y < 32; y++){
			page += virtualAddressBin[y]*pow(2,v);
			v++;
		}

		Node * cur = pageTable->head;
		check = numOfHits;

		//Check if page already mapped
		int x = 0;
		for (x = 0; x < pageTable->size; x++){
			if(page == x && ((PTE*)(cur->data))->frame != -1){//Hit
				frame = ((PTE*)(cur->data))->frame;
				((PTE*)(cur->data))->reference++;

				numOfHits++;

				break;
			}
			cur = cur->next;
		}
		
		if(numOfHits == check){//Page not mapped(Miss)
			if (framesInUse < numOfFrames) {//If a page frame is availible
				Node * cur = pageTable->head;

				//Advance to page in table
				int n = 0;
				for(n; n < page; n++){
					cur = cur->next;
				}

				//Map page
				((PTE*)(cur->data))->frame = framesInUse;
				frame = framesInUse;
				((PTE*)(cur->data))->reference++;

				numOfMiss++;
				framesInUse++;
			}
			else {//No page frame availible(Eviction)
				Node * cur = pageTable->head;

				//Find page that page frame to evict is mapped to
				while(((PTE*)(cur->data))->frame != nextEviction){
					cur = cur->next;
				}

				clearPage(cur);//Un-map page

				cur = pageTable->head;

				//Move to page that needs to be mapped
				int c = 0;
				for(c; c < page; c++){
					cur = cur->next;
				}

				//Map page
				((PTE*)(cur->data))->frame = nextEviction;
				frame = nextEviction;
				((PTE*)(cur->data))->reference++;

				numOfMiss++;
				numOfEviction++;
				nextEviction++;

				//If all page frames have been evicted
				if(nextEviction == numOfFrames){
					nextEviction = 0;//Reset eviction que
				}

				isEvicted = 1;//Set boolean to true
			}
		}

		tempFrame = frame;//Make temp of frame to be translated to binary

		//Attatch bin. frame rep. to physical address
		y = offsetBitNum;
		while(tempFrame>0)
      		{
          		 physicalAddressBin[y] = tempFrame%2;
          		 y++;
           		 tempFrame = tempFrame/2;
      		}

		//Translate physical address to decimal
		y = 0;
		v = 0;
		for(y; y < 32; y++){
			physicalAddressDec += physicalAddressBin[y]*pow(2,v);
			v++;
		}

		//Print info on translated address
		printf("Virtual Address: %d\n", tempVirtualAddress);
		printf("Page Number: %d\n",page);
		if(numOfHits == check){
			printf("Miss\n");
			if(isEvicted == 1){
				printf("Eviction\n");
			}
		}
		else{
			printf("Hit\n");
		}
		printf("Page Frame Number: %d\n",frame);
		printf("Physical Address: %d\n",physicalAddressDec);
		printf("============================\n");
	}

	//Print final info on all translated addresses
	printf("\nNumber of Translated Addresses: %d\n",i);
	printf("Page Faults: %d\n", numOfMiss);
	printf("Page Fault Rate: %.2f\n", ((double)numOfMiss/(double)i));
	printf("Page Evictions: %d\n", numOfEviction);
	printf("Page Evicttion Rate: %.2f\n\n", ((double)numOfEviction/(double)i));
}

/*
clearPage()
Description: Resets a page back to default unmapped status
===============================================================
*/
void clearPage(Node * page){
	((PTE*)(page->data))->frame = -1;
	((PTE*)(page->data))->reference = 0;
	((PTE*)(page->data))->modified = 0;
}
