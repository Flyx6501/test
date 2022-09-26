<html>

<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#include "../common/head.ftl">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div>
        <h2 class="col-md-offset-5">员工信息</h2>
    </div>
<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" method="post" action="/user/saveworker">
<#--                    <div class="form-group">-->
<#--                        <label>员工编号</label>-->
<#--                        <input name="userid" type="text" class="form-control" value="${(LoginDTO.id)!''}"/>&lt;#&ndash;这里因为是对象可能为空，所以要用括号括起来。如果为空就是空字符&ndash;&gt;-->
<#--                    </div>-->
                    <div class="form-group">
                        <label>员工姓名</label>
                        <input name="username" type="text" class="form-control" value="${(LoginDTO.username)!''}"/><#--这里因为是对象可能为空，所以要用括号括起来。如果为空就是空字符-->
                    </div>
                    <div class="form-group">
                        <label>性别</label>
                        <input name="xingb" type="text" class="form-control" value="${(LoginDTO.xingb)!''}"/><#--这里因为是对象可能为空，所以要用括号括起来。如果为空就是空字符-->
                    </div>
                    <div class="form-group">
                        <label>籍贯</label>
                        <input name="city" type="text" class="form-control" value="${(LoginDTO.city)!''}"/><#--这里因为是对象可能为空，所以要用括号括起来。如果为空就是空字符-->
                    </div>
                    <div class="form-group">
                        <label>身份证号</label>
                        <input name="idcard" type="text" class="form-control" value="${(LoginDTO.idcard)!''}"/><#--这里因为是对象可能为空，所以要用括号括起来。如果为空就是空字符-->
                    </div>
                    <input hidden type="text" name="id" value="${(LoginDTO.id)!''}">
                    <input hidden type="text" name="ctime" value="${(LoginDTO.ctime)!''}">
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
<#include "../common/footer.ftl">
</body>
</html>