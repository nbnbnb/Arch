### Domain Primitive 的定义

让我们重新来定义一下 Domain Primitive ：Domain Primitive 是一个在特定领域里，拥有精准定义的、可自我验证的、拥有行为的 Value Object 

* DP 是一个传统意义上的 Value Object，拥有 Immutable 的特性

* DP 是一个完整的概念整体，拥有精准定义

* DP 使用业务域中的原生语言

* DP 可以是业务域的最小组成部分、也可以构建复杂组合

> 使用 Domain Primitive 的三原则
* 让隐性的概念显性化

* 让隐性的上下文显性化

* 封装多对象行为

> Domain Primitive 和 DDD 里 Value Object 的区别

Domain Primitive 是 Value Object 的进阶版，在原始 VO 的基础上要求每个 DP 拥有概念的整体，而不仅仅是值对象

Domain Primitive 在 VO 的 Immutable 基础上增加了 Validity 和行为

当然 Domain Primitive 同样的要求无副作用（side-effect free）

* * * 

### 什么情况下应该用 Domain Primitive

> 常见的 DP 的使用场景包括：

* 有格式限制的 String：比如 Name，PhoneNumber，OrderNumber，ZipCode，Address 等

* 有逻辑限制的 Integer：比如 OrderId（>0），Percentage（0-100%），Quantity（>=0）等

* 可枚举的 int ：比如 Status（一般不用 Enum 因为反序列化问题）

* Double 或 BigDecimal：一般用到的 Double 或 BigDecimal 都是有业务含义的，比如 Temperature、Money、Amount、ExchangeRate、Rating 等

* 复杂的数据结构：比如 Map<String, List<Integer>> 等，尽量能把 Map 的所有操作包装掉，仅暴露必要行为
