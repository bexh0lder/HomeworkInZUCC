# 浙大城市学院实验报告

> - 课程名称：计算机网络实验       
> - 实验项目名称：实验10_网络基本命令      
> - 学生姓名：徐彬涵
> - 专业班级：软件工程2003
> - 学号：32001272 
> - 实验成绩：
> - 指导老师：霍梅梅
> - 日期：2022/04/28  

## 一. 实验目的和要求

1. 熟悉Windows平台下常用网络命令的使用：
   arp,  ftp,  ipconfig,  nbtstat,  net, netstat,  ping,  route,  telnet,  tracert,  pathping

2. 操作环境：硬件：PC机；软件：Windows 2000/XP操作系统

## 二. 实验内容、原理及实验结果与分析

### 在系统DOS命令提示符下运行arp命令（`-a` 、`-s` 、`–d`等参数）

![image-20220428183617706](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428183617706.png)

1. 要求显示当前主机的所有ARP信息

   【命令】

   ```powershell
   arp -a
   ```

   【实验结果与分析】

   ![image-20220428183710047](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428183710047.png)

2. 要求显示某指定主机IP的ARP信息

   【命令】

   ```powershell
   arp -a 192.168.123.1
   ```

   【实验结果与分析】

   ![image-20220428191250822](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428191250822.png)

3. 静态添加某指定主机IP对应的物理地址

   【命令】

   ```powershell
   arp -s 157.55.85.212   00-aa-00-62-c6-09
   ```

   【实验结果与分析】

   ![image-20220428191400639](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428191400639.png)

4. 删除刚添加的指定主机的ARP信息

   【命令】

   ```pow
   arp -d 157.55.85.212
   ```

   【实验结果与分析】

   ![image-20220428191501914](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428191501914.png)

### 在系统DOS命令提示符下运行ftp命令（cd  dir  get等命令）

![image-20220428191544922](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428191544922.png)

1. 连接到某FTP站点（如10.66.28.222）

   【命令】

   ```powershell
   ftp
   open 10.66.28.222 2007
   ```

   【实验结果与分析】

   ![image-20220428192917108](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428192917108.png)

2. 登录某FTP站点，下载某一文件

   【命令】

   ```powershell
   get downloadtest.txt
   ```

   【实验结果与分析】

   ![image-20220428203838546](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428203838546.png)

3. 尝试其他相关FTP命令

   【命令】

   ```powershell
   pwd
   bye
   put C:\Life\Desktop\downloadtest.txt
   ```

   【实验结果与分析】

   ![image-20220428194222428](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428194222428.png)

   ![image-20220428194344686](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428194344686.png)

### 在系统DOS命令提示符下运行ipconfig命令（/all, /displaydns等参数）

![image-20220428194540851](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428194540851.png)

1. 获取本地基本TCP/IP配置值

   【命令】

   ```powershell
   ipconfig
   ```

   【实验结果与分析】

   ![image-20220428194625243](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428194625243.png)

2. 获取本地详细TCP/IP配置值（包括物理地址等信息）

   【命令】

   ```powershell
   ipconfig /all
   ```

   【实验结果与分析】

   ![](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428194722712.png)

 

### 在系统DOS命令提示符下运行nbtstat命令（-a  -n等参数）

![image-20220428194924623](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428194924623.png)

1. 列出某一特定主机的NETBIOS信息（指出物理地址）

   【命令】

   ```
    nbtstat -a  f0-b4-29-dc-b2-54
   ```

   【实验结果与分析】

   ![image-20220428200722887](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428200722887.png)

2. 列出本地主机的NETBIOS信息

   【命令】

   ```powershell
   nbtstat -n
   ```

   【实验结果与分析】

   ![image-20220428195415926](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428195415926.png)

### 在系统DOS命令提示符下运行net命令（share  start  use等命令）

![image-20220428200758424](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428200758424.png)

1. 使用net share命令查看网络共享信息

   【命令】

   ```powershell
   net share
   ```

   【实验结果与分析】

   ![image-20220428200846734](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428200846734.png)

2. 使用net start/stop启动/停止某一服务

   【命令】

   ```powershell
   net start 数据使用量
   ```

   ```powershell
   net stop 数据使用量
   ```

   【实验结果与分析】

   ![image-20220428204105068](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204105068.png)
   
   ![image-20220428201419345](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428201419345.png)
   
   ![image-20220428204014409](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204014409.png)

### 在系统DOS命令提示符下运行netstat命令（-a  -s  -e等参数）

![image-20220428201559709](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428201559709.png)

1. 显示所有本地连接和侦听的端口

   【命令】

   ```powershell
   netstat -a
   ```

   【实验结果与分析】

   ![image-20220428201639123](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428201639123.png)

2. 显示本地连接常见协议的统计信息

   【命令】

   ```powershell
   netstat -s
   ```

   【实验结果与分析】

3. 显示本地以太网统计信息

   【命令】

   ```powershell
   net share
   ```

   【实验结果与分析】

   ![image-20220428201842073](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428201842073.png)

### 在系统DOS命令提示符下运行ping命令（-a  -n  -t等参数）

![image-20220428201926008](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428201926008.png)

1. 使用ping命令的常见参数显示返回信息

   【命令】

   ```powershell
    ping 192.168.123.1
    ping -a 192.168.123.1
    ping -n 8 192.168.123.1
    ping -r 5 192.168.123.1
    ping -t www.baidu.com
   ```

   【实验结果与分析】

   ![image-20220428202002016](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428202002016.png)

   ![image-20220428204244934](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204244934.png)

   ![image-20220428204307360](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204307360.png)

   ![image-20220428204342038](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204342038.png)

   ![image-20220428204434230](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204434230.png)

2. 结合ping命令和arp命令获取子网中某一主机的物理地址

   【命令】

   ```powershell
   ping 192.168.123.1
   arp -a 192.168.123.1
   ```
   
   【实验结果与分析】
   
   ![image-20220428204706282](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204706282.png)

### 在系统DOS命令提示符下运行route命令（print, add, delete等命令）

![image-20220428204759072](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204759072.png)

1. 列出本地已存在的路由信息

   【命令】

   ```powershell
   route print
   ```

   【实验结果与分析】

   ![image-20220428204852809](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428204852809.png)

2. 添加到达某一网络或主机的路由

   【命令】

   ```powershell
   route add 10.64.1.0 mask 255.255.255.0 10.64.1.1 metric 3
   ```

   【实验结果与分析】

   ![image-20220428205049454](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428205049454.png)

3. 删除刚添加的路由选项，并显示删除后的路由信息

   【命令】

   ```powershell
   route delete 10.64.1.0
   ```

   【实验结果与分析】
   
   ![image-20220428205131089](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428205131089.png)

### 在系统DOS命令提示符下运行telnet命令

![image-20220428205504515](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428205504515.png)

![image-20220428205530659](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428205530659.png)

1. 使用telnet命令连接BBS站点：10.13.21.88

   【命令】

   ```powershell
   telnet
   open 10.13.21.88
   open bbs.zju.edu.cn
   ```

   【实验结果与分析】

   ![image-20220428205624952](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428205624952.png)

   ![image-20220428210310662](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210310662.png)

2. 使用telnet命令检测特定主机的某一端口有否开启

   【命令】

   ```powershell
   telnet
   open 10.66.28.222 80
   open 10.66.28.222 2007
   ```
   
   【实验结果与分析】
   
   ![image-20220428210121133](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210121133.png)
   
   ![image-20220428210158271](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210158271.png)

### 在系统DOS命令提示符下运行tracert命令

![image-20220428210329826](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210329826.png)

1. 使用tracert命令显示连接到校园网某一主机的路由信息

   【命令】

   ```powershell
   tracert 10.61.2.6
   ```

   【实验结果与分析】

   ![image-20220428210632869](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210632869.png)

2. 使用tracert命令显示连接到Internet某一站点的路由信息

   【命令】

   ```powershell
   tracert www.baidu.com
   ```

   【实验结果与分析】
   
   ![image-20220428210848004](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210848004.png)

### 在系统DOS命令提示符下运行pathping命令

![image-20220428210910412](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428210910412.png)

1. 使用pathping命令显示连接到某一主机的路由信息

   【命令】

   ```powershell
   pathping 10.61.2.6
   ```

   【实验结果与分析】

   ![image-20220428211037951](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428211037951.png)

2. 比较使用ping和tracert命令连接到相同主机返回信息与使用pathping命令的异同点

   【命令】

   ```powershell
   pathping 10.61.2.6
   traccert 10.61.2.6
   ```
   
   【实验结果与分析】
   
   ![image-20220428211250562](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220428211250562.png)
   
   Tracert命令用来显示数据包到达目标主机所经过的路径（路由器），并显示到达每个节
   
   点（路由器）的时间。命令功能同Ping类似，但它所获得的信息要比Ping命令详细得多，它把数据包所走的全部路径、节点的IP以及花费的时间都显示出来。该命令比较适用于大型网络。
   
   
   
   pathping 命令是一个路由跟踪工具，它将ping 和  tracert 命令的功能与这两个工具所不提供的其他信息结合起来，综合了二者的功能。pathping会先显示中间的通过的路由器（类似tracert命令得到的信息），然后对每个中间路由器（节点）发送一定数量的ping包，通过统计他们对ping包响应的数据包来分析通信质量

## 三. 讨论、心得

记录实验感受、上机过程中遇到的困难及解决办法、遗留的问题、意见和建议等。

 学到了各种指令的使用方式及作用，收获很大

 