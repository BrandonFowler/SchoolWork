//CSCD340
//HomeWork 1
//Brandon Fowler

#include "utility.h"

//Simple displau choice menu 1-6(Checks for valid input)
int menu(){
	int input;
	printf("Your options are\n");
	printf("1. Print the list\n");
	printf("2. Add first\n");
	printf("3. Add last\n");
	printf("4. Sort the list(Ascending order)\n");
	printf("5. Delete a word\n");
	printf("6. Quit-\n");
	printf("Choose:");
	scanf("%d",&input);
	if(input < 1 || input > 6)
	{
		printf("\n");
		printf("Input invalid!!!\n");
		input = menu();
	}
	return input;
}

//Simple open specified file(Checks for valid input)
FILE * openFile(){
	char name[MAX];
	printf("What is the input file name?:");
	scanf("%s",name);
	FILE * fin = fopen(name,"r");
	if(fin == NULL)
	{
		fin = openFile();
	}
	return fin;	
}

//Strips new line characters from the end of a character array
void strip(char *array){
	int length = strlen(array);
	if ((length > 0) && (array[length-1] == '\n'))
    		array[length-1] = '\0';
}

//Counts the number of records in a file, and returns the count
int countRecords(FILE * fin, int lines){
	int count = 0;
	char data[MAX];
	fgets(data,MAX,fin);
	while(!feof(fin))
	{
		count++;
		fgets(data,MAX,fin);
	}
	int totalRecords = count/lines;
	if(totalRecords == 0){
		printf("Error: There are no records in the input file!\n");
		printf("Exiting!");
		exit(-1);
	}
	return totalRecords;
}

//Builds a linked list with data input from a file
void buildList(LinkedList * myList, FILE * fin, int total){
	rewind(fin);
	char * data;
	int i;
	for(i = 0; i<total; i++){
		Node * nn = buildNode();
		data = (char*)calloc(MAX,sizeof(char));
		fgets(data,MAX,fin);
		strip(data);
		nn->data = data;
		if(myList->head == NULL)
		{
			myList->head = nn;
		}
		else
		{
			Node * cur = myList->head;
			for(cur;cur->next != NULL;cur=cur->next);
			cur->next = nn;
		}	
	}
	fclose(fin);
}

