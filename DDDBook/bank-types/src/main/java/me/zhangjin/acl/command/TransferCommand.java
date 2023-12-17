package me.zhangjin.acl.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class TransferCommand {

    @Getter
    @Setter
    private Long sourceUserId;

    @Getter
    @Setter
    private String targetAccountNumber;

    @Getter
    @Setter
    private BigDecimal targetAmount;

    @Getter
    @Setter
    private String targetCurrency;

}
