#include <stdio.h>
#include <semaphore.h>
#include <pthread.h>
const int n = 50;
int tally;
sem_t s;
void total()
{
	printf("start\n");
	int count;
	for(count = 1; count <= n; count++){
		sem_wait(&s);
		tally++;
		printf("%d count add tally to %d\n",count,tally);
		sem_post(&s);
	}
}

void main()
{
	pthread_t t1,t2;
	tally = 0;
	sem_init(&s,0,1);
	pthread_create(&t1,NULL,(void*)total,NULL);
	pthread_create(&t2,NULL,(void*)total,NULL);
	pthread_join(t1,NULL);
	pthread_join(t2,NULL);
}
