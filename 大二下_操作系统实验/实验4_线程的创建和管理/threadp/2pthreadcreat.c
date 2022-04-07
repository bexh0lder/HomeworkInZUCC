//"hello world"多线程程序
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
main()
{
	pthread_t t1 , t2;

	void *p_msg(void *);
	
	pthread_create(&t1,NULL,p_msg,(void * )"hello ");
	pthread_create(&t2,NULL,p_msg,(void * )"world\n");
	pthread_join(t1, NULL);
	pthread_join(t2, NULL);
}
void *p_msg(void *m)
{
	int i;

	for(i = 0 ; i < 5 ; i ++)
	{
		printf("%s",m);
		fflush(stdout);//清除文件缓冲区，文件以写方式打开时将缓冲区内容写入文件 
		sleep(1);
	}
	return NULL;
}
