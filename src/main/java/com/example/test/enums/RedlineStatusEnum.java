package com.example.test.enums;
import lombok.Getter;

@Getter
public enum RedlineStatusEnum implements CodeEnum{
    ONLINE(0, "存栏"),
    DELETED(1, "已出栏"),
            ;

    private Integer code;

    private String message;

    RedlineStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMsg() {
        return null;

    }
}
