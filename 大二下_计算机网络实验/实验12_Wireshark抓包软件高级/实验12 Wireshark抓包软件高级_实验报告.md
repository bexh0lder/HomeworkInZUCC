

# 浙大城市学院实验报告

> - 课程名称：计算机网络实验       
> - 实验项目名称：实验十二 Wireshark抓包软件高级
> - 学生姓名：徐彬涵
> - 专业班级：软件工程2003
> - 学号：32001272 
> - 实验成绩：
> - 指导老师：霍梅梅
> - 日期：2022/05/12  

## 一. 实验目的和要求

1. 进一步学习掌握Wireshark过滤规则的设置

2. 使用Wireshark捕获Ethernet帧，并对高层协议数据包进行分析

## 二. 实验内容、原理及实验结果与分析

### **在Wireshark中创建并设置以下过滤规则**

#### 1.1 捕获局域网上的所有UDP数据包

【过滤规则】

```powershell
udp
```

![image-20220512092333829](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512092333829.png)

#### 1.2 捕获本地主机收到和发出的所有FTP数据包

【过滤规则】

```powershell
tcp port 21 || tcp port 20
tcp port 2007
```

![image-20220512164100197](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512164100197.png)

#### 1.3 捕获本地主机和某一主机之间的远程桌面控制数据包（TCP端口3389）

【过滤规则】

```powershell
tcp port 3389
```

#### 1.4 捕获本地主机和www.zucc.edu.cn之间的通信

【过滤规则】

```
host www.baidu.com
```

### **捕获并解析TCP/IP协议的高层协议数据包**

#### 2.1 捕获解析本机发出或接收的UDP数据包，并对照UDP报头格式进行解释（如发送QQ信息构造UDP数据包）

【实验结果与分析】

![image-20220512190004169](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512190004169.png)

| 长度 |        2字节        |           2字节            |    2字节     |      2字节       |  长度可变  |
| :--: | :-----------------: | :------------------------: | :----------: | :--------------: | :--------: |
| 字段 | Source Port(源端口) | Destination Port(目标端口) | Length(长度) | Checksum(校检值) | Data(数据) |
|  值  |        61531        |             53             |      92      |      0xa883      |            |

#### 2.2 捕获解析本地主机发出及收到的FTP数据包，并对照TCP报头格式进行解释，同时分析FTP发出的命令和响应（如[ftp://10.66.28.222](ftp://10.66.28.222)：2007构造FTP数据包）

【实验结果与分析】

![image-20220512194750257](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512194750257.png)

| 长度 |        16位         |            16位            |
| :--: | :-----------------: | :------------------------: |
| 字段 | Source Port(源端口) | Destination Port(目标端口) |
|  值  |        51810        |             21             |

| 长度 |           32位            |              32位               |
| :--: | :-----------------------: | :-----------------------------: |
| 字段 | Sequence Number(数据序号) | Acknowledgment Number(确认序号) |
|  值  |            20             |               90                |

| 长度 |           4位           |      6位       |
| :--: | :---------------------: | :------------: |
| 字段 | Header Length(首部长度) | Reversed(保留) |
|  值  |           20            |       0        |

**Flags(标志)**

| 长度 |        1位        |        1位        |                    1位                    |      1位      |          1位          |          1位          |
| :--: | :---------------: | :---------------: | :---------------------------------------: | :-----------: | :-------------------: | :-------------------: |
| 字段 | URG(紧急指针标志) | ACK(确认序号有效) | PSH(接收方应该尽快将这个报文段交给应用层) | RST(重建连接) | SYN(同步序号发起连接) | FIN(发端完成发送任务) |
|  值  |         0         |         1         |                     1                     |       0       |           0           |           0           |

| 长度 |         16位          |       16位       |   16位   |
| :--: | :-------------------: | :--------------: | :------: |
| 字段 | Window size(窗口大小) | Checksum(校检和) | 紧急指针 |
|  值  |         1021          |      0x2cf7      |    0     |

![image-20220512201053945](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512201053945.png)

![Wireshark网络协议分析：图文解读FTP数据报文_数据_03](https://s6.51cto.com/images/blog/202112/30105425_61cd1f61d295610205.jpg?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

这里我进行了从ftp下载文件到本地的操作，客户端向服务端发送请求test.txt文件的操作，服务端回应125表示连接打开准备传送，传送完成后服务端发送226表示关闭数据连接，请求的文件操作成功

我这里使用的是匿名登录，但如果是用户登录的话用户名和密码都会直接以明文方式显示在ftp数据包中，所以ftp是不安全的协议

#### 2.3 捕获解析本机和一特定WWW服务器之间的通信（如www.baidu.com），找出其中三次握手的数据包，并进行解释，同时分析HTTP的命令和响应

【实验结果与分析】

![image-20220512202333280](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512202333280.png)

客户端：10.67.137.140

服务端：180.101.49.11

1. 客户端向服务端发送建立连接请求，标志位SYN为1
2. 服务端接收到客户端的请求后，向客户端同样发送确认建立连接报文，标志位SYN为1,ACK为第一个数据包的SEQ+1
3. 客户端接收到服务端的回应后，向服务端发送确认报文ACK为第二个数据包的SEQ+1

- HTTP命令（主要）
  - GET
  - POST

![image-20220512203918731](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512203918731.png)

- 响应标头
  - Bdpagetype 页面类型
  - Bdqid 
  - Cache-Control 通用消息头字段，被用于在http请求和响应中，通过指定指令来实现缓存机制
  - Connection 决定当前的事务完成后，是否会关闭网络连接
  - Content-Encoding 列出了对当前实体消息（消息荷载）应用的任何编码类型，以及编码的顺序
  - Content-Type 实体头部用于指示资源的MIME类型
  - Date 当前的GMT时间
  - Expires 响应头包含日期/时间，即在此时候之后，响应过期
  - Server 服务器名字
  - Set-Cookie 设置和页面关联的Cookie
  - Strict-Transport-Security 是一个安全功能，它告诉浏览器只能通过HTTPS访问当前资源，而不是[HTTP](https://developer.mozilla.org/en-US/HTTP)
  - Traceid [全链路日志追踪](https://blog.csdn.net/promisessh/article/details/110532387)
  - Transfer-Encoding 指明了将 [entity](https://developer.mozilla.org/zh-CN/docs/Glossary/Entity_header) 安全传递给用户所采用的编码形式。
  - X-Frame-Options 用来给浏览器指示允许一个页面可否在frame、ifram、embed或者object中展现的标记。站点可以通过确保网站没有被嵌入到别人的站点里面，从而避免[点击劫持](https://developer.mozilla.org/en-US/docs/Web/Security/Types_of_attacks#click-jacking)攻击
  - X-Ua-Compatible 可以指定网页的兼容性模式设置

![image-20220512203952364](https://bex-image.oss-cn-hangzhou.aliyuncs.com/img/image-20220512203952364.png)

## 三. 讨论、心得

记录实验感受、上机过程中遇到的困难及解决办法、遗留的问题、意见和建议等。