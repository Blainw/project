<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>

</head>
<body>
    <form action="testservlet" method="post">
        <table align="center">
            <tr>
                <td>LNum:</td>
                <td><input type="text" name="LNum"></td>
            </tr>
            <tr>
                <td>Pwd:</td>
                <td><input type="password" name="Pwd"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="提交" ></td>
            </tr>
        </table>
    </form>
    <center>
<%--    <a href="Lecturer/some.do">点击我</a>--%>
<%--        <jsp:forward page="http://localhost:8080/excelsys/ToPage/login.action"></jsp:forward>--%>
    </center>
</body>
</html>
