#include<stdio.h>
#include<stdlib.h>

int main()
{
  long int size;
  long int i; 
  FILE *fp; 

  size = 10;
  size*=100;
  size *= 1024;
  //size *= 1024;
  //size *= 1024;

  fp = (FILE *)fopen("data.txt", "w");
  if(fp == NULL){
    fprintf(stderr, "data file open failed. exit\n");
    exit(1);
  }

  for(i = 0; i < size; i++)
    fprintf(fp, "%d\n", rand());
    
    

  fclose(fp);

  return 0;
}

