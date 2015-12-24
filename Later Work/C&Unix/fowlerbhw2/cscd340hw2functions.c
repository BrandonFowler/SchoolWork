//CSCD340
//Homework 2
//Brandon Fowler

#include "cscd340hw2.h"

//interpreter: Interprets input, and executes it or passes it to another function for execution
//================================================================================================
void interpreter(LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker , char *s){
	char ** beforePipe = NULL;
	char ** afterPipe = NULL;
	char ** loneCommand = NULL;

	if(strcmp(s,"\n") != 0){
		newHistory(s,linkedHistory,histTracker);//Add to history(If not already done)
	}

	if(strlen(s) > 1 && s[0] == '!' && s[1] == '!'){//Last History command
		char * command = getHistoryCommand(s,linkedHistory);
		if(command != NULL){
			strcpy(s,command);
		}
	}
	else if(strlen(s) > 1 && s[0] == '!'){//Specified History command
		char * command = getHistoryCommand(s,linkedHistory);
		if(command != NULL){
			strcpy(s,command);
		}
	}

	//Check for matching alias
	Node * ali = checkAlias(s,linkedAlias);
	if(ali != NULL){
		strcpy(s,((Alias*)ali->data)->original);
	}

	char * pipeposition = strrchr(s, '|');//Check if contains a pipe
	char * readIn = strchr(s, '<');//Check if contains a input redirect
	char * readOut = strchr(s, '>');//Check if contains an output redirect
	char * equal = strchr(s,'=');//Check if contains equal

	if(strlen(s) > 5 && s[0] == 'a' && s[1] == 'l' && s[2] == 'i' 
		&& s[3] == 'a' && s[4] == 's' && s[5] == ' '){

		createAlias(s,linkedAlias);//Set new alias
	}
	else if(strlen(s) > 7 && s[0] == 'u' && s[1] == 'n' && s[2] == 'a' 
		&& s[3] == 'l' && s[4] == 'i' && s[5] == 'a' && s[6] == 's' && s[7] == ' '){

		char * modifiedS = (char*)calloc(strlen(s) - 7,sizeof(char));

		int i;
		int j = 0;
		for(i = 8; i < strlen(s); i++){
			modifiedS[j] = s[i];
			j++;
		}
		modifiedS[j] = '\0';
		deleteAlias(linkedAlias,modifiedS);//Delete specified alias
		free(modifiedS);
	}
	else if(strlen(s) > 2 && s[0] == 'c' && s[1] == 'd' && s[2] == ' '){

		char * modifiedS = (char*)calloc(strlen(s) - 2,sizeof(char));

		int i;
		int j = 0;
		for(i = 3; i < strlen(s); i++){
			modifiedS[j] = s[i];
			j++;
		}
		modifiedS[j] = '\0';
		chdir(modifiedS);//Change directory as specified
		free(modifiedS);
	}
	else if(strcmp(s,"history")==0){
		printHistory(linkedHistory);//Show history
	}
	else if(s[0] == '!'){
		printf("Command not found in history\n");
	}
	else if(strlen(s) > 3 && s[0] == 'P' && s[1] == 'A' && s[2] == 'T' 
		&& s[3] == 'H'){

		changePath(s);//Modify PATH as specified
	}
	else if(strlen(s) > 8 && s[0] == 'H' && s[1] == 'I' && s[2] == 'S' 
		&& s[3] == 'T' && s[4] == 'C' && s[5] == 'O' && s[6] == 'U' 
		&& s[7] == 'N'&& s[8] == 'T'){

		//Change HISTCOUNT

		char * ptr = strchr(s, '=');
		int position = ptr - s;
		char * st =  (char*)calloc(strlen(s)-position+1, sizeof(char));
		position++;

		int i = 0;
		if(s[position] == ' ') position++;
		for(position; position < strlen(s); position++){
			st[i] = s[position];
			i++;
		}

		int size = 50; 
		int check = atoi(st);
		if (check < 0){
			print("Bad parameter for HISTCOUNT"); 
			return;
		}

		size = atoi(st);
		free(st);
		linkedHistory->printSize = size;
			
	}
	else if(strlen(s) > 12 && s[0] == 'H' && s[1] == 'I' && s[2] == 'S' 
		&& s[3] == 'T' && s[4] == 'F' && s[5] == 'I' && s[6] == 'L' 
		&& s[7] == 'E'&& s[8] == 'C'&& s[9] == 'O' && s[10] == 'U' 
		&& s[11] == 'N' && s[12] == 'T'){

		//Change HISTFILECOUNT

		char * ptr = strchr(s, '=');
		int position = ptr - s;
		char * st =  (char*)calloc(strlen(s)-position+1, sizeof(char));
		position++;
		if(s[position] == ' '){
			position++;
		}

		int i = 0;
		for(position; position < strlen(s); position++){
			st[i] = s[position];
			i++;
		}

		int size = 50; 
		int check = atoi(st);
		if (check < 0){
			print("Bad parameter for HISTFILECOUNT"); 
			return;
		}

		size = atoi(st);
		free(st);
		linkedHistory->writeSize = size;
			
	}
	else if(strcmp(s,"echo $PATH") == 0){
		printf("%s\n",getenv("PATH"));//Print current PATH
	}
	else if(pipeposition != NULL){//Pipe exisists
		int out = 0;//Binary switch indicating redirect
		char * modifiedS = NULL;
		char * ptr = strchr(s,'>');//Check for redirect

		if(ptr != NULL){//Redirect exists
			int p =	ptr - s;//Index of redirect in s
			out = 1;
			int i = p+1;

			if(s[i] == ' '){//Skip possible space
				i++;
			}

			//Get output file from s
			int j = 0;
			modifiedS = (char*)calloc(strlen(s)-p+1,sizeof(char));
			for(i; i < strlen(s); i++){
				modifiedS[j] = s[i];
				j++;
			}
			modifiedS[j] = '\0';

			//Remake s witout redirect/filename
			char * st = (char*)calloc(strlen(s)+1,sizeof(char));
			strcpy(st,s);
			for(j=0;j<p;j++){
				s[j] = st[j];
			}
			s[j] = '\0';

			free(st);	
		}

		int position = pipeposition - s;//Index of pipe in s
		char * firstHalf = (char*)calloc(position+1,sizeof(char));
		char * secondHalf = (char*)calloc(strlen(s)-position+1,sizeof(char));

		//Get pre pipe command
		int i = 0;
		for(i;i<position;i++){
			if(i == position-1 && s[i] == ' '){
				firstHalf[i] = '\0';
			}
			else{
				firstHalf[i] = s[i];
			}
		}
		firstHalf[i] = '\0';

		i++;
		if(s[i] == ' '){//Skip possible space
			i++;
		}

		//Get post pipe command
		int j = 0;
		for(i;i<strlen(s);i++){
			secondHalf[j] = s[i];
			j++;
		}
		secondHalf[strlen(secondHalf)] = '\0';
		
		//Check for alias in pre pipe
		Node * ali = checkAlias(firstHalf,linkedAlias);
		if(ali != NULL){
			free(firstHalf);
			firstHalf = NULL;
			firstHalf = (char*)calloc(strlen(((Alias*)ali->data)->original)+1,sizeof(char));
			strcpy(firstHalf,((Alias*)ali->data)->original);
		}
		ali = NULL;

		//Check for alias in post pipe
		ali = checkAlias(secondHalf,linkedAlias);
		if(ali != NULL){
			free(secondHalf);
			secondHalf = NULL;
			secondHalf = (char*)calloc(strlen(((Alias*)ali->data)->original)+1,sizeof(char));
			strcpy(secondHalf,((Alias*)ali->data)->original);
		}

		//Prepare exec arguments
		int preVal = makeargs(firstHalf, &beforePipe,linkedAlias);
		int postVal = makeargs(secondHalf, &afterPipe,linkedAlias);

		free(firstHalf);
		free(secondHalf);

		pipeIt(beforePipe, afterPipe, out, modifiedS);//Execute in pipeIt function
		
		clean(preVal,beforePipe);
		clean(postVal,afterPipe);
		if(modifiedS != NULL){
			free(modifiedS);
		}
	}
	else if(readIn != NULL || readOut != NULL){//Redirection exists
		redirection(linkedAlias,s);
	}
	else if(equal != NULL){//Make a variable(Only use remaining for equal sign)
		char modifiedS[106];
		strcpy(modifiedS,"alias ");
		strcat(modifiedS,s);
		createAlias(modifiedS,linkedAlias);
	}
	else if(strcmp(s,"\n") == 0){//Empty input(Do nothing)

	}
	else{//Try to execute as single command
		int res = makeargs(s, &loneCommand,linkedAlias);		
		forkIt(res, loneCommand,s);
  		clean(res, loneCommand);
		loneCommand = NULL;
	}

}

//loadMyshrc:Begins process of finding and loading .myshrc files
//================================================================================================
void loadMyshrc(LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker){
	DIR * dStream;
	struct dirent * ent;
	dStream = opendir(".");//Open directory stream in current working directory
	int check = 0;

	if (dStream){
		while ((ent = readdir(dStream)) != NULL){//Sift through files
			check = checkExtention(ent->d_name);//Check if found .myshrc file
			if (check == 1) {
				//Read each .myshrc file found	
				readFile(ent->d_name, linkedAlias, linkedHistory, histTracker);
				check = 0;
			}
		}
		closedir(dStream);
	}
}

//readFile: Get line count, make sure file not empty, pass reading to getLines()
//================================================================================================
void readFile(char *fileName, LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker){
	int lineNumber = lineCount(fileName);//Count lines
	if (lineNumber == 0){ //Check if empty
		printf("Error: can't find/read .myshrc"); 
		return;
	}
	getLines(lineNumber, fileName, linkedAlias, linkedHistory, histTracker);//Read Lines
	printf(".myshrc Done Loading\n");
}

//lineCount: Simply counts the number of lines in a specified file
//================================================================================================
int lineCount(char *name){ 
	FILE * fp = fopen(name,"r");
	int c=0;
	int lineNumber=1;

	if (fp == NULL) { 
		return 0; 
	}

	while ((c = fgetc(fp)) != EOF){
		if (c == '\n')
		lineNumber++;
	}

	fclose(fp);
	return lineNumber;
}

//getLines: Reads the lines in a .myshrc file and passes them to interpreter()
//================================================================================================
void getLines(int lines, char *name, LinkedList *linkedAlias, LinkedList *linkedHistory, int* histTracker){
	FILE * fp;
	char * line = NULL;
	size_t length = 0;
	ssize_t read;

	fp = fopen(name, "r");
	if (fp == NULL){ //No file exists by specified name
		printf("Error reading %s", name); 
	}

	//Read lines and send out for execution
	while ((read = getline(&line, &length, fp)) != -1) {
		strip(line);
		interpreter(linkedAlias, linkedHistory, histTracker, line);
	}

	fclose(fp);

	if (line) {
		free(line);
	}
}

//Checks if a file is a .myshrc file or not
//================================================================================================
int checkExtention(char * name){
	char * extention = strrchr(name, '.');

	if (!extention) {
		return -1;
	} 
	else {
		if (strcmp(extention+1, "myshrc") == 0) {
			return 1;
		}
	}
	return 0;
}

//loadHistory: Loads a pre-existing history file into the current command history
//================================================================================================
void loadHistory(LinkedList * linkedHistory, int *count){
	FILE * fp = NULL;
	char * history = ".mysh_history";//History file name
	char * fLine = NULL;
	size_t length = 0;
	ssize_t read;
	
	fp = fopen(history, "r");
	
	if (fp == NULL){ //No history file exists yet
		printf("No History To Load Yet\n");
		return; 
	}

	while ((read = getline(&fLine, &length, fp)) != -1) {
		strip(fLine);
		if(strcmp(fLine,"\n") != 0){
			newHistory(fLine,linkedHistory,count);//Add command to history list
		}
	}

	fclose(fp);

	if (fLine){
		free(fLine);
	}
	printf("History Loaded\n");
}

//outputHistory: Prints the current history to the mysh.mysh_history file
//================================================================================================
void outputHistory(LinkedList * linkedHistory) {
	FILE * fp = NULL;
	fp = fopen(".mysh_history", "w");

	Node * cur = linkedHistory->head;
	int max = linkedHistory->writeSize;//HISTFILECOUNT
	int start = linkedHistory->size - max;//Find starting point to write history

	//Advance to starting point
	if (start > 0) {
		int i = 0; 
		for(i = 0; i < start; i++) { 
			cur = cur->next; 
		}
	}

	//Write out history
	while (cur != NULL){
		fprintf(fp, ((History*)cur->data)->command,100);
		cur = cur->next;
		fprintf(fp, "\n");
	}
	fclose(fp);
}

//makeargs: Prepares a command into arguments for execution
//================================================================================================
int makeargs(char *s, char *** argv, LinkedList * linkedAlias){
	if(strlen(s)%16 == 0){//Eliminates string length errors with multiples of 16
		strcat(s," ");
	}

	int retVal = 0;//Switch indicates return conditions
	int tokentracker = 0;//Tracks number of tokens
	char tS[strlen(s)];//Temp string for token counting
	strcpy(tS, s);
	char delimiter[] = " ";
	char * curToken="";
	char * savePtr;

	//Count tokens
	for (strtok_r(tS, delimiter,&savePtr); curToken; curToken = strtok_r(NULL, delimiter,&savePtr)){
		tokentracker++;	
	}
	
	if(s[strlen(s)-1] == '&'){//Check for & and adjust accordingly
		(*argv)=(char**)calloc(tokentracker+2, sizeof(char*));
	}
	else{
		(*argv)=(char**)calloc(tokentracker+1, sizeof(char*));
	}

	//Parse s
	int i;
	int length = 0;
	curToken = strtok_r(s, delimiter, &savePtr);
	for (i=0; i < tokentracker; i++)
	{
		length = strlen(curToken);

		//Check for user made variable and switch
		if(curToken[0] == '$'){
			char t[100];
			int x = 1;
			int y = 0;
			for(x;x<length;x++){
				t[y] = curToken[x];
				y++;
			}
			t[y] = '\0';
			Node * ali = checkAlias(t,linkedAlias);
			if(ali != NULL){
				strcpy(curToken,((Alias*)(ali->data))->original);
			}	
		}

		(*argv)[i] = (char*)calloc(length, sizeof(char*));
		strcpy((*argv)[i], curToken);
		curToken = strtok_r(NULL, delimiter, &savePtr);
		if(i == tokentracker-1){//Check if last token
			//Adjust for possible &, and mark condition
			if(strlen((*argv)[i]) > 1 && (*argv)[i][strlen((*argv)[i])-1] == '&'){
				(*argv)[i][strlen((*argv)[i])-1] = '\0';
				retVal = 1;
			}
			else if((*argv)[i][strlen((*argv)[i])-1] == '&'){
				free((*argv)[i]);
				(*argv)[i] = NULL;
				retVal = 1;
			}
		}
	}

	return retVal;
}

//createAlias: Add new user specified alias
//================================================================================================
void createAlias(char * s,LinkedList * linkedAlias){
	int cha = '"';
	char * ptr;
	int position;
	int i = 0;
	int j = 0;
	int y = 0;

	//Count number of "
	for(i; i < strlen(s);i++){
		if(s[i] == '"'){
			j++;
		}
	}

	//Remove all "
	for(y; y < j;y++){
		ptr = strchr(s,cha);
		position = ptr - s;
		memmove(&s[position],&s[position+1],strlen(s) - position);
	}

	char modifiedS[strlen(s) - 5];

	//Make copy of s without 'alias ' 
	y = 0;
	for(i = 6; i < strlen(s); i++){
		modifiedS[y] = s[i];
		y++;
	}
	modifiedS[y] = '\0'; 

	ptr = strchr(modifiedS,'=');
	position = ptr - modifiedS;//Position of =
	char * alias = (char*)calloc(position+1,sizeof(char));

	//Get alias name
	int x = 0;
	for(x;x<position;x++){
		alias[x] = modifiedS[x];
	}

	char command[strlen(modifiedS) - position];

	//Get aliased command
	x++;
	y = 0;
	for(x; x< strlen(modifiedS); x++){
		command[y] = modifiedS[x];
		y++;
	}
	command[y] = '\0';

	//Add alias to list
	Node * new = buildNode();
	new->data = (Alias*)calloc(1,sizeof(Alias));
	((Alias*)new->data)->cloak = (char*)calloc(strlen(alias)+1,sizeof(char));
	strcpy(((Alias*)new->data)->cloak,alias);
	((Alias*)new->data)->original = (char*)calloc(strlen(command)+1,sizeof(char));
	strcpy(((Alias*)new->data)->original,command);
	addLast(linkedAlias,new);

	free(alias);
}

//strip: strips return from input
//================================================================================================
void strip(char *s){
	if(strlen(s) > 1){
  		int length;
  		length = strlen(s);
  		if(s[length - 2] == '\r')
    		s[length -2] = '\0';

  		else if(s[length - 1] == '\n')
    		s[length -1] = '\0';
	}
}

//pipeIt: Executes commands with piping
//================================================================================================
void pipeIt(char ** beforePipe, char ** afterPipe, int output, char * modifiedS){
	if(fork()==0){
		int fd[2];
		if(pipe(fd) < 0){//Failure to make pipe
			clean(0,beforePipe);
			clean(0,afterPipe);
			exit(-1);
		}
		if(fork() == 0){
			close(fd[0]);
			close(1);
			dup(fd[1]);
			close(fd[1]);
			int res = execvp(beforePipe[0],beforePipe);	
			if(res < 0){//exec failure
				exit(127);
			}
		}
		else{
			int status;
			waitpid(-1,&status,NULL);
			if(WEXITSTATUS(status) == 127){//Check if pre pipe failed
				clean(0,beforePipe);
				clean(0,afterPipe);
				printf("Invalid Pre Pipe Command\n");
				exit(-1);
			}
			else{
				close(fd[1]);
				close(0);
				dup(fd[0]);
				close(fd[0]);
				if(output == 1){//Check redirect to output switch
					int fd = open(modifiedS,O_WRONLY | O_CREAT, S_IRUSR 
							| S_IRGRP | S_IWGRP | S_IWUSR);
					dup2(fd,1);
					close(fd);
				}
				int res = execvp(afterPipe[0],afterPipe);	
				if(res < 0){//exec failure
					clean(0,beforePipe);
					clean(0,afterPipe);
					printf("Invalid post Pipe Command\n");
					exit(-1);
				}	
			}
		}
	}
	else{
		int status;
		waitpid(-1,&status,NULL);
	}
}

//forkIt: Executes a single command
//================================================================================================
void forkIt(int returnVal, char ** argv,char* s){
	if(returnVal == 1){//If 1 then & detected(need to run process in background) 
		int result = system(s);
	}
	else{
		pid_t id = fork();
		if(id != 0){
			int status;
			waitpid(-1,&status,NULL);
		}
		else{
			int result = execvp(argv[0],argv);		
			printf("Invalid Command\n");//Only reaches here if exec fails
			clean(0,argv);
			exit(-1);	
		}
	}
}

//getHistory: Gets a command from the history(!# or !!)
//================================================================================================
char * getHistoryCommand(char * s, LinkedList * linkedHistory){
	if(s[1] == '!' && strlen(s) == 2){// !! command
		Node * cur = linkedHistory->head;
		if(cur == NULL){
			printf("No history of commands\n");
		}
		else{
			//Advance to last command and return it
			while(cur->next != NULL){
				cur = cur->next;
			}
			return ((History*)cur->data)->command;
		}
	}
	else{//!# command
		Node * cur = linkedHistory->head;
		if(cur == NULL){
			printf("No history of commands\n");
		}
		else{
			//Get history number
			char modifiedS[strlen(s)];
			int i = 1;
			int j = 0;
			for(i;i < strlen(s);i++){
				modifiedS[j] = s[i];
				j++;
			}
			modifiedS[j] = '\0';
			int command = atoi(modifiedS);

			//Find specified command number(if exists) and return command
			while(cur != NULL){
				if(((History*)cur->data)->tracker == command){
					return ((History*)cur->data)->command;

				}
				cur = cur->next;
			}
		}
	}
	return NULL;
}

//printHistory: Prints the history to the screen
//================================================================================================
void printHistory(LinkedList * theList){
	int max = 100;
	int i = 0;
	if(theList->head == NULL){
		printf("Empty List\n");
	}
	else{	
		
		Node* cur = theList->head;
		max = theList->printSize;
		int size = theList->size - max;
		if (size > 0) { 
			for(i = 0; i < size; i++) { 
				cur = cur->next; 
			}
		}

		while(cur != NULL){
			printf("%d ",((History*)cur->data)->tracker);
			printf("%s\n",((History*)cur->data)->command);
			cur = cur->next;
		}
	}
}

//changePath: Edits PATH as specified by user
//================================================================================================
void changePath(char* s){
	char * ptr;
	ptr = strchr(s,'=');
	if(ptr != NULL){//Equal sign exitst
		int position = ptr - s;//Get index of equal sign
		char * modifiedS = (char*)calloc(strlen(s) - position + 1,sizeof(char));
		
		//Make copy of string after equal sign
		int i = position+1;
		int j = 0;
		for(i;i<strlen(s);i++){
			modifiedS[j] = s[i];
			j++;
		}
		modifiedS[j] = '\0';

		//If $PATH part of string, then make a copy without it
		if(strlen(modifiedS) > 4 && modifiedS[0] == '$' && modifiedS[1] == 'P' 
			&& modifiedS[2] == 'A' && modifiedS[3] == 'T' && modifiedS[4] == 'H'){

			ptr = strchr(modifiedS,':');
			position = ptr - modifiedS;
			char * modifiedS2 = (char*)calloc(strlen(modifiedS) - position + 1,sizeof(char));

			int x = position;
			int y = 0;
			for(x;x<strlen(modifiedS);x++){
				modifiedS2[y] = modifiedS[x];
				y++;
			}
			modifiedS2[y] = '\0';

			char * path = strcat(getenv("PATH"),modifiedS2);
			setenv("PATH",path,1);//Change path
			free(modifiedS2);
		}
		else{//$PATH not part of string
			setenv("PATH",modifiedS,1);//Change path
		}
		free(modifiedS);
	}
	else{
		printf("Invalid command\n");
	}
}

//redirection: Allows for combinations of input and output redirects with a command
//================================================================================================
void redirection(LinkedList * linkedAlias,char * s){
	if(fork() == 0){
		int app = 0;
		char * outputPtr = strchr(s,'>');//Look for output redirect
		char * inputPtr = strchr(s,'<');//Look for input redirect
		int positionOut = 0;
		int positionIn = 0;
		char * preOutHalf = NULL;
		char * postOutHalf = NULL;
		char * preInHalf = NULL;
		char * postInHalf = NULL;

		if(outputPtr != NULL){//Output redirect exits
			positionOut = outputPtr - s;
		}
		if(inputPtr != NULL){//Input redirect exists
			positionIn = 0;
		}

		if(outputPtr != NULL && s[positionOut+1] == '>'){//Look for >>
			positionOut++;
			app = 1;//Set append switch
			preOutHalf = (char*)calloc(positionOut+1,sizeof(char));
			postOutHalf = (char*)calloc(strlen(s)-positionOut+1,sizeof(char));

			//Copy each side of string around the redirect
			int x = 0;
			for(x;x<positionOut-1;x++){
				if(x == positionOut-2 && preOutHalf[x] == ' '){
					preOutHalf[x] = '\0';
				}
				else{
					preOutHalf[x] = s[x];
				}
			}
			preOutHalf[x] = '\0';

			int y = 0;
			if(s[positionOut+1] == ' '){
				positionOut++;
			}
			for(x = positionOut+1;x<strlen(s);x++){
				postOutHalf[y] = s[x];
				y++;
			}
			postOutHalf[y] = '\0';
			
		}
		else if(outputPtr != NULL){//Normal >
			preOutHalf = (char*)calloc(positionOut+1,sizeof(char));
			postOutHalf = (char*)calloc(strlen(s)-positionOut+1,sizeof(char));

			//Copy each side of string around the redirect
			int x = 0;
			for(x;x<positionOut;x++){
				if(x == positionOut-1 && s[x] == ' '){
					preOutHalf[x] = '\0';
				}
				else{
					preOutHalf[x] = s[x];
				}
			}
			preOutHalf[x] = '\0';

			if(s[positionOut+1] == ' '){
				positionOut++;
			}

			int y = 0;
			for(x = positionOut+1;x<strlen(s);x++){
				postOutHalf[y] = s[x];
				y++;
			}
			postOutHalf[y] = '\0';
					
		}


		if(preOutHalf != NULL){//Output redirect exists
			char * preInPointer = strchr(preOutHalf,'<');//Look for input redirect in pre half
			char * preOutPointer = strchr(postOutHalf,'<');//Look for input redirect in post half
			if(preInPointer != NULL){//input redirect exists
				positionIn = preInPointer - preOutHalf;
				preInHalf = (char*)calloc(positionIn+1,sizeof(char));
				postInHalf = (char*)calloc(strlen(preOutHalf)-positionIn+1,sizeof(char));

				//Copy each side of string around the redirect
				int x = 0;
				for(x;x<positionIn;x++){
					if(x == positionIn-1 && preOutHalf[x] == ' '){
						preInHalf[x] = '\0';
					}
					else{	
						preInHalf[x] = preOutHalf[x];
					}
				}
				preInHalf[x] = '\0';

				if(preOutHalf[positionIn+1] == ' '){
					positionIn++;
				}

				int y = 0;
				for(x = positionIn+1;x<strlen(preOutHalf);x++){
					if(x == strlen(preOutHalf)-1 && preOutHalf[x] == ' '){
						
					}
					else{
						postInHalf[y] = preOutHalf[x];
						y++;
					}
					
				}
				postInHalf[y] = '\0';
			}
			else if(preOutPointer != NULL){//Input redirects exists
				positionIn = preOutPointer - postOutHalf;
				preInHalf = (char*)calloc(positionIn+1,sizeof(char));
				postInHalf = (char*)calloc(strlen(preOutPointer)-positionIn+1,sizeof(char));

				//Copy each side of the string around the redirect
				int x = 0;
				for(x;x<positionIn;x++){
					if(x == positionIn-1 && postOutHalf[x] == ' '){
						preInHalf[x] = '\0';
					}
					else{
						preInHalf[x] = postOutHalf[x];
					}
				}
				preInHalf[x] = '\0';

				if(postOutHalf[positionIn+1] == ' '){
					positionIn++;
				}

				int y = 0;
				for(x = positionIn+1;x<strlen(postOutHalf);x++){
					if(x == strlen(postOutHalf)-1 && postOutHalf[x] == ' '){

					}
					else{
						postInHalf[y] = postOutHalf[x];
						y++;
					}
					
				}
				postInHalf[y] = '\0';
			}
				
		}
		else{//Only input redirect exists
			char * ptr = strchr(s,'<');
			positionIn = ptr - s;
			preInHalf = (char*)calloc(positionIn+1,sizeof(char));
			postInHalf = (char*)calloc(strlen(s)-positionIn+1,sizeof(char));

			//Copy each side of the string around the redirect
			int x = 0;
			for(x;x<positionIn;x++){
				if(x == positionIn-1 && s[x] == ' '){
					preInHalf[x] = '\0';
				}
				else{
					preInHalf[x] = s[x];
				}
			}
			preInHalf[x] = '\0';

			if(s[positionIn+1] == ' '){
				positionIn++;
			}

			int y = 0;
			for(x = positionIn+1;x<strlen(s);x++){
				postInHalf[y] = s[x];
				y++;
			}
			postInHalf[y] = '\0';
		}

		//Execut redirects----------------------------
		int in,out;
		if(outputPtr != NULL && inputPtr != NULL){//Double redirect
			char ** arg;
			//Check for alias and switch
			Node * ali = checkAlias(preInHalf,linkedAlias);
			if(ali != NULL){
				free(preInHalf);
				preInHalf = NULL;
				preInHalf = (char*)calloc(strlen(((Alias*)ali->data)->original)+1,sizeof(char));
				strcpy(preInHalf,((Alias*)ali->data)->original);
			}

			makeargs(preInHalf,&arg,linkedAlias);//Prep arguments for exec

			in = open(postInHalf,O_RDONLY);
			if(app == 1){//Open for append
				out = open(postOutHalf,O_WRONLY | O_APPEND | O_CREAT, S_IRUSR | 
					S_IRGRP | S_IWGRP | S_IWUSR); 
			}
			else{//Open normally
				out = open(postOutHalf,O_WRONLY | O_CREAT, S_IRUSR | S_IRGRP 
						| S_IWGRP | S_IWUSR); 
			}
			
			dup2(out,1);
			close(out);

			int x = 0;
			int c = 1;
			for(x;arg[x]!=NULL;x++){//Count number of arguments
				c++;
			}
	
			//Place input file name as last argument
			arg[x] = (char*)calloc(strlen(postInHalf)+1,sizeof(char));
			strcpy(arg[x],postInHalf);
			char* command[x+1];

			//Reform argument with input file
			for(x=0;x < c;x++){
				command[x] = arg[x];
			}
			command[x] = NULL;

			execvp(command[0],command);//Execute command

			//Free everything on exec failure
			for(x = 0; x < c; x++){
				free(arg[x]);
			}
			free(arg);
			if(preInHalf != NULL){
				free(preInHalf);
			}
			if(preOutHalf != NULL){
				free(preOutHalf);
			}
			if(postInHalf != NULL){
				free(postInHalf);
			}
			if(postOutHalf != NULL){
				free(postOutHalf);
			}
			printf("Invalid Redirect Command\n");
			exit(-1);
		}
		else if (outputPtr != NULL){//Single output redirect
			char ** arg;
			//Check for alias
			Node * ali = checkAlias(preOutHalf,linkedAlias);
			if(ali != NULL){
				free(preOutHalf);
				preOutHalf = (char*)calloc(strlen(((Alias*)ali->data)->original)+1,sizeof(char));
				strcpy(preOutHalf,((Alias*)ali->data)->original);
			}

			makeargs(preOutHalf,&arg,linkedAlias);//Make exec argument

			if(app == 1){//Open for append
				out = open(postOutHalf,O_WRONLY | O_APPEND | O_CREAT, S_IRUSR 
					| S_IRGRP | S_IWGRP | S_IWUSR); 
			}
			else{//Open for normal write
				out = open(postOutHalf,O_WRONLY | O_CREAT, S_IRUSR | S_IRGRP 
					| S_IWGRP | S_IWUSR); 
			}

			dup2(out,1);
			close(out);
			int res = execvp(arg[0],arg);//Execute command
			//If exec fails free everything
			if(res < 0){
				int x = 0;
				for(x = 0; arg[x] != NULL; x++){
					free(arg[x]);
				}
				free(arg);
				if(preInHalf != NULL){
					free(preInHalf);
				}
				if(preOutHalf != NULL){
					free(preOutHalf);
				}
				if(postInHalf != NULL){
					free(postInHalf);
				}
				if(postOutHalf != NULL){
					free(postOutHalf);
				}
				printf("Invalid Redirect Command\n");
				exit(-1);
			}
		}
		else{//Single input redirect
			char ** arg;
			//Check for alias
			Node * ali = checkAlias(preInHalf,linkedAlias);
			if(ali != NULL){
				free(preInHalf);
				preInHalf = (char*)calloc(strlen(((Alias*)ali->data)->original)+1,sizeof(char));
				strcpy(preInHalf,((Alias*)ali->data)->original);
			}

			makeargs(preInHalf,&arg,linkedAlias);//Make exec argument

			int x = 0;
			int c = 1;
			for(x;arg[x]!=NULL;x++){//Find argument length
				c++;
			}
	
			//Add input file as last argument
			arg[x] = (char*)calloc(strlen(postInHalf)+1,sizeof(char));
			strcpy(arg[x],postInHalf);
			char* command[x+1];

			//Reform argument with input file
			for(x=0;x < c;x++){
				command[x] = arg[x];
			}
			command[x] = NULL;

			execvp(command[0],command);//Execute command

			//Free everything on exec failure
			for(x = 0; x < c; x++){
				free(arg[x]);
			}
			free(arg);
			if(preInHalf != NULL){
				free(preInHalf);
			}
			if(preOutHalf != NULL){
				free(preOutHalf);
			}
			if(postInHalf != NULL){
				free(postInHalf);
			}
			if(postOutHalf != NULL){
				free(postOutHalf);
			}
			printf("Invalid Redirect Command\n");
			exit(-1);
			
		}
	}
	
	else{
		int status;
		waitpid(-1,&status,NULL);

	}
	

}

//checkAlias: Checks if specified alias exists
//================================================================================================
Node * checkAlias(char * item, LinkedList * linkedAlias){
	Node * cur = linkedAlias->head;
	while(cur != NULL){
		if(strcmp(((Alias*)cur->data)->cloak,item) == 0){
			return cur;
		}
		cur = cur->next;
	}
	return NULL;
}

//deleteAlias: Deletes a specified alias
//================================================================================================
void deleteAlias(LinkedList * theList, char * item){
	Node* cur = theList->head;
	Node* prev = cur;
	Node* t;
	int found;
	while(cur != NULL){
		found = 0;
		if(strlen(item) == strlen(((Alias*)cur->data)->cloak)){
			found = 1;
			int i;
			for(i = 0; i < strlen(item); i++){
				if(item[i] != ((((Alias*)cur->data)->cloak)[i])){
					found = 0;
				}
			}
		}
		if(found == 1){
			if(cur == theList->head){
				theList->head = theList->head->next;
			}
			else{
				prev->next = cur->next;
			}
			t = cur;
			cur = cur->next;
			free(((Alias*)t->data)->cloak);
			free(((Alias*)t->data)->original);
			free(t->data);
			free(t);
			t = NULL;
			theList->size = theList->size-1;
		}
		else{
			prev = cur;
			cur = cur->next;
		}
	}	
}

//newHistory: Stores a new command into the history
//================================================================================================
void newHistory(char * s,LinkedList * linkedHistory,int * histTracker){
	if(s[0] != '!'){//Dont store !! or !# commands
			
		//Advance to end of list
		Node * cur = linkedHistory->head;
		while(cur != NULL && cur->next != NULL){
			cur = cur->next;
		}

		if(cur == NULL){//No history yet
			//Store history
			(*histTracker)++;
			Node * new = (Node*)calloc(1,sizeof(Node));
			new->data = (History *)calloc(1,sizeof(History));
			((History*)(new->data))->command = (char *)calloc(strlen(s)+1,sizeof(char));
			((History*)(new->data))->tracker = *histTracker;
			strcpy(((History*)(new->data))->command,s);
			addLast(linkedHistory, new);
		}
		else if(strcmp(((History*)cur->data)->command,s) != 0){//Check if same as last stored command
			//Store history
			(*histTracker)++;
			Node * new = (Node*)calloc(1,sizeof(Node));
			new->data = (History *)calloc(1,sizeof(History));
			((History*)(new->data))->command = (char *)calloc(strlen(s)+1,sizeof(char));
			((History*)(new->data))->tracker = *histTracker;
			strcpy(((History*)(new->data))->command,s);
			addLast(linkedHistory, new);
		}
	}
}

//clean: Cleans memory from a command argument
void clean(int argc, char **argv){
	int i = 0;
	while(argv[i] != NULL){
		free(argv[i]);
		i++;
	}
	free(argv);
}

//clearlinkedAlias: Frees memory in Alias structs
//================================================================================================
void clearlinkedAlias(LinkedList * linkedAlias){
	Node * cur = linkedAlias->head;
	while(cur != NULL){
		free(((Alias*)cur->data)->cloak);
		free(((Alias*)cur->data)->original);
		cur = cur->next;
	}
	
}

//clearHistory: Free memory in History structs
//================================================================================================
void clearHistory(LinkedList * list){
	Node* cur = list->head;
	while(cur != NULL){
		free(((History*)cur->data)->command);
		cur = cur->next;
	}
}

//print: Generic print function
//================================================================================================
void print(void * s){
	printf("%s\n", (char*)s);
}
