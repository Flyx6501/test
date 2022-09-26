package com.example.test.jpaentity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@DynamicUpdate
@Table(name="Ecolimit")//表名
public class jpaLimit {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略--递增形式
    private Integer limitId;
    private Integer userId;
    private Integer cityId;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
    private String cityName;
    private String xingb;
    private String idcard;
}
