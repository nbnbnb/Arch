package me.zhangjin.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmVenderCommand extends DomainCommand {

    private String venderOrderCode;

    private Long venderId;

}
