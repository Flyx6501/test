package com.example.test.dto;

import com.example.test.enums.RedlineStatusEnum;
import com.example.test.enums.UserStatusEnum;
import com.example.test.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class RedlineDTO {
    private Integer redlineId;
    private String redlineName;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
    private String url;
    private Integer delFlag;
    private String birth;
    private String type;
    private String status;

    @JsonIgnore
    public RedlineStatusEnum getRedlineStatusEnum() {
        return EnumUtil.getByCode(delFlag, RedlineStatusEnum.class);
    }
}
