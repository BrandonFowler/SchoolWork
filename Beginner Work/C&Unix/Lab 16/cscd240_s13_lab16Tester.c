#include <stdio.h>
#include <assert.h>
#define kSpace 032


void convert(char * buffer, int val)
{
    int i;
    
    for (i = 0; i < val /*sizeof(buffer)/sizeof(buffer[0])*/; ++i)
    {
        if (buffer[i]-2 > kSpace)
	/*
	Checks if the ASCII value in buffer[i]-2 is over
	032 and reduces it if it is greater. Shifts the
	ASCII value with bitwise arithmetic if it is not
	greater. This proccess is the values are changed to 
	the ASCII values of the correct letters.
	*/
        {
            buffer[i] -= 1;
        }// end if
        else
        {
            buffer[i] = (buffer[i] << 2) + 4 - i;
		/*
		The arithmetic shifts bitwise by a power of 2,
		then adds 4 bytes, then subtracts j bytes.
		*/
        }// end else
    }// end for
    
}// end function

int main(void)
{
    int i;
    const char kTarget[] = "Art&Logic";
    char buffer[] = {0x42, 0x73, 0x75, 0x27, 0x13, 0x1C, 0x68, 0x1B, 0x64};
    
    
    convert(buffer, sizeof(buffer)/sizeof(buffer[0]));
    
    for (i = 0; i < sizeof(buffer)/sizeof(buffer[0]); ++i)
        assert(kTarget[i] == buffer[i]);
    
}// end main


/*
Lab16
Brandon Fowler
cscd240-02
*/

/*
Assert:
The assert function simply checks the statment
passed into it. If the statement if false then
the program is aborted. If the stement is true
then the program continues. This can be useful
for debuging.
*/

/*
Why it failed:
The code failed in the for loop, because, Calculating
i <= sizeof(buffer)/sizeof(buffer[0]), did not work.
Instead we needed the original value that was passed into the 
function(val). It also failed at the if statement because kSpace was 
defined 2 too small. So we needed to minus 2 in the if statement. 
Last it failed in the else statement because of the order of 
operations with the arithmetic. It needed parentheses.
*/

/*
Output:
There is no output when the program executes correctly.
*/
