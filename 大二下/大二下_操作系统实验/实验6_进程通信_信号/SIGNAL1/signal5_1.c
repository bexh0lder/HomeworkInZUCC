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
    signal(SIGINT, stop);
    while((p1 = fork()) == -1);
    if(p1 > 0)
    {
        while((p2 = fork()) == -1);
        if(p2 > 0)
        {
            wait_mark = 1;
            waiting();
            kill(p1, 16);
            kill(p2, 17);
            wait(0);
            // wait(0);
            printf("parents is killed \n");
            exit(0);
        }
        else
        {
            wait_mark = 1;
            signal(17, stop);
            waiting();
            printf("P2 is killed by parent 1\n");
            fflush(stdout);
            sleep(1);
            printf("P2 is killed by parent 2\n");
            fflush(stdout);
            exit(0);
        }
    }
    else
    {
        wait_mark = 1;
            signal(16, stop);
            waiting();
            printf("P1 is killed by parent 1\n");
            fflush(stdout);
            sleep(1);
            printf("P1 is killed by parent 2\n");
            fflush(stdout);
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
