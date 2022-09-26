package com.example.test.service.impl;

import com.example.test.dao.LimitRepository;
import com.example.test.dao.UserRepository;
import com.example.test.enums.ResultEnum;
import com.example.test.exception.SellException;
import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.service.JpaLimitService;
import com.example.test.service.JpaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JpaLimitServiceImpl implements JpaLimitService {

    @Autowired
    private LimitRepository limitRepository;

    /** 新增保存安卓端用户 */
    @Override
    public jpaLimit save(jpaLimit jpalimit) {
        return limitRepository.save(jpalimit);
    }
    /** 根据userId查询该用户的城市权限 */
    @Override
    public  List<jpaLimit> findByUserId(Integer userId) {
        List<jpaLimit> limit = limitRepository.findByUserId(userId);
        if (limit == null) {
            throw new SellException(ResultEnum.LIMIT_NOT_EXIST);
        }
        return limit;
    }


}
