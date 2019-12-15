package com.wxy.lab.product.enums;

import lombok.Getter;

@Getter
public enum  ProductStatusEnum {
    UP(0,"UP line"),
    DOWN(1,"OFF line"),
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
