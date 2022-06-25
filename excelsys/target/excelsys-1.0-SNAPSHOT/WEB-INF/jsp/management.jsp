<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>管理员界面</title>
    <base href="<%=basePath%>">

    <link href="static/img/favicon.ico" rel="icon"/>
    <link href="static/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/management.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="static/js/management.js"></script>
    <script type="text/javascript" src="static/js/export.js"></script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="header.jsp"></jsp:include>

<div class="middle" id="managepage">
    <div id="mid_left">
        <a href="javascript:void(0)" id="showlist" class="painting">所有讲师信息</a>
    </div>
    <div id="mid_right">
        <div id="show_lecturer_div">
        <select onchange="orderby(this)" id="category" style="margin-left: 15px;
	border-radius: 5px;
	margin-top: 10px;
	border-style: hidden;
	height: 30px;
	width: 150px;
	background-color: rgba(216, 191, 216, 0.5);
	outline: none;
	display: block;
	color: black;
	padding-left: 10px;">
            <option value="1" selected>按讲师号</option>
            <option value="2">按课程数</option>
        </select>
        <div id="mid_right_show">

        </div>
        <div id="limitpage">
            <input type="hidden" id="totalPage" value="${sessionScope.totalPage}"/>
            <a href="javascript:void(0)" class="n" id="prev" onclick="Prev()">上一页</a>
            <%--总页数小于等于7--%>
            <c:if test="${sessionScope.totalPage<=3}">
                <c:forEach begin="1" end="${sessionScope.totalPage}" var="i">
                    <c:if test="${i==1}">
                        <strong tagName='strong'>
                            <a href="javascript:void(0)" class="pageone" onclick="turningpage(this)" style="background-color: rgba(216, 191, 216, 0)"><span class="pagination" >${i}</span></a>
                        </strong>
                    </c:if>
                    <c:if test="${i!=1}">
                        <a href="javascript:void(0)" onclick="turningpage(this)"><span class="pagination" >${i}</span></a>
                    </c:if>
                </c:forEach>
            </c:if>

            <%--总页数大于7--%>
            <c:if test="${sessionScope.totalPage>3}">
                <c:forEach begin="1" end="3" var="i">
                    <c:if test="${i==1}">
                        <strong tagName='strong'>
                            <a href="javascript:void(0)" class="pageone" onclick="turningpage(this)" id="specialA" style="background-color: rgba(216, 191, 216, 0);"><span class="pagination" >${i}</span></a>
                        </strong>
                    </c:if>
                    <c:if test="${i!=1}">
                        <a href="javascript:void(0)" onclick="turningpage(this)"><span class="pagination" >${i}</span></a>
                    </c:if>
                </c:forEach>
            </c:if>

            <a href="javascript:void(0)" class="n" onclick="Next()">下一页></a>
        </div>
        </div>
        <div id="mid_right_update">
            <form action="Admin/updatelecturer.do" method="post"  id="updateLecturer">
                <h1>修改讲师信息</h1>
                <p hidden="hidden">讲师ID:<input name="LID"  type="text" readonly value="" ></p>
                <p>讲&nbsp;师&nbsp;账&nbsp;号:<input name="LNum" type="text" preValue="" value="" required autocomplete="off"><label id="LNum_trip"></label></p>
                <p>讲&nbsp;师&nbsp;姓&nbsp;名:<input name="LName" type="text" value="" required autocomplete="off"><label id="LName_trip"></label></p>

                <p>讲&nbsp;师&nbsp;密&nbsp;码:<input name="Pwd" type="text" value="" minlength="6" maxlength="10" required autocomplete="off"><label id="Pwd_trip"></label></p>
                <p>讲&nbsp;师&nbsp;邮&nbsp;箱:<input name="LEmail" type="text" value="" required autocomplete="off" readonly></p>
                <p>发&nbsp;布&nbsp;课&nbsp;程:<input name="CAmount" type="text" value="" required autocomplete="off" readonly></p>
                <p style="text-align: center;margin: 0 auto; padding-left: 0; margin-top: 5.45%;">
                    <input type="button" name ="reback" class="button" value="返回">
                    <input type="button" class="button" id="deleteLecturer" value="删除">
                    <input type="submit" class="button" value="修改">
                </p>
            </form>
        </div>
    </div>
</div>
<!-- 尾部 -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
