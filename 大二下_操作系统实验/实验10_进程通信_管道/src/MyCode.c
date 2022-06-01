#include <errno.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

#define STR_MAX_SIZE 100
#define CHECK(x)                                            \
    do {                                                    \
        if (!(x)) {                                         \
            fprintf(stderr, "%s:%d: ", __func__, __LINE__); \
            perror(#x);                                     \
            exit(-1);                                       \
        }                                                   \
    } while (0)


int main() {
    int fd[2], pid, i = 0, j = 0;
    int len;
    int count;
    ssize_t n;
    char str[STR_MAX_SIZE];
	//sem_t *read_mutex;
    sem_t *move_mutex;
    sem_t *print_mutex;
    //read_mutex = sem_open("pipe_test_read", O_CREAT | O_RDWR, 0666, 1);
    move_mutex = sem_open("pipe_test_move", O_CREAT | O_RDWR, 0666, 0);
    print_mutex = sem_open("pipe_test_print", O_CREAT | O_RDWR, 0666, 0);

	printf("plz tell me the quantity: ");
	scanf("%d",&count);
	
    CHECK(pipe(fd) >= 0);
    CHECK((pid = fork()) >= 0);
    
    if (pid == 0) {// child1 read
        //sem_wait(read_mutex);
        
        for(i = 1; i <= count; ++i){
        	printf("plz input the %d string: ", i);
        	scanf("%s",str);
        	write(fd[1], str, STR_MAX_SIZE);
        }
        
        sem_post(move_mutex);
        exit(EXIT_SUCCESS);
    }

    CHECK((pid = fork()) >= 0);
    if (pid == 0) {//child2 move
        sem_wait(move_mutex);
        
        for(i = 1; i <= count; ++i){
        	read(fd[0], str, STR_MAX_SIZE);
        	printf("move received the %d string %s, and move it\n", i, str);
        	for(j = 0; j < strlen(str); ++j){
        		if(str[j] >= 97 && str[j] <= 122)
        			str[j] -= 32; 
        	}
        	write(fd[1], str, STR_MAX_SIZE);
        }
        
        sem_post(print_mutex);
        exit(EXIT_SUCCESS);
    }

    CHECK((pid = fork()) >= 0);
    if (pid == 0) {//child3 print
        sem_wait(print_mutex);
        
        for(i = 1; i <= count; ++i){
        	read(fd[0], str, STR_MAX_SIZE);
        	printf("print received the %d string %s\n", i, str);
        }
        
        exit(EXIT_SUCCESS);
    }
    
    wait(0);
    wait(0);
    wait(0);

    printf("Here is parent , my children are over\n");

    //sem_close(read_mutex);
    sem_close(move_mutex);
    sem_close(print_mutex);
    //sem_unlink("pipe_test_read");
    sem_unlink("pipe_test_move");
    sem_unlink("pipe_test_print");
    return 0;
}