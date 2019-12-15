package com.wxy.lab.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "name not null")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "phone not null")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "adress not null")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid not null")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "not null")
    private String items;

}
