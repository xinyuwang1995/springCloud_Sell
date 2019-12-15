package com.wxy.lab.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0,"new order"),
    FINISH(1,"finish"),
    CANCEL(2,"CANCEL"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
