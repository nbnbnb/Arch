package com.alibaba.demo.domain.dp;

import lombok.Value;

import javax.validation.ValidationException;
import java.util.Arrays;

/**
 * 这是一个 Domain Primitive
 * <p>
 * 一个不变的 Value Object
 * <p>
 * <p>
 * 把 PhoneNumber 显性化之后，其实是生成了一个 Type（数据类型）和一个 Class（类）
 * <p>
 * * Type 指我们在今后的代码里可以通过 PhoneNumber 去显性的标识电话号这个概念
 * <p>
 * * Class 指我们可以把所有跟电话号相关的逻辑完整的收集到一个文件里
 */
public class PhoneNumber {

    // final 确保不变性
    private final String number;

    public String getNumber() {
        return number;
    }

    public PhoneNumber(String number) {
        // 校验逻辑放在 contructor 中
        if (number == null) {
            throw new ValidationException("number不能为空");
        } else if (isValid(number)) {
            throw new ValidationException("number格式错误");
        }
        this.number = number;
    }

    // findAreaCode 方法变成了 PhoneNumber 类里的 getAreaCode
    // 突出了 areaCode 是 PhoneNumber 的一个计算属性
    public String getAreaCode() {
        for (int i = 0; i < number.length(); i++) {
            String prefix = number.substring(0, i);
            if (isAreaCode(prefix)) {
                return prefix;
            }
        }
        return null;
    }

    private static boolean isAreaCode(String prefix) {
        String[] areas = new String[]{"0571", "021", "010"};
        return Arrays.asList(areas).contains(prefix);
    }

    public static boolean isValid(String number) {
        String pattern = "^0?[1-9]{2,3}-?\\d{8}$";
        return number.matches(pattern);
    }

}
