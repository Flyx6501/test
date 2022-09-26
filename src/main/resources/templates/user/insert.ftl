<html>

<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#include "../common/head.ftl">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div>
        <h2 class="col-md-offset-5">新增安卓用户</h2>
    </div>


    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/user/save">
                        <div class="form-group">
                            <label>用户名</label><input name="username" class="form-control" VALUE="${(userInfo.username)!''}" />
                        </div>
                        <div class="form-group">
                            <label>授权城市</label>

                            <select name="city" class="selectpicker form-control" multiple data-live-search="true">
                                    <#list prolist as city>
                                        <option value="${city.cityID}">
                                            ${city.cityName}
                                        </option>
                                    </#list>
                            </select>
                        </div>

                        <#--<div class="form-group">
                            <label>已授权城市</label>

                            <select id="usertype" name="usertype" class="selectpicker form-control" multiple data-live-search="true">
                                <option value="0">苹果</option>
                                <option value="1">菠萝</option>
                                <option value="2">香蕉</option>
                                <option value="3">火龙果</option>
                                <option value="4">梨子</option>
                                <option value="5">草莓</option>
                                <option value="6">哈密瓜</option>
                                <option value="7">椰子</option>
                                <option value="8">猕猴桃</option>
                                <option value="9">桃子</option>
                            </select>
                        </div>-->
                        <input hidden type="text" name="userId" value="${(userInfo.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>


    </div>
</div>


</body>



</html>