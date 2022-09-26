package com.example.test.service;

import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;

import java.util.List;

public interface JpaLimitService {

    /** 新增安卓端用户城市权限 */
    jpaLimit save(jpaLimit jpalimit);
    /** 通过userId查找城市权限 */
    List<jpaLimit> findByUserId(Integer UserId);


}
