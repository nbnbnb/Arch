### Application 模块

* Application 模块主要包含 Application Service 和一些相关的类
* Application 模块依赖 Domain 模块
* 还是不依赖任何框架，纯 POJO

> 测试
* Application 模块的代码依赖外部抽象类，需要通过测试框架去 Mock 所有外部依赖，但仍然可以 100% 被单元测试

![img.png](img.png)