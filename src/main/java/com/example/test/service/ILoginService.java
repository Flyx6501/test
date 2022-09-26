package com.example.test.service;

import com.example.test.entity.Login;

import java.util.Map;


public interface ILoginService {
    public Login login(String username, String password);
    int register(String username, String password);
Login findUserByName(String username);
}
