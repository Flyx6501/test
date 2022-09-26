package com.example.test.controller;

import com.example.test.config.CookieConstant;
import com.example.test.config.ProjectUrlConfig;
import com.example.test.dao.UserRepository;
import com.example.test.entity.Category;
import com.example.test.entity.Collect;
import com.example.test.entity.Login;
import com.example.test.enums.LoginEnum;
import com.example.test.enums.ResultEnum;
import com.example.test.exception.SellException;
import com.example.test.form.UserForm;
import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaRedline;
import com.example.test.jpaentity.jpaUser;
import com.example.test.service.ILoginService;
import com.example.test.service.JpaRedlineService;
import com.example.test.service.JpaUserService;
import com.example.test.service.UserService;
import com.example.test.util.Code;
import com.example.test.util.CookieUtil;
import com.example.test.util.Result;
import com.example.test.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/admin")
public class   LoginController {
    /*@Resource(name = "ILoginService")
    ILoginService iLoginService;*/
    private ILoginService iLoginService;
    @Autowired
    private JpaUserService jpaUserService;
    @Autowired
    private UserService userservice;



    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @RequestMapping(value = "/logintest", method = RequestMethod.POST)
    private Result logintest(@RequestParam String username, @RequestParam String password) {
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
   //private Result login(@RequestBody Map login) {
    private Result login( @RequestParam String account, @RequestParam String pwd){
        Map<String, Object> claims = new HashMap<String, Object>();
        //Map<Object, Object> userinfo = new HashMap<Object, Object>();

        //核对密码和用户名
        String username = account;
        String password = pwd;
      /*  String username = login.getUsername();
        String password = login.getPassword();*/
        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        int userId = 0;
        try {
            Login  user = userservice.findUserinfoByName(username);
            Integer id =user.getId();

           Login userinfo = userservice.login(username, password);


            if (userinfo == null) {
                return new Result(Code.GET_ERROR, "用户名或密码错误");
            } else {
                //设置Token
                userId = userinfo.getId();
                claims.put("id", userId);
                claims.put("name", username);
                claims.put("pass", password);
                String myToken = TokenUtils.sign(claims);
                System.out.println("我的token数据：" + myToken);

                Map<String, Object> myTokenMap = TokenUtils.verify(myToken);
                System.out.println("token转化为Map：" + myTokenMap);

                return new Result(Code.GET_OK, myToken, "登陆成功");
            }
        } catch (Exception e) {

            e.printStackTrace();
            return new Result(Code.GET_ERROR, "系统错误");
        }

    }

    @RequestMapping(value = "/insertCollect", method = RequestMethod.POST)
    //  private Result login(@RequestBody Map getcollect) {
       private Result insertcollect( @RequestParam String account,@RequestParam String collectX,@RequestParam String collectY){
        //String account = (String)getcollect.get("account");
       // String collectX = (String)getcollect.get("collectX");
       // String collectY = (String)getcollect.get("collectY");
        //查到该account的id存到collect的user_id中
        Login login = userservice.findUserinfoByName(account);
        Integer userId = login.getId();
        Collect collect = new Collect();
        collect.setUser_id(userId);
        collect.setCollectX(collectX);
        collect.setCollectY(collectY);
        int n = userservice.insertCollect(collect);
        if(n>=1){
            return new Result(Code.GET_OK, null, "插入成功");
        }else{
            return new Result(Code.GET_ERROR, null, "插入失败");
        }

    }

    @RequestMapping(value = "/findArea", method = RequestMethod.GET)
    //  private Result login(@RequestBody Map getcollect) {
    private Result findArea( @RequestParam Integer proID){
        Map<Object, Object> data = new HashMap<Object, Object>();
        List<Map> prolist;
        if(proID==0) {
          prolist = userservice.findPro(proID);
        }else{
            prolist = userservice.findAreaById(proID);
        }
        data.put("list",prolist);
        if(prolist.size()>0) {
             return new Result(Code.GET_OK, data, "查询成功");
        }else
             return new Result(Code.GET_ERROR, data, "查询失败");
    }

    @RequestMapping(value = "/getLimit", method = RequestMethod.POST)
    //  private Result login(@RequestBody Map getcollect) {
    private Result getLimit( @RequestParam String account){
        System.out.println(account);
        jpaLogin login = jpaUserService.findByUsername(account);
        System.out.println("通过username查询的单个用户结果是: " + login);
        Integer userId = login.getId();

        Integer proID=16;
        String cityStr=null;

        List<Map> cityNameList = userservice.findCityByLimit(proID,userId);
        for (int i = 0; i<cityNameList.size();i++) {
           String cityname = (String) cityNameList.get(i).get("cityName");
           if(cityStr==null){
               cityStr=cityname;
           }else {
               cityStr = cityStr + "," + cityname;
           }
        }
        System.out.println("连接的结果是: " + cityStr);
        /*for (Map r : cityName) {
            cityStr
            //System.out.println("分割结果是: " + r);
        }*/
        System.out.println(cityNameList);
        if(cityNameList!=null){
            return new Result(Code.GET_OK, cityStr, "查询成功");
        }else{
            return new Result(Code.GET_ERROR, cityStr, "查询失败");
        }

    }
    @RequestMapping(value = "/getChoCity", method = RequestMethod.GET)
    //  private Result login(@RequestBody Map getcollect) {
    private Result getChoiceCity( @RequestParam String account){
        System.out.println(account);
        jpaLogin login = jpaUserService.findByUsername(account);
        System.out.println("通过username查询的单个用户结果是: " + login);
        Integer userId = login.getId();

        Integer proID=16;
        String cityStr=null;

        List<Map> cityNameList = userservice.findCityByLimit(proID,userId);
        for (int i = 0; i<cityNameList.size();i++) {
            String cityname = (String) cityNameList.get(i).get("cityName");
            if(cityStr==null){
                cityStr=cityname;
            }else {
                cityStr = cityStr + "," + cityname;
            }
        }
        System.out.println("连接的结果是: " + cityStr);
        /*for (Map r : cityName) {
            cityStr
            //System.out.println("分割结果是: " + r);
        }*/
        System.out.println(cityNameList);
        if(cityNameList!=null){
            return new Result(Code.GET_OK, cityStr, "查询成功");
        }else{
            return new Result(Code.GET_ERROR, cityStr, "查询失败");
        }

    }
    @RequestMapping(value = "/getRedline", method = RequestMethod.POST)
    //  private Result login(@RequestBody Map getcollect) {
    private Result getRedline(){


        Integer proID=16;
        String redlineStr=null;

        List<jpaRedline> redlineList = userservice.findByDelFlag(0);
        for (int i = 0; i<redlineList.size();i++) {
            String redlinename = (String) redlineList.get(i).getRedlineName();
            if(redlineStr==null){
                redlineStr=redlinename;
            }else {
                redlineStr = redlineStr + "," + redlinename;
            }
        }
        System.out.println("连接的结果是: " + redlineStr);
        /*for (Map r : cityName) {
            cityStr
            //System.out.println("分割结果是: " + r);
        }*/
        System.out.println(redlineList);
        if(redlineStr!=null){
            return new Result(Code.GET_OK, redlineStr, "查询成功");
        }else{
            return new Result(Code.GET_ERROR, redlineStr, "查询失败");
        }

    }


    @GetMapping("/login")
    public ModelAndView loginPage()
    {
        return new ModelAndView("user/login");
    }

    /**
     * 管理员登陆
     * @param
     * @param
     * @return
     */
    @PostMapping("verify")
    public ModelAndView verify(@Valid UserForm form,
                               BindingResult bindingResult,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "user/login");
            return new ModelAndView("common/error", map);
        }

        System.out.println(form.getUsername()+form.getPassword());

        Integer result = jpaUserService.verify(form.getUsername(), form.getPassword());
        if(result==0){
            System.out.println("【管理员端登陆】用户不存在");

            map.put("msg", ResultEnum.SELLER_NOT_EXIST.getMessage());
            map.put("url", "user/login");
            return new ModelAndView("common/error", map);
        }
        if(result==1){
            System.out.println("【管理员端登陆】密码错误");
            //log.error("【卖家端登陆】密码错误");
            map.put("msg", LoginEnum.ERR_PWD.getMessage());
            map.put("url", "user/login");
            return new ModelAndView("common/error", map);
        }


        jpaUser login = jpaUserService.findOneUser(form.getUsername());
        Map<String, Object> claims = new HashMap<String, Object>();
//设置Token
        Integer userId = login.getUser_id();
        String username = login.getUsername();
        claims.put("id", userId);
        claims.put("name", username);
        claims.put("pass", "password");
        String mytoken = TokenUtils.sign(claims);
        System.out.println("管理员登陆我的token数据：" + mytoken);

        Map<String, Object> myTokenMap = TokenUtils.verify(mytoken);
        System.out.println("管理员登陆token转化为Map：" + myTokenMap);

//2. 设置token至redis
        // String token = UUID.randomUUID().toString();
        // Integer expire = RedisConstant.EXPIRE;

        // redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), form.getPassword(), expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, mytoken, 7200);


        Integer parentId =  login.getUser_id();
        String url="/user/userlist?parentId="+parentId;
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", url);
        // return new ModelAndView("common/error", map);

        return new ModelAndView("redirect:"+projectUrlConfig.getEcp()+url);

        // return new ModelAndView("common/success",map);
    }

    @GetMapping("logout")
    public ModelAndView logout( HttpServletRequest request,
                                HttpServletResponse response,
                                Map<String, Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId =  (Integer)myTokenMap.get("id");
        System.out.println("管理员登陆token转化为Map：" + myTokenMap);
        if (cookie != null) {
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        String url="/user/userlist?parentId="+parentId;
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", url);
        return new ModelAndView("common/success", map);
    }

/*@GetMapping("/insert")
public ModelAndView insert(@RequestParam("username")String username
                            ,Map<String,Object> map){
        if(StringUtils.isEmpty(username)){
throw new SellException(ResultEnum.SELLER_NOT_EXIST);
    }
        jpaLogin login=jpaUserService.findOne(username);
    String pwd = login.getPassword();
    System.out.println(pwd);


        map.put("username",username);
    return new ModelAndView("user/insert",map);
}
    @GetMapping("/userlist")
    public ModelAndView userlist(@RequestParam("parentId")Integer parentId,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                     Map<String,Object> map){
        //Pageable pageable = PageRequest.of(0,5);
        PageRequest request =  PageRequest.of(page - 1,size);
    Page<jpaLogin> userPage = jpaUserService.findList(parentId,request);

        System.out.println(parentId);
        map.put("parentid", parentId);
    map.put("userPage", userPage);
    map.put("currentPage", page);//当前页给前端，为了使当前页码变灰
    map.put("size", size);
        return new ModelAndView("user/list",map);

    }
    *//**
     * 删除用户
     * @param id
     * @param
     * @return
     *//*
    @GetMapping("/deluser")
    public ModelAndView deluser(@RequestParam("id")Integer id,
                                HttpServletRequest request,
                                Map<String, Object> map){
        jpaLogin login = jpaUserService.findOne(id);
        System.out.println(login.getUsername());

            if(login.getUsername() == null){
                System.out.println("删除用户 查询失败");
                map.put("msg","用户删除，查询用户失败");//错误信息
                //to do需要获取当前登录管理员的id，用于查询该管理员下的用户
                Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
                String usertoken =   cookie.getValue();
                Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
                Integer parentId =  (Integer)myTokenMap.get("id");
                String url="/admin/userlist?parentId="+parentId;
               //  String url="/admin/userlist?parentId="+2;
                map.put("url",url);//跳转的url
                return new ModelAndView("common/error",map);
            }
        return new ModelAndView("common/error",map);
    }

    @GetMapping("user/login")
    public ModelAndView loginPage()
    {
        return new ModelAndView("user/login");
    }

    *//**
     * 管理员登陆
     * @param
     * @param
     * @return
     *//*
    @PostMapping("verify")
    public ModelAndView verify(@Valid UserForm form,
                             BindingResult bindingResult,
                             HttpServletResponse response,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "user/login");
            return new ModelAndView("common/error", map);
        }

        System.out.println(form.getUsername()+form.getPassword());

        Integer result = jpaUserService.verify(form.getUsername(), form.getPassword());
        if(result==0){
            System.out.println("【管理员端登陆】用户不存在");

            map.put("msg", ResultEnum.SELLER_NOT_EXIST.getMessage());
            map.put("url", "user/login");
            return new ModelAndView("common/error", map);
        }
        if(result==1){
            System.out.println("【管理员端登陆】密码错误");
            //log.error("【卖家端登陆】密码错误");
            map.put("msg", LoginEnum.ERR_PWD.getMessage());
            map.put("url", "user/login");
            return new ModelAndView("common/error", map);
        }


        jpaUser login = jpaUserService.findOneUser(form.getUsername());
        Map<String, Object> claims = new HashMap<String, Object>();
//设置Token
       Integer userId = login.getUser_id();
       String username = login.getUsername();
        claims.put("id", userId);
        claims.put("name", username);
        claims.put("pass", "password");
        String mytoken = TokenUtils.sign(claims);
        System.out.println("管理员登陆我的token数据：" + mytoken);

        Map<String, Object> myTokenMap = TokenUtils.verify(mytoken);
        System.out.println("管理员登陆token转化为Map：" + myTokenMap);

//2. 设置token至redis
        // String token = UUID.randomUUID().toString();
        // Integer expire = RedisConstant.EXPIRE;

        // redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), form.getPassword(), expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
         CookieUtil.set(response, CookieConstant.TOKEN, mytoken, 7200);


       Integer parentId =  login.getUser_id();
        String url="/admin/userlist?parentId="+parentId;
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", url);
        // return new ModelAndView("common/error", map);

        return new ModelAndView("redirect:"+projectUrlConfig.getEcp()+url);

       // return new ModelAndView("common/success",map);
    }

    @GetMapping("logout")
    public ModelAndView logout( HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

       String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
       Integer parentId =  (Integer)myTokenMap.get("id");
        System.out.println("管理员登陆token转化为Map：" + myTokenMap);
        if (cookie != null) {
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        String url="/admin/userlist?parentId="+parentId;
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", url);
        return new ModelAndView("common/success", map);
    }*/

    @RequestMapping(value = "/getCollectList", method = RequestMethod.GET)
    private Result getcollectlist(@RequestParam String account) {
        // private Result getcollectlist(@RequestHeader("token") String accessToken) {
        Map<String, Object> changed;
        Map<Object, Object> data = new HashMap<Object, Object>();

        Login login = userservice.findUserinfoByName(account);
        Integer userId = login.getId();
        List<Collect> collectList = userservice.findCollectById(userId);


        data.put("List", collectList);


        if (collectList.size() > 0) {
            return new Result(Code.GET_OK, data, "成功");
        } else {
            return new Result(Code.GET_ERROR, data, "暂无信息");
        }

    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private Result register(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        try {
            Login userinfo = iLoginService.findUserByName(username);
            if (userinfo != null) {
                return new Result(Code.SAVE_ERROR, "用户名已存在");
            } else {
                iLoginService.register(username, password);
                return new Result(Code.SAVE_OK, "注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Code.SAVE_ERROR, "系统错误");
        }


    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.POST)
    private Result userinfo(@RequestBody Map<String, Object> map) {
        Map<String, Object> changed = new HashMap<String, Object>();
       // Map<Object, Object> data = new HashMap<Object, Object>();
        // String token = (String) map.get("token");
        //changed = TokenUtils.verify(token);
        //String username = (String) changed.get("name");
        String username = (String) map.get("name");

        Login data = userservice.findUserinfoByName(username);

        return new Result(Code.GET_OK, data, "成功");
    }



    @RequestMapping(value = "/getCategory", method = RequestMethod.GET)
    //private Result getcategory(@RequestBody Map<String,Object> map) {
    private Result getCategory(/*@RequestHeader("token") String accessToken*/) {
        Map<Object, Object> data = new HashMap<Object, Object>();
        List<Category> categoryList = userservice.findCategory();
        Integer totalCount = categoryList.size();
        Integer pageSize = 10;
        Integer i = totalCount / pageSize + 1;
        Integer totalPage = i;
        Integer currPage = 1;

        data.put("List", categoryList);
        data.put("totalCount", totalCount);
        data.put("pageSize", pageSize);
        data.put("currPage", currPage);
        data.put("totalPage", totalPage);
        if(categoryList.size()>0){
            return new Result(Code.GET_OK, data, "成功");
        }else{
            return new Result(Code.GET_ERROR, data, "没有数据");
        }

    }


    @RequestMapping(value = "/getListByCategoryId", method = RequestMethod.GET)
    //private Result getListByCategoryId(@RequestBody Map<String,Object> map) {
    private Result getListByCategoryId(@RequestParam Integer categoryId, Integer page, Integer limit /*@RequestHeader("token") String accessToken*/) {
        Map<Object, Object> data = new HashMap<Object, Object>();
        List<Map> collectList = userservice.findCollectByCategory(categoryId);
        List<Map> res = new ArrayList<Map>();
//如果limit比大于总数 就不在遍历
        if(limit>=collectList.size()){
            if(page>1){
                //如果一直刷新page>1也就是不在第一页，就直接返回空数据，没有更多数据  
            }else{for(int i = 0; i<collectList.size(); i++){
                 res.add( collectList.get(i));
                System.out.println(page);
            }}

        }else {
            int start = (page - 1) * limit;
            for (int i = start; i < limit + 1; i++) {
                res.add(collectList.get(i));
            }
        }
        Integer totalCount = collectList.size();
        Integer pageSize = limit;
        Integer i = totalCount / pageSize + 1;
        Integer totalPage = i;
        Integer currPage = page;

        data.put("List", res);
        data.put("totalCount", totalCount);
        data.put("pageSize", pageSize);
        data.put("currPage", currPage);
        data.put("totalPage", totalPage);
        if(collectList.size()>0){
            return new Result(Code.GET_OK, data, "成功");
        }else{
            return new Result(Code.GET_ERROR, data, "没有数据");
        }
    }

    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
    //private Result getListByCategoryId(@RequestBody Map<String,Object> map) {
    private Result getnews(@RequestParam Integer page, Integer limit ) {
        Map<Object, Object> data = new HashMap<Object, Object>();
        List<Map> newsList = userservice.findAllNews();
        List<Map> res = new ArrayList<Map>();

        //如果limit比大于总数 就不在遍历
        if(limit>=newsList.size()){
            if(page>1){
                //如果一直刷新page>1也就是不在第一页，就直接返回空数据，没有更多数据
            }else{for(int i = 0; i<newsList.size(); i++){
                res.add( newsList.get(i));
                System.out.println(page);
            }}

        }else {
            int start = (page - 1) * limit;
            for (int i = start; i < limit + 1; i++) {
                res.add(newsList.get(i));
            }
        }
        Integer totalCount = newsList.size();
        Integer pageSize = limit;
        Integer i = totalCount / pageSize + 1;
        Integer totalPage = i;
        Integer currPage = page;

        data.put("List", res);
        data.put("totalCount", totalCount);
        data.put("pageSize", pageSize);
        data.put("currPage", currPage);
        data.put("totalPage", totalPage);
        if(newsList.size()>0){
            return new Result(Code.GET_OK, data, "成功");
        }else{
            return new Result(Code.GET_ERROR, data, "没有数据");
        }
    }

    /*@RequestMapping(value = "/updateCollect", method = RequestMethod.POST)
    private Result updatecollect(@RequestBody Map collcet) {
        int i = 0;

        Integer id = (Integer)collcet.get("vid");
        Integer type = (Integer)collcet.get("type");
        Integer flag = (Integer)collcet.get("flag");

        Collect coll  = userservice.findCollectByKey(id);

      Integer category_id= coll.getCategory_id();
        if(flag==1){
            ++category_id;

            i = userservice.updateCollectById(id,category_id);
        }else {
            --category_id;
            i = userservice.updateCollectById(id,category_id);

        }
        //如果flag为ture说明原本是点赞过，现在取消点赞，所以要减少点赞数。
        //如果flag为flase那么现在就是点赞操作，就要增加点赞数。

        System.out.println("前端传入的数据为" + id+type+flag);
        if(i>=1){
            return new Result(Code.GET_OK, "更新成功");
        }else{   return new Result(Code.GET_ERROR, "更新失敗");}

    }*/
}