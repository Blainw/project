<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>主页</title>
    <base href="<%=basePath%>">
    <link href="static/img/favicon.ico" rel="icon"/>
    <link href="static/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/homepage.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="static/js/homepage.js"></script>
    <script type="text/javascript" src="static/js/export.js"></script>
    <script type="text/javascript" src="static/js/jquery.datetimepicker.full.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<!-- 内容 -->
<div class="middle" id="homepage">
    <div id="mid_left">
        <a href="javascript:void(0)" id="showall" >所有课程</a>
        <a href="javascript:void(0)" id="showone" >已发布课程</a>
        <a href="javascript:void(0)" id="release">发布课程</a>
        <a href="javascript:void(0)" id="uploadvideo">上传视频</a>
    </div>

    <div id="mid_right">
        <div id="show_course_div">
        <div id="mid_right_show">

        </div>
        <select onchange="changeShowCout(this)" id ="currentSize"style="margin-left: 15px;
	border-radius: 5px;
	margin-top: 10px;
	position: absolute;
	bottom: 50px;
	left: 20px;
	border-style: hidden;
	height: 30px;
	width: 150px;
	background-color: rgba(216, 191, 216, 0.5);
	outline: none;
	color: black;
	padding-left: 10px;">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9"  selected>9</option>
        </select>
        <select onchange="changeShowCategory(this)" id="selectedCPhase" style="margin-left: 15px;
	border-radius: 5px;
	margin-top: 10px;
	position: absolute;
	bottom: 50px;
	left: 200px;
	border-style: hidden;
	height: 30px;
	width: 150px;
	background-color: rgba(216, 191, 216, 0.5);
	outline: none;
	color: black;
	padding-left: 10px;">
            <option value="0" selected>所有类别</option>
            <option value="1">excel初级</option>
            <option value="2">excel中级</option>
            <option value="3">excel高级</option>
        </select>

        <input type="text" name="query_cname"  autocomplete="off" style="margin-left: 15px;
	border-radius: 5px;
	margin-top: 10px;
	position: absolute;
	bottom: 50px;
	left: 380px;
	border-style: hidden;
	height: 30px;
	width: 150px;
	background-color: rgba(216, 191, 216, 0.5);
	outline: none;
	color: black;
	padding-left: 10px;" >
        <input type="button" value="查询"  onclick="query_cname()" style="margin-left: 15px;
	border-radius: 5px;
	margin-top: 10px;
	position: absolute;
	bottom: 50px;
	left: 560px;
	border-style: hidden;
	height: 30px;
	width: 50px;
	background-color: rgba(216, 191, 216, 0.5);
	outline: none;
	color: black;
    " onmouseover="this.style.cursor='pointer'">
        <div id="limitpage">
            <input type="hidden" id="totalPage" value="${sessionScope.totalPage}"/>
            <a href="javascript:void(0)" class="n" id="prev" onclick="Prev()">上一页</a>
            <%--总页数小于等于7--%>
            <c:if test="${sessionScope.totalPage<=7}">
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
            <c:if test="${sessionScope.totalPage>7}">
                <c:forEach begin="1" end="7" var="i">
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

            <a href="javascript:void(0)" id="next" class="n" onclick="Next()">下一页></a>
        </div>
        </div>
        <div id="mid_right_update">
            <form action="Course/update.do" method="post"  id="updateCourse">
                <h1>修改信息</h1>
                <p hidden="hidden">课程ID:<input name="CID"  type="text" readonly value="" ></p>
                <p>课&nbsp;程&nbsp;编&nbsp;号:<input name="CNO" type="text" preValue="" placeholder="格式如：22xxx" value="" required autocomplete="off"><label id="CNO_trip"></label></p>
                <p>课&nbsp;程&nbsp;名&nbsp;称:<input name="CName" type="text" value="" required autocomplete="off"><label id="CName_trip"></label></p>
                <p>课&nbsp;程&nbsp;阶&nbsp;段:<select id="CPhase" onchange="changeselect(this)">
                    <option value="1" selected>excel初级</option>
                    <option value="2">excel中级</option>
                    <option value="3">excel高级</option>
                </select>
                    <label id="CPhase_trip"></label><input type="text" name="CPhase" hidden/></p>

                <p>
                    课&nbsp;程&nbsp;介&nbsp;绍:
                    <textarea rows="5" name="CIntroduce" maxlength="70" required autocomplete="off" ></textarea><label id="CIntroduce_trip"></label>
                </p>
                <p>截&nbsp;止&nbsp;时&nbsp;间:<input name="CTime" type="text" value="" required autocomplete="off"><label id="CTime_trip"></label></p>
                <p style="text-align: center;margin: 0 auto; padding-left: 0; margin-top: 5.45%;">
                    <input type="button" name ="reback" class="button" value="返回">
                    <input type="button" class="button" id="deleteCourse" value="删除">
                    <input type="submit" class="button" value="修改">
                </p>
            </form>
        </div>
        <div id="mid_right_upload" style="display: none">
            <form action="Course/videoup.do" method="post" enctype="multipart/form-data" id="form_upload">
                <p>课&nbsp;程&nbsp;编&nbsp;号:<input name="VideoBindCNO" type="text" preValue="" placeholder="格式如：22xxx" value="" required autocomplete="off"><label id="VBCNO_trip"></label></p>
                <p>上&nbsp;传&nbsp;视&nbsp;频:<input type="file" name="video" required accept="video/*"></p>
                <p style="margin-left: 100px">
                    <input type="submit" class="button" value="提交">
                </p>
                <p style="margin-left: 100px">
                    <input type="button" id="deletevideo" class="button" value="删除">
                </p>
            </form>

        </div>
    </div>

</div>
<jsp:include page="footer.jsp"/>
</body>

</html>
