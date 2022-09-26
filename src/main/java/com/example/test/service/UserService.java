package com.example.test.service;



import com.example.test.entity.Collect;
import com.example.test.entity.Category;
import com.example.test.entity.Login;
import com.example.test.jpaentity.jpaRedline;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    int insertCollect(Collect collect);

    public Login login(@Param("username") String username,@Param("password") String password);

    public Login findUserinfoByName(String username);

    List<Collect> findCollectById(@Param("user_id")Integer user_id);

    List<Category>findCategory();

    List<Map>findCollectByCategory(@Param("category_id")Integer category_id);

    List<Map>findAllNews();

    List<Map> findImgByNewsid(@Param("news_id")Integer news_id);


    Integer updateCollectById(@Param("id")Integer id,
                          @Param("category_id")Integer category_id);

    Collect findCollectByKey(@Param("id")Integer id);

    //查找所有省名字
    List<Map> findPro(@Param("proID")Integer proID);
    //根据proID查找某省的市
    List<Map> findAreaById(@Param("proID")Integer proID);

    List<Map> findCityByLimit(@Param("proID")Integer proID, @Param("userId")Integer userId);

    //查找未删除的牛只信息
    List<jpaRedline> findByDelFlag(Integer delFlag);
    Map findCityName(@Param("cityID")Integer cityID,@Param("proID")Integer proID);


}
