package me.zhangjin.bank.application.assembler;

import me.zhangjin.bank.domain.entity.Account;
import me.zhangjin.bank.dto.AccountDTO;
import org.springframework.core.annotation.Order;

/**
 * DTO Assembler：在 Application 层，Entity 到 DTO 的转化器有一个标准的名称叫 DTO Assembler
 * <p>
 * Martin Fowler在 P of EAA 一书里对于 DTO 和 Assembler的描述：Data Transfer Object
 * <p>
 * DTO Assembler 的核心作用就是将 1 个或多个相关联的 Entity 转化为 1 个或多个DTO
 */
public class AccountAssembler {

    // 推荐使用 MapStruct 进行简化操作

    // 通过各种实体，生成DTO
    public AccountDTO toDTO(Account account) {
        return null;
    }

    // 通过 DTO，生成实体
    public Account toEntity(AccountDTO accountDTO) {
        return null;
    }
    
}
