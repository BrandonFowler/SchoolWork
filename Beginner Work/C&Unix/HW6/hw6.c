#include "hw6.h"


/*
hw6.c
General functions file for hw6
Author: Brandon Fowler
Class: CSCD240-02
*/


/*
readNumber function
Reads a number from the user, parses the digits,
and builds a linked list.
*/
Node * readNumber()
{
	char temp;
	Node * head = NULL;

	printf("\n");
	printf("Please enter a number ");

	scanf("%c", &temp);
	while(temp != '\n')
	{
		Node * n = (Node *)calloc(1, sizeof(Node));
		
		if(temp == '-')
		{
			char * minus = (char *)calloc(1,sizeof(char));
			*minus = '-';
			n -> data = minus;
		}
		else
		{
			int * i = (int *)calloc(1, sizeof(int));
			*i = (int)(temp - '0');
			n -> data = i;
		}

		addfirst(&head, n);

		scanf("%c", &temp);

	}
	
	return head;

}


/*
printNumber function
Prints the name of the number, then calls
recurse print.
*/
void printNumber(char number[], Node * nList)
{
	printf("\n");
	printf("%s",number);
	printList(nList);
	printf("\n\n");
}


/*
printList function
Needed to comply with hw6 directions.
Simply calls recursePrint function.
*/
void printList(Node * nList)
{
	recursePrint(nList);
}


/*
recursePrint
Prints the linked list recursivley, from the
end of the linked list to the begining.
*/
void recursePrint(Node * cur)
{
	if(cur->next != NULL)
	{
		recursePrint(cur->next);
	}
	if(*((char*)cur->data) == '-')
	{
		printf("%c",*((char*)cur->data));
	}
	else
	{
		printf("%d",*((int*)cur->data));
	}
}


/*
excecuteChoice function
Takes in both linkedlists(Numbers), and a
function pointer; calls the function passed in,
then calls printNumber to print results.
*/
void executeChoice(Node * lTwo,Node * lOne, Node*(*funcPtr)(Node * lTwo,Node * lOne))
{
	Node * res = funcPtr(lTwo, lOne);
	printNumber("The result is: ", res);
	clearList(res);
}


/*
plus function
Adds two linkedlists(Numbers) together, may call
the minus function if situation is appropriate.
*/
Node * plus(Node * lTwo,Node * lOne)
{
	Node * res;//Stores final result
	Node * cur1 = lOne;//For traversing first number
	Node * cur2 = lTwo;//For traversing second number
	Node * cur3 = (Node*)calloc(1,sizeof(Node));//For creating calculated third number
	cur3->data = (int*)calloc(1,sizeof(int));
	Node * temp;//Used to temporarily keep list head pointers
	int count1 = 1;//Size of first number
	int count2 = 1;//Size of second number
	for(cur1;cur1->next!=NULL;cur1=cur1->next)//Counts digits
		count1++;
	for(cur2;cur2->next!=NULL;cur2=cur2->next)//Counts Digits
		count2++;
	if(*((char*)cur1->data) == '-' && *((char*)cur2->data) != '-')//First number is negative
	{	
		//Build new list from first number without minus sign
		temp = cur3;
		for(cur1 = lOne;*((char*)cur1->data) != '-';cur1 = cur1->next)
		{
			*((int*)cur3->data) = *((int*)cur1->data);
			if(*((char*)cur1->next->data) != '-')
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
			}
		}
		res = minus(temp,lTwo);//Subtract first from second
		clearList(temp);
	}
	else if(*((char*)cur1->data) != '-' && *((char*)cur2->data) == '-')//Second negative
	{
		
		//Build new list from second number without minus sign
		temp = cur3;
		for(cur2 = lTwo;*((char*)cur2->data) != '-';cur2 = cur2->next)
		{
			*((int*)cur3->data) = *((int*)cur2->data);
			if(*((char*)cur2->next->data) != '-')
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
			}
		}
		res = minus(temp,lOne);//Subtract second from first
		clearList(temp);
	}
	else//Both positive or both negative
	{
		
		free(cur3->data);
		free(cur3);
		cur3 = NULL;//Reset cur3 for new use
		res = (Node*)calloc(1,sizeof(Node));
		res->data = (int*)calloc(1,sizeof(int));
		cur3=res;
		int added = 0;//Stores value for new list
		int remainder = 0;//Stores carryover to next digit
		for(cur1=lOne,cur2=lTwo;cur1 != NULL && cur2 != NULL && *((char*)cur1->data) != '-' && *((char*)cur2->data) != '-';cur1=cur1->next,cur2=cur2->next)
		{
			
			added = (*((int*)(cur1->data)))+(*((int*)(cur2->data)))+remainder;
			if(added > 9)
			{
				remainder = added/10;
				added = added%10;
				*((int*)(cur3->data))=added;
			}
			else
			{
				*((int*)(cur3->data))=added;
				remainder = 0;	
			}

			if(cur1->next != NULL && cur2->next != NULL)
			{
				if(*((char*)cur1->next->data) != '-' && *((char*)cur2->next->data) != '-')
				{
					cur3->next = (Node*)calloc(1,sizeof(Node));
					cur3 = cur3->next;
					cur3->data = (int*)calloc(1,sizeof(int));
				}
			}	
		}
		
		if(count1 > count2)//First number longer
		{
			//Finish building new list
			for(cur1;cur1!=NULL && *((char*)cur1->data)!='-';cur1=cur1->next)
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
				added = *((int*)cur1->data) + remainder;
				if(added>9)
				{
					remainder = added/10;
					added = added%10;
					*((int*)cur3->data) = added;
				}
				else
				{
					*((int*)cur3->data) = added;
					remainder = 0;
				}
				*((int*)cur3->data) = *((int*)cur3->data)+(remainder*10);
			}
			
		}
		else if(count2 > count1)//Second number longer
		{
			//Finish building new list
			for(cur2;cur2!=NULL && *((char*)cur2->data)!='-';cur2=cur2->next)
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
				added = *((int*)cur2->data) + remainder;
				if(added>9)
				{
					remainder = added/10;
					added = added%10;
					*((int*)cur3->data) = added;
				}
				else
				{
					*((int*)cur3->data) = added;
					remainder = 0;
				}
			}
			*((int*)cur3->data) = *((int*)cur3->data)+(remainder*10);
		}
		else//Numbers same length
		{
			*((int*)cur3->data) = *((int*)cur3->data)+(remainder*10);//Fix last digit
		}
		
		
		if(cur1!=NULL && *((char*)(cur3->data))!='-' || cur2!=NULL && *((char*)(cur3->data))!='-')//Numbers are negative
		{
			//Add negative sign
			cur3->next = (Node*)calloc(1,sizeof(Node));
			cur3 = cur3->next;
			cur3->data = (char*)calloc(1,sizeof(char));
			*((char*)cur3->data)='-';
		}
		
		
	}
	
	return res;
}


/*
minus function
Subtracts second linkedlist(number) from the first.
May call the pluss function if appropriate.
*/
Node * minus(Node * lTwo,Node * lOne)
{
	
	Node * res;//Stores final result
	Node * cur1 = lOne;//Used to traverse first list
	Node * cur2 = lTwo;//Used to traverse second list
	Node * cur3 = (Node*)calloc(1,sizeof(Node));//Used to build new calculated list
	cur3->data = (int*)calloc(1,sizeof(int));
	Node * temp;//Used to temporarily hold head pointers
	Node * temp2;//Used to temporarily hold head pointers
	int count1 = 1;//Size of first number
	int count2 = 1;//Size of second number
	for(cur1;cur1->next!=NULL;cur1=cur1->next)//Count digits
		count1++;
	for(cur2;cur2->next!=NULL;cur2=cur2->next)//Count digits
		count2++;
	if(*((char*)cur1->data) == '-' && *((char*)cur2->data) != '-')//Second number negative
	{
		//Build new list from second, adding negative sign
		temp = cur3;
		for(cur2 = lTwo;cur2 != NULL;cur2 = cur2->next)
		{
			*((int*)cur3->data) = *((int*)cur2->data);
			if(cur2->next != NULL)
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
			}
		}
		cur3->next = (Node*)calloc(1,sizeof(Node));
		cur3 = cur3->next;
		cur3->data = (char*)calloc(1,sizeof(char));
		*((char*)cur3->data)='-';
		res = plus(temp,lOne);//Add numbers
		clearList(temp);
		return res;
	}
	else if(count2 > count1 && *((char*)cur2->data) != '-')//Second number longer than first
	{
		
		free(cur3->data);
		free(cur3);
		cur3=NULL;//Get rid of unused memory
		res = minus(lOne,lTwo);//Minus first from second
		for(cur1=res;cur1->next!=NULL;cur1=cur1->next);
		if(((int*)(res->data))==0 && res->next == NULL)//Check if result of 0
		{
			return res;	
		}
		else if(*((char*)cur1->data) != '-' && *((int*)cur1->data) > 0)
		{
			//Add negative sign
			Node * newNode = (Node*)calloc(1,sizeof(Node));
			newNode->data = (char*)calloc(1,sizeof(char));
			*((char*)newNode->data) = '-';
			addlast(&res,newNode);
		}
		
		return res;
	}
	else if(*((char*)cur1->data) != '-' && *((char*)cur2->data) == '-')//First number is negative
	{
		
		//Build new list from second without the minus sign
		temp = cur3;
		for(cur2 = lTwo;*((char*)cur2->data) != '-';cur2 = cur2->next)
		{
			*((int*)cur3->data) = *((int*)cur2->data);
			if(*((char*)cur2->next->data) != '-')
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
			}
		}
		res = plus(temp,lOne);//Add numbers
		clearList(temp);
		return res;	
	}
	else if(*((char*)cur1->data) == '-' && *((char*)cur2->data) == '-')//Both negative
	{
		
		//Make new lists of both without negative signs
		temp = cur3;
		for(cur1 = lOne;*((char*)cur1->data) != '-';cur1 = cur1->next)
		{
			*((int*)cur3->data) = *((int*)cur1->data);
			if(*((char*)cur1->next->data) != '-')
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
			}
		}
		Node * cur4 = (Node*)calloc(1,sizeof(Node));
		cur4->data = (int*)calloc(1,sizeof(int));
		temp2 = cur4;
		for(cur2 = lTwo;*((char*)cur2->data) != '-';cur2 = cur2->next)
		{
			*((int*)cur4->data) = *((int*)cur2->data);
			if(*((char*)cur2->next->data) != '-')
			{
				cur4->next = (Node*)calloc(1,sizeof(Node));
				cur4 = cur4->next;
				cur4->data = (int*)calloc(1,sizeof(int));
			}
		}
		res = minus(temp,temp2);//Subtract the first from the second
		clearList(temp);
		clearList(temp2);
		return res;
	}
	else//Both positive
	{
		

		free(cur3->data);
		free(cur3);
		cur3 = NULL;//Reset cur3 for new use
		int carryover = 0;//Store carryover value for subtraction
		res = (Node*)calloc(1,sizeof(Node));
		res->data = (int*)calloc(1,sizeof(int));
		cur3 = res;
		int subtracted = 0;//Store calculated value for each digit, one at a time
		for(cur1=lOne,cur2=lTwo;cur1!=NULL && cur2!=NULL;cur1=cur1->next,cur2=cur2->next)
		{
			subtracted = (*((int*)cur1->data))-carryover-(*((int*)cur2->data));
			if(subtracted < 0)
			{
				subtracted = subtracted+10;
				carryover = 1;
				(*((int*)cur3->data)) = subtracted;
			}
			else
			{
				carryover = 0;
				(*((int*)cur3->data)) = subtracted;
			}

			if(cur1->next!=NULL && cur2->next!=NULL)
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
			}	
		}

		if(count1 > count2)//Number one longer than number two
		{
			//Finish building list
			for(cur1;cur1!=NULL;cur1=cur1->next)
			{
				cur3->next = (Node*)calloc(1,sizeof(Node));
				cur3 = cur3->next;
				cur3->data = (int*)calloc(1,sizeof(int));
				subtracted = (*((int*)cur1->data))-carryover;
				
				if(subtracted < 0)
				{
					subtracted = subtracted+10;
					carryover = 1;
					(*((int*)cur3->data)) = subtracted;
				}
				else
				{
					carryover = 0;
					(*((int*)cur3->data)) = subtracted;
				}
			}
		}
		else//Numbers same length
		{
			//Fix last digit
			if(carryover==1)
			{
				(*((int*)cur3->data)) = (*((int*)cur3->data))-10;
			}
			else
			{
				(*((int*)cur3->data)) = (*((int*)cur3->data));
			}
		}
		if((*((int*)cur3->data))<0)//Second number greater value than first
		{
			//Start over, subtract first from second, then add negative sign
			clearList(res);
			Node * res2;
			res2 = minus(lOne,lTwo);
			stripzeros(&res2);
			if(((int*)(res2->data))==0 && res2->next == NULL)
			{
				return res2;	
			}
			else
			{
				Node * newNode = (Node*)calloc(1,sizeof(Node));
				newNode->data = (char*)calloc(1,sizeof(char));
				*((char*)newNode->data) = '-';
				addlast(&res2,newNode);
			}
			return res2;
		}
	}
	
	stripzeros(&res);
	return res;
}


/*
stripzeros function
Strips the leading zeros from a linkedlist(number)
*/
void stripzeros(Node ** head)
{
	Node * cur = *head;
	int done = 0;
	while(cur->next!=NULL && done == 0)
	{
		for(cur; cur->next->next!=NULL;cur=cur->next);
		
		if(*((int*)(cur->next->data))==0)
		{
			free(cur->next->data);
			free(cur->next);
			cur->next = NULL;
		}
		else
		{
			done = 1;
		}
		cur = *head;
		
	}
}


/*
menu function
Diplays menu, and returns users choice.
*/
int menu()
{
	int choice;
	
	printf("Select from the following:\n");
	printf("1) Print Number 1 & Number 2\n");
	printf("2) Change Number 1\n");
	printf("3) Change Number 2\n");
	printf("4) Add Number 2 to Number 1\n");
	printf("5) Subtract Number 2 from Number 1\n");
	printf("6) Quit\n");
	printf("Choice-->");
	scanf("%d",&choice);
	while(fgetc(stdin)!='\n');//Clear buffer
	if(choice < 1 || choice > 6)//Bounds check
	{
		printf("\n\n");
		printf("Sorry choice is invalid");
		printf("\n\n");
		choice = menu();
	}
	
	return choice;
	
}

