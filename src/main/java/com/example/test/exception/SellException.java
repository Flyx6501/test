package com.example.test.exception;

import com.example.test.enums.ResultEnum;

/**
 *
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        //把Message这个内容传到父类的构造方法里面
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
