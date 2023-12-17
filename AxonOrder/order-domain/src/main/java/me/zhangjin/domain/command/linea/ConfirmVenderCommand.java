package me.zhangjin.domain.command.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.common.DomainCommand;

@Getter
@Setter
public class ConfirmVenderCommand extends DomainCommand {

    private String venderOrderCode;

    private Long venderId;

}
