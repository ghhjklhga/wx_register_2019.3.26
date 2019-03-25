package com.edu.scau.orderconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.form.OrderForm;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.orderapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Slf4j
@CrossOrigin
public class OrderController {
    @Reference
    private OrderService orderService;


    @PostMapping("/createOrder")
    public ServerResponse createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){
        //检验订单参数是否正确
        if (bindingResult.hasErrors()){
            log.info("【创建订单】参数不正确，{}",orderForm);
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_ERROR_PARAM.getDesc());
        }
        log.info("【创建订单】orderForm为：{}",orderForm);
        ServerResponse result = orderService.createOrder(orderForm);
        return result;
    }

    @GetMapping("/testOrder")
    public ServerResponse test(){
        return ServerResponse.createBySuccessMessage("getOrder");
    }

    @PostMapping("/list")
    public ServerResponse list(@RequestParam("openid")String openid,@RequestParam("orderState")Integer orderState){
        if (StringUtils.isEmpty(openid)){
            log.error("【获取订单列表】获取前端openid参数失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_LIST_ERROR_SELECT.getDesc());
        }if(orderState == null){
            log.error("【获取订单列表】获取orderState参数失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_LIST_ERROR_SELECT.getDesc());
        }
        if (orderService==null){
            log.error("orderService为空！！！！！！！！！");
        }
        ServerResponse result = orderService.getOrderList(openid, orderState);

        log.info("【获取订单列表】获取成功！结果：{}",result);
        return result;
    }

    @PostMapping("/detail")
    public ServerResponse getOrderDetail(@RequestParam("orderId")String orderId, @RequestParam("openid")String openid){
        log.info("【获取订单详情】openid为：{},orderId为：{}",openid,orderId);
        ServerResponse response = orderService.getOrderDetail(orderId,openid);
        log.info("【获取订单详情】获取成功！结果：{}",response);
        return response;
    }

    @PostMapping("/pay")
    public ServerResponse payOrder(@RequestParam("openid")String openid, @RequestParam("orderId")String orderId){
        if (StringUtils.isEmpty(openid)){
            log.error("【付款订单】获取前端openid参数失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_PAY_ERROR.getDesc());
        }if(orderId == null){
            log.error("【付款订单】获取orderState参数失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_PAY_ERROR.getDesc());
        }
        ServerResponse response = orderService.payOrder(orderId,openid);
        log.info("【付款订单】付款成功！");
        return response;
    }

    @PostMapping("/cancel")
    public ServerResponse cancelOrder(@RequestParam("openid")String openid, @RequestParam("orderId")String orderId){
        if (StringUtils.isEmpty(openid)){
            log.error("【取消订单】获取前端openid参数失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_CANCEL_ERROR.getDesc());
        }if(orderId == null){
            log.error("【取消订单】获取orderState参数失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_CANCEL_ERROR.getDesc());
        }
        ServerResponse response = orderService.cancelOrder(orderId,openid);
        log.info("【取消订单】取消成功！");
        return response;
    }

}
