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
        <h2 class="col-md-offset-5">新增红线文件</h2>
    </div>


    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="POST" action="/user/addRedlineIo" enctype="multipart/form-data">

                        <div class="form-group">
                            <label>城市名称</label>

                            <select name="city" class="selectpicker form-control"  >
                                <#list prolist as city>
                                    <option value="${city.cityName}">
                                        ${city.cityName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <#--  <div class="form-group">
                            <label for="exampleInputPassword1">Password</label><input type="password" class="form-control" id="exampleInputPassword1" />
                        </div>-->
                        <div class="form-group">
                            <label for="exampleInputFile">文件上传</label><input name="list" type="file" id="exampleInputFile" />
                            <p class="help-block">
                                请选择地图shp文件
                            </p>
                        </div>
                       <#-- <div class="checkbox">
                            <label><input type="checkbox" />Check me out</label>
                        </div>-->
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>


    </div>
</div>


</body>



</html>