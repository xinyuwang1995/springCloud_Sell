package com.wxy.lab.order.controller;

import com.wxy.lab.order.VO.ResultVO;
import com.wxy.lab.order.converter.OrderForm2OrderDTOConverter;
import com.wxy.lab.order.dto.OrderDTO;
import com.wxy.lab.order.enums.ResultEnum;
import com.wxy.lab.order.exception.OrderException;
import com.wxy.lab.order.form.OrderForm;
import com.wxy.lab.order.service.OrderService;
import com.wxy.lab.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    //1.参数检验
    //2.查询商品信息（调用商品服务）
    //3.计算总价
    //4.扣库存 （调用商品服务）
    //5.订单入库
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[create order] parameter error,orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());


        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[create order] item is null");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);


    }
}
