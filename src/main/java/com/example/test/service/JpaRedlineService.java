package com.example.test.service;

import com.example.test.dto.LoginDTO;
import com.example.test.dto.RedlineDTO;
import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaRedline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JpaRedlineService {
    //查找未删除的牛只信息
    List<jpaRedline> findByDelFlag(Integer delFlag);

    /** 新增或修改牛只信息 */
    jpaRedline save(jpaRedline jparedline);

    /** 查询牛只信息列表. */
    public Page<RedlineDTO> findList(Integer userId, Pageable pageable);
    jpaRedline  findNodelByRedlineName(String redlineName);

    /** 查询单个牛只信息判断是否存在. */
    jpaRedline findOne(Integer redlineId);

    /** 删除牛只信息. */
    jpaRedline delredline(jpaRedline jparedline);

    /** 查询单个牛只 返回对象为RedlineDTO. */
    RedlineDTO findDtoById(Integer cattleId);
}
