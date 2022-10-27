#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
int main()
{
    pid_t pid;
    pid = fork();
    if (pid < 0)
    {
        perror("fork error:");
        exit(1);
    }
    else if (pid == 0)
    {
        printf("I am the child process.My pid is %d,My ppid is %d\n",getpid(),getppid());
        for(int i = 0; i < 5; ++i){
            system("/bin/ps");
            sleep(3);
        }
    }
    else
    {
        printf("I am the parent process.My pid is %d,My ppid is %d\n",getpid(),getppid());
        for(int i = 0; i < 5; ++i){
            system("/bin/ps");
            sleep(3);
        }
        wait(0);
        exit(0);
    }
}
