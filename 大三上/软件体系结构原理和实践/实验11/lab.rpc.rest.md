# **2022-23学年第1学期**

## **实 验 报 告**

![zucc](zucc.png)

- 课程名称: 软件体系结构与实践
- 实验项目: 访问远程服务
- 专业班级: 软件工程2003
- 学生学号: 32001272
- 学生姓名: 徐彬涵
- 实验指导教师:郭鸣

### 【实验目的】

- 访问远程服务
- 理解RPC，REST差异
1. 阅读 [访问远程服务](https://icyfenix.cn/architect-perspective/general-architecture/api-style/) ,回答下面的问题(本次提交)
- 进程间通信”（Inter-Process Communication，IPC）与远程过程调用 RPC 的区别与联系？

  - RPC 出现的最初目的，就是**为了让计算机能够跟调用本地方法一样去调用远程方法**，而IPC解决了本地方法中两个进程之间如何交换数据的问题
  - IPC Socket不仅适用于本地相同机器的不同进程间通信，由于 Socket 是网络栈的统一接口，它也理所当然地能支持基于网络的跨机器的进程间通信
  - RPC不能完全作为 IPC 的一种特例来看待，透明的调用形式造成了程序员误以为**通信是无成本的假象**，因而被滥用以致于显著降低了分布式系统的性能
    - 两个进程通信，谁作为服务端，谁作为客户端？
    - 怎样进行异常处理？异常该如何让调用者获知？
    - 服务端出现多线程竞争之后怎么办？
    - 如何提高网络利用的效率，譬如连接是否可被多个请求复用以减少开销？是否支持多播？
    - 参数、返回值如何表示？应该有怎样的字节序？
    - 如何保证网络的可靠性？譬如调用期间某个链接忽然断开了怎么办？
    - 发送的请求服务端收不到回复该怎么办？
    - ……

- 访问远程服务三个基本问题，分别是什么？

  - 如何表示数据

    这里数据包括了传递给方法的参数，以及方法执行后的返回值。无论是将参数传递给另外一个进程，还是从另外一个进程中取回执行结果，都涉及到它们应该如何表示。进程内的方法调用，使用程序语言预置的和程序员自定义的数据类型，就很容易解决数据表示问题，远程方法调用则完全可能面临交互双方各自使用不同程序语言的情况；即使只支持一种程序语言的 RPC 协议，在不同硬件指令集、不同操作系统下，同样的数据类型也完全可能有不一样表现细节，譬如数据宽度、字节序的差异等等。有效的做法是将交互双方所涉及的数据转换为某种事先约定好的中立数据流格式来进行传输，将数据流转换回不同语言中对应的数据类型来进行使用，这个过程说起来拗口，但相信大家一定很熟悉，就是序列化与反序列化。每种 RPC 协议都应该要有对应的序列化协议，譬如：

    - ONC RPC 的[External Data Representation](https://en.wikipedia.org/wiki/External_Data_Representation) （XDR）
    - CORBA 的[Common Data Representation](https://en.wikipedia.org/wiki/Common_Data_Representation)（CDR）
    - Java RMI 的[Java Object Serialization Stream Protocol](https://docs.oracle.com/javase/8/docs/platform/serialization/spec/protocol.html#a10258)
    - gRPC 的[Protocol Buffers](https://developers.google.com/protocol-buffers)
    - Web Service 的[XML Serialization](https://docs.microsoft.com/en-us/dotnet/standard/serialization/xml-serialization-with-xml-web-services)
    - 众多轻量级 RPC 支持的[JSON Serialization](https://tools.ietf.org/html/rfc7159)
    - ……

  - 如何传递数据

    ：准确地说，是指如何通过网络，在两个服务的 Endpoint 之间相互操作、交换数据。这里“交换数据”通常指的是应用层协议，实际传输一般是基于标准的 TCP、UDP 等标准的传输层协议来完成的。两个服务交互不是只扔个序列化数据流来表示参数和结果就行的，许多在此之外信息，譬如异常、超时、安全、认证、授权、事务，等等，都可能产生双方需要交换信息的需求。在计算机科学中，专门有一个名称“

    Wire Protocol

    ”来用于表示这种两个 Endpoint 之间交换这类数据的行为，常见的 Wire Protocol 有：

    - Java RMI 的[Java Remote Message Protocol](https://docs.oracle.com/javase/8/docs/platform/rmi/spec/rmi-protocol3.html)（JRMP，也支持[RMI-IIOP](https://zh.wikipedia.org/w/index.php?title=RMI-IIOP&action=edit&redlink=1)）
    - CORBA 的[Internet Inter ORB Protocol](https://en.wikipedia.org/wiki/General_Inter-ORB_Protocol)（IIOP，是 GIOP 协议在 IP 协议上的实现版本）
    - DDS 的[Real Time Publish Subscribe Protocol](https://en.wikipedia.org/wiki/Data_Distribution_Service)（RTPS）
    - Web Service 的[Simple Object Access Protocol](https://en.wikipedia.org/wiki/SOAP)（SOAP）
    - 如果要求足够简单，双方都是 HTTP Endpoint，直接使用 HTTP 协议也是可以的（如 JSON-RPC）
    - ……

  - 如何确定方法

    这在本地方法调用中并不是太大的问题，编译器或者解释器会根据语言规范，将调用的方法签名转换为进程空间中子过程入口位置的指针。不过一旦要考虑不同语言，事情又立刻麻烦起来，每门语言的方法签名都可能有所差别，所以“如何表示同一个方法”，“如何找到对应的方法”还是得弄个跨语言的统一的标准才行。这个标准做起来可以非常简单，譬如直接给程序的每个方法都规定一个唯一的、在任何机器上都绝不重复的编号，调用时压根不管它什么方法签名是如何定义的，直接传这个编号就能找到对应的方法。这种听起既粗鲁又寒碜的办法，还真的就是 DCE/RPC 当初准备的解决方案。虽然最终 DCE 还是弄出了一套语言无关的

    接口描述语言

    （Interface Description Language，IDL），成为此后许多 RPC 参考或依赖的基础（如 CORBA 的 OMG IDL），但那个唯一的绝不重复的编码方案

    UUID

    （Universally Unique Identifier）却也被保留且广为流传开来，今天已广泛应用于程序开发的方方面面。类似地，用于表示方法的协议还有：

    - Android 的[Android Interface Definition Language](https://developer.android.com/guide/components/aidl)（AIDL）
    - CORBA 的[OMG Interface Definition Language](https://www.omg.org/spec/IDL)（OMG IDL）
    - Web Service 的[Web Service Description Language](https://zh.wikipedia.org/wiki/WSDL)（WSDL）
    - JSON-RPC 的[JSON Web Service Protocol](https://en.wikipedia.org/wiki/JSON-WSP)（JSON-WSP）
    - ……

- 通过网络进行分布式运算的八宗罪（8 Fallacies of Distributed Computing）？

  1. The network is reliable —— 网络是可靠的。
  2. Latency is zero —— 延迟是不存在的。
  3. Bandwidth is infinite —— 带宽是无限的。
  4. The network is secure —— 网络是安全的。
  5. Topology doesn't change —— 拓扑结构是一成不变的。
  6. There is one administrator —— 总会有一个管理员。
  7. Transport cost is zero —— 不必考虑传输成本。
  8. The network is homogeneous —— 网络是同质化的。

- REST 风格的系统应该满足以下六大原则是什么？

  1. **服务端与客户端分离**（Client-Server）
     将用户界面所关注的逻辑和数据存储所关注的逻辑分离开来，有助于提高用户界面的跨平台的可移植性，这一点正越来越受到广大开发者所认可，以前完全基于服务端控制和渲染（如 JSF 这类）框架实际用户已甚少，而在服务端进行界面控制（Controller），通过服务端或者客户端的模版渲染引擎来进行界面渲染的框架（如 Struts、SpringMVC 这类）也受到了颇大的冲击。这一点主要推动力量与 REST 可能关系并不大，前端技术（从 ES 规范，到语言实现，到前端框架等）的近年来的高速发展，使得前端表达能力大幅度加强才是真正的幕后推手。由于前端的日渐强势，现在还流行起由前端代码反过来驱动服务端进行渲染的 SSR（Server-Side Rendering）技术，在 Serverless、SEO 等场景中已经占领了一块领地。
  2. **无状态**（Stateless）
     无状态是 REST 的一条核心原则，部分开发者在做服务接口规划时，觉得 REST 风格的服务怎么设计都感觉别扭，很有可能的一种原因是在服务端持有着比较重的状态。REST 希望服务器不要去负责维护状态，每一次从客户端发送的请求中，应包括所有的必要的上下文信息，会话信息也由客户端负责保存维护，服务端依据客户端传递的状态来执行业务处理逻辑，驱动整个应用的状态变迁。客户端承担状态维护职责以后，会产生一些新的问题，譬如身份认证、授权等可信问题，它们都应有针对性的解决方案（这部分内容可参见“[安全架构](https://icyfenix.cn/architect-perspective/general-architecture/system-security)”的内容）。
     但必须承认的现状是，目前大多数的系统都达不到这个要求，往往越复杂、越大型的系统越是如此。服务端无状态可以在分布式计算中获得非常高价值的好处，但大型系统的上下文状态数量完全可能膨胀到让客户端在每次请求时提供变得不切实际的程度，在服务端的内存、会话、数据库或者缓存等地方持有一定的状态成为一种是事实上存在，并将长期存在、被广泛使用的主流的方案。
  3. **可缓存**（Cacheability）
     无状态服务虽然提升了系统的可见性、可靠性和可伸缩性，但降低了系统的网络性。“降低网络性”的通俗解释是某个功能如果使用有状态的设计只需要一次（或少量）请求就能完成，使用无状态的设计则可能会需要多次请求，或者在请求中带有额外冗余的信息。为了缓解这个矛盾，REST 希望软件系统能够如同万维网一样，允许客户端和中间的通讯传递者（譬如代理）将部分服务端的应答缓存起来。当然，为了缓存能够正确地运作，服务端的应答中必须明确地或者间接地表明本身是否可以进行缓存、可以缓存多长时间，以避免客户端在将来进行请求的时候得到过时的数据。运作良好的缓存机制可以减少客户端、服务器之间的交互，甚至有些场景中可以完全避免交互，这就进一步提高了性能。
  4. **分层系统**（Layered System）
     这里所指的并不是表示层、服务层、持久层这种意义上的分层。而是指客户端一般不需要知道是否直接连接到了最终的服务器，抑或连接到路径上的中间服务器。中间服务器可以通过负载均衡和共享缓存的机制提高系统的可扩展性，这样也便于缓存、伸缩和安全策略的部署。该原则的典型的应用是内容分发网络（Content Distribution Network，CDN）。如果你是通过网站浏览到这篇文章的话，你所发出的请求一般（假设你在中国国境内的话）并不是直接访问位于 GitHub Pages 的源服务器，而是访问了位于国内的 CDN 服务器，但作为用户，你完全不需要感知到这一点。我们将在“[透明多级分流系统](https://icyfenix.cn/architect-perspective/general-architecture/diversion-system)”中讨论如何构建自动的、可缓存的分层系统。
  5. **统一接口**（Uniform Interface）
     这是 REST 的另一条核心原则，REST 希望开发者面向资源编程，希望软件系统设计的重点放在抽象系统该有哪些资源上，而不是抽象系统该有哪些行为（服务）上。这条原则你可以类比计算机中对文件管理的操作来理解，管理文件可能会进行创建、修改、删除、移动等操作，这些操作数量是可数的，而且对所有文件都是固定的、统一的。如果面向资源来设计系统，同样会具有类似的操作特征，由于 REST 并没有设计新的协议，所以这些操作都借用了 HTTP 协议中固有的操作命令来完成。
     统一接口也是 REST 最容易陷入争论的地方，基于网络的软件系统，到底是面向资源更好，还是面向服务更合适，这事情哪怕是很长时间里都不会有个定论，也许永远都没有。但是，已经有一个基本清晰的结论是：面向资源编程的抽象程度通常更高。抽象程度高意味着坏处是往往距离人类的思维方式更远，而好处是往往通用程度会更好。用这样的语言去诠释 REST，大概本身就挺抽象的，笔者还是举个例子来说明：譬如，几乎每个系统都有的登录和注销功能，如果你理解成登录对应于 login()服务，注销对应于 logout()服务这样两个独立服务，这是“符合人类思维”的；如果你理解成登录是 PUT Session，注销是 DELETE Session，这样你只需要设计一种“Session 资源”即可满足需求，甚至以后对 Session 的其他需求，如查询登陆用户的信息，就是 GET Session 而已，其他操作如修改用户信息等都可以被这同一套设计囊括在内，这便是“抽象程度更高”带来的好处。
     想要在架构设计中合理恰当地利用统一接口，Fielding 建议系统应能做到每次请求中都包含资源的 ID，所有操作均通过资源 ID 来进行；建议每个资源都应该是自描述的消息；建议通过超文本来驱动应用状态的转移。
  6. **按需代码**（[Code-On-Demand](https://en.wikipedia.org/wiki/Code_on_demand)）
     按需代码被 Fielding 列为一条可选原则。它是指任何按照客户端（譬如浏览器）的请求，将可执行的软件程序从服务器发送到客户端的技术，按需代码赋予了客户端无需事先知道所有来自服务端的信息应该如何处理、如何运行的宽容度。举个具体例子，以前的[Java Applet](https://en.wikipedia.org/wiki/Java_applet)技术，今天的[WebAssembly](https://en.wikipedia.org/wiki/WebAssembly)等都属于典型的按需代码，蕴含着具体执行逻辑的代码是存放在服务端，只有当客户端请求了某个 Java Applet 之后，代码才会被传输并在客户端机器中运行，结束后通常也会随即在客户端中被销毁掉。将按需代码列为可选原则的原因并非是它特别难以达到，而更多是出于必要性和性价比的实际考虑。

- 学习REST API 设计规范
  - [REST API Design](http://sigcc.gitee.io/xplatform2022/#/14/rest.api.design)
    - 动词：一个好的RESTful API只允许第三方调用者使用这`四个半`HTTP动词进行数据交互
    
      ```
      GET (选择)：从服务器上`获取`一个具体的资源或者一个资源列表。
      
      POST （创建）： 在服务器上`创建`一个新的资源。
      
      PUT （更新）：以整体的方式`更新`服务器上的一个资源。
      
      PATCH （更新）：只`更新`服务器上一个资源的一个`属性`。
      
      DELETE （删除）：`删除`服务器上的一个资源。
      
      还有两个不常用的HTTP动词：
      
      HEAD ： 获取一个资源的`元数据`，如数据的哈希值或最后的更新时间。
      
      OPTIONS：获取客户端能对资源做什么操作的信息。
      ```
    
    - 版本化：必须在引入新版本API的同时保持旧版本API仍然可用
    
    - 数据设计与抽象：规划好你的API的外观要先于开发它实际的功能
    
    - API分析：持续跟踪那些正为人使用的API的版本和端点信息
    
    - API根URL：API根入口点保持尽可能的简单很重要
    
    - 端点：一个端点就是指向特定资源或资源集合的URL
    
    - 过滤器：尽可能减少那些会影响到第三方开发者的无谓限制（例如限制数据数量）
    
    - 状态码：要使用HTTP的状态码，因为它们是HTTP的标准。很多的网络设备都可以识别这些状态码
    
      - 1xx范围的状态码是保留给底层HTTP功能使用的，并且估计在你的职业生涯里面也用不着手动发送这样一个状态码出来。
      - 2xx范围的状态码是保留给成功消息使用的，你尽可能的确保服务器总发送这些状态码给用户。
      - 3xx范围的状态码是保留给重定向用的。大多数的API不会太常使用这类状态码，但是在新的超媒体样式的API中会使用更多一些。
      - 4xx范围的状态码是保留给客户端错误用的。例如，客户端提供了一些错误的数据或请求了不存在的内容。这些请求应该是幂等的，不会改变任何服务器的状态。
      - 5xx范围的状态码是保留给服务器端错误用的。这些错误常常是从底层的函数抛出来的，并且开发人员也通常没法处理。发送这类状态码的目的是确保客户端能得到一些响应。收到5xx响应后，客户端没办法知道服务器端的状态，所以这类状态码是要尽可能的避免。
    
    - 预期的返回文档：当使用不同的HTTP动词向服务器请求时，客户端需要在返回结果里面拿到一系列的信息
    
      ​	经典RESTful API定义
    
      ```
      GET /collection: 返回一系列资源对象
      GET /collection/resource: 返回单独的资源对象
      POST /collection: 返回新创建的资源对象
      PUT /collection/resource: 返回完整的资源对象
      PATCH /collection/resource: 返回完整的资源对象
      DELETE /collection/resource: 返回一个空文档
      ```
    
    - 认证：在每一个请求里，可以明确知道哪个客户端创建了请求，哪个用户提交了请求，并且提供了一种标准的访问过期机制或允许用户从客户端注销
    
    - 内容类型：返回有效的数据格式，把使用方式放在响应数据的接收头里面
    
    - 超媒体API：超媒体API概念的运作跟人们的行为类似。通过请求API的根来获得一个URL的列表，这个列表里面的每一个URL都指向一个集合，并且提供了客户端可以理解的信息来描述每一个集合
    
    - 文档
    
    - 勘误：原始的HTTP封包：设计API时，去查看原始的HTTP封包
    
  - [HTTP API 设计指南](https://github.com/cocoajin/http-api-design-ZH_CN)
  
  - https://cloud.google.com/apis/design
  
- 学习使用REST API 工具
  - https://sigcc.gitee.io/xplatform2022/#/14/restcli VS Code 插件 👍
  - [接口测试神器：Apifox - 简书](https://www.jianshu.com/p/76a981be0506)
2. 教材上凤凰架构 `bookstore案例` 微服务  实例，查看代码，理解REST API（阅读代码）
- [技术演示工程 | 凤凰架构](https://icyfenix.cn/exploration/projects/)
- 参考代码理解成熟度
3. `bookstore案例` 添加 团购、秒杀、优惠码。。等功能（大作业提交）
- 实现 `REST API`
- 参考 [Apifox](https://www.apifox.cn/web/project/977285)
  - https://github.com/gothinkster/realworld/tree/master/api
- 注意`RMM`，满足`成熟度2`以上
4. `薪水支付系统` REST API 设计与实现  （选做）
- 选做项目向老师申请单独检查，期末加分

5. 教材IPC 六种机制，各找一个例子，语言不限 （选做）

   - **管道**（Pipe）或者**具名管道**（Named Pipe）

     Linux命令中的这个竖线 “|” 就是一个管道，它的功能是前一个命令(ps aux) 的输出，作为后一个命令(grep mysql)的输入（由此也可将管道传输是单向的）：

     ```shell
     ps -aux | grep mysql
     ```

   - **信号**（Signal）

     Linux系统中为了响应各种各样的事件，提供了几十种信号，使用 `kill -l` 命令可以进行查看

     ```bash
     kill + signo + pid
     
     例如：
     kill -9 1050
     ```

   - **信号量**（Semaphore）

     [Dubbo](https://so.csdn.net/so/search?q=Dubbo&spm=1001.2101.3001.7020)支持在服务端通过在service或者method，通过executes参数设置每个方法，允许并发调用的最大线程数，即在任何时刻，只允许executes个线程同时调用该方法，超过的则抛异常返回，从而对提供者服务进行并发控制，保护资源。

     Java Semaphore：信号量
     定义：Java信号量类似于一个计数器，用于限制在任何时刻，只允许给定个线程对某个共享资源的访问。
     用法：
     获取信号量：每个线程通过执行tryAcquire（非阻塞）或者acquire（阻塞，可中断）获取一个信号量或者说是通行证，同时将信号总量减一，当数量变为0时，则后面来的线程获取则返回false或者阻塞；
     释放信号量：线程对并发资源访问完毕之后，通过调用relase方法将信号总量加1，允许一个线程访问该共享资源；

   - **共享内存**（Shared Memory）

     共享内存API函数：

     ```c
     #include <sys/ipc.h>
     #include <sys/shm.h>
     
     int shmget(key_t key, size_t size, int shmflg);
     
     void *shmat(int shmid, const void *shmaddr, int shmflg);
     
     int shmdt(const void *shmaddr);
     
     int shmclt(int shmid, int cmd, struct shmid_ds *buf);
     ```

   - **消息队列**（Message Queue）

     RabbitMQ是用Erlang实现的一个高并发高可靠AMQP消息队列服务器。

     Erlang是一门动态类型的函数式编程语言，它也是一门解释型语言，由Erlang虚拟机解释执行。从语言模型上说，Erlang是基于Actor模型的实现。在Actor模型里面，万物皆Actor，每个Actor都封装着内部状态，Actor相互之间只能通过消息传递这一种方式来进行通信。对应到Erlang里，每个Actor对应着一个Erlang进程，进程之间通过消息传递进行通信。相比共享内存，进程间通过消息传递来通信带来的直接好处就是消除了直接的锁开销(不考虑Erlang虚拟机底层实现中的锁应用)。

     AMQP(Advanced Message Queue Protocol)定义了一种消息系统规范。这个规范描述了在一个分布式的系统中各个子系统如何通过消息交互。而RabbitMQ则是AMQP的一种基于erlang的实现。AMQP将分布式系统中各个子系统隔离开来，子系统之间不再有依赖。子系统仅依赖于消息。子系统不关心消息的发送者，也不关心消息的接受者。

   - **套接字接口**（Socket）

### 【实验报告要求】

1. 作业文档、源程序 提交至 course.zucc.edu.cn
2. Markdown 完成作业，包括正确的格式
3. 实验中碰到的问题和心得体会写在此处

### 【参考资源】

1. [https://icyfenix.cn/](https://icyfenix.cn/)