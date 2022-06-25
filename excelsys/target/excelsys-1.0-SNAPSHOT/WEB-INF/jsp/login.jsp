<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>登录</title>
    <base href="<%=basePath%>">
    <link href="static/img/favicon.ico" rel="icon"/>
    <link href="static/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/login.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="static/js/logincheck.js"></script>
</head>

<body>
<!-- 头部 -->
<jsp:include page="header.jsp"></jsp:include>
<!-- 内容 -->
<div class="container" id="loginpage">
    <div class="left">
        <div class="left_left">
            <p class="emailfont">Excel函数学习系统！</p>
            <p>这里，有各种全面的excel函数教程</p>
            <p>全国各地都可进行登陆学习</p>
            <p>你可以发布excel函数的理解</p>
            <p>也可以学习excel函数的知识</p>
            <p>快来使用吧！</p>
        </div>
    </div>
    <div class="right">
        <input type="radio" name="login" id="st"/>
        <input type="radio" name="login" id="le" checked/>
        <div class="right1">

            <div>
                <label style="font-size: 16px; " for="st" class="st">管理端</label>
            </div>
            <div>
                <label style="font-size: 16px; " for="le" class="le">用户端</label>
            </div>
        </div>

        <div class="lelogin">
            <form action="Lecturer/login.do" method="post" id="login">
                <span class="errormessage">
                </span>
                <div class="right2">
                    <input type="text" placeholder="请输入4位数账号" name="LNum" id="inLNum"/>
                    <input type="password" placeholder="请输入6-10位密码" name="Pwd" id="inPwd"/>
                </div>
                <div class="right3">
                    <input type="checkbox" name="whether"/><span>下次自动登录</span>
                </div>
                <div class="right4">
                    <input type="submit" value="登录" />
                </div>
            </form>
            <div class="right5">
                <a href="#" style="margin-left: 10%;color: #d8d130;size: 12px;">忘了密码？</a>
                <a href="ToPage/register.action" style="margin-left: 45%;color:#d8d130;size: 12px;">注册新账号</a>
            </div>
        </div>
        <div class="stlogin">
            <form action="Admin/login.do" method="post" id="slogin">
                <span class="admin_login_tips">
                </span>
                <div class="right2">
                    <input type="text" placeholder="请输入账号" name="SName" required/>
                    <input type="password" placeholder="请输入密码" name="SPwd" required/>
                </div>
                <div class="right3">
                </div>
                <div class="right4">
                    <input type="submit" value="登录" />
                </div>
            </form>
<%--            <div class="mosaic">--%>
<%--                <img src="${pageContext.request.contextPath }/static/img/QRcode.jpg"/>--%>
<%--                <label>请使用微信扫描二维码登录</label>--%>
<%--            </div>--%>
<%--            <div id="wxautologin">--%>
<%--                <input type="checkbox" name="auto"/><span>下次自动登录</span>--%>
<%--            </div>--%>
<%--            <div style="width: 100%;text-align: center;color: #d8d130;margin-top: 30px;">--%>
<%--                <span>微信和qq邮箱的数据不互通</span>--%>
<%--            </div>--%>
<%--        </div>--%>
        </div>
    </div>
</div>
<!-- 尾部 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
