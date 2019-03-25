package com.edu.scau.registerprovicer.serviceImpl;

import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.Illness;
import com.edu.scau.commom.pojo.Section;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.IllnessVO;
import com.edu.scau.registerapi.service.IllnessService;
import com.edu.scau.registerprovicer.repository.IllnessRepository;
import com.edu.scau.registerprovicer.repository.SectionReponsitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IllnessServiceImpl implements IllnessService {
    @Autowired
    private IllnessRepository illnessRepository;
    @Autowired
    private SectionReponsitory sectionReponsitory;

    @Override
    public ServerResponse addIllness(Illness illness) {
        if (illness == null){
            log.error("【新增疾病】失败，参数传入错误！结果：{}",illness);
            throw new RegisterException(ResponseEnum.ILLNESS_ERROR_CREATE.getDesc());
        }
        Integer result = illnessRepository.insertIllness(illness);
        if (result != 1){
            log.error("【新增疾病】失败，插入数据库失败！结果：{}",result);
            throw new RegisterException(ResponseEnum.ILLNESS_ERROR_CREATE.getDesc());
        }
        return ServerResponse.createBySueecss();
    }

    @Override
    public ServerResponse delIllness(Integer id) {
        if(id == null){
            log.error("【删除疾病】失败，参数传入错误！结果：{}",id);
            throw new RegisterException(ResponseEnum.ILLNESS_ERROR_DELETE.getDesc());
        }
        Integer result = illnessRepository.deleteIllnessById(id);
        if (result != 1){
            log.error("【删除疾病】失败，插入数据库失败！结果：{}",result);
            throw new RegisterException(ResponseEnum.ILLNESS_ERROR_DELETE.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.ILLNESS_SUCCESS_DELETE.getDesc());
    }

    @Override
    public ServerResponse getIllnessAllListBySection() {
        //  将所有对象装在一个列表中
        List<IllnessVO> illnessVOList = new ArrayList<>();
        List<Section> sectionList = sectionReponsitory.selectSectionListAll();
        for (Section section : sectionList){
            IllnessVO illnessVO = new IllnessVO();
            illnessVO.setSectionid(section.getId());
            illnessVO.setSectionname(section.getName());

            List<Illness> illnessList = illnessRepository.selectIllnessListBySid(section.getId());
            illnessVO.setIllnessList(illnessList);
            //  添加到VO数组
            illnessVOList.add(illnessVO);
        }
        log.info("【按疾病挂号】获取列表成功！");
        return ServerResponse.createBySuccessData(illnessVOList);
    }

    @Override
    public ServerResponse getIllnessByName(String name) {
        if (StringUtils.isEmpty(name)){
            log.info("【查找疾病】输入为空！");
            ServerResponse.createByErrorMeeage("输入为空！");
        }
        List<Illness> illnessList = illnessRepository.selectIllnessByName(name);
        log.info("【查找疾病】成功！疾病条数为：{}",illnessList.size());

        return ServerResponse.createBySuccessData(illnessList);
    }
}
