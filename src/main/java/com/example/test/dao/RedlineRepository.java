package com.example.test.dao;

import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaRedline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RedlineRepository extends JpaRepository<jpaRedline,Integer> {
    List<jpaRedline> findByDelFlag(Integer delFlag);
        //根据userId查找该用户上传的红线文件
    @Query("select t from jpaRedline t where t.userId =:userId ")
    Page<jpaRedline> findByUserId(Integer userId, Pageable pageable);

    @Query("select t from jpaRedline t where t.redlineName =:redlineName and t.delFlag=0")
    jpaRedline findNodelByRedlineName(String redlineName);

    Optional<jpaRedline> findByRedlineId(Integer redlineId);
}
