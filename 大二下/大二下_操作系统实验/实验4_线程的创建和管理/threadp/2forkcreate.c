//"hello world"多进程程序
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
int main()
{
    int pid;
    int i;
	if((pid=fork())==0)
	{                                         /*这里是子进程执行的代码*/
		
		for(i = 0 ; i < 5 ; i ++)
			{
				printf("%s","hello");
				fflush(stdout);//清除文件缓冲区，文件以写方式打开时将缓冲区内容写入文件 
				sleep(1);
			}
		exit(0);
	}
	else
	{                                         /*这里是父进程执行的代码*/
		//wait(0);                            /*如果此处没有这一句会如何？*/
		for(i = 0 ; i < 5 ; i ++)
			{
				printf("%s","world\n");
				fflush(stdout);//清除文件缓冲区，文件以写方式打开时将缓冲区内容写入文件
				sleep(1);
			}
	}

	return 0;
}
