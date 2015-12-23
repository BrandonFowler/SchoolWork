#include <stdio.h>
int main()
{
int twod[4][3] = { {2, 4, 6}, {8, 10, 12}, {14, 16, 18}, {20, 22, 24}};
printf("%p\n", twod);
printf("twod is: %p\n", twod);
printf("twod + 3 is: %p\n", twod + 3);
printf("*(*(twod + 1)) is: %d\n", *(*(twod + 1)));
printf("*twod + 1 is: %p\n", *twod+1);
printf("*twod[2] is: %d\n", *twod[2]);
printf("*(twod + 2) + 2 is: %p\n", *(twod + 2) + 2);
printf("twod[1] is: %p\n", twod[1]);
printf("twod[1][2] is: %d\n", twod[1][2]);
int *ptr;
ptr = * twod;
printf("ptr %p\n", ptr);
printf("twod [1] %p\n", twod [1]);
printf("ptr[1] %d\n", ptr[1]);
printf("ptr[1] %d\n", ptr[1]);
printf("twod + 1 %p\n", twod+1);
printf("ptr[10] %d\n", ptr[10]);

void function1(int twod[4][3],int rows,int cols);
function1(twod,4,3);
void function2(int twod[4][3],int rows,int cols);
function2(twod,4,3);
void function3(int * twod, int rows, int cols);
function3(twod[0],4,3);
return 0;
}

void function1(int twod[4][3], int rows, int cols)
{
	printf("\n");
	int r;
	int c;
	for(r = 0; r < rows; r++)
	{
		for(c = 0; c < cols; c++)
		{
			printf("%d ",twod[r][c]);
		}
		printf("\n");
	}
}

void function2(int twod[4][3], int rows, int cols)
{
	printf("\n");
	int r;
	int c;
	for(r = 0; r < rows; r++)
	{
		for(c = 0; c < cols; c++)
		{
			printf("%d ",(*(*(twod+r)+c)));
		}
		printf("\n");
	}
}

void function3(int * twod, int rows, int cols)
{
	printf("\n");
	int NumOfElems = 12;
	int i;
	for(i=0;i<NumOfElems;i++)
	{
		if(i%3==0)
		{
			printf("\n");
		}
		printf("%d ",*(twod+i));
	}
	printf("\n");
}
