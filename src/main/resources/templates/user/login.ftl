<!DOCTYPE html>
<html>
<head>
    <title>牛体姿态估计信息管理系统-用户中心|登录</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>


<style type="text/css">

#bg {
   /* background-color:#b0c4de;*/
    background: url(http://img0.baidu.com/it/u=2763925783,2437093317&fm=253&app=138&f=JPEG?w=755&h=500) no-repeat;
    position: fixed;
    z-index: -1;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-size: cover;
}

a {
    cursor: pointer;
}

.row {
    margin: 0px;
}

.show {
    position: absolute;
    left: 0;
    top: 50px;
    bottom: 0;
    right: 0;
    margin: auto;
}

.title {
    text-align: center;
    font-weight: bold;
}

.top-line {
    margin: 0 50px;
}

.form-title {
    text-align: center;
}

.login-panel {
    background-color: white;
    width: 300px;
    padding: 40px 30px;
    border-radius: 5px;
    margin: 0 auto;
    margin-top: 30px;
    z-index: 999;
}

.vcode {
    cursor: pointer;
}

#vcode-panel {
    padding: 0px;
}

.form-group {
    margin-top: 28px;
}

#submit {
    width: 100%;
}

.order {
    line-height: 60px;
    text-align: center;
}

.order .line {
    display: inline-block;
    width: 10%;
    border-top: 1px solid #ccc;
}

.order .txt {
    color: #686868;
    vertical-align: middle;
}

.btn-login {
    background: white;
    padding: 5px;
}

.btn-login:first-child {
    border-radius: 3px;
    background: #f48fb1;
    border: #ff4081 solid 1px;
}

.btn-login:first-child:hover {
    background: #f06292;
}

.btn-login:last-child {
    border-radius: 3px;
    background: #a5d6a7;
    border: #00e676 solid 1px;
}

.btn-login:last-child:hover {
    background: #81c784;
}

.switch {
    width: 300px;
    margin: 0 auto;
    margin-top: 30px;
    text-align: center;
}

.switch-action {
    z-index: 999;
    width: 100%;
    background-color: rgba(255, 230, 255, .12);
    color: white;
    padding: 15px;
    border: #2b542c 1px solid;
    border-radius: 5px;
    margin-bottom: 30px;
}

.switch-action:hover {
    background-color: rgba(255, 160, 200, .12);
}

.switch-action span {
    font-weight: bold;
}

.bottom-info {
    width: 100%;
    margin: 10px 0px 20px 0px;
    text-align: center;
    color: white;
}

.bottom-info a {
    text-decoration: none;
    color: white;
}

.bottom-info a:hover {
    color: #5bc0de;
}
  </style>

<script type="text/javascript">
    var code;
    function createCode()
    {
            code = "";
            var codeLength = 6; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
        for(var i = 0; i < codeLength; i++)
        {
            //floor() 方法返回小于等于x的最大整数。
            var charNum = Math.floor(Math.random() * 52);
            code += codeChars[charNum];
        }
        if(checkCode)
        {
            checkCode.className = "code";
            checkCode.innerHTML = code;
        }
}
    function validateCode()
{
    var inputCode=document.getElementById("inputCode").value;
    if(inputCode.length <= 0)
         {

               alert("请输入验证码！");
             return false;
        }
            else if(inputCode.toUpperCase() != code.toUpperCase())
            {

                alert("验证码输入有误！");
                createCode();
                return false;
            }
            else
                {return true;}
}
</script>


</head>
<body onload="createCode()">
<div id="bg"></div>
<div class="show">
  <#--  <h3 class="title">牛只姿态估计信息管理系统</h3>-->
    <div class="login-panel">
       <#-- <h4 class="form-title">登录&nbsp;牛只姿态估计信息管理系统</h4>-->
        <h4 class="title">牛体姿态估计信息管理系统</h4>
        <hr class="top-line">
       <form role="form" method="post" action="/admin/verify" onsubmit="return validateCode()">
                   <div class="form-group">
                       <label for="username">用户名</label>
                       <input type="text" class="form-control" name="username" id="username">
                   </div>
                   <div class="form-group">
                       <label for="password">密码</label>
                       <input type="password" class="form-control" name="password" id="password">
                   </div>
                   <div class="form-group">
                       <label for="vcode">验证码</label>
                       <div class="row">
                           <div class="col-md-6 col-sm-6 col-xs-6" id="vcode-panel">
                               <input type="text" class="form-control" id="inputCode">
                           </div>

                        <div>
                            <table border="0" cellspacing="5" cellpadding="5" >
                                <tr>
                                    <td></td>
                                    <td><div class="code" id="checkCode"  ></div></td>
                                    <td><a href="#" onclick="createCode()">看不清<br>换一张</a></td>
                                </tr>
                            </table>
                        </div>

                       </div>
                   </div>

                   <div class="form-group">
                   <button type="submit" class="btn btn-info form-control" >登录</button>
                              <!-- <button type="submit" class="btn btn-info" onclick="validateCode();">登录</button>-->

                     <!--  <a href="/sell/seller/order/login/index?username=admin&&password=admin">登录</a> -->
                   </div>


               </form>
    </div>

    <<#--div class="switch row">
        <button class="switch-action" id="register">&lt;&nbsp;还没有账号？<span>立即注册</span></button>
    </div>

    <div class="bottom-info">
        Powered By：<a href="#">刘祥</a>&nbsp;•&nbsp;<a href="#">关于我们</a>
    </div>-->
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/login.js"></script>

</body>
</html>