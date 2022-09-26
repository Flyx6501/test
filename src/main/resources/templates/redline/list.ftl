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
        <h2 class="col-md-offset-5">牛只资料列表</h2>
    </div>


    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>牛只编号</th>
                            <th>品种</th>
<#--                            <th>出生日期</th>-->
                            <th>胎次</th>
                            <th>最近产犊日期</th>
                            <th>牛只分类</th>
                            <th>繁殖状态</th>
                            <th>负责人</th>
                            <th>存栏情况</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list redlinePage.content as jpaRedline>
                            <tr>
                                <td>${jpaRedline.redlineId}</td>
                                <td>${jpaRedline.redlineName}</td>
<#--                                <td>${jpaRedline.birth}</td>-->
                                <td>${jpaRedline.url}</td>

                                <td>${jpaRedline.createTime}</td>
                                <td>${jpaRedline.type}</td>
                                <td>${jpaRedline.status}</td>
                                <td>${jpaRedline.userId}</td>
                                <td>${jpaRedline.getRedlineStatusEnum().message}</td>
<#--                                <td>详情</td>-->
                                <td>
                                    <#if jpaRedline.getRedlineStatusEnum().message == "存栏">
                                        <a href="/user/addRedline?cattleId=${jpaRedline.redlineId}">修改</a>

                                    </#if>
                                </td>
                                <td>
                                    <#if jpaRedline.getRedlineStatusEnum().message == "存栏">
                                        <a href="/user/delredline?redlineId=${jpaRedline.redlineId}">删除</a>
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
        <#if redlinePage.getTotalPages() gte 2>
            <div class="col-md-12 column">

                <ul class="pagination pull-right">
                    <#if currentPage lte 1>      <#--小于等于-->
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/user/redlinelist?userId=${userId}&page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>
                    <#list 1..redlinePage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li>  <a href="/user/redlinelist?userId=${userId}&page=${index}&size=10">${index}</a></li>
                        </#if>
                    </#list>




                    <#if currentPage gte redlinePage.getTotalPages()> <#--gte大于等于-->
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/user/redlinelist?parentId=${parentid}&page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                </ul>

            </div>
        </#if>
    </div>
</div>

</body>



</html>