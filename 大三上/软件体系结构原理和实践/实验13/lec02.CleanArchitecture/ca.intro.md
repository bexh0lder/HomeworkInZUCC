



# Clean Architecture Essentials

# 整洁架构要点

[#cleanarchitecture 洁净建筑](https://dev.to/t/cleanarchitecture) [#tdd](https://dev.to/t/tdd) [#dotnet 网络](https://dev.to/t/dotnet)

# The "Software Architecture" 「软件架构」

We usually see Software Architecture descriptions like "The software architecture is an ASP.NET Web API with Entity Framework Core and SQL Server". This article explains why you should describe software by the use cases instead of layers and the frameworks it uses.

我们经常看到软件体系结构的描述，如“软件体系结构是一个具有实体框架核心和 SQL 服务器的 ASP.NET Web API”。本文解释了为什么应该按用例而不是按层和它使用的框架来描述软件。

Secondly, I will distill the Clean Architecture Principles.

其次，我将提炼出整洁架构原则。



<iframe width="710" height="399" src="https://www.youtube.com/embed/hZGF6RHrr8o" allowfullscreen="" loading="lazy" class=" fluidvids-elem" style="animation-delay: -1ms !important; animation-duration: 1ms !important; animation-iteration-count: 1 !important; background-attachment: initial !important; scroll-behavior: auto !important; transition-duration: 0s !important; transition-delay: 0s !important; box-sizing: border-box; position: absolute; top: 0px; left: 0px; width: 678.414px; height: 381.238px; border: 0px; margin: 0px;"></iframe>



## Architecture is About Usage 建筑是关于使用的

By a quick look at the following blueprint, you can easily guess it is for a church, a theater or a place that people can gather together. Mostly because there is an open space with many benches focused on the same direction, big doors so a large number of people can enter and leave quickly.

通过快速浏览下面的蓝图，你可以很容易地猜到这是一个教堂，一个剧院或一个地方，人们可以聚集在一起。主要是因为这里有一个开放的空间，许多长椅都朝向同一个方向，大门可以让大量的人快速进出。

[![Church Blueprint](https://res.cloudinary.com/practicaldev/image/fetch/s--2t4Vg_JP--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/nauj79ytnncw9o0da1fl.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--2t4Vg_JP--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/nauj79ytnncw9o0da1fl.png)

It is not a house blueprint, right?

这不是房子的蓝图，对吧？

> The challenge in software design is to scream the use cases in source code in a way that the first look tells us what the software does instead of frameworks that it is made of.
>
> 软件设计的挑战是在源代码中尖叫用例，以一种第一眼看到的方式告诉我们软件是做什么的，而不是它是由什么构成的框架。

The default approach of software development is to prioritize the frameworks and technology details. An e-commerce website will scream Web at you, Model-View-Controller or any other framework building blocks.

软件开发的默认方法是对框架和技术细节进行优先级排序。一个电子商务网站会对你、模型-视图-控制器或任何其他框架构建块尖叫。

Could we design the software in a different way? Let's introduce Clean Architecture.

我们可以用不同的方式来设计软件吗? 让我们来介绍一下清洁架构。

## Clean Architecture 整洁架构

The Clean Architecture style aims for a loosely coupled implementation focused on use cases and it is summarized as:

清洁架构风格的目标是一个松散耦合的实现，侧重于用例，总结如下:

1. It is an architecture style that the Use Cases are the central organizing structure. 用例是中心组织结构，这是一种架构风格

2. Follows the Ports and Adapters pattern.

    

   遵循端口和适配器模式

   - The implementation is guided by tests (TDD Outside-In). 实现由测试(TDD Outside-In)指导
   - Decoupled from technology details. 与技术细节脱钩

3. Follows lots of principles (Stable Abstractions Principle, Stable Dependencies Principle, SOLID and so on). 遵循许多原则(稳定抽象原则、稳定依赖原则、 SOLID 等等)

### Use Cases 用例

Use Cases are algorithms that interpret the input to generate the output data, their implementation should be closer as possible to the business vocabulary.

用例是解释输入以生成输出数据的算法，它们的实现应该尽可能接近业务词汇表。

When talking about a use case, it does not matter if it a Mobile or a Desktop application, use cases are delivery independent. The most important about use cases is how they interact with the actors.

在讨论用例时，无论它是移动应用程序还是桌面应用程序，用例都是独立交付的。关于用例最重要的是它们如何与参与者交互。

- Primary actors initiate a use case. They can be the End User, another system or a clock. 主要参与者发起一个用例。他们可以是最终用户、另一个系统或时钟
- Secondary actors are affected by use cases. 次要角色受到用例的影响

A set of use cases is used to describe software. Following the Customer primary actor on the left side, in the middle the Ticket Terminal system and the secondary actors on the right side:

一组用例用于描述软件。在左侧是“客户”主要参与者，在中间是票务终端系统，右侧是次要参与者:

[![Ticket Terminal Use Cases](https://res.cloudinary.com/practicaldev/image/fetch/s--Da-IEd35--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/q2twal2uljc0qoy7j4kl.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--Da-IEd35--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/q2twal2uljc0qoy7j4kl.png)

This article uses code snippets from sample applications and talks. If you are familiar with .NET, these GitHub projects host the full implementation:

本文使用示例应用程序和演讲中的代码片段。如果你熟悉。这些 GitHub 项目承载了完整的实现:

- 🌐 [Clean Architecture Manga 整洁架构漫画](https://github.com/ivanpaulovich/clean-architecture-manga).
- 🌐 [Todo 男名男子名](https://github.com/ivanpaulovich/todo).

Following the project structure:

遵循项目结构:

[![Structure](https://res.cloudinary.com/practicaldev/image/fetch/s--Sq8p_4E6--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/w7todun7jck4lln2nopf.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--Sq8p_4E6--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/w7todun7jck4lln2nopf.png)

Following the Register Use Case implementation:

注册用例实现之后:

```c#
public sealed class Register : IUseCase
{
    public async Task Execute(RegisterInput input)
    {
        var customer = _entityFactory.NewCustomer(input.SSN, input.Name);
        var account = _entityFactory.NewAccount(customer);

        var credit = account.Deposit(_entityFactory, input.InitialAmount);
        customer.Register(account);

        await _customerRepository.Add(customer);
        await _accountRepository.Add(account, credit);
        await _unitOfWork.Save();

        var output = new RegisterOutput(customer, account);
        _outputPort.Standard(output);
    }

    // properties and constructor ommited
}
```

The use cases are first-class objects in the application and WebApi layers. By a quick look at the use case names in the source tree, you can guess that the source code is for a Wallet software.

用例是应用程序和 WebApi 层中的第一类对象。通过快速查看源代码树中的用例名称，您可以猜测源代码是用于 Wallet 软件的。

In natural language the **RegisterUseCase** steps are:

在自然语言中 RegisterUseCase 步骤如下:

- Instantiate a Customer. 实例化一个客户
- Open up an Account then Deposit an Initial Amount. 开立一个账户，然后存入一个初始金额
- Save the data. 保存数据
- Write a message to the output port. 向输出端口写一条消息

### Ports and Adapters a.k.a Hexagonal Architecture 端口和适配器，又称六角形结构

Clean Architecture applies the Separation of Concerns Principle through the Ports and Adapters pattern. This means that the application layer exposes **Ports** (Interfaces) and **Adapters** are implemented in the infrastructure layer.

清洁架构通过端口和适配器模式应用关注点分离原则。这意味着应用程序层公开端口(接口) ，适配器在基础设施层实现。

- **Ports** can be an Input Port or an Output Port. The Input Port is called by the Primary Actors and the Output Ports are invoked by the Use Cases. 端口可以是输入端口或输出端口。输入端口由主参与者调用，输出端口由用例调用
- **Adapters** are technology-specific. 适配器是特定于技术的

[![Ports and Adapters](https://res.cloudinary.com/practicaldev/image/fetch/s--zmhXh2Wv--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/4x5rmc160cak8eip39bx.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--zmhXh2Wv--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/4x5rmc160cak8eip39bx.png)

This is the preferred architectural style for microservices. Unfortunately, I see lots of incomplete implementations and source code that do not get the most of the pattern.

这是 microservices 的首选体系结构风格。不幸的是，我看到许多不完整的实现和源代码并没有得到大部分的模式。

The previous picture shows for each dependency two implementations, one Fake (Test Double) implementation, and one Real Implementation. The purpose of it is to make possible to run the software independently of external dependencies.

前面的图片显示了每个依赖项的两个实现、一个 Fake (Test Double)实现和一个 Real 实现。它的目的是使独立于外部依赖项运行软件成为可能。

> A Fake implementation provides an illusion of external dependencies, it has the same capabilities expected from the real implementation and it runs without I/O.
>
> 假实现提供了一种外部依赖性的假象，它具有真实实现所期望的相同功能，并且在没有 i/o 的情况下运行。

The issue that I see in many codebases is the focus on implementing tests against Mocks which can only run through Unit Tests. A fake could run in the Production environment, can help the developer get feedback and it really pays back the investment.

我在许多代码库中看到的问题是，重点在于实现只能通过单元测试运行的 mock 测试。一个假的可以在产品环境中运行，可以帮助开发人员获得反馈，并且真正的回报投资。

Let's start with the Hexagonal Architecture style intent:

让我们从六角形建筑风格的意图开始:

> Develop, test and run an application in isolation of external devices. Allows a developer to get feedback after every new implementation.
>
> 在独立于外部设备的情况下开发、测试和运行应用程序。允许开发人员在每个新实现之后获得反馈。

Follow [TDD Outside-in](https://paulovich.net/clean-architecture-tdd-baby-steps/) in order to achieve the intent:

遵循 TDD 由外至内的原则，以达到目的:

1. Start the development from the Test Cases, implement the Use Cases. 从测试用例开始开发，实现用例
2. When you find a dependency, instead of implementing the real one start by creating a Fake (Test Double). 当您发现一个依赖项，而不是实现真正的依赖项开始创建一个假(测试双精度)
3. Get Feedback, make possible to run your application against the Fakes. You can even publish it to production. 获得反馈，使运行你的应用程序对付假货成为可能。你甚至可以发布它到生产
4. Implement the real adapter in isolation. 独立地实现真正的适配器
5. The last step is to create the User Interface. 最后一步是创建用户界面

### Principles 原则

Clean Architecture is full of principles, let's analyze code snippets for the different levels of Stability and Abstraction:

干净的架构充满了原则，让我们分析一下稳定性和抽象的不同层次的代码片段:

The `IAccountRepository` interface is **highly abstract**, **general** and **stable**. It does not have "implementation", it is a high-level concept and it does not have dependencies.

IAccountRepository 接口非常抽象、通用和稳定。它没有“实现”，它是一个高层次的概念，它没有依赖关系。

```c#
public interface IAccountRepository
{
    Task<IAccount> Get(Guid id);
    Task Add(IAccount account, ICredit credit);
    Task Update(IAccount account, ICredit credit);
    Task Update(IAccount account, IDebit debit);
    Task Delete(IAccount account);
}
```

The `AccountRepository` is a **Very Concrete** `sealed class`, and it is
**Very Specific** to Entity Framework and **Unstable** by implementing interfaces and depending on libraries.

AccountRepository 是一个非常具体的密封类，它非常特定于实体框架，并且通过实现接口和依赖库而不稳定。

```c#
public sealed class AccountRepository : IAccountRepository
{
    private readonly MangaContext _context;

    public AccountRepository(MangaContext context)
    {
        _context = context ??
            throw new ArgumentNullException(nameof(context));
    }

    public async Task Add(IAccount account, ICredit credit)
    {
        await _context.Accounts.AddAsync((EntityFrameworkDataAccess.Account) account);
        await _context.Credits.AddAsync((EntityFrameworkDataAccess.Credit) credit);
    }

    public async Task Delete(IAccount account)
    {
        string deleteSQL =
            @"DELETE FROM Credit WHERE AccountId = @Id;
                    DELETE FROM Debit WHERE AccountId = @Id;
                    DELETE FROM Account WHERE Id = @Id;";

        var id = new SqlParameter("@Id", account.Id);

        int affectedRows = await _context.Database.ExecuteSqlRawAsync(
            deleteSQL, id);
    }

    public async Task<IAccount> Get(Guid id)
    {
        Infrastructure.EntityFrameworkDataAccess.Account account = await _context
            .Accounts
            .Where(a => a.Id == id)
            .SingleOrDefaultAsync();

        if (account is null)
            throw new AccountNotFoundException($"The account {id} does not exist or is not processed yet.");

        var credits = _context.Credits
            .Where(e => e.AccountId == id)
            .ToList();

        var debits = _context.Debits
            .Where(e => e.AccountId == id)
            .ToList();

        account.Load(credits, debits);

        return account;
    }

    public async Task Update(IAccount account, ICredit credit)
    {
        await _context.Credits.AddAsync((EntityFrameworkDataAccess.Credit) credit);
    }

    public async Task Update(IAccount account, IDebit debit)
    {
        await _context.Debits.AddAsync((EntityFrameworkDataAccess.Debit) debit);
    }
}
```

The `RegisterRequest` class is **concrete**, by exposing getters and setters it is **inconsistent** and **specific** to the consumer.

RegisterRequest 类是具体的，通过公开 getter 和 setter，它不一致并且特定于使用者。

```c#
/// <summary>
/// Registration Request
/// </summary>
public sealed class RegisterRequest
{
    /// <summary>
    /// SSN
    /// </summary>
    [Required]
    public string SSN { get; set; }

    /// <summary>
    /// Name
    /// </summary>
    [Required]
    public string Name { get; set; }

    /// <summary>
    /// Initial Amount
    /// </summary>
    [Required]
    public decimal InitialAmount { get; set; }
}
```

The `RegisterInput` class is **concrete**, a little bit **consistent** and **less specific**.

RegisterInput 类是具体的，具有一定的一致性和较少的特定性。

```c#
public sealed class RegisterInput : IUseCaseInput
{
    public SSN SSN { get; }
    public Name Name { get; }
    public PositiveMoney InitialAmount { get; }

    public RegisterInput(
        SSN ssn,
        Name name,
        PositiveMoney initialAmount)
    {
        SSN = ssn;
        Name = name;
        InitialAmount = initialAmount;
    }
}
```

The last one is the `IAccount` interface that is **highly abstract**, **general** and **stable**.

最后一个是 IAccount 接口，它高度抽象、通用和稳定。

```c#
public interface IAccount : IAggregateRoot
{
    ICredit Deposit(IEntityFactory entityFactory, PositiveMoney amountToDeposit);
    IDebit Withdraw(IEntityFactory entityFactory, PositiveMoney amountToWithdraw);
    bool IsClosingAllowed();
    Money GetCurrentBalance();
}
```

The Clean Architecture Principles will guide you to place objects with certain properties according to the following spectrum:

整洁架构原则将指导你根据以下范围来放置具有特定属性的对象:

[![Clean Architecture Spectrum](https://res.cloudinary.com/practicaldev/image/fetch/s--i5I369c1--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/6rdi4hyfx0ebhk0eq02t.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--i5I369c1--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/6rdi4hyfx0ebhk0eq02t.png)

Another Clean Architecture representation is by concentric circles, where:

另一个整洁架构的表现形式是同心圆，其中:

- The more inner in the diagram the more the layer is stable and abstract. 图中越内部，层就越稳定和抽象
- The dependency direction goes inwards the center. 依赖项方向向内移动到中心
- Classes that change together are packaged together. 一起更改的类被打包在一起

[![Clean Architecture Layers](https://res.cloudinary.com/practicaldev/image/fetch/s--JRtU38Yg--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/oa8pcds17jnbxtw42j5d.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--JRtU38Yg--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/oa8pcds17jnbxtw42j5d.png)

Following another complete example showing that:

以下是另一个完整的例子:

- The User Interface and the Infrastructure Layers are very unstable and concrete. Highly specific to the devices they are designed for. 用户界面和基础设施层非常不稳定和具体。高度特定的设备，他们的设计
- The Core Layer is highly abstract and general. Very stable. 核心层是高度抽象和通用的，非常稳定

[![Order Ticket](https://res.cloudinary.com/practicaldev/image/fetch/s--XtGIxCym--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/4hpaw8rmbkvkq4zbzawk.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--XtGIxCym--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/4hpaw8rmbkvkq4zbzawk.png)

## Plugin Architecture 插件架构

During the software development, we will inevitably face discussions about what is the best frontend framework, best database or ORM. We should no fall into these never-ending discussions and deffer decisions like those in the earlier stages. Consider the Uncle Bob quote:

在软件开发过程中，我们不可避免地会遇到关于什么是最好的前端框架、最好的数据库或 ORM 的讨论。我们不应该陷入这种永无止境的讨论，不应该像早期阶段那样作出决定。想想鲍勃叔叔的话:

> A good architecture allows major decisions to be deferred.
>  A good architect maximizes the number of decisions not made.
>
> 一个好的架构允许重大的决策被推迟，一个好的架构师会最大化那些没有做出的决策。

One can easily find arguments to say that NoSQL is the best database, another developer can find good arguments to choose SQL Server as the database.

一个人可以很容易地找到参数说 NoSQL 是最好的数据库，另一个开发人员可以找到好的参数选择 SQL Server 作为数据库。

My answer to this is that either one should be implemented. The best option is to implement the Fake storage and move on with the project.

我的回答是，任何一个都应该实施。最好的选择是实现虚假存储，然后继续进行项目。

[![Plugin Architecture](https://res.cloudinary.com/practicaldev/image/fetch/s--uXLkqFL7--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/m9ietb03vjnhxn4k3x9z.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--uXLkqFL7--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/m9ietb03vjnhxn4k3x9z.png)

Keep in mind that you should embrace the change because in the future you will find a Cloud service that will be the better option.

请记住，您应该接受这一变化，因为将来您会发现云服务将是更好的选择。

## Ports and Adapters in details 详细说明端口和适配器

[![Alt Text](https://res.cloudinary.com/practicaldev/image/fetch/s--OS0cjvVV--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/pu5uquekwb6pgr74ctye.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--OS0cjvVV--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/pu5uquekwb6pgr74ctye.png)

# Pluggable User Interface 可插拔的用户界面

> Decoupling the User Interface is equally important than decoupling Repositories and Services but we usually don't put much effort into it. This practice leads to Controllers that look like God classes, hard to test and to maintain.
>
> 对用户界面进行分离与对存储库和服务进行分离同样重要，但我们通常不会在这方面花费太多精力。这种做法导致控制器看起来像上帝类，难以测试和维护。

Suppose that you have a GetAccountDetailsUseCase. It should display one of the following options:

假设你有一个 GetAccountDetailsUseCase，它应该显示以下选项之一:

1. The Account Details. 帐户详情
2. Not Found in case it does not exits. 没有找到，以防它不存在

The initial code should look like:

最初的代码应该是这样的:

```c#
public async Task<IActionResult> Get([FromRoute][Required] GetAccountDetailsRequest request)
{
    var input = new GetAccountDetailsInput(request.AccountId);
    try
    {
        var output = await _useCase.Execute(input);
        return Ok(output);
    }
    catch (AccountNotFoundException ex)
    {
        return NotFound(ex.Message);
    }
}
```

I wish that the Controller does not know about the output message to decide which View to return. Let's delegate these responsibility to the Presenter.

我希望 Controller 不知道输出消息来决定返回哪个视图。让我们把这些责任委托给演示者。

[![User Interface](https://res.cloudinary.com/practicaldev/image/fetch/s--yYx2U-jB--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/2f0srp30n1sqdedkeu7p.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--yYx2U-jB--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/2f0srp30n1sqdedkeu7p.png)

Both `Controller` and `UseCase` implementations uses the same `Presenter` instance. The Controller does not know about the output message, so we can have an Action that looks like:

Controller 和 UseCase 实现都使用相同的 Presenter 实例。控制器不知道输出消息，所以我们可以有一个 Action，它看起来像:

```c#
/// <summary>
/// Get an account details
/// </summary>
[HttpGet("{AccountId}", Name = "GetAccount")]
[ProducesResponseType(StatusCodes.Status200OK, Type = typeof(GetAccountDetailsResponse))]
[ProducesResponseType(StatusCodes.Status404NotFound)]
public async Task<IActionResult> Get([FromRoute][Required] GetAccountDetailsRequest request)
{
    var input = new GetAccountDetailsInput(request.AccountId);
    await _useCase.Execute(input);
    return _presenter.ViewModel;
}
```

The Presenter is in charge of translating the Value Objects into a WebApi response. Fortunately the Value Objects exposes a `ToDecimal()` or `ToString()` methods which converts it into primitive types.

Presenter 负责将 Value Objects 转换为 WebApi 响应。幸运的是，Value Objects 公开 ToDecimal ()或 ToString ()方法，这些方法将其转换为基本类型。

The `GetAccountDetailsPresenter` implements both `NotFound` and `Standard` methods mimicking the StdOut and StdErr from Unix. These methods creates the ViewModel object.

GetAccountDetailsPresenter 实现了 NotFound 和 Standard 方法，模仿 Unix 中的 StdOut 和 StdErr。这些方法创建 ViewModel 对象。

```c#
public interface IOutputPort
{
    void NotFound(string message);
    void Standard(GetAccountDetailsOutput getAccountDetailsOutput);
}

public sealed class GetAccountDetailsPresenter : IOutputPort
{
    public IActionResult ViewModel { get; private set; }

    public void NotFound(string message)
    {
        ViewModel = new NotFoundObjectResult(message);
    }

    public void Standard(GetAccountDetailsOutput output)
    {
        var transactions = new List<TransactionModel>();

        foreach (var item in output.Transactions)
        {
            var transaction = new TransactionModel(
                item.Amount.ToMoney().ToDecimal(),
                item.Description,
                item.TransactionDate);

            transactions.Add(transaction);
        }

        var response = new GetAccountDetailsResponse(
            output.AccountId,
            output.CurrentBalance.ToDecimal(),
            transactions);

        ViewModel = new OkObjectResult(response);
    }
}
```

The `GetAccountDetailsUseCase` depends on the `IOutputPort` interface and it calls `NotFound` or `Standard` method accordingly.

GetAccountDetailsUseCase 依赖于 IOutputPort 接口，它相应地调用 NotFound 或 Standard 方法。

```c#
public sealed class GetAccountDetails : IUseCase, IUseCaseV2
{
    private readonly IOutputPort _outputPort;
    private readonly IAccountRepository _accountRepository;

    public GetAccountDetails(
        IOutputPort outputPort,
        IAccountRepository accountRepository)
    {
        _outputPort = outputPort;
        _accountRepository = accountRepository;
    }

    public async Task Execute(GetAccountDetailsInput input)
    {
        IAccount account;

        try
        {
            account = await _accountRepository.Get(input.AccountId);
        }
        catch (AccountNotFoundException ex)
        {
            _outputPort.NotFound(ex.Message);
            return;
        }

        var output = new GetAccountDetailsOutput(account);
        _outputPort.Standard(output);
    }
}
```

## Adding a Mediator 添加一个调解器

```c#
/// <summary>
/// Get an account details
/// </summary>
[HttpGet("{AccountId}", Name = "GetAccount")]
[ProducesResponseType(StatusCodes.Status200OK, Type = typeof(GetAccountDetailsResponse))]
[ProducesResponseType(StatusCodes.Status404NotFound)]
public async Task<IActionResult> Get([FromRoute][Required] GetAccountDetailsRequest request)
{
    var input = new GetAccountDetailsInput(request.AccountId);
    await _mediator.PublishAsync(input);
    return _presenter.ViewModel;
}
```

You notice that I added the `mediator` instance to decouple the Controller and the `UseCase`, it means that the Controller will produce messages, give them to the mediator then the mediator will deliver the message to the appropriate UseCase. Of course, you can invoke the UseCase directly, verify whats works best for your project.

您注意到，我添加了中介实例来解耦 Controller 和 UseCase，这意味着 Controller 将生成消息，将它们提供给中介，然后中介将把消息传递给适当的 UseCase。当然，您可以直接调用 UseCase，验证什么对您的项目最有效。

By definition the Output messages given by the UseCase are a consistent and immutable objects, it uses Value Objects to describe the business state.

根据定义，UseCase 给出的 Output 消息是一致的、不可变的对象，它使用 Value Objects 来描述业务状态。

# Evolutionary Architecture 进化架构

All developers will build a Task List app at least once. As a .NET Developer we always start thinking by creating an WebApi first then dig into the business details, for this sample application I wanted to proceed differently.

所有的开发者都会至少构建一次任务列表应用程序。作为一个。NET Developer 我们总是首先考虑创建一个 WebApi，然后深入研究业务细节，对于这个示例应用程序，我希望以不同的方式进行。

[![Todo Use Cases](https://res.cloudinary.com/practicaldev/image/fetch/s--oCG36zQ---/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/p0djcfppl8rg337xecvm.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--oCG36zQ---/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/p0djcfppl8rg337xecvm.png)

I started the development from the Unit Tests, implementing the Use Cases independently and for each dependency creating a Fake. After a while I decided to create a SQL Server database because that is what .NET Developers do, we spin up a SQL Server so we can persist taks ;)

我从单元测试开始开发，独立地实现用例，并为每个依赖项创建一个赝品。过了一会儿，我决定创建一个 sqlserver 数据库，因为这就是。NET 开发人员这样做，我们启动一个 SQL Server，这样我们就可以持久化;)

To make it short, at the end a Console UI and a Storage to GitHub gist was good enough for me.

长话短说，最后一个控制台 UI 和一个到 GitHub gist 的存储对我来说已经足够了。

[![Evolutionary Architecture](https://res.cloudinary.com/practicaldev/image/fetch/s--xnmXLPRX--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/16lvcpag4bddy0dcgcih.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--xnmXLPRX--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/16lvcpag4bddy0dcgcih.png)

# Wrapping Up 包装

- Clean Architecture is about usage and the use cases are the central organizing principle. 清洁架构是关于使用的，用例是中心组织原则
- Use cases implementation are guided by tests. 用例实现是由测试引导的
- The User Interface and Persistence are designed to fulfill the core needs (not the opposite!). 用户界面和持久性的设计是为了满足核心需求(而不是相反!)
- Defer decisions by implementing the simplest component first. 通过首先实现最简单的组件来推迟决策

The [source code is on GitHub](https://github.com/ivanpaulovich/clean-architecture-manga) and it is updated frequently with new videos and pull-requests. Check it out!

源代码在 GitHub 上，并且经常更新新的视频和拉取请求！