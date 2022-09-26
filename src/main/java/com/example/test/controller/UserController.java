package com.example.test.controller;

import com.example.test.config.CookieConstant;
import com.example.test.config.ProjectUrlConfig;
import com.example.test.dao.LimitRepository;
import com.example.test.dto.LoginDTO;
import com.example.test.dto.RedlineDTO;
import com.example.test.jpaentity.jpaLimit;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaRedline;
import com.example.test.enums.LoginEnum;
import com.example.test.enums.ResultEnum;
import com.example.test.exception.SellException;
import com.example.test.form.UserForm;
import com.example.test.jpaentity.jpaLogin;
import com.example.test.jpaentity.jpaUser;
import com.example.test.service.*;
import com.example.test.util.Code;
import com.example.test.util.CookieUtil;
import com.example.test.util.Result;
import com.example.test.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
    private ILoginService iLoginService;
    @Autowired
    private JpaUserService jpaUserService;
    @Autowired
    private UserService userservice;
    @Autowired
    private JpaLimitService jpaLimitService;

    @Autowired
    private JpaRedlineService jparedlineservice;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /*navuserlist*/
    @GetMapping("/userlist")
    public ModelAndView userlist( HttpServletRequest request,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 Map<String,Object> map){
        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId = (Integer) myTokenMap.get("id");

        //Pageable pageable = PageRequest.of(0,5);
        PageRequest pagerequest =  PageRequest.of(page - 1,size);
        Page<LoginDTO> userPage = jpaUserService.findList(parentId,pagerequest);

        System.out.println(parentId);
        map.put("parentid", parentId);
        map.put("userPage", userPage);
        map.put("currentPage", page);//当前页给前端，为了使当前页码变灰
        map.put("size", size);
        return new ModelAndView("user/list",map);

    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("userId") Integer userId,
                               Map<String, Object> map) {
        LoginDTO login = jpaUserService.findDtoById(userId);
        List<jpaLimit>  limitList = jpaLimitService.findByUserId(userId);
        /*OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
*/
        map.put("LoginDTO", login);
        System.out.println(login);
        map.put("jpaLimitList", limitList);
        System.out.println(limitList);
        return new ModelAndView("user/detail", map);
    }
    @GetMapping("/newuser")
    public ModelAndView newuser(@RequestParam(value = "userId", required = false) Integer userId,
                               Map<String, Object> map) {
        if (!StringUtils.isEmpty(userId)) {
            LoginDTO login = jpaUserService.findDtoById(userId);
            List<jpaLimit>  limitList = jpaLimitService.findByUserId(userId);
            map.put("LoginDTO", login);
            System.out.println(login);
            map.put("jpaLimitList", limitList);
            System.out.println(limitList);
       }

        return new ModelAndView("user/newuser", map);
    }
    /**
     * 删除用户
     * @param userId
     * @param
     * @return
     */
    @GetMapping("/anbackpwd")
    public ModelAndView anbackpwd(@RequestParam("userId")Integer userId,
                                HttpServletRequest request,
                                Map<String, Object> map){


        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId = (Integer) myTokenMap.get("id");
        if(cookie==null) {
            System.out.println("未登录不可删除用户");

        }

        try {
            jpaLogin login = jpaUserService.findOne(userId);
            if(login.getUsername() == null){
                System.out.println("重置密码，用户查询失败");
                map.put("msg","重置密码，查询用户失败");//错误信息
            }

            System.out.println(login.getUsername());
            jpaUserService.backpwd(login);

        } catch (SellException e) {
            log.error("【管理后台端重置密码】发生异常{}", e);
            map.put("msg", e.getMessage());
            String url="/user/userlist?parentId="+parentId;
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ADUSER_BACKPWD_SUCCESS.getMessage());
        String url="/user/userlist?parentId="+parentId;
        map.put("url",url);//跳转的url
        return new ModelAndView("common/success");
    }

    /**
     * 删除用户
     * @param userId
     * @param
     * @return
     */
    @GetMapping("/deluser")
    public ModelAndView deluser(@RequestParam("userId")Integer userId,
                                HttpServletRequest request,
                                Map<String, Object> map){


            //通过cookie获取当前登录用户id
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            String usertoken =   cookie.getValue();
            Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId = (Integer) myTokenMap.get("id");
            if(cookie==null) {
                System.out.println("未登录不可删除用户");

            }

        try {
            jpaLogin login = jpaUserService.findOne(userId);
            if(login.getUsername() == null){
                System.out.println("删除用户 查询失败");
                map.put("msg","用户删除，查询用户失败");//错误信息
            }

            System.out.println(login.getUsername());
            jpaUserService.deluser(login);

        } catch (SellException e) {
            log.error("【管理后台端删除用户】发生异常{}", e);
            map.put("msg", e.getMessage());
            String url="/user/userlist?parentId="+parentId;
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ADUSER_DELETE_SUCCESS.getMessage());
        String url="/user/userlist?parentId="+parentId;
        map.put("url",url);//跳转的url
        return new ModelAndView("common/success");
    }

    @GetMapping("/addUser")
    public ModelAndView addUser(@RequestParam(value = "username", required = false)String username
                                ,Map<String,Object> map){

        if(!StringUtils.isEmpty(username)){
             jpaLogin login=jpaUserService.findByUsername(username);
        String pwd = login.getPassword();
        System.out.println(pwd);
         map.put("userInfo",login);
        }
        //查询山东proID为16所有市
        List<Map> prolist;
        prolist = userservice.findAreaById(16);
        map.put("prolist",prolist);


        map.put("username",username);
        return new ModelAndView("user/insert",map);
    }

    @PostMapping("/saveworker")
    public ModelAndView addWorker(@RequestParam(value = "id", required = false)Integer id,
            @RequestParam(value = "username", required = false)String username,
                                @RequestParam(value = "city", required = false)String city,
                                  @RequestParam(value = "xingb", required = false)String xingb,
                                  @RequestParam(value = "idcard", required = false)String idcard,
                                  @RequestParam(value = "ctime", required = false)String ctime,
                                HttpServletRequest request,
                                Map<String,Object> map) {
        System.out.println(username);
        System.out.println(city);
        System.out.println(xingb);
        System.out.println(idcard);
        jpaLogin loginhas = jpaUserService.findByIdcard(idcard);
        if(loginhas!=null){


            map.put("msg", "员工更新成功");

        }
        else{
            map.put("msg", "员工新增成功");
        }
        if(username==null || city==null || xingb==null || idcard==null){
            System.out.println("【添加用户失败】存在空值");

        }
        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId = (Integer) myTokenMap.get("id");
        if(cookie==null) {
            System.out.println("未登录不可添加用户");
            map.put("msg", "未登录用户不可操作");
            String url="/user/addUser";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }
        jpaLogin jpalogin = new jpaLogin();
        jpalogin.setDelflag(0);
        jpalogin.setParentId(parentId);
        jpalogin.setRole(0);
        jpalogin.setUsername(username);
        jpalogin.setCity(city);
        jpalogin.setXingb(xingb);
        jpalogin.setIdcard(idcard);
        jpalogin.setPassword(idcard);
        jpalogin.setId(id);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        Date ctime_new = formatter.parse(ctime, pos);

        jpalogin.setCtime(ctime_new);

        jpalogin.setUtime(currentTime_2);
        try{
            jpaUserService.save(jpalogin);
        }catch (SellException e){
            log.error("【员工添加】发生异常{}", e);
            map.put("msg", e.getMessage());
            String url="/user/addUser";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }


        String url="/user/userlist?parentId="+2;
        map.put("url",url);//跳转的url
        return new ModelAndView("common/success");
    }
    //更新和新建登录用户
    // todo此方法需要加事务
    @PostMapping("/save")
    public ModelAndView addUser(@RequestParam(value = "username", required = false)String username,
                                @RequestParam(value = "city", required = false)String city,
                                HttpServletRequest request,
                                Map<String,Object> map) {
        jpaLogin loginhas = jpaUserService.findByUsername(username);
       if(loginhas!=null){

            map.put("msg", "该用户名已存在");
            String url="/user/addUser";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }
        if(username==null || city==null){
            System.out.println("【添加用户失败】username或city参数为null");

        }
        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId = (Integer) myTokenMap.get("id");
        if(cookie==null) {
            System.out.println("未登录不可添加用户");
            map.put("msg", "未登录用户不可操作");
            String url="/user/addUser";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }
        /*System.out.println(username);
        System.out.println(city);*/
        jpaLogin jpalogin = new jpaLogin();
        jpalogin.setDelflag(0);
        jpalogin.setParentId(parentId);
        jpalogin.setPassword(username);
        jpalogin.setRole(0);
        jpalogin.setUsername(username);
        try{
            jpaUserService.save(jpalogin);
        }catch (SellException e){
            log.error("【管理后台端添加用户】发生异常{}", e);
            map.put("msg", e.getMessage());
            String url="/user/addUser";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }

        jpaLogin login = jpaUserService.findByUsername(username);
        Integer userId=login.getId();
            //以逗号分割，得出的数据存到 result 里面
        String[] result = city.split(",");
        for (String r : result) {
            Integer cityId = Integer.valueOf(r);
            //这个地方待优化，最好是能够从前端接收到citynamed
            Map citynameMap = userservice.findCityName(cityId,16);

           String cityname = String.valueOf(citynameMap.get("cityName"));
            jpaLimit limit = new jpaLimit();
            limit.setCityName(cityname);
            limit.setUserId(userId);
            limit.setCityId(cityId);
            jpaLimitService.save(limit);
            //System.out.println("分割结果是: " + r);
        }
//不通过这种循环遍历在前端设置为selected而是在userlist用户列表添加用户详情，在详情中展示该用户的权限
       /* List<jpaLimit> limitlist = jpaLimitService.findByUserId(userId);
        for (jpaLimit r : limitlist) {
            System.out.println("分割结果是: " + r);
        }*/

        map.put("msg", ResultEnum.ADUSER_ADD_SUCCESS.getMessage());
        String url="/user/userlist?parentId="+2;
        map.put("url",url);//跳转的url
        return new ModelAndView("common/success");
    }


    @GetMapping("/addRedline")
    public ModelAndView addRedline(@RequestParam(value = "cattleId", required = false)Integer cattleId,

            Map<String,Object> map){

        if (!StringUtils.isEmpty(cattleId)) {
            RedlineDTO cattle = jparedlineservice.findDtoById(cattleId);
//            List<jpaLimit>  limitList = jpaLimitService.findByUserId(cattleId);
            map.put("CattleDTO", cattle);
            System.out.println(cattle);
//            map.put("jpaLimitList", limitList);
//            System.out.println(limitList);
        }
        //查询山东proID为16所有市
//        List<Map> prolist;
//        prolist = userservice.findAreaById(16);
//        map.put("prolist",prolist);
        return new ModelAndView("redline/insert",map);
    }



    @PostMapping("/savecattle")
    public ModelAndView savecattle(@RequestParam(value = "cattle_id", required = false)Integer cattle_id,
                                  @RequestParam(value = "redline_name", required = false)String redline_name,
                                  @RequestParam(value = "birth", required = false)String birth,
                                  @RequestParam(value = "url", required = false)String url,
                                  @RequestParam(value = "update_time", required = false)String update_time,
                                  @RequestParam(value = "type", required = false)String type,
                                  @RequestParam(value = "status", required = false)String status,
                                  @RequestParam(value = "userid", required = false)Integer userid,
                                  @RequestParam(value = "ctime", required = false)String ctime,
                                  HttpServletRequest request,
                                  Map<String,Object> map) throws ParseException {
        System.out.println(redline_name);
        System.out.println(status);
        System.out.println(type);
        System.out.println(ctime);
        System.out.println(cattle_id);
//        jpaLogin loginhas = jpaUserService.findByIdcard(idcard);
////        if(loginhas!=null){
////
////            map.put("msg", "该员工已存在");
////            String url="/user/newuser";
////            map.put("url", url);
////            return new ModelAndView("common/error", map);
////        }
        if(redline_name==null || birth==null || url==null || status==null){
            System.out.println("【添加牛只失败】存在空值");

        }
        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer parentId = (Integer) myTokenMap.get("id");
        if(cookie==null) {
            System.out.println("未登录不可添加用户");
            map.put("msg", "未登录用户不可操作");
            String reurl="/user/addUser";
            map.put("url", reurl);
            return new ModelAndView("common/error", map);
        }
        jpaRedline jparedline = new jpaRedline();
        jparedline.setRedlineId(cattle_id);
        jparedline.setDelFlag(0);
        jparedline.setUserId(parentId);
        jparedline.setUrl(url);
        jparedline.setRedlineName(redline_name);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(currentTime);
//        ParsePosition pos = new ParsePosition(8);
        Date ctime_new = formatter.parse(ctime);

//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        String time = "2019-09-19";
//        Date date = ft.parse(time);

        jparedline.setStatus(status);
        jparedline.setType(type);
        jparedline.setUserId(userid);
        jparedline.setCreateTime(ctime_new);
        System.out.println("ctime_new"+ctime_new);
//        Date update_timenew=formatter.parse(update_time, pos);
//        jparedline.setUpdateTime(update_timenew);
        try{
            jparedlineservice.save(jparedline);
        }catch (SellException e){
            log.error("【牛只添加】发生异常{}", e);
            map.put("msg", e.getMessage());
            String reurl="/user/addRedline";
            map.put("url", reurl);
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "牛只添加成功");
        String reurl="/user/redlinelist";
        map.put("url",reurl);//跳转的url
        return new ModelAndView("common/success");
    }
    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/");

    @RequestMapping("/addRedlineIo")
    @ResponseBody
    //public Result fileupload(@RequestBody Map map ,HttpServletRequest request,HttpServletResponse response){
    //public  List<String> fileupload(HttpServletRequest request,HttpSession session, HttpServletResponse response,
    public ModelAndView fileupload(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                             @RequestParam(value = "city", required = false)String city,
                             @RequestParam("list") List<MultipartFile> list,
                                   Map<String,Object> map) throws IOException, ServletException {
        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        Integer userId = (Integer) myTokenMap.get("id");

        jpaRedline redlineNodel = jparedlineservice.findNodelByRedlineName(city);
        if(redlineNodel!=null){
            map.put("msg","已存在");
            String url="/user/addRedline";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }
        // List<MultipartFile> list =map.get("list");
        String filePaths = "";
        String message = "";

        //String realPath = uploadPath + format;
        String format = sdf.format(new Date());
        String realPath = uploadPath + format;
        // String realPath=request.getSession().getServletContext().getRealPath("/upload");
        System.out.println("存文件路径为："+realPath);
        ArrayList<String> arrayList = new ArrayList<>();
        //设置保存路径
        for (MultipartFile multipartFile:list){
            String originalFilename = new String(multipartFile.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");
            String substring = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            originalFilename = originalFilename.replace(substring, uuid.toString()+"");
            StringBuilder stringBuilder = new StringBuilder(realPath);
            //将完整路径拼接起来保存
            String path = stringBuilder.append("/").append(originalFilename).toString();

            System.out.println("存文件路径为："+path);
            File file = new File(path);
            if(!file.exists()){
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try {
                    //创建文件
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            //执行保存
            multipartFile.transferTo(new File(realPath,originalFilename));
            //将保存的路径存入集合返回

            //  String filePath = "D:/Program Files/IDEA/workSpace/testsave/target/classes/upload/"+originalFilename;
            String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+realPath+originalFilename;
            //String filePath = ResourceUtils.getURL("classpath:").getPath()+"/upload/"+originalFilename;
            if(filePaths.length()<=0){
                filePaths = filePaths+filePath;
            }else{  filePaths =filePaths+","+filePath;}


            //arrayList.add(filePath);
            message = "上传成功";
        }
        System.out.println("上传文件路径为："+filePaths);
       // return new Result(Code.GET_OK,filePaths,message);
        // return arrayList;
        jpaRedline redline = new jpaRedline();
        redline.setRedlineName(city);
        redline.setUrl(filePaths);
        redline.setDelFlag(0);
        redline.setUserId(userId);
        jparedlineservice.save(redline);
        map.put("msg", city+message);
        String url="/user/addRedline";
        map.put("url",url);//跳转的url
        return new ModelAndView("common/success");
    }

//牛只信息列表
    @GetMapping("/redlinelist")
    public ModelAndView redlinelist( HttpServletRequest request,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  Map<String,Object> map){
        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();
        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        if(myTokenMap==null) {
            log.error("【token过期");
            map.put("msg", "token已过期，请重新登陆");
            String url="/admin/login";
            map.put("url", url);
            return new ModelAndView("common/error", map);

        }
        Integer userId = (Integer) myTokenMap.get("id");

        //Pageable pageable = PageRequest.of(0,5);
        PageRequest pagerequest =  PageRequest.of(page - 1,size);
        Page<RedlineDTO> redlinePage = jparedlineservice.findList(userId,pagerequest);

        System.out.println(userId);
        //map.put("parentid", userId);
        map.put("userId", userId);
        map.put("redlinePage", redlinePage);
        map.put("currentPage", page);//当前页给前端，为了使当前页码变灰
        map.put("size", size);
        return new ModelAndView("redline/list",map);

    }

    /**
     * 删除用户
     * @param redlineId
     * @param
     * @return
     */
    @GetMapping("/delredline")
    public ModelAndView delredline(@RequestParam("redlineId")Integer redlineId,
                                HttpServletRequest request,
                                Map<String, Object> map){


        //通过cookie获取当前登录用户id
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String usertoken =   cookie.getValue();

        Map<String, Object> myTokenMap = TokenUtils.verify(usertoken);
        if(myTokenMap==null) {
            log.error("【token过期");
            map.put("msg", "token过期");
            String url="/user/login";
            map.put("url", url);
            return new ModelAndView("common/error", map);

        }
        Integer parentId = (Integer) myTokenMap.get("id");
        if(cookie==null) {
            System.out.println("未登录不可删除用户");

        }

        try {
            jpaRedline redline = jparedlineservice.findOne(redlineId);
            if(redline.getRedlineName() == null){
                System.out.println("删除用户 查询失败");
                map.put("msg","用户删除，查询用户失败");//错误信息
            }


            jparedlineservice.delredline(redline);

        } catch (SellException e) {
            log.error("【管理后台端删除红线文件】发生异常{}", e);
            map.put("msg", e.getMessage());
            String url="/user/redlinelist";
            map.put("url", url);
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.FILE_DELETE_SUCCESS.getMessage());
        String url="/user/redlinelist";
        map.put("url",url);//跳转的url
        return new ModelAndView("common/success");
    }
   /* @RequestMapping("/addRedlineIo")
    @ResponseBody
    //public Result fileupload(@RequestBody Map map ,HttpServletRequest request,HttpServletResponse response){
    //public  List<String> fileupload(HttpServletRequest request,HttpSession session, HttpServletResponse response,
    public Result fileupload(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                             @RequestParam("list") List<MultipartFile> list) throws IOException, ServletException {

        // List<MultipartFile> list =map.get("list");
        String filePaths = "";
        String message = "";
        //取出上传的文件 循环完成保存操作
        //String realPath = uploadPath + format;
        String format = sdf.format(new Date());
        String realPath = uploadPath + format;
       // String realPath=request.getSession().getServletContext().getRealPath("/upload");
        System.out.println("存文件路径为："+realPath);
        ArrayList<String> arrayList = new ArrayList<>();
        //设置保存路径
        for (MultipartFile multipartFile:list){
            String originalFilename = new String(multipartFile.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");
            String substring = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            originalFilename = originalFilename.replace(substring, uuid.toString()+"");
            StringBuilder stringBuilder = new StringBuilder(realPath);
            //将完整路径拼接起来保存
            String path = stringBuilder.append("/").append(originalFilename).toString();

            System.out.println("存文件路径为："+path);
            File file = new File(path);
            if(!file.exists()){
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try {
                    //创建文件
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            //执行保存
            multipartFile.transferTo(new File(realPath,originalFilename));
            //将保存的路径存入集合返回

            //  String filePath = "D:/Program Files/IDEA/workSpace/testsave/target/classes/upload/"+originalFilename;
            String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+realPath+originalFilename;
            //String filePath = ResourceUtils.getURL("classpath:").getPath()+"/upload/"+originalFilename;
            if(filePaths.length()<=0){
                filePaths = filePaths+filePath;
            }else{  filePaths =filePaths+","+filePath;}


            //arrayList.add(filePath);
            message = "上传成功";
        }
        System.out.println("上传文件路径为："+filePaths);
        return new Result(Code.GET_OK,filePaths,message);
        // return arrayList;
    }*/

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile fileUpload){
        if(fileUpload.isEmpty()){
            return "文件为空";
        }
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //指定本地文件夹存储图片
        String filePath = "D:/Program Files/IDEA/workSpace/testsave/target/classes/upload/";

        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }


}
