package com.example.test.dao;

import com.example.test.dto.LoginDTO;
import com.example.test.jpaentity.jpaLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<jpaLogin,Integer> {
    //查找未删除的名为username的用户，判断是否可以新增
    @Query("select t from jpaLogin t where t.username =:username and t.delflag=0 ")
    jpaLogin findByUsername(String username);
    //查找未删除的名为username的用户，判断是否可以新增员工
    @Query("select t from jpaLogin t where t.idcard =:idcard and t.delflag=0 ")
    jpaLogin findByIdcard(String idcard);

   /* @Query("select t from jpaLogin t where t.username =:username and t.delflag=0 ")
    jpaLogin findByUsernameHas(String username);*/

    //@Query("select t from jpaLogin t where t.parentId =:parentId and t.delflag = 0 ")
    @Query("select t from jpaLogin t where t.parentId =:parentId ")
    Page<jpaLogin> findByParentId(Integer parentId, Pageable pageable);

    Optional<jpaLogin> findById(Integer id);
   /* @Select("select cost from fuwu_gongdan where id =#{id} and hasPay = 0 ")
    Fws_gongdan selectCost(String id);*/
    /** 返回对象为LoginDTO. */
    Optional<LoginDTO> findDtoById(Integer userId);
}
