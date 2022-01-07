### Infrastructure 模块

* Infrastructure 模块包含了 Persistence、Messaging、External 等模块
* 比如：Persistence 模块包含数据库 DAO 的实现，包含 Data Object、ORM Mapper、Entity 到 DO 的转化类等

* Persistence 模块要依赖具体的 ORM 类库，比如 MyBatis
* 如果需要用 Spring-Mybatis 提供的注解方案，则需要依赖 Spring

> 测试
* Infrastructure 的每个模块的代码相对独立，接口数量比较少，相对比较容易写单测
* 但是由于依赖了外部 I/O，速度上不可能很快，但好在模块的变动不会很频繁，属于一劳永逸

![img.png](img.png)