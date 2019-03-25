package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.Hospital;
import com.edu.scau.commom.pojo.StarHospital;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.HospitalDetailVO;
import com.edu.scau.commom.vo.HospitalVO;
import com.edu.scau.registerapi.service.HospitalService;
import com.edu.scau.registerprovicer.repository.HospitalRepository;
import com.edu.scau.userapi.service.StarHospitalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Reference
    private StarHospitalService starHospitalService;

    @Override
    public ServerResponse addHospital(Hospital hospital) {
        Integer result = hospitalRepository.insertHospital(hospital);
        if (result != 1){
            log.error("【新建医院】失败，结果：{}",result);
            throw new RegisterException(ResponseEnum.HOSPITAL_ERROR_CREATE.getDesc());
        }
        //  返回新的医院列表
        List<Hospital> hospitalList = hospitalRepository.selectHospitalListByDid(hospital.getDistinctid());
        List<HospitalVO> hospitalVOList = buildHospitalListVO(hospitalList);
//        PageHelper.startPage(1, 10);
//        PageInfo<Hospital> hospitalPageInfo = new PageInfo<>(hospitalList);
        return ServerResponse.createBySuccessData(hospitalVOList);
    }

    @Override
    public ServerResponse getHospitalListByDistinctId(Integer distinctid) {
        List<Hospital> hospitalList = hospitalRepository.selectHospitalListByDid(distinctid);
        List<HospitalVO> hospitalVOList = buildHospitalListVO(hospitalList);
//        PageHelper.startPage(1, 10);
//        PageInfo<Hospital> hospitalPageInfo = new PageInfo<>(hospitalList);

        return ServerResponse.createBySuccessData(hospitalVOList);
    }

    @Override
    public ServerResponse getHospitalListAll() {
        List<Hospital> hospitalList = hospitalRepository.selectHospitalListAll();
        List<HospitalVO> hospitalVOList = buildHospitalListVO(hospitalList);

        return ServerResponse.createBySuccessData(hospitalVOList);
    }

    //  将hospital对象转换成VO对象
    private HospitalVO buildHospitalVO(Hospital hospital) {
        HospitalVO hospitalVO = new HospitalVO();
        BeanUtils.copyProperties(hospital, hospitalVO);
        return hospitalVO;
    }

    @Override
    public ServerResponse getHospitalDetailById(Integer id) {
        if (id == null){
            log.error("【获取医院详情】获取controller参数失败！");
            throw new RegisterException(ResponseEnum.HOSPITAL_DETAIL_ERROR_SELECT.getDesc());
        }
        Hospital hospital = hospitalRepository.selectHospitalById(id);
        if (hospital == null){
            log.error("【获取医院详情】获取数据库失败，结果为：{}",hospital);
            throw new RegisterException(ResponseEnum.HOSPITAL_DETAIL_ERROR_SELECT.getDesc());
        }
        HospitalDetailVO hospitalDetailVO = buildHospitalDetailVO(hospital);

        return ServerResponse.createBySuccessData(hospitalDetailVO);
    }

    private HospitalDetailVO buildHospitalDetailVO(Hospital hospital) {
        HospitalDetailVO hospitalDetailVO = new HospitalDetailVO();
        BeanUtils.copyProperties(hospital, hospitalDetailVO);

        return hospitalDetailVO;
    }

    @Override
    public Hospital getHospitalById(Integer id) {
        if (id == null){
            log.error("【获取医院信息】获取失败！id为null!");
            return null;
        }
        Hospital hospital = hospitalRepository.selectHospitalById(id);
        return hospital;
    }

    @Override
    public ServerResponse getStarHospitalList(String openid){
        List<StarHospital> starHospitalList = starHospitalService.getStarHospitalIdList(openid);
        List<HospitalVO> hospitalVOList = new ArrayList<>();
        for (StarHospital starHospital: starHospitalList){
            Hospital hospital = hospitalRepository.selectHospitalById(starHospital.getHospitalid());
            HospitalVO hospitalVO = buildHospitalVO(hospital);
            hospitalVOList.add(hospitalVO);
        }
        return ServerResponse.createBySuccessData(hospitalVOList);
    }

    //  模糊查询
    @Override
    public ServerResponse getHospitalListBySimplename(String simpleName) {
        List<Hospital> hospitalList = hospitalRepository.selectHospitalBySimpleName(simpleName);
        List<HospitalVO> hospitalVOList = buildHospitalListVO(hospitalList);

        return ServerResponse.createBySuccessData(hospitalVOList);
    }

    private List<HospitalVO> buildHospitalListVO(List<Hospital> hospitalList) {
        List<HospitalVO> hospitalVOList = hospitalList.stream().map( e ->
            new HospitalVO(e.getId(), e.getName(),e.getLevel(),e.getAddress(),e.getPhone(),e.getPicture(),e.getDescription()))
                .collect(Collectors.toList());

        return  hospitalVOList;
    }


}
