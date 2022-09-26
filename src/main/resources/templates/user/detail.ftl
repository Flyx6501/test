<html>

<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#include "../common/head.ftl">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div>
        <h2 class="col-md-offset-5">员工详情</h2>
    </div>
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>员工id</th>
                            <th>员工姓名</th>
                            <th>员工状态</th>
<#--                            <th>操作</th>-->
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                           <td>${LoginDTO.id}</td>
                           <td>${LoginDTO.username}</td>
<#--                            <td>${orderDTO.orderAmount}</td>-->
                            <td>${LoginDTO.getUserStatusEnum().message}</td>
<#--                            <td><a href="" type="button" >授权更多</a></td>-->
                        </tr>
                        </tbody>
                    </table>
                </div>

                <#--员工详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>员工姓名</th>
                            <th>籍贯</th>
                            <th>性别</th>
                            <th>身份证号</th>
                            <th>创建时间</th>
                            <th>更新时间</th>
                            <th <#--colspan="2"-->>操作</th>
                        </tr>
                        </thead>
                        <tbody>
<#--                        <#list jpaLimitList as limitDetail>-->
                            <tr>
                                <td>${LoginDTO.username}</td>
                                <td>${LoginDTO.city}</td>
                                <td>${LoginDTO.xingb}</td>
                                <td>${LoginDTO.idcard}</td>
                                <td>${LoginDTO.ctime}</td>
                                <td>${LoginDTO.utime}</td>
                                <td>  <a href="/user/newuser?userId=${LoginDTO.id}">修改</a></td>

                            </tr>
<#--                        </#list>-->
                        </tbody>
                    </table>
                </div>

                <#--操作-->
               <#-- <div class="col-md-12 column">
                    <a href="" type="button" class="btn btn-default btn-info">收回权限</a>
                    <a href="" type="button"  class="btn btn-default btn-warning">授权更多</a>
                    <td>收回权限</td>
                    <td>授权更多</td>
                    <#if orderDTO.getOrderStatusEnum().message == "新订单">
                        <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-info">完结订单</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button"  class="btn btn-default btn-warning">取消订单</a>
                    </#if>
                </div>-->
            </div>
        </div>
    </div>
</div>
</body>



</html>