package com.example.test.service.impl;

import com.example.test.converter.JpaLogin2LoginDTOConverter;
import com.example.test.dao.LoginRepository;
import com.example.test.dao.UserRepository;
import com.example.test.dto.LoginDTO;
import com.example.test.entity.Login;
import com.example.test.enums.LoginEnum;
import com.example.test.enums.ResultEnum;
import com.example.test.exception.SellException;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaUser;
import com.example.test.service.JpaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JpaUserServiceImpl implements JpaUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;


    //查找未删除的名为username的用户，判断是否可以新增
    @Override
    public jpaLogin findByUsername(String username) {
        jpaLogin login = userRepository.findByUsername(username);

        return login;
    }

    @Override
    public jpaLogin findByIdcard(String idcard) {
        jpaLogin login = userRepository.findByIdcard(idcard);

        return login;
    }


    @Override
    public Page<LoginDTO> findList(Integer parentId, Pageable pageable) {
        Page<jpaLogin> userPage = userRepository.findByParentId(parentId, pageable);
        List<LoginDTO> loginDTOList = JpaLogin2LoginDTOConverter.convert(userPage.getContent());

        return new PageImpl<LoginDTO>(loginDTOList, pageable, userPage.getTotalElements());
    }

    @Override
    public jpaLogin findOne(Integer id) {
        Optional<jpaLogin> login = userRepository.findById(id);
        if(login==null){
            throw new SellException(ResultEnum.SELLER_NOT_EXIST);

        }

        return login.orElse(new jpaLogin());
    }
    //rElse(T)无论前面Optional容器是null还是non-null，都会执行orElse里的方法，orElseGet(Supplier)并不会，
   //如果service无异常抛出的情况下，Optional使用orElse或者orElseGet的返回结果都是一样的
    @Override
    public LoginDTO findDtoById(Integer userId) {
        jpaLogin jpalogin = new jpaLogin();
        Optional<jpaLogin> login = userRepository.findById(userId);
//        jpalogin.setId();
//        jpalogin.setUsername();
//        jpalogin.setPassword();login.get().getUsername();
        if(login==null){
            throw new SellException(ResultEnum.SELLER_NOT_EXIST);

        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(login.get(), loginDTO);
        return loginDTO;
    }

    @Override
    public jpaLogin deluser(jpaLogin jpalogin) {
        //判断用户状态
        if (!jpalogin.getDelflag().equals(0)) {
            log.error("【删除用户】用户删除状态不正确, Id={}, delflag={}", jpalogin.getId(), jpalogin.getDelflag());
            throw new SellException(ResultEnum.USER_STATUS_ERROR);
        }
        jpalogin.setDelflag(1);
        jpaLogin updateResult = userRepository.save(jpalogin);

        if (updateResult == null) {
            log.error("【删除用户失败】更新失败, jpaLogin={}", jpalogin);
            throw new SellException(ResultEnum.USER_UPDATE_FAIL);
        }
        return jpalogin;
    }

    @Override
    public jpaLogin backpwd(jpaLogin jpalogin) {
        //判断用户状态
        if (!jpalogin.getDelflag().equals(0)) {
            log.error("【重置密码】用户删除状态不正确, Id={}, delflag={}", jpalogin.getId(), jpalogin.getDelflag());
            throw new SellException(ResultEnum.USER_STATUS_ERROR);
        }
        String username = jpalogin.getUsername();
        jpalogin.setPassword(username);
        jpaLogin updateResult = userRepository.save(jpalogin);

        if (updateResult == null) {
            log.error("【重置密码失败】更新失败, jpaLogin={}", jpalogin);
            throw new SellException(ResultEnum.USER_UPDATE_FAIL);
        }
        return jpalogin;
    }

    @Override
    public Integer verify(String username, String password) {
        // 去数据库查
        jpaUser userinfo = loginRepository.findByUsername(username);

        if (userinfo == null) {
            log.error("用户不存在！{}", username);
            return LoginEnum.ERR_USN.getCode();
        }
        if (!password.equals(userinfo.getPwd())) {
            log.error("用户密码错误！{}");
            return LoginEnum.ERR_PWD.getCode();
        }


        return LoginEnum.SUCCESS.getCode();
    }

    @Override
    public jpaUser findOneUser(String username) {
        jpaUser user = loginRepository.findByUsername(username);
        if (user == null) {
            throw new SellException(ResultEnum.SELLER_NOT_EXIST);
        }
        return user;
    }

    /** 新增保存用户 */
    @Override
    public jpaLogin save(jpaLogin jpalogin) {
        return userRepository.save(jpalogin);
    }


}
