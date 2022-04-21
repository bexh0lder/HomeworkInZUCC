#include<stdio.h>
#include<signal.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/wait.h>
#include<string.h>

int wait_mark;
void waiting(), stop();

int main()
{
    int p1, p2;
    while((p1 = fork()) == -1);
    if(p1 > 0) //父进程区域
    {
        wait_mark = 1;
        struct sigaction act;
        sigemptyset(&act.sa_mask);
        act.sa_sigaction=stop;//三参数信号处理函数
        act.sa_flags=SA_SIGINFO;//信息传递开关，允许传送参数信息给new_op
        if(sigaction(SIGINT,&act,NULL) < 0)
        {
            printf("install sigal error\n");
        }
        union sigval mysigval;
        //char data[10];
        //memset(data,0,sizeof(data));
		//for(int i=0;i < 10;i++)
		//	data[i]='$';
	    //mysigval.sival_ptr=data;
	    mysigval.sival_int=233;//不代表具体含义，只用于说明问题
	    //char data[] = "you success!1";
	    //mysigval.sival_ptr=data;
        waiting();
        sigqueue(p1,16,mysigval);//父进程向子进程（p1）发送信号，并传递附加信息
        wait(0);
        printf("parents is killed \n"); //有\n所以不用fflush(stdout)
        sleep(1);
        exit(0);
    }
    else
    {
        while((p2 = fork()) == -1);
        if(p2 > 0) //子进程区域
        {
            wait_mark = 1;
            struct sigaction act;
            sigemptyset(&act.sa_mask);
            act.sa_sigaction=stop;//三参数信号处理函数
            act.sa_flags=SA_SIGINFO;//信息传递开关，允许传送参数信息给new_op
            union sigval mysigval;
            //char data[10];
		    //memset(data,0,sizeof(data));
			//for(int i=0;i < 10;i++)
			//	data[i]='$';	
	        //mysigval.sival_ptr=data;
	        mysigval.sival_int=888;//不代表具体含义，只用于说明问题
	        //char data[] = "you success2!";
	        //mysigval.sival_ptr=data;
            if(sigaction(16,&act,NULL) < 0)
            {
                printf("install sigal error\n");
            }            
            waiting();
            sigqueue(p2,17,mysigval);//父进程向子进程（p1）发送信号，并传递附加信息
			wait(0);
            printf("Child process is killed by parent\n");
            sleep(1);
            exit(0);
        } 
        else //孙子进程区域
        {
            wait_mark = 1;
            struct sigaction act;
            sigemptyset(&act.sa_mask);
            act.sa_sigaction=stop;//三参数信号处理函数
            act.sa_flags=SA_SIGINFO;//信息传递开关，允许传送参数信息给new_op
            if(sigaction(17,&act,NULL) < 0)
            {
                printf("install sigal error\n");
            }
            waiting();
            printf("Grandson process is killed by son\n");
            sleep(1);
            exit(0);
        }
    }
}
void waiting()
{
    while(wait_mark != 0);
}

void stop(int signum,siginfo_t *info,void *myact)
{
    int i;
    if(signum == 2) printf("Oh my father, I see you, you are my real father\n");
    else printf("Oh my father, I am %d, you have sent a number %d\n", signum, info->si_int);
	//for(i=0;i<10;i++){
	//	printf("%c ",(*( (char*)((*info).si_ptr)+i)));
	//}
	// printf("\nhandle signal %d over;\n\n",signum);
    wait_mark = 0;
}
