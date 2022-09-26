package com.example.test.service;

import com.example.test.dto.LoginDTO;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaUserService {
    /** 查询单个安卓用户判断是否可以新增. */
    jpaLogin findByUsername(String username);
    /** 查询单个员工判断是否可以新增. */
    jpaLogin findByIdcard(String idcard);
    /** 查询用户列表. */
    public Page<LoginDTO> findList(Integer parentId, Pageable pageable);
    jpaLogin findOne(Integer id);
    /** 查询单个员工用户 返回对象为LoginDTO. */
    LoginDTO findDtoById(Integer userId);

    /** 删除用户. */
    jpaLogin deluser(jpaLogin jpalogin);

    /** 重置密码. */
    jpaLogin backpwd(jpaLogin jpalogin);
    /** 登陆用，校验用户名和密码. */
    Integer verify(String username, String password);

    /** 通过username查询单个管理员用户. */
    jpaUser findOneUser(String username);
    /** 新增保存或更新员工 */
    jpaLogin save(jpaLogin jpalogin);
}
