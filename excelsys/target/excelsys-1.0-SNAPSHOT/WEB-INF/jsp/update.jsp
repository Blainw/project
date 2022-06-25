<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<html>

<head>
    <base href="<%=basePath%>">
    <title>修改</title>
    <link href="static/css/register.css" rel="stylesheet" type="text/css"/>
    <link href="static/img/favicon.ico" rel="icon"/>
    <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="static/js/updatecheck.js"></script>
</head>

<body>
<div id="loginDiv">
    <form action="Lecturer/update.do" method="post"  id="update">
        <h1>修改信息</h1>
        <p hidden="hidden">><span>用户编号:</span><input name="LID" type="text" readonly value="${sessionScope.currentLecturer.LID}" ></p>
           <p><span>用&nbsp;&nbsp;户&nbsp;&nbsp;名:</span><input name="LNum" type="text" value="${sessionScope.currentLecturer.LNum}" required autocomplete="off"><label id="LNum_trip"></label></p>
            <p><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input name="LName" type="text" value="${sessionScope.currentLecturer.LName}" required autocomplete="off"><label id="LName_trip"></label></p>
           <p><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input name="Pwd" type="password" value="${sessionScope.currentLecturer.pwd}" required autocomplete="off"><label id="Pwd_trip"></label></p>

            <p>
                <span>电子邮件:</span><input name="LEmail" type="email" value="${sessionScope.currentLecturer.LEmail}" required autocomplete="off"><label id="LEmail_trip"></label>
            </p>

            <p style="padding-left: 19.3%;margin-top: 10%">
                <input type="button" name ="reback" class="button" value="返回">
                <input type="submit" class="button" value="修改">
            </p>
    </form>
</div>

</body>

</html>

