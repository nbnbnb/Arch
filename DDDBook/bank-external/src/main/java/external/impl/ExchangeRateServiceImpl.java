package external.impl;

import me.zhangjin.bank.external.ExchangeRateService;
import me.zhangjin.bank.types.Currency;
import me.zhangjin.bank.types.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private YahooForexService yahooForexService;

    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        if (source.equals(target)) {
            return new ExchangeRate(BigDecimal.ONE, source, target);
        }
        BigDecimal forex = yahooForexService.getExchangeRate(source.getValue(), target.getValue());
        return new ExchangeRate(forex, source, target);
    }
}

