package com.wxy.lab.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"wait pay"),
    SUCCESS(1,"success paid"),

    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
