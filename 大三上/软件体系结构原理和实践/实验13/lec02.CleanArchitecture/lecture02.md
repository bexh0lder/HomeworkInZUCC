## 参考书《架构整洁之道》
https://github.com/RivenZoo/CleanArchitectureCN   

第四部分 组件原则

- [组件](https://github.com/RivenZoo/CleanArchitectureCN/blob/master/chapter12/组件.md)
- [组件内聚性](https://github.com/RivenZoo/CleanArchitectureCN/blob/master/chapter13/组件内聚性.md)
- [组件耦合](https://github.com/RivenZoo/CleanArchitectureCN/blob/master/chapter14/组件耦合.md)

https://www.bookstack.cn/read/Clean-Architecture-zh/docs-README.md  Readings

第五部分 软件架构

- [第 15 章 什么是软件架构](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch15.md)
- [第 16 章 独立性](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch16.md)
- [第 17 章 划分边界](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch17.md)
- 第 18 章 边界剖析
- [第 19 章 策略与层次](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch19.md)
- [第 20 章 业务逻辑](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch20.md)
- [第 21 章 尖叫的软件架构](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch21.md)
- [第 22 章 整洁架构](https://www.bookstack.cn/read/Clean-Architecture-zh/docs-ch22.md)

- 独立于框架：这些系统的架构并不依赖某个功能丰富的框架之中的某个函数。框架可以被当成工具来使用，但不需要让系统来适应框架。
- 可被测试：这些系统的业务逻辑可以脱离 UI、数据库、Web 服务以及其他的外部元素来进行测试。
- 独立于 UI：这些系统的 UI 变更起来很容易，不需要修改其他的系统部分。例如，我们可以在不修改业务逻辑的前提下将一个系统的 UI 由 Web 界面替换成命令行界面。
- 独立于数据库：我们可以轻易将这些系统使用的 Oracle 、SQL Server 替换成 Mongo、BigTable、CouchDB 之类的数据库。因为业务逻辑与数据库之间已经完成了解耦。
- 独立于任何外部机构：这些系统的业务逻辑并不需要知道任何其他外部接口的存在。

## 软件设计的哲学 https://www.bookstack.cn/books/A-Philosophy-of-Software-Design-zh

- [2.2 Symptoms of complexity 复杂性的症状](https://www.bookstack.cn/read/A-Philosophy-of-Software-Design-zh/spilt.2.docs-ch2.md)

> 复杂性通过以下三种段落中描述的三种一般方式体现出来。这些表现形式中的每一个都使执行开发任务变得更加困难。
>
> 变更放大：复杂性的第一个征兆是，看似简单的变更需要在许多不同地方进行代码修改。例如，考虑一个包含几个页面的网站，每个页面显示带有背景色的横幅。在许多早期的网站中，颜色是在每个页面上明确指定的，如图 2.1（a）所示。为了更改此类网站的背景，开发人员可能必须手动修改每个现有页面；对于拥有数千个页面的大型网站而言，这几乎是不可能的。幸运的是，现代网站使用的方法类似于图 2.1（b），其中横幅颜色一次在中心位置指定，并且所有各个页面均引用该共享值。使用这种方法，可以通过一次修改来更改整个网站的标题颜色。

> 认知负荷：复杂性的第二个症状是认知负荷，这是指开发人员需要多少知识才能完成一项任务。较高的认知负担意味着开发人员必须花更多的时间来学习所需的信息，并且由于错过了重要的东西而导致错误的风险也更大。例如，假设 C 中的一个函数分配了内存，返回了指向该内存的指针，并假定调用者将释放该内存。这增加了使用该功能的开发人员的认知负担。如果开发人员无法释放内存，则会发生内存泄漏。如果可以对系统进行重组，以使调用者不必担心释放内存（分配内存的同一模块也负责释放内存），它将减少认知负担。

> 未知的未知:复杂性的第三个症状是，必须修改哪些代码才能完成任务，或者开发人员必须获得哪些信息才能成功地执行任务，这些都是不明显的。图 2.1(c)说明了这个问题。网站使用一个中心变量来确定横幅的背景颜色，所以它看起来很容易改变。但是，一些 Web 页面使用较暗的背景色来强调，并且在各个页面中明确指定了较暗的颜色。如果背景颜色改变，那么强调的颜色必须改变以匹配。不幸的是，开发人员不太可能意识到这一点，所以他们可能会更改中央 bannerBg 变量而不更新强调颜色。即使开发人员意识到这个问题，也不清楚哪些页面使用了强调色，因此开发人员可能必须搜索 Web 站点中的每个页面。

## Lecture

https://github.com/ivanpaulovich/TechTalks/tree/master/Ivan-Paulovich-Clean-Architecture-Essentials

[讲义](ca.intro.md) [pdf](Ivan-Paulovich-Clean-Architecture-Essentials-SSC.pdf)

- 围绕用例设计的好处是什么
- 思考业务逻辑如何独立于数据库
- 思考业务逻辑如何独立于UI

[其他参考](https://github.com/ivanpaulovich/TechTalks)

#### Demo 程序

https://github.com/ivanpaulovich/clean-architecture-manga

- or  https://gitee.com/sigcc/clean-architecture-manga

https://github.com/ivanpaulovich/clean-architecture-video-tutorials

https://github.com/kgrzybek/modular-monolith-with-ddd#5-how-to-run

https://github.com/rafaelfgx/DotNetCoreArchitecture



# GOTO 2019 • Clean Architecture with ASP.NET Core 3.0 • Jason Taylor

https://www.bilibili.com/video/BV1NV411r7gJ?from=search&seid=15995676131754279486

https://github.com/jasontaylordev/CleanArchitecture Clean Architecture Solution Template

https://jasontaylor.dev/clean-architecture-getting-started/

## Getting Started

6. https://github.com/jasontaylordev/CleanArchitecture  download zip file
2. Navigate to `src/WebUI/ClientApp` and run `npm start` to launch the front end (Angular)
3. Navigate to `src/WebUI` and run `dotnet run` to launch the back end (ASP.NET Core Web API)



## 访问浏览器

```powershell
 Now listening on: https://localhost:port  # your port      
```

## NorthwindTraders

```
cd NorthwindTraders\Src\WebUI
dotnet build
dotnet run

cd NorthwindTraders\Src\WebUI\clientApp
npm start
```

## Sqlite

```sh
# Src\WebUI\appsettings.json
  "ConnectionStrings": {
    "NorthwindDatabase": "Data Source=./Data/Northwind.db"
  }
# \Src\Persistence\DesignTimeDbContextFactoryBase.cs
optionsBuilder.UseSqlite(connectionString);
dotnet ef database update

```



## FAQ

```json
// global.json
{
 "sdk": {
  "version": "3.1.402"  //根据你的情况修改 sdk 版本
 }
}
```



## SqlServer Docker

```sh
docker pull mcr.microsoft.com/mssql/server:2019-latest
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=<YourStrong!Passw0rd>' -p 1433:1433 --name sql1 -d mcr.microsoft.com/mssql/server:2019-latest

```

