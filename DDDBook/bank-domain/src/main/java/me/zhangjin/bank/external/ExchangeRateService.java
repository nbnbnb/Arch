package me.zhangjin.bank.external;

import me.zhangjin.bank.types.Currency;
import me.zhangjin.bank.types.ExchangeRate;

public interface ExchangeRateService {
      ExchangeRate getExchangeRate(Currency source, Currency target);
}
