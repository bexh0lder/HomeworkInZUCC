#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>

main () 
	{      
	   pid_t pid;
		pid=fork(); 
		if (pid < 0) 
			printf("error in fork!"); 
		else if (pid == 0) 
			{
			printf("i am the child process, my process id is %d\n",pid); 
			}
		else 
			{
			printf("i am the parent process, my process id is %d\n",pid);
			} 
	} 
