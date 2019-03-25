package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.DoctorEnum;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.enums.WeedEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.Doctor;
import com.edu.scau.commom.pojo.Number;
import com.edu.scau.commom.pojo.StarDoctor;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.DoctorDetailVO;
import com.edu.scau.commom.vo.DoctorVO;
import com.edu.scau.registerapi.service.DoctorService;
import com.edu.scau.registerprovicer.repository.DoctorRepository;
import com.edu.scau.registerprovicer.repository.NumberRepository;
import com.edu.scau.userapi.service.StarDoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NumberRepository numberRepository;
    @Reference
    private StarDoctorService starDoctorService;

    @Override
    public ServerResponse addDoctor(Doctor doctor) {
        Integer result = doctorRepository.insertDoctor(doctor);
        if (result != 1){
            log.error("【新建医生】失败！结果：{}",result);
            throw new RegisterException(ResponseEnum.DOCTOR_ERROR_CREATE.getDesc());
        }

        return getDoctorListByHid(doctor.getHospitalid());
    }

    @Override
    public ServerResponse getDoctorListByHid(Integer hospitalid) {
        List<Doctor> doctorList = doctorRepository.selectDoctorListByHid(hospitalid);
        PageHelper.startPage(1, 10);
        PageInfo<Doctor> doctorPageInfo = new PageInfo<>(doctorList);

        return ServerResponse.createBySuccessData(doctorPageInfo);
    }

    @Override
    public ServerResponse getDoctorListAll() {
        List<Doctor> doctorList = doctorRepository.selectDoctorListAll();
        //查找所有
        List<DoctorVO> doctorVOList = buildDoctorVOList(doctorList,WeedEnum.ALL.getCode());

        return ServerResponse.createBySuccessData(doctorVOList);
    }

    @Override
    public ServerResponse getDoctorDetailById(Integer doctorid) {
        if(doctorid == null){
            log.error("【查找医生】id查找，获取参数失败，结果：{}",doctorid);
            return ServerResponse.createBySuccessMessage(ResponseEnum.DOCTOR_ERROR_SEARCH.getDesc());
        }
        Doctor doctor = doctorRepository.selectDoctorById(doctorid);
        DoctorDetailVO doctorDetailVO = buildDoctorDetailVO(doctor);
        if (doctor == null){
            log.error("【查找医生】id查找，查找失败，结果：{}",doctor);
            return ServerResponse.createBySuccessMessage(ResponseEnum.DOCTOR_ERROR_SEARCH.getDesc());
        }
        return ServerResponse.createBySuccessData(doctorDetailVO);
    }
    @Override
    public ServerResponse getDoctorBySid(Integer sectionid){
        if (sectionid == null){
            log.error("【获取医生列表】sectionid获取,获取参数失败");
            throw new RegisterException(ResponseEnum.DOCTOR_ERROR_SELECT_LIST.getDesc());
        }
        List<Doctor> doctorList = doctorRepository.selectDoctorBySid(sectionid);
        PageHelper.startPage(1, 10);
        PageInfo<Doctor> doctorPageInfo = new PageInfo<>(doctorList);
        log.info("【获取医生列表】sectionid获取，获取成功！");
        return ServerResponse.createBySuccessData(doctorPageInfo);
    }
    //  通过诊室号码和星期几 获取医生列表
    @Override
    public ServerResponse getDoctorByDpidAndDay(Integer dpid, Integer day){
        if (dpid == null){
            log.error("【获取医生列表】departmentid获取，获取参数失败");
            throw new RegisterException(ResponseEnum.DOCTOR_ERROR_SELECT_LIST.getDesc());
        }
        List<Doctor> doctorList = doctorRepository.selectDoctorByDpid(dpid);
        log.info("【获取医生列表】结果：{}",doctorList);
        //  不用判断是否为空，因为可能没有医生，属正常
        List<DoctorVO> doctorVOList = buildDoctorVOList(doctorList, day);

        return ServerResponse.createBySuccessData(doctorVOList);
    }
    //  转换为VO列表
    private List<DoctorVO> buildDoctorVOList(List<Doctor> doctorList, Integer day) {
        List<DoctorVO> doctorVOList = new ArrayList<>();
        for (Doctor doctor: doctorList){
            DoctorVO doctorVO = buildDoctorVO(doctor, day);
            doctorVOList.add(doctorVO);
        }
        return doctorVOList;
    }

    private DoctorVO buildDoctorVO(Doctor doctor, Integer day) {
        DoctorVO doctorVO = new DoctorVO();
        //复制相同的属性
        BeanUtils.copyProperties(doctor, doctorVO);
        //填充不能复制属性
        doctorVO.setPosition(DoctorEnum.getName(doctor.getLevel()));
        //检测该医生该天 是否有号
        if (isHavaNumber(doctor.getId(),day)){
            doctorVO.setHavaNumber(true);
        }else {
            doctorVO.setHavaNumber(false);
        }
        return doctorVO;
    }

    private DoctorDetailVO buildDoctorDetailVO(Doctor doctor) {
        DoctorDetailVO doctorDetailVO = new DoctorDetailVO();
        //复制相同的属性
        BeanUtils.copyProperties(doctor, doctorDetailVO);
        //填充不能复制属性
        doctorDetailVO.setPosition(DoctorEnum.getName(doctor.getLevel()));

        return doctorDetailVO;
    }
    //  判断医生是否有号
    private boolean isHavaNumber(Integer doctorId, Integer day) {
        List<Number> numberList;
        if(day==WeedEnum.ALL.getCode()){
            numberList = numberRepository.selectNumberByDoctorid(doctorId);
        }else {
            numberList = numberRepository.selectNumberByDidAndDay(doctorId, day);
        }
        for (Number number: numberList){
            if (number.getRest() > 0){
                return true;
            }
        }
        return false;
    }
    // 查询：通过id获取医生详情
    @Override
    public Doctor getDoctorById(Integer id) {
        if (id == null){
            log.error("【获取医生信息】获取失败！id为null");
            throw new RegisterException(ResponseEnum.DOCTOR_ERROR_SEARCH.getDesc());
        }
        Doctor doctor = doctorRepository.selectDoctorById(id);
        return doctor;
    }

    //  查询: 获取收藏医生列表
    @Override
    public ServerResponse getStarDoctorList(String openid){
        // 1、获取收藏id列表
        List<StarDoctor> starDoctorList = starDoctorService.getStarDoctorList(openid);
        // 2、逐个查询
        List<DoctorVO> doctorVOList = new ArrayList<>();
        for (StarDoctor starDoctor: starDoctorList){
            Doctor doctor = doctorRepository.selectDoctorById(starDoctor.getDoctorid());
            DoctorVO doctorVO = buildDoctorVO(doctor,WeedEnum.ALL.getCode());
            doctorVOList.add(doctorVO);
        }
        return ServerResponse.createBySuccessData(doctorVOList);
    }

    //  查询：获取该科医生列表
    @Override
    public ServerResponse getSectionDoctorListBySidAndDay(Integer sid, Integer day) {
        List<Doctor> doctorList = doctorRepository.selectDoctorBySid(sid);
        List<DoctorVO> doctorVOList = buildDoctorVOList(doctorList, day);

        return ServerResponse.createBySuccessData(doctorVOList);
    }

    //  查询： 模糊查询
    @Override
    public ServerResponse getDoctorListBySearch(String simplename) {
        List<Doctor> doctorList = doctorRepository.selectDoctorListBySimpleName(simplename);
        List<DoctorVO> doctorVOList = buildDoctorVOList(doctorList, WeedEnum.ALL.getCode());

        return ServerResponse.createBySuccessData(doctorVOList);
    }



}