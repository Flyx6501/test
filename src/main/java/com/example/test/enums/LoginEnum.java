package com.example.test.enums;

import lombok.Getter;

@Getter
public enum LoginEnum implements CodeEnum{
    ERR_USN(0, "用户不存在"),
    ERR_PWD(1, "密码错误"),
    SUCCESS(2, "成功");

    private Integer code;

    private String message;

    LoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getMsg() {
        return message;
    }
}
