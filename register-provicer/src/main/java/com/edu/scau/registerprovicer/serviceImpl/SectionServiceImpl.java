package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.Section;
import com.edu.scau.commom.pojo.SectionChild;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.SectionChildVO;
import com.edu.scau.commom.vo.SectionVO;
import com.edu.scau.registerapi.service.SectionService;
import com.edu.scau.registerprovicer.repository.SectionChildReponsitory;
import com.edu.scau.registerprovicer.repository.SectionReponsitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionReponsitory sectionReponsitory;
    @Autowired
    private SectionChildReponsitory sectionChildReponsitory;

    @Override
    public ServerResponse getSectionList() {
        List<Section> sectionList = sectionReponsitory.selectSectionListAll();
        if(sectionList==null || sectionList.size()==0){
            log.info("【获取科室列表】获取失败，查找数据库条数为0");
            throw new RegisterException(ResponseEnum.Section_ERROR_SELECT.getDesc());
        }
        List<SectionVO> sectionVOList = buildSectionVOList(sectionList);
        return ServerResponse.createBySuccessData(sectionVOList);
    }

    private List<SectionVO> buildSectionVOList(List<Section> sectionList) {
        List<SectionVO> sectionVOList = new ArrayList<>();
        for (Section section: sectionList){
            SectionVO sectionVO = new SectionVO();
            sectionVO.setSec_id(section.getId());
            sectionVO.setSec_name(section.getName());
            //  设置孩子列表
            List<SectionChild> sectionChildList = sectionChildReponsitory.selectSectionChildListBySid(section.getId());
            List<SectionChildVO> sectionChildVOList = buildSectionChildVOList(sectionChildList);
            sectionVO.setSectionChildVOList(sectionChildVOList);

            sectionVOList.add(sectionVO);
        }
        return sectionVOList;
    }

    private List<SectionChildVO> buildSectionChildVOList(List<SectionChild> sectionChildList) {
        List<SectionChildVO> sectionChildVOList = sectionChildList.stream().map( e ->
            new SectionChildVO(e.getId(), e.getName()))
                .collect(Collectors.toList());
        return sectionChildVOList;
    }
}
