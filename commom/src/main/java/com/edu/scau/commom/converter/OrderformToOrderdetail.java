package com.edu.scau.commom.converter;

import com.edu.scau.commom.form.OrderForm;
import com.edu.scau.commom.pojo.OrderDetail;
import org.springframework.beans.BeanUtils;

public class OrderformToOrderdetail {

    public static OrderDetail converter(OrderForm orderForm){
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(orderForm, orderDetail);
        return orderDetail;
    }
}
