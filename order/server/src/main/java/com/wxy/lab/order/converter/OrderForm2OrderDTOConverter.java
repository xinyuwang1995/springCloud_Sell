package com.wxy.lab.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxy.lab.order.dataobject.OrderDetail;
import com.wxy.lab.order.dto.OrderDTO;
import com.wxy.lab.order.enums.ResultEnum;
import com.wxy.lab.order.exception.OrderException;
import com.wxy.lab.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("[json convert error],string={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);


        return orderDTO;
    }
}
