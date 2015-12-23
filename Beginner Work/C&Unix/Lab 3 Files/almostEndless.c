#include <stdio.h>
int main()
{
int x = 0;
while(x < 20000000)
{
printf("..");
fflush(stdout);
sleep(3);
}// end while
return 0;
}// end main
