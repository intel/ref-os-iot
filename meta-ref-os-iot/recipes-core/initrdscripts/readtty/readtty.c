#include <stdlib.h>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>

char byteread = {0};

void* readthread1(void* arg)
{
    int fd = *((int *) arg);
    //printf("This is thread on %s\n",tty);
    ssize_t size = read(fd, &byteread, 1);
    //printf("Thread1 on Read byte %c\n", byteread);
    return NULL;
}
int main(int argc, char *argv[]) {

    int timeout = 0, counter = 0;
    if ( argc > 1 ) {
        if (sscanf (argv[1], "%i", &timeout) != 1 ){
            fprintf(stderr, "error - not an integer");
        }
    }

    char *tty1 = "/dev/tty1";
    char *ttyS2 = "/dev/ttyS2";
    static struct termios oldtty1, newtty1, oldttyS2, newttyS2;
    pthread_t first_thread, second_thread;
    int* fdtty1p, *fdttyS2p;
    int fdtty1, fdttyS2;

    fdtty1= open(tty1, O_RDWR);
    if(fdtty1 > 0)
    {
     	fdtty1p = (int*) malloc(sizeof(int));
        if (fdtty1p == 0){
            fprintf(stderr, "ERROR: Out of memory\n\n");
        }
        else {
            *fdtty1p = fdtty1;
            tcgetattr( fdtty1, &oldtty1);
            newtty1 = oldtty1;
            newtty1.c_lflag &= ~(ICANON);
            tcsetattr( fdtty1, TCSANOW, &newtty1);
            if(pthread_create(&first_thread, NULL, readthread1,  fdtty1p)) {
                fprintf(stderr, "Error creating thread\n");
            }

        }
    }
    fdttyS2 = open(ttyS2, O_RDWR);
    if(fdttyS2 > 0)
    {
        fdttyS2p = (int*) malloc(sizeof(int));
        if (fdttyS2p == 0){
            fprintf(stderr, "ERROR: Out of memory\n\n");
        }
        else {
            *fdttyS2p = fdttyS2;
            tcgetattr( fdttyS2, &oldttyS2);
            newttyS2 = oldttyS2;
            newttyS2.c_lflag &= ~(ICANON);
            tcsetattr( fdttyS2, TCSANOW, &newttyS2);
            if(pthread_create(&second_thread, NULL, readthread1, fdttyS2p)) {
                fprintf(stderr, "Error creating thread\n");
            }
        }
    }
    while (1)
    {
        if(byteread !=0)
        {
            printf("%c\n", byteread);
            break;
        }
        if (timeout > 0) {
            if(counter > timeout * 10)
                break;
        }
        usleep(100000);
        counter++;
    }
  if(fdtty1 > 0)
    {
        tcsetattr( fdtty1, TCSANOW, &oldtty1);
        close(fdtty1);
    }

    if(fdttyS2 > 0)
    {
        tcsetattr( fdttyS2, TCSANOW, &oldttyS2);
        close(fdttyS2);
    }
    if(fdtty1p!=0)
        free(fdtty1p);

    if(fdttyS2p!=0)
        free(fdttyS2p);
    return 0;
}
