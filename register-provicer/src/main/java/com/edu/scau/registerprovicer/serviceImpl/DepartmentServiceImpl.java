package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.Department;
import com.edu.scau.commom.pojo.DepartmentChild;
import com.edu.scau.commom.pojo.Hospital;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.DepartmentChildVO;
import com.edu.scau.commom.vo.DepartmentVO;
import com.edu.scau.registerapi.service.DepartmentService;
import com.edu.scau.registerprovicer.repository.DepartmentChildRepository;
import com.edu.scau.registerprovicer.repository.DepartmentRepository;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentChildRepository departmentChildRepository;

    @Override
    public ServerResponse addDepartment(Department department) {
        if(department == null){
            log.error("【新建门诊】获取数据失败，结果：{}",department);
            throw new RegisterException(ResponseEnum.DEPARTMENT_ERRER_CREATE.getDesc());
        }
        Integer result = departmentRepository.insertDepartment(department);
        if(result != 1){
            log.error("【新建门诊】插入数据库失败，结果：{}",result);
            throw new RegisterException(ResponseEnum.DEPARTMENT_ERRER_CREATE.getDesc());
        }
        log.info("【新建门诊】新建成功！department:{}",department);

        return getDepartmentlistByHid(department.getHospitalid());
    }

    @Override
    public ServerResponse getDepartmentlistByHid(Integer hospitalid) {
        if(hospitalid == null){
            log.error("【获取门诊】获取数据失败，结果：{}",hospitalid);
            throw new RegisterException(ResponseEnum.DEPARTMENT_ERROR_SEARCH.getDesc());
        }
        List<Department> departmentList = departmentRepository.selectDepartmentListByHid(hospitalid);
        List<DepartmentVO> departmentVOList = buildDepartmentVOList(departmentList);

        log.info("【获取门诊列表】获取成功，结果共{}条！",departmentList.size());
        return ServerResponse.createBySuccessData(departmentVOList);
    }

    private List<DepartmentVO> buildDepartmentVOList(List<Department> departmentList) {
        //  1、转换成departmentVO
        List<DepartmentVO> departmentVOList = departmentList.stream().map(e ->
            new DepartmentVO(e.getId(), e.getName())).collect(Collectors.toList());

        //  2、加入孩子诊室
        for (int i=0; i<departmentVOList.size(); i++){
            List<DepartmentChild> departmentChildList = departmentChildRepository.selectChildByDeptId(departmentVOList.get(i).getDept_id());
            //  转换孩子为VO
            List<DepartmentChildVO> departmentChildVOList = buildDepartmentChildListVO(departmentChildList);
            //  set孩子VO列表
            departmentVOList.get(i).setDepartmentChildList(departmentChildVOList);
        }
        return departmentVOList;
    }

    private List<DepartmentChildVO> buildDepartmentChildListVO(List<DepartmentChild> departmentChildList) {
        List<DepartmentChildVO> departmentChildVOList = departmentChildList.stream().map(e ->
            new DepartmentChildVO(e.getId(), e.getName())).collect(Collectors.toList());

        return departmentChildVOList;
    }

    //  获取：通过Id
    @Override
    public Department getDepartmentById(Integer departmentId){
        if (departmentId == null){
            log.error("【获取诊室信息】获取失败！id为null!");
            return null;
        }
        Department department = departmentRepository.selectDepartmentById(departmentId);
        return department;
    }


}
