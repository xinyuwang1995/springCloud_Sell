package com.wxy.lab.order.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    PARAM_ERROR(1,"parameter error"),
    CART_EMPTY(1,"CART EMPTY"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
