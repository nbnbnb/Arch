package me.zhangjin.acl.converter;

import me.zhangjin.acl.domain.entity.Account;
import me.zhangjin.acl.persistence.AccountDO;

/**
 * DO 和 Entity 转换器
 * <p>
 * <p>
 * Data Converter：在 Infrastructure 层
 * <p>
 * Entity 到 DO 的转化器没有一个标准名称，但是为了区分 Data Mapper，我们叫这种转化器 Data Converter
 * <p>
 * 这里要注意 Data Mapper 通常情况下指的是 DAO，比如 Mybatis 的 Mapper
 * <p>
 * Data Mapper 的出处也在 P of EAA一书里：Data Mapper
 */
public interface AccountConverter {

    // 推荐使用 MapStruct 进行简化操作

    Account toEntity(AccountDO accountDO);

    AccountDO toDO(Account account);

}
