#include<unistd.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<errno.h>
#include<sys/wait.h>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define KEY 1234                              
#define SIZE 1024                             

int main()
{
	int shmid;
	pid_t *pid;
	shmid=shmget(KEY,SIZE,IPC_CREAT|0600);         
	if(shmid==-1)
	{
		printf("create share memory failed:%s",strerror(errno));
		return 0;
	}
	if(fork( )==0) //child 
	{                                	
		pid=(pid_t*)shmat(shmid,NULL,0);  
		if(pid==(void*)-1)
		{
			printf("connect to the share memory failed:%s",strerror(errno));
			return 0;
		}
		*pid = getpid();
		sleep(2);//let the father output 
		printf("My father's pid is %d\n", *pid);
		shmdt(pid);                           
		exit(0);

	}else //father
	{                                         
		sleep(1); //let the child run first	 
		pid=(pid_t*)shmat(shmid,NULL,0);    
		if(pid==(void*)-1)
		{
			printf("connect the share memory failed:%s",strerror(errno));
			return 0;
		}
		printf("My child's pid is %d\n", *pid);
		*pid = getpid();
		wait(0);
		shmdt(pid);                           
		shmctl(shmid,IPC_RMID,NULL);
	}
}
