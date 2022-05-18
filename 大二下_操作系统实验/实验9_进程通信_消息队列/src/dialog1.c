#include <stdio.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <sys/ipc.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include<signal.h>

#define MSGKEY1 66
#define MSGKEY2 68


struct msgform
{
    long mtype;
    char mtext[1000];
}msg;

int msgqid;

void server( ) {
    msgqid=msgget(MSGKEY1,0777|IPC_CREAT);
    while (1){
        sleep(1);
        msgrcv(msgqid,&msg,1024,0,0);
        if (msg.mtype == 2) {
            printf("(server)received:%s\n", msg.mtext);
            strcpy(msg.mtext, "");
            msg.mtype = 1;
            msgsnd(msgqid, &msg, 1024, 0);
        }else
            continue;
    }
}
void client() {
    /*打开消息队列*/
    msgqid=msgget(MSGKEY2,0777|IPC_CREAT);
    while (1) {
        sleep(1);
        scanf("%s", msg.mtext);
        msg.mtype = 2;
        msgsnd(msgqid, &msg, 1024, 0);
        if (strncmp(msg.mtext, "bye", 3) == 0) break;
    }
}
int main( )
{
	int pid;
    if(fork()!=0)
        server();
    else
        client();
    kill(pid,2);
    wait(0);
}
