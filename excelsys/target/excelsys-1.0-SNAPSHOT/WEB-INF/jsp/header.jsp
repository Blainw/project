<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="head">
	<!-- 右边内容 -->
	<div class="right">

	</div>

</div>
<script  type="text/javascript">
	$(function (){
		if($("#loginpage").length>0){
			$(".head>.right").html("<a href='ToPage/login.action'>登录</a><span>&nbsp;|&nbsp;</span> " +
					"<a href='ToPage/register.action'>注册</a><span>&nbsp;|&nbsp;</span>");
		}
		if($("#homepage").length>0){
			$(".head>.right").html("<label style=\"font-size: 15px\">欢迎您：${sessionScope.currentLecturer.LName}</label>\n" +
					"\t\t\t<a href=\"ToPage/update.action\">修改</a><span>&nbsp;|&nbsp;</span>\n" +
					"\t\t\t<a href=\"Lecturer/loginout.do\" >退出</a><span>&nbsp;|&nbsp;</span>\n" +
					"\t\t\t<a href=\"javascript:void(0)\"  id=\"exportall_course\">导出全部</a><span>&nbsp;|&nbsp;</span>\n" +
					"\t\t\t<a href=\"javascript:void(0)\" id=\"exportcurrent_course\">导出当前</a>");


			$(selector).html("<a href='javascript:void(0)' id='bindClick'>绑定点击事件</a>");

			$(document).on('click', '#bindClick',function(){
				alert("点击了");
			});


		}
		if($("#managepage").length>0){
			$(".head>.right").html("<label style=\"font-size: 15px\">欢迎您：${sessionScope.currentAdmin.RName}</label>\n" +
					"\t\t\t<a href=\"Admin/loginout.do\" >退出</a><span>&nbsp;|&nbsp;</span>\n" +
					"\t\t\t<a href=\"javascript:void(0)\" id=\"exportall_lecturer\">导出全部</a><span>&nbsp;|&nbsp;</span>\n" +
					"\t\t\t<a href=\"javascript:void(0)\" id=\"exportcurrent_lecturer\">导出当前</a>");
		}
	})
</script>





