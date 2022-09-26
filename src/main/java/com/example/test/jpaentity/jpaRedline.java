package com.example.test.jpaentity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@DynamicUpdate
@Table(name="Ecoredline")//表名
public class jpaRedline {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略--递增形式
    private Integer redlineId;
    private String redlineName;
    private Integer userId;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
    private String url;
    private Integer delFlag;
    private String type;
    private String status;
}
