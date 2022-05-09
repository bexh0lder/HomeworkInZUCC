# 浙大城市学院实验报告

> - 课程名称：计算机网络实验       
> - 实验项目名称：实验十一 Wireshark网络抓包基础     
> - 学生姓名：徐彬涵
> - 专业班级：软件工程2003
> - 学号：32001272 
> - 实验成绩：
> - 指导老师：霍梅梅
> - 日期：2022/05/05  

## 一. 实验目的和要求

1. 掌握Wireshark软件的安装
2. 学习Wireshark过滤规则的设置
3. 使用Wireshark捕获Ethernet帧，并对Ethernet帧和协议数据包进行分析

 

## 二. 实验内容、原理及实验结果与分析

### 1、**安装Wireshark软件**

1.1 安装Wireshark

下载地址：[**ftp://10.66.28.222:2007**](ftp://10.66.28.222:2007) 

或 **https://www.wireshark.org/download.html**

参考教程：**https://www.wireshark.org/docs/wsug_html_chunked/**

【实验结果与分析】

![image-20220506091931070](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220506091931070.png)

### 2、**在Wireshark中创建并设置以下普通过滤规则**

#### 2.1 捕获本地主机收到和发出的所有数据包

【过滤规则】

 空白字符

![image-20220507143042644](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507143042644.png)

![image-20220507143210966](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507143210966.png)

#### 2.2 捕获本地主机收到和发出的所有ARP包

【过滤规则】

```powershell
arp
```

![image-20220507143254931](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507143254931.png)

![image-20220507143352327](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507143352327.png)

#### 2.3 捕获局域网上所有的ICMP包

【过滤规则】

```powershell
icmp
```

![image-20220507143450985](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507143450985.png)

![image-20220507143514524](C:\Users\Bexh0lder\AppData\Roaming\Typora\typora-user-images\image-20220507143514524.png)

#### 2.4 捕获MAC地址为00-06-68-16-38-80(替换成隔壁主机的MAC地址)的数据包

【过滤规则】

```powershell
ether host D8:12:65:4D:0D:11
```

![image-20220507145041941](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507145041941.png)

![image-20220507145037094](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507145037094.png)

#### 2.5 捕获本地主机收到和发出的HTTP包

【过滤规则】

```powershell
http
```

 ![image-20220507145950922](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507145950922.png)

 

### 3、**捕获并解析Ethernet帧及协议**

#### 3.1 捕获解析本机发出或接收的Ethernet 802.3格式的帧，并对照帧格式进行解释

【过滤规则】

```powershell
ether[12:2]<=1500
```

【实验结果与分析】

![image-20220306121710604](https://img-blog.csdnimg.cn/img_convert/3dbaf43ef63f77b5837754f3b62cd074.png)

| 长度 |              6字节              |         6字节          |    2字节     |
| :--: | :-----------------------------: | :--------------------: | :----------: |
| 字段 | Destination Address（目标地址） | Source Address(源地址) | Length(长度) |
|  值  |        01:80:c2:00:00:00        |   9c:4e:20:c2:45:83    |      46      |

#### 3.2 捕获解析本地主机发出及收到的ARP数据包，解释ARP广播帧的内容及返回数据包信息（如ping一台旁边没连接过的电脑，捕获ARP数据包）

【实验结果与分析】

![image-20220507154659365](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507154659365.png)

![image-20220507154713757](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507154713757.png)

| 长度（字节） |             2             |             2             |               1               |               1               |               2               |
| :----------: | :-----------------------: | :-----------------------: | :---------------------------: | :---------------------------: | :---------------------------: |
|     字段     | Hardware type（硬件类型） | Protocol type（协议类型） | Hardware size（硬件地址长度） | Protocol size（协议地址长度） |      Opcode（操作类型）       |
|      值      |          0x0001           |          0x0800           |               6               |               4               | 0x0001(request)/0x0002(reply) |

| 长度（字节） |                  6                  |                 4                 |                 6                 |                4                |
| :----------: | :---------------------------------: | :-------------------------------: | :-------------------------------: | :-----------------------------: |
|     字段     | Sender MAC address（发送端MAC地址） | Sender IP address（发送端IP地址） | Target MAC address（目的MAC地址） | Target IP address（目的IP地址） |
|      值      |          38:68:93:7e:28:90          |          192.168.123.111          |         00:00:00:00:00:00         |         192.168.123.117         |

#### 3.3 捕获解析局域网上所有的ICMP包，并进行解释（ping一台其他主机）

【实验结果与分析】

![image-20220507161547253](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507161547253.png)

![image-20220507162218568](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507162218568.png)



| 长度（字节） |      1       |      1       |         2          |          2           |             2             |
| :----------: | :----------: | :----------: | :----------------: | :------------------: | :-----------------------: |
|     字段     | Type（类型） | Code（代码） | Checksum（校检和） | Identifier（标识符） | Sequence Number（序列号） |
|      值      |      8       |      0       |       0x4d2b       |        0x0001        |          0x0030           |

 

#### 3.4 对照IP数据包头格式，构造HTTP数据包，解释捕获的IP数据包头的内容

【实验结果与分析】

![image-20220507162915292](https://gitee.com/bexh0lder/image-host/raw/master/img/image-20220507162915292.png)

| 长度（位bit） |        4        |             4             |                     8                     |              16              |           16           |       3       |
| ------------- | :-------------: | :-----------------------: | :---------------------------------------: | :--------------------------: | :--------------------: | :-----------: |
| 字段          | Version（版本） | Header Length（首部长度） | Differentiated Services Field（服务类型） | Total Length（总长度字节数） | Identification（标识） | Flags（标志） |
| 值            |        4        |            20             |                   0x00                    |             483              |         0x0958         |     0x40      |

| 长度（位bit） |            13             |            8             |        8         |              16               |             32             |                32                |
| ------------- | :-----------------------: | :----------------------: | :--------------: | :---------------------------: | :------------------------: | :------------------------------: |
| 字段          | Fragment Offset（片偏移） | Time to Live（生存时间） | Protocol（协议） | Header Checksum（首部校验和） | Source Address（源IP地址） | Desination Address（目的IP地址） |
| 值            |             0             |           128            |      TCP(6)      |            0x0000             |      192.168.123.111       |         202.107.195.209          |

## 三. 讨论、心得

记录实验感受、上机过程中遇到的困难及解决办法、遗留的问题、意见和建议等。