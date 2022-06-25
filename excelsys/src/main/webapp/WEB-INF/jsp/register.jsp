<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<html>

<head>
    <base href="<%=basePath%>">
<%--    <meta http-equiv="Content-Type" content="register/html; charset=gb2312">--%>
    <title>注册</title>
    <link href="static/css/register.css" rel="stylesheet" type="text/css"/>
    <link href="static/img/favicon.ico" rel="icon"/>
    <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="static/js/registercheck.js"></script>
</head>

<body>


<div id="loginDiv">
    <form action="Lecturer/register.do" method="post"  id="register">
        <h1>讲师注册</h1>
        <p><span>用&nbsp;&nbsp;户&nbsp;&nbsp;名:</span><input name="LNum" type="text" required autocomplete="off"><label id="LNum_trip"></label></p>
            <p><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input name="LName" type="text" maxlength="5"  required autocomplete="off"> <label id="LName_trip"></label></p>
           <p><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input name="Pwd" type="password" required autocomplete="off"><label id="Pwd_trip"></label></p>
            <p><span>确认密码:</span><input name="Rpwd" type="password" required autocomplete="off"><label id="RePwd_trip"></label></p>
<%--            <p>--%>
<%--                <span>用户类型:</span><select name="type" id="userType" style="background-color: #c1aec7">--%>
<%--&lt;%&ndash;                    <option value="0" disabled selected>请选择</option>&ndash;%&gt;--%>
<%--                    <option value="1">普通用户</option>--%>
<%--                    <option value="2">管理员</option>--%>
<%--                </select><label id="type_trip"></label>--%>
<%--            </p>--%>
            <p>
                <span>电子邮件:</span><input name="LEmail" type="email" required autocomplete="off"><label id="LEmail_trip"></label>
            </p>

            <p style="padding-left: 19.3%">
                <input type="submit" class="button" value="提交">
                <input type="reset" name ="reset" class="button" value="重置">
            </p>
        <span style="margin-left: 51%;margin-top: 5%;display: block;color:rgb(179,179,179);">已有账号，
            <a href="ToPage/login.action" style="color: rgb(187 144 24);">去登录</a></span>
    </form>
</div>

</body>

</html>

