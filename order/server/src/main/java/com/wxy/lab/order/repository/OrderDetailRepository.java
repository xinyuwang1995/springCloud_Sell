package com.wxy.lab.order.repository;

import com.wxy.lab.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}
