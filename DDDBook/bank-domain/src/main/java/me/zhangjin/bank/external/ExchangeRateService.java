package me.zhangjin.bank.external;

import me.zhangjin.bank.domain.dp.NewCurrency;
import me.zhangjin.bank.domain.dp.ExchangeRate;

// 类似对于数据库的抽象，所有第三方服务也需要通过抽象解决第三方服务不可控，入参出参强耦合的问题

// 这个例子里我们抽象出 ExchangeRateService 的服务，和 ExchangeRate、NewCurrency 的 Domain Primitive 类
public interface ExchangeRateService {
    ExchangeRate getExchangeRate(NewCurrency source, NewCurrency target);
}
