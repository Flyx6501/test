package com.example.test.service.impl;

import com.example.test.dao.ILoginDao;
import com.example.test.entity.Login;
import com.example.test.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private ILoginDao iLoginDao;
    @Override
    public Login login(String username, String password) {

        return iLoginDao.login(username, password);
    }

    @Override
    public int register(String username, String password)  {
        return iLoginDao.register(username, password);
    }
    public Login findUserByName(String username){
        return iLoginDao.findUserByName(username);
    }
}
