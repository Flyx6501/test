package com.example.test.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum implements CodeEnum{
    ONLINE(0, "在岗"),
    DELETED(1, "离岗"),
            ;

    private Integer code;

    private String message;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
