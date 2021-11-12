package me.zhangjin.bank.external;

import me.zhangjin.bank.domain.dp.NewCurrency;
import me.zhangjin.bank.domain.dp.ExchangeRate;

public interface ExchangeRateService {
      ExchangeRate getExchangeRate(NewCurrency source, NewCurrency target);
}
