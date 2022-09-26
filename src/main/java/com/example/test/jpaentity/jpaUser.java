package com.example.test.jpaentity;

import lombok.Data;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="User")//表名
public class jpaUser {
    @Id
    Integer user_id;
    String username;
    String pwd;
    Integer parent_id;
    Date createtime;
    Date updatetime;

}
