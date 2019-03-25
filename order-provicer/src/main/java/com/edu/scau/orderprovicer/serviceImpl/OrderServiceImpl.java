package com.edu.scau.orderprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.api.redisapi.api.RedisService;
import com.edu.scau.commom.enums.NumberTimeEnum;
import com.edu.scau.commom.enums.OrderEnum;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.form.OrderForm;
import com.edu.scau.commom.pojo.*;
import com.edu.scau.commom.pojo.Number;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.utils.KeyUtil;
import com.edu.scau.commom.vo.DoctorOrderVO;
import com.edu.scau.commom.vo.OrderDetailVO;
import com.edu.scau.commom.vo.OrderMasterVO;
import com.edu.scau.orderapi.service.OrderService;
import com.edu.scau.orderprovicer.repository.OrdersRepository;
import com.edu.scau.registerapi.service.DepartmentService;
import com.edu.scau.registerapi.service.DoctorService;
import com.edu.scau.registerapi.service.HospitalService;
import com.edu.scau.registerapi.service.NumberService;
import com.edu.scau.userapi.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Order;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Reference
    private UserService userService;
    @Reference
    private DoctorService doctorService;
    @Reference
    private NumberService numberService;
    @Reference
    private HospitalService hospitalService;
    @Reference
    private DepartmentService departmentService;
    @Reference
    private RedisService redisService;

    @Override
    @Transactional
    public ServerResponse createOrder(OrderForm orderForm) {
        log.info("【创建订单】service层，orderForm为: {}",orderForm);
        //          复制相同属性
        OrderDetail orderDetail = buildOrderDetail(orderForm);

        log.info("【属性复制】orderDetail为: {}",orderDetail);
        //          创建单号
        orderDetail.setId(KeyUtil.getUniqueKey());

        //          注入用户信息
        User user = userService.getUserByOpenid(orderDetail.getOpenid());
        orderDetail.setUserid(user.getId());
        orderDetail.setOpenid(user.getOpenid());
        //        1、查库存
        Number number = numberService.getNumberInfoById(orderDetail.getNumberid());
        //星期转化为日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, number.getDate());
        orderDetail.setVisitdate(Integer.valueOf(simpleDateFormat.format(calendar.getTime())));
        orderDetail.setVisittime(number.getTime());

        if (number.getSum() <= 0){
            log.info("【创建订单】失败！号码库存不足,{}",number.getRest());
            throw new RegisterException(ResponseEnum.NUMBER_NO_STOCK.getDesc());
        }
        //          检验医生是否存在
        Doctor doctor = doctorService.getDoctorById(orderDetail.getDoctorid());
        if(doctor==null){
            log.error("【创建订单】失败！医生不存在或id与姓名不匹配！，{}",orderForm.getDoctorid());
            throw new RegisterException(ResponseEnum.ORDER_ERROR_NO_DOCTOR.getDesc());
        }

        //          检验医院是否存在
        Hospital hospital = hospitalService.getHospitalById(orderForm.getHospitalid());
        if (hospital==null){
            log.error("【创建订单】失败！医院不存在或id与名字不匹配！,{}",orderForm.getHospitalid());
            throw new RegisterException(ResponseEnum.ORDER_ERROR_NO_HISPITAL.getDesc());
        }
        //          检验门诊是否存在
        Department department = departmentService.getDepartmentById(orderDetail.getDepartmentid());
        if (department==null){
            log.error("【创建订单】失败！门诊不存在或id与名字不匹配！,{}",orderForm.getDepartmentid());
            throw new RegisterException(ResponseEnum.ORDER_ERROR_NO_DEPARTMENT.getDesc());
        }
        //        2、查价钱
        BigDecimal price = doctor.getPrice();
        orderDetail.setPrice(price);
        //          补全其他属性
        orderDetail.setOrdertime(new Date());
        //        3、订单入库
        Integer insertOrderResult = ordersRepository.insertOrderDetail(orderDetail);
        if(insertOrderResult != 1){
            log.error("【创建订单】订单入库失败，结果：{}",insertOrderResult);
            throw new RegisterException(ResponseEnum.ORDER_ERROR_INSERT.getDesc());
        }
        //        4、扣库存
        boolean decSumResult = numberService.decNumberById(number.getId());
        if (!decSumResult){
            log.error("【创建订单】扣库存失败，结果：{}",decSumResult);
            throw new RegisterException(ResponseEnum.ORDER_ERROR_DEC_STOCK.getDesc());
        }
        //        5、返回订单详情
        return ServerResponse.createBySuccessData(orderDetail);
    }

    private OrderDetail buildOrderDetail(OrderForm orderForm) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setNumberid(orderForm.getNumberid());
        orderDetail.setDoctorid(orderForm.getDoctorid());
        orderDetail.setDepartmentid(orderForm.getDepartmentid());
        orderDetail.setHospitalid(orderForm.getHospitalid());
        orderDetail.setOpenid(orderForm.getOpenid());

        return orderDetail;
    }

    @Override
    @Transactional
    public ServerResponse payOrder(String orderid, String openid) {
        Integer result = ordersRepository.payOrderDetail(orderid, openid);
        if (result != 1){
            log.info("【付款订单】失败！更新数据库失败！");
            throw new RegisterException(ResponseEnum.ORDER_PAY_ERROR.getDesc());
        }
        OrderDetail orderDetail = ordersRepository.selectOrderDetailByOrderidAndOpenid(orderid,openid);
        OrderDetailVO orderDetailVO = buildOrderDetailVO(orderDetail);

        return ServerResponse.createBySuccessMsgData(ResponseEnum.ORDER_PAY_SUCCESS.getDesc(), orderDetailVO);
    }

    @Override
    public ServerResponse cancelOrder(String orderid, String openid) {
        Integer result = ordersRepository.cancelOrderDetail(orderid, openid);
        if (result != 1){
            log.info("【取消订单】失败！更新数据库失败！");
            throw new RegisterException(ResponseEnum.ORDER_CANCEL_ERROR.getDesc());
        }
        OrderDetail orderDetail = ordersRepository.selectOrderDetailByOrderidAndOpenid(orderid,openid);
        OrderDetailVO orderDetailVO = buildOrderDetailVO(orderDetail);

        return ServerResponse.createBySuccessMsgData(ResponseEnum.ORDER_CANCEL_SUCCESS.getDesc(), orderDetailVO);
    }

    @Override
    public ServerResponse getOrderList(String openid, Integer orderState) {
        if(StringUtils.isEmpty(openid)){
            log.error("【获取订单列表】获取失败，参数传递失败！");
            throw new RegisterException(ResponseEnum.ORDER_LIST_ERROR_SELECT.getDesc());
        }
        List<OrderDetail> orderList = new ArrayList<>();
        if (orderState == OrderEnum.ALL_ORDER.getCode()){
            orderList = ordersRepository.selectOrderDetailListByOpenid(openid);
        }else {
            orderList = ordersRepository.selectOrderDetailListByOpenidAndOstate(openid,orderState);
        }
        List<OrderMasterVO> orderMasterVOList =  buildOrderMasterVO(orderList);

        return ServerResponse.createBySuccessData(orderMasterVOList);
    }

    private List<OrderMasterVO> buildOrderMasterVO(List<OrderDetail> orderDetailList) {
        List<OrderMasterVO> orderMasterVOList = new ArrayList<>();
        //  转化为orderMasterVO列表
        for (OrderDetail orderDetail: orderDetailList){
            OrderMasterVO orderMasterVO = buildOrderMasterVO(orderDetail);
            //插入VOList列表
            orderMasterVOList.add(orderMasterVO);
        }
        return orderMasterVOList;
    }

    private OrderMasterVO buildOrderMasterVO(OrderDetail orderDetail){
        OrderMasterVO orderMasterVO = new OrderMasterVO();
        BeanUtils.copyProperties(orderDetail, orderMasterVO);

        //注入医院信息。名字，图片
        Doctor doctor = doctorService.getDoctorById(orderDetail.getDoctorid());
        orderMasterVO.setDoctorname(doctor.getName());
        //注入医生信息，名字
        Hospital hospital = hospitalService.getHospitalById(orderDetail.getHospitalid());
        orderMasterVO.setHospitalname(hospital.getName());
        orderMasterVO.setHospitalpicture(hospital.getPicture());
        //转化时间
        orderMasterVO.setVisittime(NumberTimeEnum.getTime(orderDetail.getVisittime()));

        return orderMasterVO;
    }

    //  获取订单详情
    @Override
    public ServerResponse getOrderDetail(String orderid, String openid) {
        if (StringUtils.isEmpty(openid)){
            log.error("【获取订单详情】获取controller层openid失败");
            throw new RegisterException(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        if (StringUtils.isEmpty(orderid)){
            log.error("【获取订单详情】获取controller层orderid失败");
            throw new RegisterException(ResponseEnum.ORDER_DETAIL_ERROR_SELECT.getDesc());
        }
        OrderDetail orderDetail = ordersRepository.selectOrderDetailByOrderidAndOpenid(orderid, openid);
        //  orderDetail转化为VO
        OrderDetailVO orderDetailVO = buildOrderDetailVO(orderDetail);

        return ServerResponse.createBySuccessData(orderDetailVO);
    }

    private OrderDetailVO buildOrderDetailVO(OrderDetail orderDetail) {
        log.info("【转化detailVO】orderDetail：{}",orderDetail);

        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderDetail, orderDetailVO);
        //  1、用户信息，电话，姓名
        User user = userService.getUserByOpenid(orderDetail.getOpenid());
        orderDetailVO.setUsername(user.getUsername());
        orderDetailVO.setPhone(user.getPhone());
        //  2、department信息，名称
        Department department = departmentService.getDepartmentById(orderDetail.getDepartmentid());
        orderDetailVO.setDepartmentname(department.getName());
        //  3、doctor信息，姓名
        Doctor doctor = doctorService.getDoctorById(orderDetail.getDoctorid());
        orderDetailVO.setDoctorname(doctor.getName());
        //  4、医院信息，名字，图片
        Hospital hospital = hospitalService.getHospitalById(orderDetail.getHospitalid());
        orderDetailVO.setHospitalname(hospital.getName());
        orderDetailVO.setHospitalpicture(hospital.getPicture());
        //  5、转化挂号时间
        orderDetailVO.setVisittime(NumberTimeEnum.getTime(orderDetail.getVisittime()));
        //  6、填充不能复制的属性,转化
        SimpleDateFormat orderDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderTime = orderDateFormat.format(orderDetail.getOrdertime());
        orderDetailVO.setOrdertime(orderTime);

        return orderDetailVO;
    }

    //医生停诊
    @Override
    @Transactional
    public ServerResponse cancelOrderByDidAndTime(Integer doctorId,Integer visitdate,Integer visittime){
//        取消未付款订单
        Integer result = ordersRepository.cancelOrderByDidAndTime(doctorId, visitdate,visittime,OrderEnum.NOT_PAY.getCode());
        log.info("【医生取消未付款订单】共{}条",result);
        //取消已付款订单
        List<OrderDetail> orderDetailList = ordersRepository.selectOrderListByDidAndTimeAndState(doctorId,visitdate,visittime,OrderEnum.ALREADY_PAY.getCode());
        for (OrderDetail orderDetail: orderDetailList){
            Integer cancelCesult = ordersRepository.cancelOrderDetail(orderDetail.getId(),orderDetail.getOpenid());
            if (cancelCesult != 1){
                log.error("【医生取消已付款订单】更新数据库失败！");
                throw new RegisterException(ResponseEnum.ORDER_CANCEL_ERROR.getDesc());
            }
            log.info("【医生取消已付款订单】取消成功！orderDetail：{}",orderDetail);
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.DOCTOR_CLOSE_SUCCESS.getDesc());
    }

    // TODO: 2019/1/26
    @Override
    public ServerResponse getDoctorOrderVOList(String userToken, Integer date){
        // TODO: 2019/3/13
        //从redisService中获取userInfo
        DoctorUser doctorUser = (DoctorUser) redisService.get(userToken);
        Integer doctorId = doctorUser.getId();
        //如果date为空则返回今天
        if (StringUtils.isEmpty(date)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String today = simpleDateFormat.format(new Date());
            date = Integer.valueOf(today);
        }
        //获取医生订单列表
        List<OrderDetail> orderDetailList = ordersRepository.selectOrderListByDidAndDate(doctorId,date);
        List<DoctorOrderVO> doctorOrderVOList = buildDoctorOrderVOList(orderDetailList);
        return ServerResponse.createBySuccessData(doctorOrderVOList);
    }

    private List<DoctorOrderVO> buildDoctorOrderVOList(List<OrderDetail> orderDetailList) {
        List<DoctorOrderVO> doctorOrderVOList = new ArrayList<>();

        for (OrderDetail orderDetail: orderDetailList){
            DoctorOrderVO doctorOrderVO = new DoctorOrderVO();
            BeanUtils.copyProperties(orderDetail, doctorOrderVO);
            //订单号，string要手动添加Integer
            doctorOrderVO.setId(orderDetail.getId());
            //加入时间
            doctorOrderVO.setVisitTime(NumberTimeEnum.getTime(orderDetail.getVisittime()));
            //加入用户信息
            User user = userService.getUserByOpenid(orderDetail.getOpenid());
            doctorOrderVO.setAddress(user.getAddress());
            doctorOrderVO.setUsername(user.getUsername());
            doctorOrderVO.setUserphone(user.getPhone());
            doctorOrderVOList.add(doctorOrderVO);
        }
        return doctorOrderVOList;
    }

    //  医生完结订单
    @Override
    @Transactional
    public ServerResponse finishOrderByDoctor(String orderId, Integer currentState) {
        //完结订单
        Integer result = ordersRepository.updateOrderState(orderId,currentState, OrderEnum.ALREADY_COMPLETE.getCode());
        if (result != 1){
            log.error("【医生完结订单】失败！更新数据库失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_FINISH_ERROR.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.ORDER_FINISH_SUCCESS.getDesc());
    }

    //医生取消订单
    @Override
    @Transactional
    public ServerResponse cancelOrderByDoctor(String orderId, Integer currentState){
        Integer result = ordersRepository.updateOrderState(orderId, currentState, OrderEnum.ALREADY_CANCEL.getCode());
        if (result != 1){
            log.error("【医生取消订单】失败！更新数据库失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.ORDER_CANCEL_ERROR.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.ORDER_CANCEL_SUCCESS.getDesc());
    }

    //医生获取所有订单
    @Override
    public ServerResponse getDoctorAllOrderVOList(String userToken) {
        DoctorUser doctorUser = (DoctorUser) redisService.get(userToken);
        List<OrderDetail> orderDetailList = ordersRepository.selectOrderListByDid(doctorUser.getDoctorid());
        List<DoctorOrderVO> doctorOrderVOList = buildDoctorOrderVOList(orderDetailList);
        return ServerResponse.createBySuccessData(doctorOrderVOList);
    }
}
