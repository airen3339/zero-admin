package com.zero.common.response.domain;

import lombok.Getter;

/**
 * ResponseData对象的code属性枚举类
 * @author herenpeng
 * @since 2020-09-13 20:00
 */
@Getter
public enum CodeEnum {
    /**
     * code为20000，代表正常返回数据
     */
    OK(20000),
    /**
     * code为50000，代表未登录系统
     */
    NOT_LOGIN(50000);

    private Integer value;

    CodeEnum(Integer value) {
        this.value = value;
    }

}
