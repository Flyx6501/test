package com.example.test.dto;

import com.example.test.enums.UserStatusEnum;
import com.example.test.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
@Data
public class LoginDTO {
    private int id;
    private String username;
    private String password;
    private Integer role;
    private Date ctime;
    private Integer parentId;
    //1 表示已删除 0表示未删除
    private Integer delflag;
    private String xingb;
    private String idcard;
    private String city;
    private Date utime;

    @JsonIgnore
    public UserStatusEnum getUserStatusEnum() {
        return EnumUtil.getByCode(delflag, UserStatusEnum.class);
    }

}

