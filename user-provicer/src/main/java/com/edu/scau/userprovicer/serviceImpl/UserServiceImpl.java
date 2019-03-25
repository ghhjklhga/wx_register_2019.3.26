package com.edu.scau.userprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.api.redisapi.api.RedisService;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.form.UserForm;
import com.edu.scau.commom.pojo.Doctor;
import com.edu.scau.commom.pojo.DoctorUser;
import com.edu.scau.commom.pojo.User;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.utils.MD5Util;
import com.edu.scau.commom.utils.TokenUtil;
import com.edu.scau.commom.vo.DoctorUserVO;
import com.edu.scau.commom.vo.UserInfoVO;
import com.edu.scau.registerapi.service.DoctorService;
import com.edu.scau.userapi.service.UserService;
import com.edu.scau.userprovicer.repository.DoctorUserRespository;
import com.edu.scau.userprovicer.repository.UserRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorUserRespository doctorUserRespository;
    // TODO: 2019/3/8  
    @Reference
    private RedisService redisService;
    @Reference
    private DoctorService doctorService;

    @Override
    public ServerResponse signin(UserForm userForm) {
        if (userForm == null){
            log.error("【录入用户信息】获取参数失败！结果：{}",userForm);
            throw new RegisterException(ResponseEnum.USER_ERROR_INSERT.getDesc());
        }
        //  userform转换成user对象
        User user = build(userForm);
        Integer result = userRepository.insertUser(user);
        if (result != 1){
            log.error("【录入用户信息】插入数据库失败！结果：{}",result);
            throw new RegisterException(ResponseEnum.USER_ERROR_INSERT.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.USER_SUCCESS_INSERT.getDesc());
    }
    private User build(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        return user;
    }

    @Override
    public ServerResponse getUserInfoByOpenid(String openid) {
        if (StringUtils.isEmpty(openid)){
            log.error("【获取用户信息】参数传入失败！结果：{}",openid);
            throw new RegisterException(ResponseEnum.USER_ERROR_PARAM.getDesc());
        }
        User user = userRepository.selectUserByOpenid(openid);
        UserInfoVO userInfoVO = buildUserInfoVO(user);

        return ServerResponse.createBySuccessData(userInfoVO);
    }

    private UserInfoVO buildUserInfoVO(User user) {
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return userInfoVO;
    }

    @Override
    public ServerResponse getUserAll() {
        List<User> userList = userRepository.selectUserAll();
        PageHelper.startPage(1, 10);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);

        return ServerResponse.createBySuccessData(userPageInfo);
    }

    @Override
    public User getUserByOpenid(String openid) {
        if (StringUtils.isEmpty(openid)){
            log.error("【获取用户信息】获取失败！openid为空！");
            throw new RegisterException(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        User user = userRepository.selectUserByOpenid(openid);
        return user;
    }

    //医生用户登录方法
    @Override
    public ServerResponse doctorUserLogin(String username, String password) {
        String MD5password = MD5Util.MD5(password);
        DoctorUser doctorUser = doctorUserRespository.selectDoctorUserByUnameAndPwd(username,MD5password);
        if (doctorUser == null){
            log.info("【医生用户登录】登录失败！账号{}密码{}不正确！",username,password);
            return ServerResponse.createByErrorMeeage(ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getDesc());
        }
        log.info("【医生用户登录】登录成功！");
        //登录成功，把状态存进Redis,把TOKEN发送给用户
        String token = TokenUtil.getToken();
        //把token放进redis,并设置过期时间为30分钟
        redisService.set(token, doctorUser, 1800);
        //建立userVO
        DoctorUserVO doctorUserVO = buildDoctorUserVO(doctorUser, token);
        return ServerResponse.createBySuccessMsgData(ResponseEnum.LOGIN_SUCCESS.getDesc(),doctorUserVO);
    }

    private DoctorUserVO buildDoctorUserVO(DoctorUser doctorUser, String userToken) {
        DoctorUserVO doctorUserVO = new DoctorUserVO();
        doctorUserVO.setId(doctorUser.getDoctorid());
        doctorUserVO.setUserToken(userToken);
        Doctor doctor = doctorService.getDoctorById(doctorUser.getDoctorid());
        doctorUserVO.setName(doctor.getName());
        return doctorUserVO;
    }

    //医生注册
    @Override
    public ServerResponse doctorUserSignin(String username, String password, Integer doctorId) {
        List<DoctorUser> doctorUserList = doctorUserRespository.selectDoctorsuserByUsernameOrDoctorId(username, doctorId);
        if (doctorUserList.size() != 0){
            log.info("【医生用户注册】失败，用户名或doctorId已存在！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.REGISTER_USRNAME_DOCTORID_EXIST.getDesc());
        }
        String MD5password = MD5Util.MD5(password);
        DoctorUser doctorUser = new DoctorUser();
        doctorUser.setUsername(username);
        doctorUser.setPassword(MD5password);
        doctorUser.setDoctorid(doctorId);
        Integer result = doctorUserRespository.insertDoctoruser(doctorUser);
        if (result != 1){
            log.error("【医生注册】失败，结果result={}",result);
            return ServerResponse.createByErrorMeeage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse dockerUserLogout(String token){
        //在缓存中删除用户token,因为在拦截器中已经检查过，所以这里可以不检查
        redisService.del(token);
        return ServerResponse.createBySuccessMessage(ResponseEnum.LOGOUT_SUCCESS.getDesc());
    }


}
