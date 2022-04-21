#include<stdio.h>
#include<signal.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/wait.h>

int wait_mark;
void waiting(), stop();

int main()
{
    int p1, p2;
    while((p1 = fork()) == -1);
    if(p1 > 0) //父进程区域
    {
        wait_mark = 1;
        signal(SIGINT, stop);
        waiting();
        kill(p1, 16);//父进程向子进程（p1）发送信号
        wait(0);
        printf("parents is killed \n"); //有\n所以不用fflush(stdout)
        exit(0);
    }
    else
    {
        while((p2 = fork()) == -1);
        if(p2 > 0) //子进程区域
        {
            wait_mark = 1;
            signal(16, stop);
            waiting();
            kill(p2, 17);//子进程向孙子进程（p2）发送信号
            wait(0);
            printf("Child process is killed by parent\n");
            sleep(1);
            exit(0);
        } 
        else //孙子进程区域
        {
            wait_mark = 1;
            signal(17, stop);
            waiting();
            printf("Grandson process is killed by parent son\n");
            sleep(1);
            exit(0);
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
