package me.zhangjin.domain.acl.soa;

import me.zhangjin.domain.entity.Order;

public interface RiskVerify {
    boolean riskCheck(String uid);
}
