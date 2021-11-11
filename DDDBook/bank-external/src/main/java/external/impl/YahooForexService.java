package external.impl;


import java.math.BigDecimal;

public interface YahooForexService {
    BigDecimal getExchangeRate(String a, String b);
}
