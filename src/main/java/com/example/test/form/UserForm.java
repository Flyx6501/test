package com.example.test.form;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserForm {
    @NotEmpty(message = "用户名必填")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码必填")
    private String password;
}
