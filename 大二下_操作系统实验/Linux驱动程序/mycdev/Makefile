obj-m:=mycdev.o
PWD:=$(shell pwd)
CUR_PATH:=$(shell uname -r)
KERNEL_PATH:=/usr/src/kernels/$(CUR_PATH)




all:  
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules  
clean:  
	rm -rf *.o *~ core .*.cmd *.mod.c ./tmp_version
