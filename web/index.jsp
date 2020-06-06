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
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/common/common.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
    $(function () {
        $('#reservationTime').datetimepicker({
            format: 'YYYY-MM-DD hh:mm:ss',
            locale: moment.locale('zh-cn')
        });
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
            $(".student").hide();
            $(".reservation").hide();
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
            $(".reservation").hide();
            let userName = $("#userName").val();
            let userNo = $("#userNo").val();
            sendStudentAjax(userName,userNo);
        })
        $("#studentSearch").click(function () {
            let userName = $("#userName").val();
            let userNo = $("#userNo").val();
            sendStudentAjax(userName,userNo);
        })
        $("#reservation").click(function () {
            $(".message").hide();
            $(".tableData").show();
            $(".student").hide();
            $(".laboratory").hide();
            $(".reservation").show();
            let laboratoryId = $("#laboratoryId").val();
            let status = $("#reservation-status").val();
            sendLaboratoryAjax();
            sendReservationAjax(laboratoryId,status);
        })
        $("#reservationSearch").click(function () {
            let laboratoryId = $("#laboratoryId").val();
            let status = $("#reservation-status").val();
            sendReservationAjax(laboratoryId,status);
        })
        $("#admin-laboratory").click(function () {
            $(".message").hide();
            $(".tableData").show();
            $(".student").hide();
            $(".laboratory").hide();
            $(".reservation").hide();
            sendReservationAjaxAdmin();
        })
        $("#appointSubmit").click(function () {
            let reservationTime = $("#reservationTime").val();
            if(reservationTime===null || reservationTime.length<1){
                alert("请选择预约时间!");
                return;
            }
            let laboratoryId = $("#laboratoryId-modal").val();
            let remark = $("#remark-modal").val();
            sendAppointAjax(laboratoryId,reservationTime,remark);
        });
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
                            let num = parseInt(i) + 1;
                            let btnVal = "";
                            let id = data[i].laboratoryId;
                            let laboratoryName = data[i].laboratoryName;
                            let status = data[i].status
                            if (status === 0) {
                                btnVal = "<button type='button' value='"+id+"' data-name='"+laboratoryName+"' class='btn btn-success appoint' onclick='appointClick(this)' data-toggle='modal' data-target='.appointLaboratory'>空闲中</button>";
                            } else {
                                btnVal = "<button type='button' value='"+id+"' class='btn btn-danger getDetail' data-toggle='modal' onclick='getAppointDetail(this)' data-target='.getReservationDetail'>预约中</button>";
                            }
                            items += "<tr><td>" + num + "</td><td>" + laboratoryName + "</td><td>"
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
                        $(".table").empty(); //清空
                        // 查询成功
                        let items = "<tr><th>#</th><th>学生姓名</th><th>学生学号</th><th>学生性别</th><th>备注</th></tr>";
                        data = data.data
                        for (let i = 0; i < data.length; i++) {
                            let num = parseInt(i) + 1;
                            items += "<tr><td>"+ num + "</td><td>" + data[i].userName + "</td><td>" + data[i].userNo + "</td><td>"
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
        function sendReservationAjax(laboratoryId,status) {
            $.ajax({
                url:"<%=basePath%>/getAppointServlet",
                dataType: "json",
                type: "post",
                data: {
                    "laboratoryId": laboratoryId,
                    "status": status
                },
                success: function (data) {
                    if (data.success && data.code === 200) {
                        $(".table").empty(); //清空
                        // 查询成功
                        let items = "<tr><th>#</th><th>实验室名称</th><th>学生姓名</th><th>学生学号</th><th>预约时间</th><th>成绩</th><th>备注</th><th>状态</th></tr>";
                        data = data.data
                        for (let i = 0; i < data.length; i++) {
                            let status = data[i].status
                            if(status===0){
                                status = "待完成"
                            }else {
                                status = "已完成"
                            }
                            let num = parseInt(i) + 1;
                            items += "<tr><td>"+ num + "</td><td>" + data[i].laboratoryName + "</td><td>" + data[i].userName + "</td><td>"
                                + data[i].userNo + "</td><td>" + data[i].reservationTime+ "</td><td>" + data[i].score  + "</td><td>" + data[i].remark + "</td><td>" + status +"</td></tr>";
                        }
                        $(".table").append(items);
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            })
        }
        function sendAppointAjax(laboratoryId,reservationTime,remark) {
            $.ajax({
                url:"<%=basePath%>/appointLaboratoryServlet",
                dataType: "json",
                type: "post",
                data: {
                    "laboratoryId": laboratoryId,
                    "reservationTime": reservationTime,
                    "remark": remark
                },
                success: function (data) {
                    if (data.success && data.code === 200) {
                        //预约成功，
                        alert("预约成功，请准时参加试验");
                        //清空 form的值
                        $(".modal-body form").find('input[type=text],select,textarea').each(function() {
                            $(this).val('');
                        });
                        sendAjax(null,null,null);
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            })
        }
        function sendLaboratoryAjax() {
            $.ajax({
                url:"<%=basePath%>/getAllLaboratoryNameServlet",
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (data.success && data.code === 200) {
                        $(".reservation .form-group #laboratoryId").empty(); //清空
                        let items = "<option value='-1'>请选择实验室</option>";
                        data = data.data;
                        for (let i = 0; i < data.length; i++) {
                            let id = data[i].laboratoryId;
                            let laboratoryName = data[i].laboratoryName;
                            items += "<option value='"+ id +"'>" + laboratoryName +"</option>";
                        }
                        $(".reservation .form-group #laboratoryId").append(items);
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            })
        }
    })
    function sendReservationAjaxAdmin() {
        $.ajax({
            url:"<%=basePath%>/getAppointServlet",
            dataType: "json",
            type: "post",
            data: {
                "status": 0
            },
            success: function (data) {
                if (data.success && data.code === 200) {
                    $(".table").empty(); //清空
                    // 查询成功
                    let items = "<tr><th>#</th><th>实验室名称</th><th>学生姓名</th><th>学生学号</th><th>预约时间</th><th>成绩</th><th>备注</th><th>状态</th><th>操作</th></tr>";
                    data = data.data
                    for (let i = 0; i < data.length; i++) {
                        let operating = "<button type='button' class='btn btn-primary changeStatus' value='"+data[i].laboratoryId+"' data-name='"+data[i].reservationId+"' onclick='changeStatus(this)'>实验室整理</button>";
                        let status = data[i].status
                        if(status===0){
                            status = "待完成"
                        }else {
                            status = "已完成"
                        }
                        let num = parseInt(i) + 1;
                        items += "<tr><td>"+ num + "</td><td>" + data[i].laboratoryName + "</td><td>" + data[i].userName + "</td><td>"
                            + data[i].userNo + "</td><td>" + data[i].reservationTime+ "</td><td>" + data[i].score  + "</td><td>" + data[i].remark + "</td><td>" + status +"</td><td>" + operating +"</td></tr>";
                    }
                    $(".table").append(items);
                }
            },
            error: function (data) {
                alert(data.responseText)
            }
        })
    }
    function appointClick(obj) {
        let id =  event.srcElement.value;
        let name =  obj.dataset.name;
        $("#laboratoryId-modal").val(id);
        $("#laboratoryName-modal").val(name);
    }
    function getAppointDetail(obj){
        let id =  event.srcElement.value;
        $.ajax({
            url:"<%=basePath%>/getAppointServlet",
            dataType: "json",
            type: "post",
            data: {
                "laboratoryId": id
            },
            success: function (data) {
                if (data.success && data.code === 200) {
                    //查询成功
                    $(".modal-body table").empty(); //清空
                    let items = "<tr><th>#</th><th>实验室名称</th><th>学生姓名</th><th>学生学号</th><th>预约时间</th><th>状态</th></tr>";
                    data = data.data
                    for (let i = 0; i < data.length; i++) {
                        let status = data[i].status
                        if(status===0){
                            status = "待完成"
                        }else {
                            status = "已完成"
                        }
                        let num = parseInt(i) + 1;
                        items += "<tr><td>"+ num + "</td><td>" + data[i].laboratoryName + "</td><td>" + data[i].userName + "</td><td>"
                            + data[i].userNo + "</td><td>" + data[i].reservationTime+ "</td><td>" + status +"</td></tr>";
                    }
                    $(".modal-body table").append(items);
                }
            },
            error: function (data) {
                alert(data.responseText)
            }
        })
    }
    function changeStatus(obj) {
        let laboratoryId =  event.srcElement.value;
        let reservationId = obj.dataset.name;
        let change = confirm("请确保该同学已完成实验？将该实验室修改为可预约状态？并把该实验预约记录修改为已完成状态？");
        if(change){
            $.ajax({
                url:"<%=basePath%>/changeStatusServlet",
                dataType: "json",
                type: "post",
                data: {
                    "laboratoryId": laboratoryId,
                    "reservationId": reservationId
                },
                success: function (data) {
                    if (data.success && data.code === 200) {
                        //操作成功
                        sendReservationAjaxAdmin();
                    }
                },
                error: function (data) {
                    alert(data.responseText)
                }
            })
        }
    }
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
        <form class="reservation form-inline selectForm">
            <div class="form-group">
                <label for="laboratoryId">实验室：</label>
                <select class="form-control" id="laboratoryId">
                    <option value="-1">请选择实验室</option>
                </select>
            </div>
            <div class="form-group">
                <label for="reservation-status">状态：</label>
                <select class="form-control" id="reservation-status">
                    <option value="-1">请选择状态</option>
                    <option value="0">待完成</option>
                    <option value="1">已完成</option>
                </select>
            </div>
            <button type="button" class="btn btn-default" id="reservationSearch">搜索</button>
        </form>
        <table class="table table-bordered table-hover">
        </table>
    </div>
</div>
<%--模态框--%>
<div class="modal fade appointLaboratory" id="appointLaboratory" tabindex="-1" role="dialog" aria-labelledby="appointLaboratory">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">预约实验室</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="laboratoryId-modal">
                    <div class="form-group">
                        <label for="laboratoryName-modal" class="col-sm-2 control-label">实验室名称：</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control" id="laboratoryName-modal">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="reservationTime" class="col-sm-2 control-label">预约时间：</label>
                        <div class='col-sm-10'>
                            <div class="form-group">
                                <!--指定 date标记-->
                                <div class='input-group date'>
                                    <input type='text' id='reservationTime' class="form-control">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark-modal" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="10" id="remark-modal" placeholder="请输入备注信息"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" value="btn" class="btn btn-primary" id="appointSubmit">预约</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade getReservationDetail" id="getReservationDetail" tabindex="-1" role="dialog" aria-labelledby="appointLaboratory">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="title">实验室预约信息</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-hover">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>