package me.zhangjin.bank.persistence;

/**
 * Data Object （DO、数据对象）：实际上是我们在日常工作中最常见的数据模型。
 * <p>
 * 但是在DDD的规范里，DO应该仅仅作为数据库物理表格的映射，不能参与到业务逻辑中。
 * <p>
 * 为了简单明了，DO的字段类型和名称应该和数据库物理表格的字段类型和名称一一对应，这样我们不需要去跑到数据库上去查一个字段的类型和名称。
 * <p>
 * （当然，实际上也没必要一摸一样，只要你在Mapper那一层做到字段映射）
 */
public class AccountDO {
    public Long getId() {
        return null;
    }
}
