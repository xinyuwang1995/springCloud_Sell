package com.wxy.lab.order.repository;


import com.wxy.lab.order.dataobject.OrderMaster;
import com.wxy.lab.order.enums.OrderStatusEnum;
import com.wxy.lab.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("Jack");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("mgtu");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());



        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);

    }

}
