### 常见的 DP 的使用场景包括：
> 有格式限制的 String：比如 Name，PhoneNumber，OrderNumber，ZipCode，Address 等

> 有限制的 Integer：比如 OrderId（>0），Percentage（0-100%），Quantity（>=0）等

> 可枚举的 int ：比如 Status（一般不用 Enum 因为反序列化问题）

> Double 或 BigDecimal：一般用到的 Double 或 BigDecimal 都是有业务含义的，比如 Temperature、Money、Amount、ExchangeRate、Rating 等

> 复杂的数据结构：比如 Map<String, List<Integer>> 等，尽量能把 Map 的所有操作包装掉，仅暴露必要行为