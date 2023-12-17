package me.zhangjin.domain.repository.impl;

import me.zhangjin.domain.domain.entity.Account;
import me.zhangjin.domain.converter.AccountConverter;
import me.zhangjin.domain.persistence.AccountDO;
import me.zhangjin.domain.persistence.AccountDAO;
import me.zhangjin.domain.repository.AccountRepository;
import me.zhangjin.domain.domain.dp.AccountId;
import me.zhangjin.domain.domain.dp.AccountNumber;
import me.zhangjin.domain.domain.dp.UserId;
import me.zhangjin.domain.repository.DbRepositorySupport;
import me.zhangjin.domain.repository.EntityDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// AccountRepositoryImpl 实现类，由于其职责被单一出来
// 只需要关注 Account 到 AccountDO 的映射关系和 Repository 方法到 DAO 方法之间的映射关系，相对于来说更容易测试

@Component
public class AccountRepositoryImpl extends DbRepositorySupport<Account, AccountId> implements AccountRepository {

    // Repository 对应的是 Entity 对象读取储存的抽象，在接口层面做统一，不关注底层实现
    // 比如，通过 save 保存一个 Entity 对象，但至于具体是 insert 还是 update 并不关心

    // Repository 的具体实现类通过调用 DAO 来实现各种操作，通过 Builder/Factory/Converter 对象实现 AccountDO 到 Account 之间的转化

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountConverter accountConverter;

    protected AccountRepositoryImpl(Class<Account> targetClass) {
        super(targetClass);
    }

    // region 自定义的 Repository 方法

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.selectByAccountNumber(accountNumber.getValue());

        Account res = accountConverter.toEntity(accountDO);

        // 自定义的方法
        // 根据需要，可以显式加入到对象跟踪器中
        if (res != null) {
            // 这里的就是让查询出来的对象能够被追踪
            // 如果自己实现了一个定制查询接口，要记得单独调用 attach
            super.attach(res);
        }

        return res;
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.selectByUserId(userId.getValue());

        Account res = accountConverter.toEntity(accountDO);

        // 自定义的方法
        // 根据需要，可以显式加入到对象跟踪器中
        if (res != null) {
            // 这里的就是让查询出来的对象能够被追踪
            // 如果自己实现了一个定制查询接口，要记得单独调用 attach
            super.attach(res);
        }

        return res;
    }

    // endregion


    // region onXXX 相关方法，就是实际的执行 DB 保持逻辑地方

    @Override
    protected void onInsert(Account aggregate) {
        AccountDO accountDO = accountConverter.toDO(aggregate);
        accountDAO.insert(accountDO);
    }

    @Override
    protected Account onSelect(AccountId accountId) {
        AccountDO accountDO = accountDAO.selectById(accountId.getAccountId());
        return accountConverter.toEntity(accountDO);
    }

    @Override
    protected void onUpdate(Account aggregate, EntityDiff diff) {

        AccountDO accountDO = accountConverter.toDO(aggregate);

        // Update 方法应该根据 diff 来获取 Entity 的变化

        // if diff.modified
        accountDAO.update(accountDO);

        // 对于 Entity 对应多个 Table 的场景
        // 所以的子表变更，都要在一个更新方法中处理

        /***
        Diff lineItemDiffs = diff.getDiff("lineItems");
        if (lineItemDiffs instanceof ListDiff) {
            ListDiff diffList = (ListDiff) lineItemDiffs;
            for (Diff itemDiff : diffList) {
                if (itemDiff.getType() == DiffType.Removed) {
                    LineItem line = (LineItem) itemDiff.getOldValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    lineItemDAO.delete(lineDO);
                }
                if (itemDiff.getType() == DiffType.Added) {
                    LineItem line = (LineItem) itemDiff.getNewValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    lineItemDAO.insert(lineDO);
                }
                if (itemDiff.getType() == DiffType.Modified) {
                    LineItem line = (LineItem) itemDiff.getNewValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    lineItemDAO.update(lineDO);
                }
            }
        }
        */
    }

    @Override
    protected void onDelete(Account aggregate) {
        AccountDO accountDO = accountConverter.toDO(aggregate);
        accountDAO.delete(accountDO);
    }

    // endregion
}
