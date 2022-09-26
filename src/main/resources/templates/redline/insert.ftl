<html>


<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <title>牛只识别检测与信息管理系统</title>
    <#--   <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
&lt;#&ndash;       <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">&ndash;&gt;
       <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">-->

    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/style.css">
    <script src="https://cdn.bootcss.com/moment.js/2.22.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

    <#-- <!-- 最新的 Bootstrap 核心 JavaScript 文件 &ndash;&gt;
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>-->

    <#--多选框-->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>


    <style>
        .table th, .table td {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body>
<div id="wrapper" class="toggled">

    <#include "../common/head.ftl">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div>
        <h2 class="col-md-offset-5">牛只信息</h2>
    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
        <form role="form" method="post" action="/user/savecattle">
        <#--                    <div class="form-group">-->
        <#--                        <label>员工编号</label>-->
        <#--                        <input name="userid" type="text" class="form-control" value="${(LoginDTO.id)!''}"/>&lt;#&ndash;这里因为是对象可能为空，所以要用括号括起来。如果为空就是空字符&ndash;&gt;-->
        <#--                    </div>-->
            <div class="form-group">
                <label>品种</label>
                <input name="redline_name" type="text" class="form-control" value="${(CattleDTO.redlineName)!''}"/>
            </div>
<#--            <div class="form-group">-->
<#--                <label>出生日期</label>-->
<#--                <input name="birth" type="datetime-local" class="measureDate" placeholder="请选择日期" value="${(jpaRedline.birth)!''}"/>-->
<#--&lt;#&ndash;                <input name="birth" type="text" class="form-control" value="${(jpaRedline.birth)!''}"/>&ndash;&gt;-->
<#--            </div>-->
            <div class="form-group">
                <label>胎次</label>
                <input name="url" type="text" class="form-control" value="${(CattleDTO.url)!''}"/>
            </div>
            <div class="form-group">
                <label>最近产犊日期</label>
                <input name="update_time" type="text" class="form-control" value="${(CattleDTO.update_time)!''}"/>
            </div>
            <div class="form-group">
                <label>牛只分类</label>
                <input name="type" type="text" class="form-control" value="${(CattleDTO.type)!''}"/>
            </div>
            <div class="form-group">
                <label>繁殖状态</label>
                <input name="status" type="text" class="form-control" value="${(CattleDTO.status)!''}"/>
            </div>
            <div class="form-group">
                <label>负责人</label>
                <input name="userid" type="text" class="form-control" value="${(CattleDTO.userId)!''}"/>
            </div>
            <input hidden type="text" name="cattle_id" value="${(CattleDTO.redlineId)!''}">
            <input hidden type="text" name="ctime" value="${(CattleDTO.createTime)!''}">
            <button type="submit" class="btn btn-default">提交</button>
        </form>
                </div>
            </div>
        </div>
    </div>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                </div>
            </div>
        </div>


    </div>
</div>
<script type="text/javascript">
    let date = new Date()
    let yyyy = date.getFullYear()
    let MM = (date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1)
    let dd = date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate()
    let HH = date.getHours() < 10 ? ("0" + date.getHours()) : date.getHours()
    let mm = date.getMinutes() < 10 ? ("0" + date.getMinutes()) : date.getMinutes()
    let ss = date.getSeconds() < 10 ? ("0" + date.getSeconds()) : date.getSeconds()

    let curDay = yyyy + '-' + MM + '-' + dd + ' ' + HH + ':' + mm + ':' + ss;

    $('.measureDate').val(curDay)
    console.log(date)
</script>


</body>



</html>