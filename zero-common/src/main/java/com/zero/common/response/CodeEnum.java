package com.zero.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResponseData对象的code属性枚举类
 *
 * @author herenpeng
 * @since 2020-09-13 20:00
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    /**
     * code为20000，代表正常返回数据
     */
    OK(20000),
    /**
     * code为30000，代表登录错误
     */
    LOGIN_ERROR(30000),
    /**
     * code为40003，权限不足
     */
    INSUFFICIENT_PERMISSIONS(40003),
    /**
     * code为50000，代表系统发生异常
     */
    SYS_EXCEPTION(50000);

    private final Integer value;

}