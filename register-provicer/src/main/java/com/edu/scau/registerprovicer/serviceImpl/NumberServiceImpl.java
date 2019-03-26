package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.api.redisapi.api.RedisService;
import com.edu.scau.commom.enums.NumberTimeEnum;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.enums.WeedEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.form.NumberForm;
import com.edu.scau.commom.pojo.DoctorUser;
import com.edu.scau.commom.pojo.Number;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.NumberDayVO;
import com.edu.scau.commom.vo.NumberVO;
import com.edu.scau.registerapi.service.NumberService;
import com.edu.scau.registerprovicer.repository.NumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NumberServiceImpl implements NumberService {
    @Reference
    private RedisService redisService;
    @Autowired
    private NumberRepository numberRepository;

    @Override
    public ServerResponse getNumberByDidAndDay(Integer doctorid, Integer day) {
        log.info("【获取医生号码】did:{},day:{}",doctorid,day);
        if(doctorid == null){
            log.error("【获取号码】获取controller医生id失败，结果:{}",doctorid);
            throw new RegisterException(ResponseEnum.NUMBER_ERROR_PARAM.getDesc());
        }
        List<Number> numberList = numberRepository.selectNumberListByDidAndDate(doctorid,day);
        log.info("【数据库NUMBER结果】，{}",numberList);
        //  转换VO
        List<NumberVO> numberVOList = buildNumberVOList(numberList);

        return ServerResponse.createBySuccessData(numberVOList);
    }
    //  将NumberList 转换成 NumberVOlist
    private List<NumberVO> buildNumberVOList(List<Number> numberList) {
        List<NumberVO> numberVOList = numberList.stream().map( e ->
            new NumberVO(e.getId(), e.getRest(), e.getSum(),NumberTimeEnum.getTime(e.getTime())))
                .collect(Collectors.toList());
        return numberVOList;
    }

    @Override
    public ServerResponse addNumberListByDoctorid(NumberForm numberForm) {
        if (numberForm == null){
            log.error("【新建号码】获取数据失败！结果：{}",numberForm);
            throw new RegisterException(ResponseEnum.NUMBER_ERROR_CREARE.getDesc());
        }
        Number number = buildNumber(numberForm);
        Integer result = numberRepository.insertNumber(number);
        if (result != 1){
            log.error("【新建号码】插入数据库失败！结果：{}",result);
            throw new RegisterException(ResponseEnum.NUMBER_ERROR_CREARE.getDesc());
        }
        List<Number> numberList = numberRepository.selectNumberByDoctorid(number.getDoctorid());
        log.info("【新建号码】成功！结果：{}",number);

        return ServerResponse.createBySuccessData(numberList);
    }

    private Number buildNumber(NumberForm numberForm) {
        Number number = new Number();
        BeanUtils.copyProperties(numberForm, number);
        number.setRest(numberForm.getSum());
        return number;
    }

    @Override
    public ServerResponse getNumberById(Integer numberId){
        Number number = numberRepository.selectNumberById(numberId);
        if (number == null){
            log.error("【获取号码信息】失败，查找数据库为空！");
            throw new RegisterException(ResponseEnum.NUMBER_ERROR_PARAM.getDesc());
        }
        NumberVO numberVO = buildNumberVO(number);

        return ServerResponse.createBySuccessData(numberVO);
    }
    //获取所有日期的号码
    @Override
    public ServerResponse getNumberByDoctorid(Integer doctorId) {
        List<Number> numberList = numberRepository.selectNumberByDoctorid(doctorId);
        List<NumberDayVO> numberDayVOList = buildNumberDayVOList(numberList);
        return ServerResponse.createBySuccessData(numberDayVOList);
    }
    //获取所有日期的所有号码
    private List<NumberDayVO> buildNumberDayVOList(List<Number> numberList) {
        List<NumberDayVO> numberDayVOList = new ArrayList<>();
        //获取当前日期为星期几
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK)-1;
        for(int i=0; i<7; i++){
            //每一个日期号码对象
            NumberDayVO numberDayVO = new NumberDayVO();
            //每一个日期号码对象的numberVOlist
            List<NumberVO> numberVOList = new ArrayList<>();
            //设置日期,每循环完一圈则设置下一天
            currentDay = (currentDay+i)%7;
            numberDayVO.setDay(WeedEnum.getWeed(currentDay));
            //把相应日期号码对象加进列表
            for(Number number: numberList){
                if (number.getDate()==currentDay){
                    NumberVO numberVO = buildNumberVO(number);
                    numberVOList.add(numberVO);
                }
            }
            //设置单个日期对象的号码列表
            numberDayVO.setNumberVOList(numberVOList);
            //完成一个
            numberDayVOList.add(numberDayVO);
        }
        return numberDayVOList;

    }

    private NumberVO buildNumberVO(Number number) {
        NumberVO numberVO = new NumberVO();
        BeanUtils.copyProperties(number,numberVO);
        //加上时间
        numberVO.setTime(NumberTimeEnum.getTime(number.getTime()));

        return numberVO;
    }

    //  扣库存
    @Override
    @Transactional
    public boolean decNumberById(Integer numberId){
        Integer decSumResult = numberRepository.decNumberById(numberId);
        if (decSumResult != 1){
            log.error("【创建订单】扣库存失败，结果：{}",decSumResult);
            return false;
        }
        return true;
    }

    @Override
    public Number getNumberInfoById(Integer id) {
        if (id == null){
            log.error("【获取号码信息】获取失败！id为null！");
            return null;
        }
        Number number = numberRepository.selectNumberById(id);
        return number;
    }
}
