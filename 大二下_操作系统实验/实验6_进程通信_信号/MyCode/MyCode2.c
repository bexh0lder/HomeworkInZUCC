#include<stdio.h>
#include<signal.h>
#include<unistd.h>
#include<stdlib.h>

int wait_mark;
int count;
void waiting(), stop();

int main()
{
    int p1, p2;
    while((p1 = fork()) == -1);
    if(p1 > 0) //父进程区域
    {
        count = 0;
        signal(16, stop);
        while(1)
        {
      		sleep(1);//优先睡眠保证子进程能在父进程发信号前进入等待状态
        	kill(p1, 17);//父进程向子进程发送信号
            wait_mark = 1;
            waiting();
            count ++;
            printf("parent process caught signal #%d\n",count);
        }
    }
    else
    {
        count = 0;
        signal(17, stop);
        while(1)
        {
            wait_mark = 1;
            waiting();
            count ++;
            printf("child process caught signal #%d\n",count);
            sleep(1);
            kill(getppid(), 16);//子进程向父进程发送信号
        }
    }
}

void waiting()
{
    while(wait_mark != 0);
}

void stop()
{
    wait_mark = 0;
}
