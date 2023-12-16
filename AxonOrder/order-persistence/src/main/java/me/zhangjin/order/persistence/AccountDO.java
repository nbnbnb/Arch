package me.zhangjin.order.persistence;

/**
 * <p>Data Object （DO、数据对象）：实际上是我们在日常工作中最常见的数据模型。
 *
 * <p>但是在 DDD 的规范里，DO 应该仅仅作为数据库物理表格的映射，不能参与到业务逻辑中。
 *
 * <p>为了简单明了，DO 的字段类型和名称应该和数据库物理表格的字段类型和名称一一对应，这样我们不需要去跑到数据库上去查一个字段的类型和名称。
 *
 * <p>当然，实际上也没必要一摸一样，只要你在 Mapper 那一层做到字段映射
 */
public class AccountDO {

    // AccountDO 是单纯的和数据库表的映射关系，每个字段对应数据库表的一个 column，这种对象叫 Data Object
    // DO 只有数据，没有行为
    // AccountDO 的作用是对数据库做快速映射，避免直接在代码里写 SQL。
    // 无论你用的是 MyBatis 还是 Hibernate 这种 ORM，从数据库来的都应该先直接映射到 DO 上，但是代码里应该完全避免直接操作 DO

    public Long getId() {
        return null;
    }

}
