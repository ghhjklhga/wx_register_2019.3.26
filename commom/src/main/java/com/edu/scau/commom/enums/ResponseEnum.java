package com.edu.scau.commom.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ORDER_FINISH_SUCCESS(74, "完结订单成功"),

    ORDER_FINISH_ERROR(73, "完结订单失败"),

    TOKEN_EMPTY_ERROR(72, "token为空"),

    LOGOUT_SUCCESS(71, "退出登录成功"),

    LOGIN_ALREADY(70, "用户已登录"),

    LOGIN_SUCCESS(69, "登录成功"),

    USER_NOT_LOGIN(68, "用户未登录"),

    REGISTER_USRNAME_DOCTORID_EXIST(67, "用户名或doctorId已存在"),

    NOTICE_SEND_ERROR(66, "通知发送成功"),

    NOTICE_SEND_SUCCESS(65, "通知发送失败"),

    DOCTOR_OPEN_SUCCESS(64, "开诊成功"),

    DOCTOR_OPEN_ERROR(63, "看诊失败"),

    DOCTOR_CLOSE_SUCCESS(62, "停诊成功"),

    DOCTOR_CLOSE_ERROR(61, "停诊失败"),

    USERNAME_OR_PASSWORD_ERROR(60, "账号或密码不正确！"),

    USERNAME_OR_PASSWORD_NULL(59, "账号或密码为空！"),

    SEARCH_ERROR_WITH_NULL_INPUT(58, "查询失败,输入为空！"),

    STAR_DOCTOR_ERROR_LIST(57, "获取收藏医生列表失败"),

    STAR_HOSPIRAL_ERROR_LIST(56, "获取收藏医院列表失败"),

    STAR_HOSPITAL_SUCCESS_DELETE(55, "取消收藏医院成功"),

    STAR_HOSPITAL_ERROR_DELETE(54, "取消收藏医院失败"),

    STAR_HOSPITAL_SUCCESS_INSERT(53, "收藏医院成功"),

    STAR_HOSPITAL_ERROR_INSERT(52, "收藏医院失败"),

    STAR_DOCTOR_SUCCESS_DELETE(51, "取消收藏医生成功"),

    STAR_DOCTOR_ERROR_DELETE(50, "取消收藏医生失败"),

    STAR_DOCTOR_SUCCESS_INSERT(49, "收藏医生成功"),

    STAR_DOCTOR_ERROR_INSERT(48, "收藏医生失败"),

    ORDER_CANCEL_SUCCESS(47, "取消订单成功"),

    ORDER_CANCEL_ERROR(46, "取消订单失败"),

    ORDER_PAY_SUCCESS(45, "付款订单成功"),

    ORDER_PAY_ERROR(44, "付款订单失败"),

    OPENID_ERROR_CATCH(43, "获取openid失败"),

    ORDER_DETAIL_ERROR_SELECT(42, "获取订单详情"),

    HOSPITAL_DETAIL_ERROR_SELECT(41, "获取医院详情失败"),

    Section_ERROR_SELECT(40, "获取科失败"),

    ORDER_LIST_ERROR_SELECT(39, "获取订单列表失败"),

    DISTINCT_ERROR_SELECT(38, "获取地区列表失败"),

    HOSPITAL_LIST_ERROR_SELECT(37, "获取医院列表失败"),

    USER_ERROR_SELECT(36, "获取用户数据失败"),

    USER_SUCCESS_INSERT(35, "用户信息录入成功"),

    USER_ERROR_PARAM(34, "参数传入失败"),

    USER_ERROR_INSERT(33, "录入用户失败"),

    DOCTOR_ERROR_SELECT_LIST(32, "获取医生列表失败"),

    ILLNESS_SUCCESS_DELETE(31, "删除疾病成功"),

    ILLNESS_ERROR_DELETE(30, "删除疾病失败"),

    ILLNESS_ERROR_CREATE(29, "新增疾病失败"),

    NUMBER_ERROR_CREARE(28, "新建号码失败"),

    NUMBER_ERROR_PARAM(27, "获取号码失败"),

    DEPARTMENT_ERROR_SEARCH(26, "获取门诊失败"),

    DEPARTMENT_ERRER_CREATE(25, "新建门诊失败"),

    DOCTOR_ERROR_SEARCH(24, "查找医生失败"),

    DOCTOR_ERROR_CREATE(23, "新建医生失败"),

    HOSPITAL_ERROR_SUCCESS(22, "新建医生成功"),

    HOSPITAL_ERROR_CREATE(21, "新建医院失败"),

    FEEDBACK_SUCCESS_CREATE(20, "反馈成功"),

    FEEDBACK_ERROR_CREATE(19, "反馈失败"),

    NOTICE_ERROR_CREATE(18, "新建通知失败"),

    NUMBER_NO_STOCK(17, "号码库存不足"),

    ORDER_ERROR_INSERT(16, "订单入库失败"),

    ORDER_ERROR_DEC_STOCK(15, "扣库存失败"),

    ORDER_ERROR_NO_HISPITAL(14, "没有改医院"),

    ORDER_ERROR_NO_DEPARTMENT(13, "没有该门诊"),

    ORDER_ERROR_NO_DOCTOR(12, "没有改医生"),

    ORDER_ERROR_PARAM(11, "订单参数不正确"),

    SUCCESS(0, "成功"),
    ERROR(1, "错误");

    private int code;
    private String desc;

    ResponseEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
