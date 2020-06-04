<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <style type="text/css">
    table{
      width: 600px;
    }
    table,tr,td{
      border:1px solid red;
    }

  </style>
  <body>
  <form action="<%=basePath%>/registerServlet" method="post">
    <table align="center" cellspacing="0">
      <caption>注册</caption>
      <tr>
        <td width="50px" align="center">姓名：</td>
        <td width="550px" align="center"><input type="text" name="userName"/></td>
      </tr>
      <tr>
        <td width="50px;" align="center">密码：</td>
        <td width="550px" align="center"><input type="text" name="userPassword"/></td>
      </tr>
      <tr>
        <td width="50px" align="center">性别：</td>
        <td width="550px" align="center"><input type="text" name="userSex"/></td>
      </tr>
      <tr>
        <td width="50px;" align="center">备注：</td>
        <td width="550px" align="center"><input type="text" name="remark"/></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="注册"/> &nbsp;&nbsp;<input type="reset" value="重置"/></td>
      </tr>
    </table>
  </form>
  </body>
</html>
