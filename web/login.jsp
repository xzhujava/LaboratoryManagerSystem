<%@ page import="com.laboratory.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/common/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript">
    /**
     * 登录按钮触发登录请求
     */
    $(function () {
        $("#loginSubmit").click(function () {
            let userNo = $('#inputUserNo').val();
            let userNoReg = /^[1-9]\d*$/;
            let userPassword = $('#inputPassword').val();
            var isUserNo = userNoReg.test(userNo);
            if(userNo.trim().length<1 || !isUserNo){
                alert("学号格式错误！");
                return;
            }
            if(userPassword.trim().length<1){
                alert("密码不应为空！");
                return;
            }
            $.ajax({
                url:"<%=basePath%>/loginServlet",
                dataType:"json",
                type:"post",
                data:{
                    "userNo":userNo,
                    "userPassword":userPassword
                },
                success:function (data) {
                    console.log(data.code);
                    if(data.success){
                        // 登录成功，跳转到首页
                        window.location.href="index.jsp";
                    }
                },
                error:function (data) {
                    alert(data.responseText)
                }
            })
        });
    })
    /**
     * 注册按钮触发注册请求
     */
    $(function () {
        $("#registerSubmit").click(function () {
            let userName = $('#inputUserName_register').val();
            let userNo = $('#inputUserNo_register').val();
            let userNoReg = /^[1-9]\d*$/;
            let userPassword = $('#inputPassword_register').val();
            let userSex = $('input[name="sex"]:checked').val();
            let remark = $('#remark').val();
            if(userName.trim().length<1){
                alert("用户名不应为空！");
                return;
            }
            var isUserNo = userNoReg.test(userNo);
            if(userNo.trim().length<1 || !isUserNo){
                alert("学号格式错误！");
                return;
            }
            if(userPassword.trim().length<6){
                alert("请保证密码在6位及以上！");
                return;
            }
            $.ajax({
                url:"<%=basePath%>/registerServlet",
                dataType:"json",
                type:"post",
                data:{
                    "userName":userName,
                    "userNo":userNo,
                    "userPassword":userPassword,
                    "userSex":userSex,
                    "remark":remark
                },
                success:function (data) {
                    console.log(data.code);
                    if(data.success){
                        // 注册成功，跳转到首页
                        window.location.href="index.jsp";
                    }
                },
                error:function (data) {
                    alert(data.responseText)
                }
            })
        });
    })
</script>
<body>
<div class="title"></div>
<div class="login">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="inputUserNo" class="col-md-4 control-label">学/工号：</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="inputUserNo" placeholder="请输入你的学号或工号">
            </div>
        </div>

        <div class="form-group">
            <label for="inputPassword" class="col-sm-4 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="inputPassword" placeholder="请输入你的密码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        没有账号? <a href="#" id="gotoRegister">去注册</a>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-3">
                <button type="button" class="btn btn-default" id="loginSubmit">登录</button>
            </div>
            <div class=" col-sm-3">
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </div>
    </form>
</div>
<div class="register">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="inputUserName_register" class="col-md-4 control-label">姓名：</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="inputUserName_register" placeholder="请输入你的姓名">
            </div>
        </div>
        <div class="form-group">
            <label for="inputUserNo_register" class="col-md-4 control-label">学号：</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="inputUserNo_register" placeholder="请输入你的学号">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword_register" class="col-sm-4 control-label">密码：</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="inputPassword_register" placeholder="请输入你的密码">
            </div>
        </div>
        <div class="form-group">
            <label for="inlineRadioM" class="col-sm-4 control-label">性别：</label>
            <label class="radio-inline">
                <input type="radio" name="sex" checked id="inlineRadioM" value="男"> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" id="inlineRadioW" value="女"> 女
            </label>
        </div>
        <div class="form-group">
            <label for="remark" class="col-sm-4 control-label">备注：</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="remark" placeholder="请输入备注信息">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        已有账号? <a href="#" id="gotoLogin">去登录</a>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-3">
                <button type="button" class="btn btn-default" id="registerSubmit">注册</button>
            </div>
            <div class=" col-sm-3">
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
