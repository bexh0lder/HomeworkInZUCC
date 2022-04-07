#include <stdio.h>
#include <stdlib.h>
//#include <sys/types.h>
#include <sys/syscall.h>
#include <pthread.h>
//#include <unistd.h> 
void *PrintThread(void * );
/*互斥量 */
pthread_mutex_t mutex;
#define Num_Threads 3

int main()
{
	int i,ret;
	pthread_t a_thread;
	int ThdNum[Num_Threads];

	for (i = 0; i < Num_Threads; i++)
		ThdNum[i] = i;
	 /*互斥初始化*/
	pthread_mutex_init (&mutex, NULL);
	for(i = 0; i < Num_Threads;i++)
	{
		ret = pthread_create(&a_thread,NULL,PrintThread,(void *)&ThdNum[i]);
		if (ret == 0)
			printf("Thread launched succeddfully\n");
	}
	i = getchar();
	return (0);
}

void *PrintThread(void * num)
{
	int i;

	for(i = 0;i < 3; i++)
		{
		pthread_mutex_lock(&mutex); /*获取互斥锁*/
		/*临界资源*/
		printf("thread number is %d,my pid = %d,my lwp id = %d,my tid = %u\n",*((int *)num),getpid(),syscall(SYS_gettid),pthread_self());
		//sleep(1);
		pthread_mutex_unlock(&mutex); /*释放互斥锁*/
		}
	return NULL;
}
