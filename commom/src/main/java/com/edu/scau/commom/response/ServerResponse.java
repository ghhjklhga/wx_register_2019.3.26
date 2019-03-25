package com.edu.scau.commom.response;


import com.edu.scau.commom.enums.ResponseEnum;
import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int code;
    private String msg="";
    private T data=null;

    private ServerResponse(int code){
        this.code = code;
    }

    private ServerResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(int code, T data){
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ServerResponse<T> createBySueecss(){
        return new ServerResponse<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseEnum.SUCCESS.getCode(), msg);
    }
    public static <T> ServerResponse<T> createBySuccessData(T data){
        return new <T> ServerResponse<T>(ResponseEnum.SUCCESS.getCode(), data);
    }
    public static <T> ServerResponse<T> createBySuccessMsgData(String msg, T data){
        return new <T> ServerResponse<T>(ResponseEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMeeage(String msg){
        return new ServerResponse<T>(ResponseEnum.ERROR.getCode(),msg);
    }

}
