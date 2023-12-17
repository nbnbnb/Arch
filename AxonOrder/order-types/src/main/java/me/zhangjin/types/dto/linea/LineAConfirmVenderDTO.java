package me.zhangjin.types.dto.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.dto.common.BaseDTO;

@Getter
@Setter
public class LineAConfirmVenderDTO extends BaseDTO {
    private String venderOrderCode;

    private Long venderId;
}
