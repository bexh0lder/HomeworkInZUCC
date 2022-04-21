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
    //signal(SIGINT, stop);
    while((p1 = fork()) == -1);
    if(p1 > 0)
    {
        while((p2 = fork()) == -1);
        if(p2 > 0)
        {
            signal(38, stop);
            wait_mark = 1;
            waiting();
            kill(p1, 16);
            printf("wait p1 send\n");
            fflush(stdout);
            wait_mark = 1;
            waiting(); /*等待子进程发信号表示输出完再向另一个子进程发信号，保证子进程之间输出不交替*/
            kill(p2, 17);
            wait(0);
            wait(0);
            printf("parents is killed \n");
            exit(0);
        }
        else
        {
            wait_mark = 1;
            signal(17, stop);
            waiting();
            //lockf(1,F_LOCK,100);
            printf("P2 is killed by parent 1\n");
            fflush(stdout);
            sleep(1);
            printf("P2 is killed by parent 2\n");
            fflush(stdout);
            //lockf(1,F_ULOCK,100);
            // kill(getppid(), SIGINT);
            exit(0);
        }
    }
    else
    {
        wait_mark = 1;
            signal(16, stop);
            waiting();
            //lockf(1,F_LOCK,100);
            printf("P1 is killed by parent 1\n");
            fflush(stdout);
            sleep(1);
            printf("P1 is killed by parent 2\n");
            fflush(stdout);
            //lockf(1,F_ULOCK,100);
            printf("p1 send\n");
            fflush(stdout);
            kill(getppid(), 38);
            exit(0);
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
