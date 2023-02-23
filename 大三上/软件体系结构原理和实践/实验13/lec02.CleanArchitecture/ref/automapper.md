# AutoMapper快速上手

 **一.什么是AutoMapper**

 AutoMapper是一个简单的对象映射框架(OOM),对象映射原理是把一种类型的输入对象转换为不同类型的输出对象,通俗讲就是通过一些约束讲一种类型中数据自动映射到另一数据类型中

**二.AutoMapper的好处**

 以前的时候我们将DTO对象转换为Model对象或者将Model对象转换为DTO对象时,我们必须将每一个属性都手动映射

```csharp
 //源数据对象
var source = new Source
{
     Id = 1,
     Name = "张三"
};
//目标数据对象
var target = new Target
{
     Id = source.Id,
     Name = source.Name
};
```

   这样情况如果属性过多会导致浪费大量的时间在对象转换中,于是各种OOM框架应时而生,而AutoMapper便是其一,AutoMapper其优势在于易用性与强大型,AutoMapper除了基本的对象映射之外还可以对进行配置各种需要的映射关系(不同属性名称之间的映射，映射之间的类型转换，支持嵌套映射，支持泛型等),AutoMapper最新版本为6.2.2,而AutoMapper在6.2.0版本中又发生了一次巨大改变,使得开发者能更加简单的使用AutoMapper进行工作。下面是AutoMapper的一个简单示例。

```csharp
//初始化AutoMapper
Mapper.Initialize(config => { });
//源数据对象
var source = new Source
{
    Id = 1,
    Name = "张三"
};
//映射
var target = Mapper.Map<Source, Target>(source);
Console.WriteLine(target.Id);
Console.WriteLine(target.Name);
```

![img](./auto_images/jk0fa3dg4b.png?imageView2/2/w/1620)

可以看到我们只需要简单的使用便可以完成两个对象之间的属性映射,开发中极大的省去了枯燥的属性转换.

**三,AutoMapper的性能**

  AutoMapper做为一个被广泛使用的OOM框架,其底层使用的是表达式树来进行映射,所以在性能方面还是比较突出的,下面是我做的一个性能测试

```csharp
//初始化AutoMapper
Mapper.Initialize(config => { });
//源数据对象
IList<Source> sourceList = new List<Source>();
for (int i = 0; i < 10000; i++)
{//创建1万个对象进行映射
     sourceList.Add(new Source
    {
        Id = i,
        Name = "张三" + i
    });
}
Stopwatch watch = new Stopwatch();
watch.Start();
//映射
var targetList = Mapper.Map<IList<Source>, IList<Target>>(sourceList);
watch.Stop();
Console.WriteLine("映射1万个对象的时间为:"+watch.ElapsedMilliseconds);                    
```

![img](./auto_images/5pdpu3roct.png?imageView2/2/w/1620)

   可以看到映射了1万个对象只花费了191毫秒.虽然说对象属性越多映射所下所花费的时间会越长,但是这个性能已经极为OK了

**四.AutoMaper的使用**

AutoMapper作为一个易用性极强并且简便的OOM，在使用方面做到了非常简便，尤其在6.2.0版本之后，基本不需要做什么配置，即可完成映射。这里也是以6.2.0版本来做示例

1. **1.   引入AutoMapper**

AutoMapper类库直接可以从NuGit包中引用

```csharp
install-package automapper -v 6.2.0
```

**2.初始化** 

 　映射类型

```csharp
/// <summary>
/// 源类型
/// </summary>
class Source
{ 
    public int Id { get; set; }
    public String SName { get; set; }
    public String  DateTime { get; set; }
    public int Age { get; set; }

}
/// <summary>
/// 目标类型
/// </summary>
class Target
{
    public int Id { get; set; }
    public String TName { get; set; }
    public String DateTime { get; set; }
    public int Age { get; set; }
}
/// <summary>
/// 源类型
/// </summary>
class Source
{ 
    public int Id { get; set; }
    public String SName { get; set; }
    public String  DateTime { get; set; }
    public int Age { get; set; }

}
/// <summary>
/// 目标类型
/// </summary>
class Target
{
    public int Id { get; set; }
    public String TName { get; set; }
    public String DateTime { get; set; }
    public int Age { get; set; }
}
```

 **Mapper.Initialize()**方法执行AutoMapper的初始化操作,此操作在一个应用程序中只能执行一次.在初始化方法中可以初始化映射中的任何操作

**注意:**6.20版本之前必须在在配置中设置**CreateMap**才能映射,**6.2.0**版本开始如果不进行配置其它则可以省略,但是如果省略**CreateMap**后默认会以**Target**类型为基准,如果Target类型有未映射的属性,就会出现异常,加上CreateMap后就无异常,所以推荐手动加上映射配置,以防异常

**2.映射**

```csharp
var source = new Source { Id = 1, SName = "张三", Age = 11, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source, Target>(source);
Console.WriteLine(target.Id);
Console.WriteLine(target.TName);
Console.WriteLine(target.Age);
Console.WriteLine(target.DateTime);
```

​    Mapper.Map<S,T> 执行映射方法  S为源类型,T为目标类型,参数为源类型，

![img](./auto_images/pjglkulw33.png?imageView2/2/w/1620)

其中属性TName因为没找到同名属性，所以并没有映射成功，另外发现源类型中DateTime字符串也成功映射成为目标类型的DateTime，自动类型转换。自动类型转换是6.2.0版本才加入的，在之前需要在配置中进行配置

**3.反向映射**

 在AutoMapper中有一个方法配置是可以配置可以反向映射的, ReverseMap().

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
    //Initialize方法为AutoMapper初始化方法
    //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
    //ReverseMap方法可以实现反向映射
    config.CreateMap<Source, Target>().ReverseMap();
});
var source = new Source { Id = 1, SName = "张三", Age = 11, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source, Target>(source);
Console.WriteLine(target.Id);
Console.WriteLine(target.TName);
Console.WriteLine(target.Age);
Console.WriteLine(target.DateTime);
Console.WriteLine();
//反向映射
var reverSource = Mapper.Map<Target, Source>(target);
Console.WriteLine(reverSource.Id);
Console.WriteLine(reverSource.SName);
Console.WriteLine(reverSource.Age);
Console.WriteLine(reverSource.DateTime);
```

​    **注意:ReverseMap**也可以不加,但是那样就跟没有配置一样,所以在目标类型中属性没有全部映射完毕情况会出异常,所以还是建议手动配置

**4属性名称不一致之间的映射**

​    属性名称不一致之间的映射需要在初始化时进行配置相应属性名称

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
    //Initialize方法为AutoMapper初始化方法
    //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
    config.CreateMap<Source, Target>()
    //ForMember可以配置一系列的配置信息
    //参数1:目标类型属性的表达式
    //参数2:执行操作的选择   AutoMapper定义了一系列的配置选择供开发者使用
    .ForMember(dest=>dest.TName,options=>options.MapFrom(sou=>sou.SName));
});
var source = new Source { Id = 1, Age = 11, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source, Target>(source);
Console.WriteLine(target.Id);
Console.WriteLine(target.TName);
Console.WriteLine(target.Age);
Console.WriteLine(target.DateTime);
```

此时目标类型的TName即可映射成功

![img](./auto_images/v6cjvwoslv.png?imageView2/2/w/1620)

**5.空值替换**

​    AutoMapper中允许设置一个备用值来代替源类型中的空值

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
    //Initialize方法为AutoMapper初始化方法
    //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
    config.CreateMap<Source, Target>()
    //ForMember可以配置一系列的配置信息
    //参数1:目标类型属性的表达式
    //参数2:执行操作的选择   AutoMapper定义了一系列的配置选择供开发者使用
    .ForMember(dest => dest.TName, options => options.MapFrom(sou => sou.SName))
    //NullSubstitute是空值替换的配置操作
    .ForMember(dest => dest.TName, options => options.NullSubstitute("备用值"));
});
```

 执行映射

```csharp
var source = new Source { Id = 1, Age = 11, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source, Target>(source);
```

![img](./auto_images/fs1q8lmna1.png?imageView2/2/w/1620)

**6.映射之前与之后操作**

​    AutoMapper可以在映射前后定义一系列的逻辑操作,,使用到的两个方法是**BeforeMap**和**AfterMap**

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
    //Initialize方法为AutoMapper初始化方法
    //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
    config.CreateMap<Source, Target>()
    //ForMember可以配置一系列的配置信息
    //参数1:目标类型属性的表达式
    //参数2:执行操作的选择   AutoMapper定义了一系列的配置选择供开发者使用
    .ForMember(dest => dest.TName, options => options.MapFrom(sou => sou.SName))//映射之前操作【将源类型Age值+10】
     //BeforMap和AfterMap需要一个Action<TSource,TDestination>参数
     .BeforeMap((sou, dest) =>
     {
          sou.Age += 10;
     })
     //映射之后操作【将目标类型Age值+10】
     .AfterMap((sou, dest) =>
     {
          dest.Age += 10;
      });
});                          
```

 执行映射

```csharp
var source = new Source { Id = 1, Age = 11, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source, Target>(source);
Console.WriteLine("Id:"+target.Id);
Console.WriteLine("TName:"+target.TName);
Console.WriteLine("Age:"+target.Age);
Console.WriteLine("DateTime"+target.DateTime);
```

![img](./auto_images/rlq0mhw1ys.png?imageView2/2/w/1620)

**7.条件映射**

AutoMapper中可以设置条件映射,即满足指定条件才允许映射,条件映射使用的方法是**Condition**

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
    //Initialize方法为AutoMapper初始化方法
    //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
    config.CreateMap<Source, Target>()
    //设置属性的映射条件【Age不大于10即不映射】
    .ForMember(dest => dest.Age, options => options.Condition(sou => sou.Age > 10));
    });
var source = new Source { Id = 1, Age = 10, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source, Target>(source);
```

可以看到Age属性并没有进行映射

![img](./auto_images/xzxml2ugix.png?imageView2/2/w/1620)

**8.泛型类型映射**

AutoMapper中可以直接支持开放泛型类型映射,所以不需要创建封闭泛型类型

映射实体模型

```csharp
    /// <summary>
    /// 源类型
    /// </summary>
    class Source<T>
    {
        public int Id { get; set; }
        public String SName { get; set; }
        public T SPro { get; set; }
        public String  DateTime { get; set; }
        public int Age { get; set; }

    }
    /// <summary>
    /// 目标类型
    /// </summary>
    class Target<T>
    {
        public int Id { get; set; }
        public String TName { get; set; }
        public T TPro { get; set; }
        public String DateTime { get; set; }
        public int Age { get; set; }

   }
```

 映射配置

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
   //Initialize方法为AutoMapper初始化方法
   //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】//泛型类型的映射,AutoMapper允许直接支持开放类型
   config.CreateMap(typeof(Source<>), typeof(Target<>))
      //泛型中配置条件【由于是开放类型，所以只能使用属性名称字符串】
      .ForMember("TName", options => options.MapFrom("SName"))
      //空值替换
      .ForMember("TName", options => options.NullSubstitute("备用值"))
      .ForMember("TPro", option => option.MapFrom("SPro"));
});
var source = new Source<String> { Id = 1,SPro="1", Age = 10, DateTime = "2018-4-23" };
//执行映射
var target = Mapper.Map<Source<String>, Target<int>>(source);
Console.WriteLine("Id:"+target.Id);
Console.WriteLine("TPro:"+target.TPro);
Console.WriteLine("TName:"+target.TName);
Console.WriteLine("Age:"+target.Age);
Console.WriteLine("DateTime"+target.DateTime);
```

![img](./auto_images/1wks8m3w9w.png?imageView2/2/w/1620)

并且可以看到,AutoMapper泛型类型映射时支持类型转换

**9.嵌套类型映射**

 映射实体模型

```csharp
  /// <summary>
    /// 源类型
    /// </summary>
    class Source
    {
        public int Id { get; set; }
        public InnerSource InnerSource { get; set; }

    }
    /// <summary>
    /// 目标类型
    /// </summary>
    class Target
    {
        public int Id { get; set; }
        //例1
        //public InnerSource InnerTarget { get; set; }
        //例2
        //public InnerTarget InnerTarget { get; set; }

    }
    /// <summary>
    /// 内部源类型
    /// </summary>
    class InnerSource
    {
        public int InnerId { get; set; }
        public String InnerName { get; set; }
    }
    /// <summary>
    /// 内部目标类型
    /// </summary>
    class InnerTarget
    {
        public int InnerId { get; set; }
        public String InnerName { get; set; }
    }
```

 AutoMapper嵌套类型映射其实就是相当于2对类型的映射.所以配置跟前面配置是一样的.

如果目标类型中的嵌套类型跟源类型中的嵌套类型是同一类型,如目标类型中例1,那么就直接可以映射,

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
     //Initialize方法为AutoMapper初始化方法
     //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
     config.CreateMap<Source, Target>()
     .ForMember(dest => dest.InnerTarget, options => options.MapFrom(sou => sou.InnerSource));
});
var source = new Source { Id = 1,InnerSource = new InnerSource { InnerId=11,InnerName="内部名称"} };
//执行映射
var target = Mapper.Map<Source, Target>(source);
Console.WriteLine("Id:"+target.Id);         Console.WriteLine("InnerTarget.Id:"+target.InnerTarget.InnerId);
Console.WriteLine("InnerTarget.InnerName:" + target.InnerTarget.InnerName);
```

  如果目标类型中嵌套类型与源类型的嵌套类型不是同一类型,如例2,只需配置一下嵌套类型的映射即可.

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
     //Initialize方法为AutoMapper初始化方法
     //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
     //ReverseMap方法可以实现反向映射
     //配置嵌套类型映射
     config.CreateMap<InnerSource, InnerTarget > ();
     config.CreateMap<Source, Target>()
               .ForMember(dest => dest.InnerTarget, options => options.MapFrom(sou => sou.InnerSource));
});
```

 **注意**:嵌套类型的映射也可以不配置,但是不配置如果目标类型属性没有全部映射完成,也是会报异常.所以并不推荐

**10.继承映射**

 映射实体模型

```csharp
  /// <summary>
    /// 源类型
    /// </summary>
    class Source
    {
        public int Id { get; set; }
        public String Name { get; set; }

    }
    /// <summary>
    /// 目标类型
    /// </summary>
    class Target
    {
        public int Id { get; set; }
        public String Name { get; set; }

    }
    /// <summary>
    /// 子类源类型
    /// </summary>
    class SonSource:Source
    {
        public int SonId { get; set; }
        public String SonName { get; set; }
    }
    /// <summary>
    /// 子类目标类型
    /// </summary>
    class SonTarget:Target
    {
        public int SonId { get; set; }
        public String SonName { get; set; }
    }
```

 AutoMapper支持以多态形式继承映射，继承映射以Include(父填子) 或InculdeBase(子填父)。

```csharp
//初始化AutoMapper
Mapper.Initialize(config =>
{
      //Initialize方法为AutoMapper初始化方法
      //6.2.0版本后如果不需要额外的配置,则CreateMap可省略,但6.2.0版本之前不可省略【不过不建议省略】
      config.CreateMap<Source, Target>()
       //配置派生类的映射【此处是父填子示例，子填父也同理】
                .Include<SonSource, SonTarget>();
        //配置映射【派生类必须配置】
        config.CreateMap<SonSource, SonTarget>();
});
```

 执行映射

```csharp
//源对象
IList<Source> sourceList = new List<Source>
{
    new Source{Id=1,Name="Source1"},
    new SonSource{SonId=1,SonName="SonSource1",Id=2,Name="Source2"},
     new Source{Id=3,Name="Source3"},
};
     //映射
     var targetList = Mapper.Map<IList<Source>, IList<Target>>(sourceList);
     foreach (var item in targetList)
      {
           //转换为子类
           SonTarget son = item as SonTarget;
           if (null != son) 
                  Console.WriteLine("编号:" + son.Id + "名称:" + son.Name + "子类编号:" + son.SonId + "子类名称:" + son.SonName);
            else
                  Console.WriteLine("编号:" + item.Id + "名称:" + item.Name);
}
```

![img](./auto_images/w0wx9q2cvz.png?imageView2/2/w/1620)

**11.无须配置的Helper类**

 此类只能简单的进行配置，无法实现复杂变化，不过一般使用则无需配置【此类出处：https://home.cnblogs.com/u/xiadao521/】

```csharp
/// <summary>
    /// 对象映射
    /// </summary>
    public static class Extensions
    {
        /// <summary>
        /// 同步锁
        /// </summary>
        private static readonly object Sync = new object();

        /// <summary>
        /// 将源对象映射到目标对象
        /// </summary>
        /// <typeparam name="TSource">源类型</typeparam>
        /// <typeparam name="TDestination">目标类型</typeparam>
        /// <param name="source">源对象</param>
        /// <param name="destination">目标对象</param>
        public static TDestination MapTo<TSource, TDestination>(this TSource source, TDestination destination)
        {
            return MapTo<TDestination>(source, destination);
        }

        /// <summary>
        /// 将源对象映射到目标对象
        /// </summary>
        /// <typeparam name="TDestination">目标类型</typeparam>
        /// <param name="source">源对象</param>
        public static TDestination MapTo<TDestination>(this object source) where TDestination : new()
        {
            return MapTo(source, new TDestination());
        }

        /// <summary>
        /// 将源对象映射到目标对象
        /// </summary>
        private static TDestination MapTo<TDestination>(object source, TDestination destination)
        {
            if (source == null)
                throw new ArgumentNullException(nameof(source));
            if (destination == null)
                throw new ArgumentNullException(nameof(destination));
            var sourceType = GetType(source);
            var destinationType = GetType(destination);
            var map = GetMap(sourceType, destinationType);
            if (map != null)
                return Mapper.Map(source, destination);
            lock (Sync)
            {
                map = GetMap(sourceType, destinationType);
                if (map != null)
                    return Mapper.Map(source, destination);
                InitMaps(sourceType, destinationType);
            }
            return Mapper.Map(source, destination);
        }

        /// <summary>
        /// 获取类型
        /// </summary>
        private static Type GetType(object obj)
        {
            var type = obj.GetType();
            if ((obj is System.Collections.IEnumerable) == false)
                return type;
            if (type.IsArray)
                return type.GetElementType();
            var genericArgumentsTypes = type.GetTypeInfo().GetGenericArguments();
            if (genericArgumentsTypes == null || genericArgumentsTypes.Length == 0)
                throw new ArgumentException("泛型类型参数不能为空");
            return genericArgumentsTypes[0];
        }

        /// <summary>
        /// 获取映射配置
        /// </summary>
        private static TypeMap GetMap(Type sourceType, Type destinationType)
        {
            try
            {
                return Mapper.Configuration.FindTypeMapFor(sourceType, destinationType);
            }
            catch (InvalidOperationException)
            {
                lock (Sync)
                {
                    try
                    {
                        return Mapper.Configuration.FindTypeMapFor(sourceType, destinationType);
                    }
                    catch (InvalidOperationException)
                    {
                        InitMaps(sourceType, destinationType);
                    }
                    return Mapper.Configuration.FindTypeMapFor(sourceType, destinationType);
                }
            }
        }

        /// <summary>
        /// 初始化映射配置
        /// </summary>
        private static void InitMaps(Type sourceType, Type destinationType)
        {
            try
            {
                var maps = Mapper.Configuration.GetAllTypeMaps();
                Mapper.Initialize(config => {
                    ClearConfig();
                    foreach (var item in maps)
                        config.CreateMap(item.SourceType, item.DestinationType);
                    config.CreateMap(sourceType, destinationType);
                });
            }
            catch (InvalidOperationException)
            {
                Mapper.Initialize(config => {
                    config.CreateMap(sourceType, destinationType);
                });
            }
        }

        /// <summary>
        /// 清空配置
        /// </summary>
        private static void ClearConfig()
        {
            var typeMapper = typeof(Mapper).GetTypeInfo();
            var configuration = typeMapper.GetDeclaredField("_configuration");
            configuration.SetValue(null, null, BindingFlags.Static, null, CultureInfo.CurrentCulture);
        }

        /// <summary>
        /// 将源集合映射到目标集合
        /// </summary>
        /// <typeparam name="TDestination">目标元素类型,范例：Sample,不要加List</typeparam>
        /// <param name="source">源集合</param>
        public static List<TDestination> MapToList<TDestination>(this System.Collections.IEnumerable source)
        {
            return MapTo<List<TDestination>>(source);
        }
    }
```

 

https://cloud.tencent.com/developer/article/1395087