#include <stdlib.h>  
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>  
#include <semaphore.h>  
#include <errno.h>  
#include <fcntl.h>  
char information;
int messages;  //the number of messages 
sem_t mutex,B_empty,C_empty,D_empty,B_full,C_full,D_full;
void *send()
{ 
	int i;
	printf("\nsend is called.\n"); 
	for(i=0;i<messages;i++) 
	{ 		
		sem_wait(&B_empty);	
		sem_wait(&C_empty);
		sem_wait(&D_empty);
		sem_wait(&mutex);  //锁住槽位，对于多个生产者的时候有必要，单个生产者没有必要
		printf("\nplz input the message information\n");
		scanf("%c",&information);
		getchar(); //Avoid carriage return from being typed
		printf("\nA sends a message %c\n",information); 
		sleep(1);
		sem_post(&mutex);  //释放锁 
		sem_post(&B_full);
		sem_post(&C_full);
		sem_post(&D_full);
	} 
	return NULL; 
} 
void *acceptB()
{ 
	int i;
	printf("\nacceptB is called.\n"); 
	for(i=0;i<messages;i++) 
	{ 		
		sem_wait(&B_full);  //判断缓冲区中是否有条目，有的话将条目数减少1 
		sem_wait(&mutex); 	//锁住缓冲区，对多个消费者有必要，对单个消费者没必要
		printf("\nB accepts a message %c\n",information);
		sleep(1);
		sem_post(&mutex);  //释放锁 
		sem_post(&B_empty); //将缓冲区中的空槽数目加1 
	}
	return NULL;
}
void *acceptC()
{ 
	int i;
	printf("\nacceptC is called.\n"); 
	for(i=0;i<messages;i++) 
	{ 	
		sem_wait(&C_full);  //判断缓冲区中是否有条目，有的话将条目数减少1 
		sem_wait(&mutex); 	//锁住缓冲区，对多个消费者有必要，对单个消费者没必要
		printf("\nC accepts a message %c\n",information);
		sleep(1);
		sem_post(&mutex);  //释放锁 
		sem_post(&C_empty); //将缓冲区中的空槽数目加1 
	}
	return NULL;
}
void *acceptD()
{ 
	int i;
	printf("\nacceptD is called.\n"); 
	for(i=0;i<messages;i++) 
	{ 			
		sem_wait(&D_full);  //判断缓冲区中是否有条目，有的话将条目数减少1 
		sem_wait(&mutex); 	//锁住缓冲区，对多个消费者有必要，对单个消费者没必要
		printf("\nD accepts a message %c\n",information);
		sleep(1);
		sem_post(&mutex);  //释放锁 
		sem_post(&D_empty); //将缓冲区中的空槽数目加1 
	}
	return NULL;
}
int main(int argc,char *argv[]) 
{ 
	if(argc != 2) 
	{ 
		printf("usage: prodcons <#itmes>\n"); 
		exit(0); 
	} 
	pthread_t t_sendA,t_acceptB,t_acceptC,t_acceptD; 
	messages = atoi(argv[1]);
	//创建信号量
	sem_init(&mutex,0,1);
	sem_init(&B_empty,0,1); 
	sem_init(&C_empty,0,1); 
	sem_init(&D_empty,0,1); 
	sem_init(&B_full,0,0); 
	sem_init(&C_full,0,0); 
	sem_init(&D_full,0,0); 
	pthread_create(&t_sendA,NULL,send,NULL); 
	pthread_create(&t_acceptB,NULL,acceptB,NULL);
	pthread_create(&t_acceptC,NULL,acceptC,NULL);
	pthread_create(&t_acceptD,NULL,acceptD,NULL);
	pthread_join(t_sendA,NULL); 
	pthread_join(t_acceptB,NULL); 
	pthread_join(t_acceptC,NULL); 
	pthread_join(t_acceptD,NULL); 
	exit(0); 
} 
