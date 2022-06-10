#include <linux/init.h>
#include <linux/module.h>
#include <linux/types.h>
#include <linux/fs.h>
#include <asm/uaccess.h>
#include <linux/cdev.h>

MODULE_AUTHOR("huj");
MODULE_LICENSE("GPL");

#define MYCDEV_MAJOR 231 /*给定的主设备号*/
#define MYCDEV_SIZE 100

static int mycdev_open(struct inode *inode, struct file *fp)
{
   printk("mycdev module open a file..\n");
	return 0;
}

static int mycdev_release(struct inode *inode, struct file *fp)
{
	printk("mycdev module release a file..\n");    
	return 0;
}

static ssize_t mycdev_read(struct file *fp, char __user *buf, size_t size, loff_t *pos)
{
    unsigned long p = *pos;
    unsigned int count = size;
    //char kernel_buf[MYCDEV_SIZE]="This is huj mycdev!";
    char kernel_buf[MYCDEV_SIZE];
    int i;

    memset(kernel_buf,0,MYCDEV_SIZE);
    memcpy(kernel_buf, "This is huj!", MYCDEV_SIZE);

    if(p >= MYCDEV_SIZE)
        return -1;
    if(count > MYCDEV_SIZE)
        count = MYCDEV_SIZE - p;

    if (copy_to_user(buf, kernel_buf, count) != 0) {
        printk("read error!\n");
        return -1;
    }

    
    for (i = 0; i < count; i++) {
        __put_user(kernel_buf[i], buf);//write 'i' from kernel space to user space's buf;
        buf++;
    }
    

    printk("reader: %d bytes was read...\n", count);
    return count;
}

static ssize_t mycdev_write(struct file *fp, const char __user *buf, size_t size, loff_t *pos)
{
    return size;
}

/* 填充 mycdev的 file operation 结构*/
static const struct file_operations mycdev_fops =
{
    .owner = THIS_MODULE,
    .read = mycdev_read,
    .write = mycdev_write,
    .open = mycdev_open,
    .release = mycdev_release,
};

/*模块初始化函数*/
static int __init mycdev_init(void)
{
    int ret;
    printk("mycdev module is staring..\n");
    ret=register_chrdev(MYCDEV_MAJOR,"my_cdev",&mycdev_fops); /*注册驱动程序*/
    if(ret<0){
        printk("register failed..\n");
        return 0;
    }else{
        printk("register success..\n");
    }
    return 0;
}

/*模块卸载函数*/
static void __exit mycdev_exit(void)
{
    printk("mycdev module is leaving..\n");
    unregister_chrdev(MYCDEV_MAJOR,"my_cdev"); /*注销驱动程序*/
}

module_init(mycdev_init);
module_exit(mycdev_exit);

