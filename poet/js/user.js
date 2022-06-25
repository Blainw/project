
	var flag1=false;
	$(document).ready(function(){
			// alert(window.localStorage.getItem("flag1"));
			$("#show").html(window.localStorage.getItem("user"));//获取到存储在浏览器上名称为user的数据
			if(window.localStorage.getItem("flag1")!=null)
			flag1=window.localStorage.getItem("flag1");
			if(flag1){
				$(".operation_container").children("a").hide();
				$(".operation_container").append("<span id='getout'>您好："+window.localStorage.getItem("user")+
				"&nbsp;&nbsp;&nbsp;<a href='#'>退出登录</a></span>");
			}
			flag1=false;
			$("#getout").click(function(){
				window.localStorage.removeItem("flag1");//移除存储在浏览器上名称为flag1的数据
				$(".operation_container").children("a").show();
				$(this).hide();
			});
	});
