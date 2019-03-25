package com.edu.scau.orderapi.service;

import com.edu.scau.commom.form.OrderForm;
import com.edu.scau.commom.response.ServerResponse;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    //创建订单
    @Transactional
    ServerResponse createOrder(OrderForm orderDetail);

    //付款订单
    ServerResponse payOrder(String orderid, String openid);

    //取消订单
    ServerResponse cancelOrder(String orderid, String openid);

    //查看订单列表
    ServerResponse getOrderList(String openid, Integer orderState);

    //查看订单详情
    ServerResponse getOrderDetail(String orderid, String openid);

    ServerResponse cancelOrderByDidAndTime(Integer doctorId, Integer visitdate, Integer visittime);

    // TODO: 2019/1/26
    ServerResponse getDoctorOrderVOList(String userToken, Integer date);

    ServerResponse finishOrderByDoctor(String orderId, Integer currentState);

    //医生取消订单
    @Transactional
    ServerResponse cancelOrderByDoctor(String orderId, Integer currentState);

    ServerResponse getDoctorAllOrderVOList(String userToken);
}
