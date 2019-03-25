package com.edu.scau.provicer.redisprovicer.intercepter;

import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.pojo.DoctorUser;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.provicer.redisprovicer.service.RedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

@Component
@Slf4j
public class DoctorLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisServiceImpl redisServiceImpl;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        System.out.println("--------------进入 OnlineInterceptor--------------");

        String userToken = request.getHeader("userToken");
        if (StringUtils.isEmpty(userToken)){
            log.error("【医生用户登录验证】失败！token为空!");
            this.returnJSON(response, ServerResponse.createByErrorMeeage(ResponseEnum.TOKEN_EMPTY_ERROR.getDesc()));
            return false;
        }
        DoctorUser doctorUser = (DoctorUser) redisServiceImpl.get(userToken);
        if(doctorUser==null){
            this.returnJSON(response, ServerResponse.createByErrorMeeage(ResponseEnum.TOKEN_EMPTY_ERROR.getDesc()));
            return false;
        }else {
            response.setHeader("userToken", userToken);
            this.returnJSON(response, ServerResponse.createBySuccessMsgData(ResponseEnum.LOGIN_ALREADY.getDesc(),doctorUser));
        }
        return true;
    }

    private void returnJSON(HttpServletResponse response, ServerResponse serverResponse) {
        PrintWriter printWriter = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        JSONObject jsonObject = JSONObject.fromObject(serverResponse);

        try{
            printWriter = response.getWriter();
            printWriter.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(printWriter!=null){
                printWriter.close();
            }
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
