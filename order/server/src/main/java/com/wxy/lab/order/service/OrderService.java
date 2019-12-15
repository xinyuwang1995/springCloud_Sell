package com.wxy.lab.order.service;

import com.wxy.lab.order.dataobject.OrderDetail;
import com.wxy.lab.order.dataobject.OrderMaster;
import com.wxy.lab.order.dto.OrderDTO;
import org.springframework.stereotype.Service;


public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);
}
