package com.edu.scau.commom.exception;

import com.edu.scau.commom.enums.ResponseEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterException extends RuntimeException {
    private Integer code = ResponseEnum.ERROR.getCode(); //异常对应的返回码
    private String dsc; //异常对应的描述信息

    public RegisterException(){}

    public RegisterException(String dsc){
        code = ResponseEnum.ERROR.getCode();
        this.dsc = dsc;
    }
}
