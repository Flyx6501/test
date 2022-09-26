package com.example.test.service.impl;

import com.example.test.dao.RedlineRepository;
import com.example.test.dao.UserDao;
import com.example.test.entity.Collect;
import com.example.test.entity.Category;
import com.example.test.entity.Login;
import com.example.test.enums.ResultEnum;
import com.example.test.exception.SellException;
import com.example.test.jpaentity.jpaRedline;
import com.example.test.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userservice;

    @Autowired
    private UserDao userdao;

    @Autowired
    private RedlineRepository relineRepository;


    public int insertCollect(Collect collect) {
        // TODO Auto-generated method stub
        return userdao.insertCollect(collect);
    }



    public Login login(@Param("username") String username,@Param("password") String password) {
        return userdao.login(username,password);
    }
    public Login findUserinfoByName(@Param("username")String username) {
        return userdao.findUserinfoByName(username);
    }

    //查找收藏位置根据用户id
    @Override
   public List<Collect> findCollectById(@Param("user_id")Integer user_id){
        return userdao.findCollectById(user_id);
    }

    public List<Category>findCategory() {
        return userdao.findCategory();
    }

    public List<Map>findCollectByCategory(@Param("category_id")Integer category_id) {
        return userdao.findCollectByCategory(category_id);
    }

    public List<Map>findAllNews() {
        List<Map> res = new ArrayList<Map>();
        Map<Object, Object> news = new HashMap<Object, Object>();
        List<Map> thumb = new ArrayList<Map>();
        List<Map> newss = userdao.findAllNews();


        for(int i = 0; i<newss.size(); i++){
            news= newss.get(i);
            Integer news_id = (Integer)news.get("news_id");
            System.out.println("获取的newsid为：" + news_id);
            thumb = userdao.findImgByNewsid(news_id);
            news.put("thumbEntities", thumb);
            res.add(news);
        }


        return res;
    }

    public  List<Map> findImgByNewsid(@Param("news_id")Integer news_id) {
    return userdao.findImgByNewsid(news_id);
    }

    //根据id查找并修改collcet表的数据
    public Integer updateCollectById(@Param("id")Integer id,
                                 @Param("category_id")Integer category_id) {
        return userdao.updateCollectById(id,category_id);
    }
    public Collect findCollectByKey(@Param("id")Integer id){
        return userdao.findCollectByKey(id);
    }


    //查找所有省名字
    public List<Map> findPro(@Param("proID")Integer proID) {

        return userdao.findPro(proID);
    }

    //根据proID查找某省的市
    public  List<Map> findAreaById(@Param("proID")Integer proID){

        return userdao.findAreaById(proID);
    }
    //根据安卓用户id查找该用户的权限城市
    public List<Map> findCityByLimit(@Param("proID")Integer proID, @Param("userId")Integer userId) {
        return userdao.findCityByLimit(proID,userId);
    }

    //查找未删除的红线文件

    @Override
    public List<jpaRedline> findByDelFlag(Integer delFlag) {
        List<jpaRedline> redlines = relineRepository.findByDelFlag(delFlag);
        if (redlines == null) {
            throw new SellException(ResultEnum.REDLINE_NOT_EXIST);
        }
        return redlines;
    }


    @Override
    public Map findCityName(@Param("cityID")Integer cityID,@Param("proID")Integer proID) {
        return userdao.findCityName(cityID,proID);
    }

}
