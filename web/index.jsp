<%@ page import="com.laboratory.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    HttpSession sysSession = request.getSession();
%>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/common/common.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
    $(function () {
        $(".student-ul li").mouseover(function () {
            $(this).siblings('li').removeClass('move');
            $(this).addClass('move');
        })
        $(".student-ul li").mouseout(function () {
            $(this).removeClass('move');
        })
        $(".student-ul li").click(function () {
            $(this).siblings('li').removeClass('selected');
            $(this).addClass('selected');
        })
        $("#home").click(function () {
            $(".message").show();
            $(".tableData").hide();
        })
        $("#laboratory").click(function () {
            $(".message").hide();
            $(".tableData").show();
            $(".laboratory").show();
            let laboratoryName = $("#laboratoryName").val();
            let laboratoryLocation = $("#laboratoryLocation").val();
            let status = $("#status").find("option:checked").val();
            sendAjax(laboratoryName,laboratoryLocation,status);
        })
        $("#search").click(function () {
            let laboratoryName = $("#laboratoryName").val();
            let laboratoryLocation = $("#laboratoryLocation").val();
            let status = $("#status").find("option:checked").val();
            sendAjax(laboratoryName,laboratoryLocation,status);
        })
        $("#student").click(function () {
            $(".message").hide();
            $(".tableData").show();
            $(".student").show();
            $(".laboratory").hide();
            let userName = $("#userName").val();
            let userNo = $("#userNo").val();
            sendStudentAjax(userName,userNo);
        })
        $("#studentSearch").click(function () {
            let userName = $("#userName").val();
            let userNo = $("#userNo").val();
            sendStudentAjax(userName,userNo);
        })
        function sendAjax(laboratoryName,laboratoryLocation,status) {
            $.ajax({
                url:"<%=basePath%>/laboratoryServlet",
                dataType: "json",
                type: "post",
                data: {
                    "laboratoryName": laboratoryName,
                    "laboratoryLocation": laboratoryLocation,
                    "status": status
                },
                success: function (data) {
                    if (data.success && data.code === 200) {
                        $(".table").empty(); //清空
                        // 查询成功
                        let items = "<tr><th>#</th><th>实验室名称</th><th>实验室地址</th><th>状态</th></tr>";
                        data = data.data
                        for (let i = 0; i < data.length; i++) {
                            let btnVal = "";
                            if (data[i].status === 0) {
                                btnVal = "<button type='button' class='btn btn-success'>空闲</button>";
                            } else {
                                btnVal = "<button type='button' class='btn btn-danger'>预约</button>";
                            }
                            items += "<tr><td>" + data[i].laboratoryId + "</td><td>" + data[i].laboratoryName + "</td><td>"
                                + data[i].laboratoryLocation + "</td><td>" + btnVal + "</td></tr>";
                        }
                        $(".table").append(items);
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            })
        }
        function sendStudentAjax(userName,userNo) {
            $.ajax({
                url:"<%=basePath%>/getStudentServlet",
                dataType: "json",
                type: "post",
                data: {
                    "userName": userName,
                    "userNo": userNo
                },
                success: function (data) {
                    if (data.success && data.code === 200) {
                        console.log(data.data)
                        $(".table").empty(); //清空
                        // 查询成功
                        let items = "<tr><th>#</th><th>学生姓名</th><th>学生学号</th><th>学生性别</th><th>备注</th></tr>";
                        data = data.data
                        for (let i = 0; i < data.length; i++) {
                            items += "<tr><td>"+ data[i].userId + "</td><td>" + data[i].userName + "</td><td>" + data[i].userNo + "</td><td>"
                                + data[i].userSex + "</td><td>" + data[i].remark + "</td></tr>";
                        }
                        $(".table").append(items);
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            })
        }
    })
</script>
<body>
<!--头部导航-->
<div class="top-nav">
    <div class="logo">
        <img src="image/logo.jpg" style="width: 100px;height: 100px">
        <h2>实验室管理系统</h2>
    </div>
</div>
<!--左侧导航-->
<div class="left-nav">
    <ul class="student-ul">
        <li class='selected' id='home'>
            <div><b> 首页 </b></div>
        </li>
        <c:forEach items="${menuList}" var="menu">
            <li id="${menu.menuKey}">
                <div><b> ${menu.menuName} </b></div>
            </li>
        </c:forEach>
    </ul>
</div>
<!--数据主体-->
<div class="datas">
    <%
        User user = (User) sysSession.getAttribute("user");
    %>
    <div class="message"><h3>欢迎你回来，<%=user.getUserName()%></h3></div>
    <div class="tableData">
        <form class="laboratory form-inline selectForm">
            <div class="form-group">
                <label for="laboratoryName">实验室名称：</label>
                <input type="text" class="form-control" id="laboratoryName" placeholder="请输入实验室名称">
            </div>
            <div class="form-group">
                <label for="laboratoryLocation">实验室地址：</label>
                <input type="text" class="form-control" id="laboratoryLocation" placeholder="请输入实验室地址">
            </div>
            <div class="form-group">
                <label for="status">实验室状态：</label>
                <select class="form-control" id="status">
                    <option value="-1">请选择实验室状态</option>
                    <option value="0">空闲</option>
                    <option value="1">预约</option>
                </select>
            </div>
            <button type="button" class="btn btn-default" id="search">搜索</button>
        </form>
        <form class="student form-inline selectForm">
            <div class="form-group">
                <label for="userName">学生姓名：</label>
                <input type="text" class="form-control" id="userName" placeholder="请输入学生姓名">
            </div>
            <div class="form-group">
                <label for="userNo">学生学号：</label>
                <input type="text" class="form-control" id="userNo" placeholder="请输入学生学号">
            </div>
            <button type="button" class="btn btn-default" id="studentSearch">搜索</button>
        </form>
        <table class="table table-bordered table-hover">
        </table>
    </div>
</div>
</body>
</html>