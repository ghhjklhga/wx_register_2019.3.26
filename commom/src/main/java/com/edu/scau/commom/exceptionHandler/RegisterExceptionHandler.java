package com.edu.scau.commom.exceptionHandler;

import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.response.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class RegisterExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ServerResponse exceptionHandlerResponse(HttpServletRequest request, Exception e) throws Exception{
        ServerResponse response ;
        if (e instanceof RegisterException){
            response = ServerResponse.createByErrorMeeage(((RegisterException) e).getDsc());
        }else {
            response = ServerResponse.createByErrorMeeage(ResponseEnum.ERROR.getDesc());
        }
        return response;
    }
}
