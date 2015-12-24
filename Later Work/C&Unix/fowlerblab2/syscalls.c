//CSCD340
//Lab2
//Brandon Fowler

#include <stdlib.h>
#include <stdio.h>

int main(){
	FILE * fin = fopen("test.txt","r");
	char temp[100];
	fgets(temp,100,fin);
	while(!feof(fin)){
		printf("%s",temp);
		fgets(temp,100,fin);
	}
	fclose(fin);
	return 0;
}
