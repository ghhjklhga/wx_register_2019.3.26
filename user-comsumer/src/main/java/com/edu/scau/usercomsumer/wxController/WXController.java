package com.edu.scau.usercomsumer.wxController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.api.redisapi.api.RedisService;
import com.edu.scau.commom.dto.WXSessionDTO;
import com.edu.scau.commom.enums.PrefixEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.utils.HttpClientUtil;
import com.edu.scau.commom.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/wx")
public class WXController {
    @Reference
    private RedisService redisService;

    @PostMapping("/login")
    public ServerResponse wxLogin(String code){
        log.info("code: {}",code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
//                        "?appid=APPID" +
//                        "&secret=SECRET" +
//                        "&js_code=JSCODE" +
//                        "&grant_type=authorization_code";

        Map<String,String> map = new HashMap<>();
        map.put("appid","wx4faae8afab5288a2");
        map.put("secret","7cf3b9e1149b4cf6967481f7adbd1b6e");
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        //  通过发送微信请求获得openid和secret
        String jsonResult = HttpClientUtil.doGet(url, map);
        //  转换成对象
        WXSessionDTO wxSessionDTO = JsonUtil.jsonToPojo(jsonResult, WXSessionDTO.class);

        redisService.set(PrefixEnum.REDIS_PREFIX.getContext()+wxSessionDTO.getOpenid(),
                        wxSessionDTO.getSession_key(),
                        30 * 60);

        return ServerResponse.createBySuccessData(wxSessionDTO.getOpenid());
    }
}
