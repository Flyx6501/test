package com.example.test.dao;

import com.example.test.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public interface ILoginDao {
    public Login login(@Param("username") String username, @Param("password") String password);
    int register(@Param("username") String username, @Param("password") String password);
    Login findUserByName(@Param("username") String username);
}
