<html>

<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#include "../common/header.ftl">
    <#include "../common/head.ftl">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div>
        <h2 class="col-md-offset-5">员工列表</h2>
    </div>


    <div id="page-content-wrapper">
        <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>员工编号</th>
                                <th>姓名</th>
                                <th>创建人</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list userPage.content as jpaLogin>
                            <tr>
                                <td>${jpaLogin.id}</td>
                                <td>${jpaLogin.username}</td>
                                <td>${jpaLogin.parentId}</td>
                                <td>${jpaLogin.getUserStatusEnum().message}</td>
                                <td>${jpaLogin.ctime}</td>
                                <td><a href="/user/detail?userId=${jpaLogin.id}">详情</a></td>
                                <td>
                                    <#if jpaLogin.getUserStatusEnum().message == "在岗">
                                        <a href="/user/newuser?userId=${jpaLogin.id}">修改</a>

                                    </#if>
                                </td>
                                <td> <#if jpaLogin.getUserStatusEnum().message == "在岗">
                                        <a href="/user/deluser?userId=${jpaLogin.id}">删除</a>
                                    <#else>
                                        <a href="/user/deluser?userId=${jpaLogin.id}">上岗</a>
                                    </#if>
                                </td>
                            </tr>
                           </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        <#--分页部分-->
<#if userPage.getTotalPages() gte 2>
            <div class="col-md-12 column">

                <ul class="pagination pull-right">
                    <#if currentPage lte 1>      <#--小于等于-->
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/user/userlist?parentId=${parentid}&page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>
                    <#list 1..userPage.getTotalPages() as index>
                         <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                    <li>  <a href="/user/userlist?parentId=${parentid}&page=${index}&size=10">${index}</a></li>
                    </#if>
                    </#list>




                    <#if currentPage gte userPage.getTotalPages()> <#--gte大于等于-->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/user/userlist?parentId=${parentid}&page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                </ul>

            </div>
            </#if>
    </div>
</div>

</body>



</html>