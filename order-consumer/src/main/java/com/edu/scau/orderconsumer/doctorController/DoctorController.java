package com.edu.scau.orderconsumer.doctorController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.orderapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctorOrder")
@Slf4j
@CrossOrigin
public class DoctorController {
    @Reference
    private OrderService orderService;

    @PostMapping("/dateOrderList")
    public ServerResponse getDoctorOrderList(@RequestHeader("userToken")String userToken,
                                             @RequestParam(value = "visitdate",required = false, defaultValue = "")Integer visitdate){
        ServerResponse response = orderService.getDoctorOrderVOList(userToken,visitdate);
        log.info("【获取医生一天订单列表】结果：{}",response);
        return response;
    }

    @PostMapping("/allOrderList")
    public ServerResponse getDoctorAllOrderList(@RequestHeader("userToken")String userToken){
        ServerResponse response = orderService.getDoctorAllOrderVOList(userToken);
        log.info("【获取医生所有订单列表】结果：{}",response);
        return response;
    }

    @PostMapping("/finish")
    public ServerResponse finishOrder(@RequestHeader("userToken")String userToken,
                                      @RequestParam("orderId")String orderId,
                                      @RequestParam("currentState")Integer currentState){
        if (StringUtils.isEmpty(orderId)){
            log.error("【医生完结订单】失败，orderID为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_FINISH_ERROR.getDesc());
        }
        ServerResponse response = orderService.finishOrderByDoctor(orderId, currentState);
        return response;
    }

    @PostMapping("/cancel")
    public ServerResponse cancelOrder(@RequestHeader("userToken")String userToken,
                                      @RequestParam("orderId")String orderId,
                                      @RequestParam("currentState")Integer currentState){
        if (StringUtils.isEmpty(orderId)){
            log.error("【医生取消订单】失败，orderID为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_CANCEL_ERROR.getDesc());
        }
        ServerResponse response = orderService.cancelOrderByDoctor(orderId, currentState);
        return response;
    }

    //医生停诊
    public ServerResponse stopVisit(@RequestHeader("userToken")String userToken,
                                    @RequestParam("startDate")Integer startDate,
                                    @RequestParam("endDate")Integer endDate){
        return null;
    }
}
