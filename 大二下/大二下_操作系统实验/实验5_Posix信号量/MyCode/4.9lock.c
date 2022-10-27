#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <errno.h>
#include <unistd.h>
/*互斥量 */
pthread_mutex_t mutex;
int myglobal=0;
void *thread_function(void *arg)
{
	int i,j;
	for(i = 0; i <20; i++){
		pthread_mutex_lock(&mutex); /*获取互斥锁*/
		j = myglobal;
		j++;
		printf(".");
		fflush(stdout);
		usleep(1000);
		myglobal = j;
		pthread_mutex_unlock(&mutex); /*释放互斥锁*/
	}
	return NULL;
}

int main(){
	pthread_t mythread;
	int i;
	/*互斥初始化*/
	pthread_mutex_init (&mutex, NULL);
	if( pthread_create(&mythread,NULL,thread_function,NULL))
	{
		printf("error creating thread!\n");
		abort();
	}
	for(i = 0; i <20; i++)
	{
		pthread_mutex_lock(&mutex); /*获取互斥锁*/
		myglobal++;
		printf("O");
		fflush(stdout);
		usleep(1000);
		pthread_mutex_unlock(&mutex); /*释放互斥锁*/
	}
	if( pthread_join(mythread,NULL))
	{
		printf("error joining thread!\n");
		abort();
	}
	printf("\nmyglobal equald %d\n",myglobal);
	exit(0);
}
