package com.example.test.jpaentity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@DynamicUpdate
@Table(name="Login")//表名
public class jpaLogin {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略--递增形式
    private Integer id;
    private String username;
    private String password;
    private Integer role;
    @CreatedDate
    @UpdateTimestamp
    private Date ctime;
    private Integer parentId;
    private Integer delflag;
    private String xingb;
    private String idcard;
    private String city;
    @CreatedDate
    @UpdateTimestamp
    private Date utime;
}
