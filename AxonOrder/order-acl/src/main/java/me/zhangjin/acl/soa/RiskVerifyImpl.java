package me.zhangjin.acl.soa;

import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.acl.soa.RiskVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiskVerifyImpl implements RiskVerify {

    @Autowired
    private OrderLogger orderLogger;

    @Override
    public boolean riskCheck(String uid) {
        orderLogger.info("riskCheck success uid: %s", uid);
        return true;
    }

}
