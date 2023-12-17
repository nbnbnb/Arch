### Repository 代码规范

#### 接口规范

1. 接口名称不应该使用底层实现的语法：我们常见的 insert、select、update、delete 都属于 SQL 语法，使用这几个词相当于和 DB 底层实现做了绑定。相反，`我们应该把 Repository 当成一个中性的类似 Collection 的接口，使用语法如 find、save、remove`。在这里特别需要指出的是区分 insert/add 和 update 本身也是一种和底层强绑定的逻辑，一些储存如缓存实际上不存在 insert和 update 的差异，在这个 case 里，使用中性的 save 接口，然后在具体实现上根据情况调用 DAO 的 insert 或 update 接口。


2. 出参入参不应该使用底层数据格式：`需要记得的是 Repository 操作的是  Entity 对象（实际上应该是 Aggregate Root），而不应该直接操作底层的 DO` 。更近一步，Repository 接口实际上应该存在于 Domain 层，根本看不到 DO 的实现。这个也是为了避免底层实现逻辑渗透到业务代码中的强保障。


3. 应该避免所谓的 “通用” Repository 模式：很多 ORM 框架都提供一个“通用”的 Repository 接口，然后框架通过注解自动实现接口，比较典型的例子是Spring Data、Entity Framework 等，这种框架的好处是在简单场景下很容易通过配置实现，但是坏处是基本上无扩展的可能性（比如加定制缓存逻辑），在未来有可能还是会被推翻重做。当然，这里避免通用不代表不能有基础接口和通用的帮助类。

> 这里需要再次强调的是 Repository 的接口是在 Domain 层，但是实现类是在Infrastructure 层

#### 通用套路

> 所有的 Entity/Aggregate 会被转化为 DO，然后根据业务场景，调用相应的 DAO方法进行操作，事后如果需要则把 DO 转换回 Entity。代码基本很简单，唯一需要注意的是 save 方法，需要根据 Aggregate 的 ID 是否存在且大于 0 来判断一个Aggregate 是否需要更新还是插入。

* * *   

由于 Repository 接口规范的限制，让调用方仅能操作 Aggregate Root，而无法单独针对某个非 Aggregate Root 的 Entity 直接操作。这个和直接调用 DAO 的方式很不一样。
这个的解决方案是需要能识别到底哪些 Entity 有变更，并且只针对那些变更过的 Entity 做操作，就需要加上变更追踪的能力。换一句话说就是原来很多人为判断的代码逻辑，现在可以通过变更追踪来自动实现，让使用方真正只关心 Aggregate 的操作。

> 业界有两个主流的变更追踪方案：

* 基于 Snapshot 的方案：当数据从DB里取出来后，在内存中保存一份 snapshot，然后在数据写入时和 snapshot 比较。常见的实现如 Hibernate。


* 基于 Proxy 的方案：当数据从 DB 里取出来后，通过 weaving 的方式将所有 setter 都增加一个切面来判断 setter 是否被调用以及值是否变更，如果变更则标记为 Dirty。在保存时根据 Dirty 判断是否需要更新。常见的实现如 Entity Framework。

Snapshot 方案的好处是比较简单，成本在于每次保存时全量 Diff 的操作（一般用 Reflection ），以及保存 Snapshot 的内存消耗。


Proxy 方案的好处是性能很高，几乎没有增加的成本，但是坏处是实现起来比较困难，且当有嵌套关系存在时不容易发现嵌套对象的变化（比如子 List 的增加和删除等），有可能导致 bug。


由于 Proxy 方案的复杂度，业界主流（包括 EF Core）都在使用 Snapshot 方案。这里面还有另一个好处就是通过 Diff 可以发现哪些字段有变更，然后只更新变更过的字段，再一次降低 UPDATE 的成本。


> 乐观锁问题

在高并发情况下，如果使用上面的 Change-Tracking 方法，由于 Snapshot 在本地内存的数据 *有可能* 和 DB 数据不一致，会导致并发冲突的问题，这个时候需要在更新时加入乐观锁。当然，正常数据库操作的 Best Practice 应该也要有乐观锁，只不过在这个 case 里，需要在乐观锁冲突后，记得更新本地 Snapshot 里的值。

> 一个可能的 BUG

这个其实算不上 bug，但是单独指出来希望大家能注意一下，使用 Snapshot 的一个副作用就是如果没更新 Entity 然后调用了 save 方法，这时候实际上是不会去更新 DB 的。这个逻辑跟 Hibernate 的逻辑一致，是 Snapshot 方法的天生特性。如果要强制更新到 DB，建议手动更改一个字段如 lastModified，然后再调用 save。

* * *

#### Repository 迁移路径
> 可以通过以下几个步骤逐渐的实现 Repository 模式：
1. 生成 Order 实体类，初期字段可以和 OrderDO 保持一致
2. 生成 OrderDataConverter，通过 MapStruct 基本上 2 行代码就能完成
3. 写单元测试，确保 Order 和 OrderDO 之间的转化 100% 正确
4. 生成 OrderRepository 接口和实现，通过单测确保 OrderRepository 的正确性
5. 将原有代码里使用了 OrderDO 的地方改为 Order
6. 将原有代码里使用了 OrderDAO 的地方都改为用 OrderRepository
7. 通过单测确保业务逻辑的一致性