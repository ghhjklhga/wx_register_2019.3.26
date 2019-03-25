package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.Distinct;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.DistinctService;
import com.edu.scau.registerprovicer.repository.DistinctRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
@Slf4j
public class DistinctServiceImpl implements DistinctService {
    @Autowired
    private DistinctRespository distinctRespository;

    @Override
    public ServerResponse getDistinctAllList() {
        List<Distinct> distinctList = distinctRespository.selectDistinctAll();
        if(distinctList.size() == 0){
            log.error("【获取地区】获取失败！数据库查找失败，结果：{}",distinctList.size());
            throw new RegisterException(ResponseEnum.DISTINCT_ERROR_SELECT.getDesc());
        }
        return ServerResponse.createBySuccessData(distinctList);
    }
}
