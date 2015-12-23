#include "stocks.h"
#include "linkedlist.h"
#include "hw5.h"


/*
stocks.c
Functions file for stocks
Author: Brandon Fowler
Class:cscd240-02
*/



extern Node * head;
extern int size;


//Opens user specified file, compares data, and prints results
void printStockValue()
{
	int fdIn = openInputFile();//Stores file descriptor
	char temp[4096] = "NULL";//Stores input data
	int res = read(fdIn,&temp[0],1);
	int count = 1;//Array index
	char symbol[50] = "NULL";
	double price;
	Node * cur;
	double CV;//Current value
	double PV;//Purchase value
	double PL;//Profit/Loss
	int count2 = 0;//Line feeds in file
	while(res != 0)
	{
		res = read(fdIn,&temp[count],1);
		if(temp[count] == 10)//Line feed
		{	
			count2++;
		}
		count++;
	}
	close(fdIn);
	
	printf("\n\n");

	//Begin parse
	char * t = strtok(temp," ");
	strcpy(symbol,t);
	int j;
	for(j=0; j < count2;j++)//Go until count reaches number of lines read
	{
		
		t = strtok(NULL,"\n");
		price = atof(t);
	
		for(cur=head;cur!=NULL;cur = cur->next)
		{
			if(strcmp(((Stock*)(cur->data))->c.symbol,symbol) == 0)//Same symbol
			{
				//Compare and print results
				CV = (price)*((Stock*)(cur->data))->shares;
				PV = (((Stock*)(cur->data))->shares)*(((Stock*)(cur->data))->price);
				PL = CV-PV;
				printf("%s current price: %.2lf\n",((Stock*)(cur->data))->c.name,price);
				printf("	Current Value:	$%.2lf\n",CV);
				printf("	Puchase Value:	$%.2lf\n",PV);
				if(PL < 0)//Lost value
				{
					PL = (PL)*(-1);
					printf("	Profit/Loss:	$(%.2lf)\n",PL);
				}
				else
				{
					printf("	Profit/Loss:	$%.2lf\n",PL);
				}
				printf("\n\n");		
			}
		}
		
		t = strtok(NULL," ");
		
		if(t != NULL)
		{
			strcpy(symbol,t);
		}
		
	}
	
}


//Ask user for input, adds data to list or updates
//existing data.
void buyStock()
{
	char symbol[50] = "NULL";//Store symbol
	char name[50] = "NULL";//Store name
	double price;//Store purchase price
	int amount;//Store stock purchase amount
	int found = 0;//Tracks if data already in list
	printf("\n\n");
	while(fgetc(stdin)!='\n');
	printf("Enter the company symbol:");
	fgets(symbol,50,stdin);
	printf("Enter the company name:");
	fgets(name,50,stdin);
	printf("Enter Price:");
	scanf("%lf",&price);
	printf("Enter amount you wish to purchase:");
	scanf("%d",&amount);
	
	int i;
	for(i=0;i<strlen(name);i++)
	{
		if(name[i] == '\n')
		{
			name[i] = '\0';//Strip new line
		}
	}
	for(i=0;i<strlen(symbol);i++)
	{
		if(symbol[i] == '\n')
		{
			symbol[i] = '\0';//Strip new line
		}
	}
	
	
	if(found == 0)//Data not already in list
	{
		Stock d;
		
		char * n = (char*)calloc(50,sizeof(char));
		
		char * s = (char*)calloc(50,sizeof(char));

		strcpy(n,name);
		strcpy(s,symbol);
		
		d.price = price;
		d.shares = amount;
		d.c.name = n;
		d.c.symbol = s;
		addOrdered(d);//Add to list
	}
	
}


//Allows user to sell stock, if they have it
void sellStock()
{
	while(fgetc(stdin)!='\n');
	char temp[50] = "NULL";//Stores company symbol
	int amount;//Stores amount to sell
	int found = 0;//Tracks if symbol found in list
	printf("\n\n");
	printf("Type the symbol of the stock you wish to sell:");
	fgets(temp,50,stdin);
	printf("Type the amount you wish to sell:");
	scanf("%d",&amount);

	int i;
	for(i=0;i<strlen(temp);i++)
	{
		if(temp[i] == '\n')
		{
			temp[i] = '\0';//Strip new line
		}
	}

	Node * cur;
	for(cur = head; cur != NULL; cur = cur->next)
	{
		if(strcmp(((Stock *)(cur->data))->c.symbol,temp)==0)//Symbol found
		{
			if((((Stock *)(cur->data))->shares) < amount)//Check stock amount owned
			{
				printf("\n\n");
				printf("You don't have that much stock in this company.\n");
				found = 1;
			}
			else//Have enough to sell
			{
				(((Stock *)(cur->data))->shares) = (((Stock *)(cur->data))->shares)-(amount);
				found = 1;
				printf("\n\n");
				printf("Stocks Sold!");
			}
		}
	}

	if(found != 1)//Company symbol not in list
	{
		printf("\n\n");
		printf("You don't have shares in this company\n");
	}
}


//Prints data in entire linked list
void printStockInfo()
{
	printf("\n\n");
	Node * cur = head;
	for(cur;cur != NULL;cur = cur->next)
	{
		printf("%s ",((Stock*)(cur->data))->c.name);
		printf("%s\n\n",((Stock*)(cur->data))->c.symbol);
		printf("Shares: %d\n\n",((Stock*)(cur->data))->shares);
		printf("Price: %.2lf\n\n",((Stock*)(cur->data))->price);
		
	}
}
