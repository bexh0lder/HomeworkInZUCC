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
    int fd[2], pid, i = 0;
    int len;
    ssize_t n;
    char str[STR_MAX_SIZE];
    // ���������ź��������������򴴽�����������ֱ�Ӵ򿪣���ʼֵΪ0
	sem_t *read_mutex;
    sem_t *move_mutex;
    sem_t *print_mutex;
    read_mutex = sem_open("pipe_test_read", O_CREAT | O_RDWR, 0666, 1);
    move_mutex = sem_open("pipe_test_move", O_CREAT | O_RDWR, 0666, 0);
    print_mutex = sem_open("pipe_test_print", O_CREAT | O_RDWR, 0666, 0);

    CHECK(pipe(fd) >= 0);
    CHECK((pid = fork()) >= 0);

    if (pid == 0) {// child1 read
        sem_wait(read_mutex);
        printf("1\n");
        sem_wait(move_mutex);
        exit(EXIT_SUCCESS);
    }

    CHECK((pid = fork()) >= 0);
    if (pid == 0) {//child2 move
        sem_wait(move_mutex);
        printf("2\n");
        sem_wait(print_mutex);
        exit(EXIT_SUCCESS);
    }

    CHECK((pid = fork()) >= 0);
    if (pid == 0) {//child3 print
        sem_wait(print_mutex);
        printf("3\n");
        exit(EXIT_SUCCESS);
    }
    
    wait(0);// �ȴ���һ���ӽ���������ɣ������̼�������
    wait(0);
    wait(0);

    printf("Here is parent , my children are over\n");

    sem_close(read_mutex);
    sem_close(move_mutex);
    sem_close(print_mutex);
    sem_unlink("pipe_test_read");
    sem_unlink("pipe_test_move");
    sem_unlink("pipe_test_print");
    return 0;
}
