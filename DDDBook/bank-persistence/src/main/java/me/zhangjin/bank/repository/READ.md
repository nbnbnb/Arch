### 并发乐观锁

* 在高并发情况下，如果使用示例中的 Change-Tracking 方法，由于Snapshot 在本地内存的数据*有可能* 和 DB 数据不一致，会导致并发冲突的问题，这个时候需要在更新时加入乐观锁。

* 当然，正常数据库操作的 Best Practice 应该也要有乐观锁，只不过在这个 case 里，`需要在乐观锁冲突后，记得更新本地 Snapshot 里的值`。

### 一个可能的 BUG

* 这个其实算不上 bug，但是单独指出来希望大家能注意一下，使用 Snapshot 的一个副作用就是如果没更新 Entity 然后调用了 save 方法，这时候实际上是不会去更新 DB 的
* 这个逻辑跟 Hibernate 的逻辑一致，是 Snapshot 方法的天生特性
* 如果要强制更新到 DB，建议手动更改一个字段如 gmtModified，然后再调用 save