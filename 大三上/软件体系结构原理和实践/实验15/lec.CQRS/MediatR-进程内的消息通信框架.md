# MediatR-进程内的消息通信框架

2019-09-23阅读 3210

MediatR是一款进程内的消息订阅、发布框架，提供了Send方法用于发布到单个处理程序、Publish方法发布到多个处理程序，使用起来非常方便。目前支持 .NET Framework4.5、.NET Stardand1.3、.NET Stardand2.0等版本，可跨平台使用。

## 使用MediatR

要在项目中使用MediatR，首先要添加引用：nuget install MediatR

在使用MediatR的时候，需要设置一个容器来实例化所有的Handler，因此我们需要与依赖注入框架结合使用，MediatR支持目前主流的依赖注入框架，例如Autofac等，也可以直接使用 .NET Core 的依赖注入框架。

如果使用 .net core的依赖注入，将MediatR添加到容器将会很方便：

```javascript
services.AddMediatR(typeof(Program).Assembly);
```

如果是多个程序集，如果是多个程序集：

```javascript
services.AddMediatR(typeof(Program).Assembly, typeof(HelloWorld).Assembly);
```

MediatR有两种消息处理模式：

- Request/Response模式：Message将被单个Handler处理，可以有返回值
- Notifictaion模式：Message可以被多个Handler处理，无返回值

## Request/Response模式

使用起来很简单，首先定义Request消息，方法如下：

```javascript
public class Ping : IRequest<string> { }
```

然后，定义它的处理程序：

```javascript
public class PingHandler : IRequestHandler<Ping, string> {
    public Task<string> Handle(Ping request, CancellationToken cancellationToken) {
        return Task.FromResult("Pong");
    }
}
```

这样就可以了，我们在控制台发送Ping消息：

```javascript
var response = await mediator.Send(new Ping());
Console.WriteLine(response); // "Pong"
```

### 无返回值的消息

当处理消息不需要返回值时，我们应该使用如下方式定义消息：

```javascript
public class Ping : IRequest { }
```

对应的消息处理程序如下：

```javascript
public class PingHandler: AsyncRequestHandler<Ping> {
    protected override Task Handle(Ping request, CancellationToken cancellationToken) {
        // todo...
    }
}
```

### 同步的消息处理

默认情况下消息的处理都是异步的（返回值为Task对象），如果你想要同步执行消息，需要按下面的方式定义消息处理程序：

```javascript
public class PingHandler : RequestHandler<Ping, string> {
    protected override string Handle(Ping request) {
        return "Pong";
    }
}
```

这种模式符合CQRS中Command的处理方式，一个Command只能有一个Handler，因此，在使用CQRS时可以参考。

## Notification 模式

Notification模式将消息发布给多个处理程序，消息的处理没有返回值。

消息的定义：

```javascript
public class HelloWorld : INotification
{
}
```

多个处理程序：

```javascript
public class CNReply : INotificationHandler<HelloWorld>
{
    public Task Handle(HelloWorld notification, CancellationToken cancellationToken)
    {
        Console.WriteLine($"CN Reply: Hello from CN");
        return Task.CompletedTask;
    }
}

public class USReply : INotificationHandler<HelloWorld>
{
    public Task Handle(HelloWorld notification, CancellationToken cancellationToken)
    {
        Console.WriteLine($"US Reply: Hello from US");
        return Task.CompletedTask;
    }
}
```

然后通过Publish方法发布消息：

```javascript
await mediator.Publish(helloworld);
```

### 发布策略

默认情况下，MediatR的消息发布是一个一个执行的，即便是返回Task的情况，也是使用await等待上一个执行完成后才进行下一个的调用。如果需要使用并行的方法进行调用，可以进行定制，具体可参考官方示例：[MediatR.Examples.PublishStrategies](https://github.com/jbogard/MediatR/tree/master/samples/MediatR.Examples.PublishStrategies)

## 多态支持

MediatR消息处理程序是支持逆变的，例如我们可以定义一个消息监听程序，监听所有发布的Notification：

```javascript
public class MessageListener : INotificationHandler<INotification>
{
    public Task Handle(INotification notification, CancellationToken cancellationToken)
    {
        Console.WriteLine($"接收到新的消息：{notification.GetType()}");

        return Task.CompletedTask;
    }
}
```

对于IRequest类型的消息，此种方式未验证成功。如果可以的话，倒是可以做一个无处理程序的消息的监听，也是挺好玩的。

## 异步

对于MediatR来说，无论是发送IRequest类型消息，还是发布INotification类型消息，都是异步的。这里需要特别留意，即使你使用的是同步的消息处理程序，对于消息发布来说，都是异步的，与你的处理程序是同步或异步无关。

## 参考文档

- [MediatR官方文档](https://github.com/jbogard/MediatR/wiki)