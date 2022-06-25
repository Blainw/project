var everyPage_count=4;//一页中显示多少条数据，默认4条
var currentshow=1;//当前显示的页面，默认是是所有课程
$(function (){

    var isLNum=true;
    Loadhome();
    $("#showlist").click(function () {
        $("#mid_right_show").html("");
        displaydiv($("#show_lecturer_div"));
        currentshow=1;
        showlist(1,everyPage_count,1);
        painting(this);
    })

    /*点击课程修改界面的返回按钮，还原之前的显示效果*/
    $("input[name='reback']").click(function (){
        $("#mid_right_update label[id$=_trip]").text("");
        displaydiv($("#show_lecturer_div"));
        painting($("#showlist"));//给当前标签着色
        // $(location).attr('href','ToPage/homepage.action');
    })

    /*ajax检查LNum是否合法*/
    $("#updateLecturer input[name='LNum']").blur(function (){
        var preValue=$("#mid_right_update input[name='LNum']").attr('preValue');
        if($("#updateLecturer input[name='LNum']").val()!=preValue) {
            if (checkUser()) {//如果检查输入是合法的，再才进行ajax数据库匹配
                $.ajax({
                    type: "post",
                    url: "Admin/checklnum.do",
                    data: {
                        LNum: $("#mid_right_update input[name='LNum']").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data == 1) {
                            isLNum = false;
                                $("#LNum_trip").text("此用户已存在");
                        }
                        if (data == 0) {
                            isLNum = true;
                            $("#LNum_trip").html("<font style='color: green'>" + "√" + "</font>");
                        }

                    }

                })
            }

        }else{
            isLNum=true;
            $("#LNum_trip").html("<font style='color: green'>" + "√" + "</font>");
        }
    })

    $("#deleteLecturer").click(function (){
        var LNum=parseInt($("#updateLecturer input[name='LNum']").attr("prevalue"));
        var r=confirm("确认是否删除");
        if(r==true){
            // alert("shanchu");
            $(window).attr("location","Admin/delete.do?LNum="+LNum+"");
        }
    })

    $("#updateLecturer").submit(function (event){
        if(!isLNum){
            event.preventDefault();
        }
    })

})

//给左侧栏中当前的标签着色
function painting(obj){
    $(".painting").removeClass("painting");
    $(obj).addClass("painting");
}

function displaydiv(obj){
    $("#mid_right > *").css("display","none");//让每个选项卡的视图全都隐藏
    $(obj).css("display","block");//展示指定选项卡的视图
}

function Loadhome(){
    displaydiv($("#show_lecturer_div"));
    showlist(1,everyPage_count,1);
}

/*检查用户名*/
function checkUser() {
    var LNum = parseInt($("#mid_right_update input[name='LNum']").val());
    var reg = /^\d{4}$/;
    if (reg.test(LNum) == false) {
        $("#LNum_trip").text("用户名格式不正确");
        return false;
    }
    $("#LNum_trip").html("<font style='color: green'>"+"√"+"</font>");
    return true;
}

function showlist(currentPage,everyPage_count,flag){
        $.ajax({
            type:"post",
            url:"Admin/selectalllecturer.do",
            data:{
                pageNum:currentPage,
                pageSize:everyPage_count,
                isCategory:$("#category").find("option:selected").text()
            },
            dataType:"json",
            success:function (data){
                $("#mid_right #mid_right_show").append("<div class=\"tipdiv\">\n" +
                    "                <label>序号</label>\n" +
                    "                <label>讲师号</label>\n" +
                    "                <label>姓名</label>\n" +
                    "                <label>密码</label>\n" +
                    "                <label>邮箱</label>\n" +
                    "                <label style=\"margin-left: 7%;\">发布课程数</label>\n" +
                    "            </div>");
                $.each(data,function (i,list){
                    $("#mid_right #mid_right_show").append
                    (" <div class=\"multip_div\" onclick='showUpdate(this)'>\n" +
                        "                <label style=\"font-weight: bold;font-size: 1.5em;\">"+list.seq+"</label>\n" +
                        "                <label style=\"display: none\">"+list.lid+"</label>\n" +
                        "                <label style=\"font-size: 1.5em;\">"+list.lnum+"</label>\n" +
                        "                <label style=\"font-size: 1.5em;\">"+list.lname+"</label>\n" +
                        "                <label style=\"font-size: 1.5em;\">"+list.pwd+"</label>\n" +
                        "                <label style=\"font-size: 1.5em;\">"+list.lemail+"</label>\n" +
                        "                <label style=\"font-size: 1.5em; margin-left: 7%; text-align: center;\">"+list.camount+"</label>\n" +
                        "            </div>");
                })

                refreshdiv(flag);
                // window.location.reload();
            }
        })
}

//局部刷新翻页div
function refreshdiv(flag){
    if(flag==1)
        $("#limitpage").load(location.href + " #limitpage>*");
}

//页码翻页
function turningpage(obj){
    addStrong(obj);
    // var lastpage= $("#limitpage span").length;//获取最后一个页码
    //  var currentIndex = $("#limitpage a").index(obj);//获取当前a标签的下标
    var currentPage=$(obj).find("span").text();
    if(currentshow==1){
        $("#mid_right_show").html("");
        showlist(currentPage,everyPage_count,0);
    }
    if(currentshow==2){
        $("#mid_right_show").html("");
        showlist(currentPage,everyPage_count,0);
    }

}


//给当前a标签添加strong
function addStrong(obj){
    if( $(obj).parent().attr('tagName')!='strong') {
        $("strong").find("a").css("background-color", "rgba(216, 191, 216, 0.5)");
        $("strong").find("a").unwrap();
        if($("#limitpage a").index(obj)!=1&&$("#limitpage a").index(obj)!=0) {
            $("#prev").css("margin-right", "8px");
            $(".pageone").css("margin-right", "9px");
        }
        $(obj).wrap(function () {
            return "<strong tagName='strong'></strong>";
        })
        if($("#limitpage a").index(obj)==1){
            $(".pageone").parent().css("margin-right", "9px");
        }
        $(obj).css("background-color", "rgba(216, 191, 216, 0)");
    }else {

    }
}


//上一页点击事件
function Prev(){
    var currentPage=parseInt($("strong a span").text());//当前页的页码值
    var currentindex=parseInt($("#limitpage a").index($("strong a")));

    // alert("当前页码值"+currentPage+"  实际页码总数："+TemtotalPage);
    if(currentindex==1&&currentPage>1){
        $("a span").each(function (){
            $(this).text(parseInt($(this).text())-1);
        })
        // $("#totalPage").val($("#totalPage").val()-1);
    }
    if(currentindex!=1)
        addStrong($("strong").prev());

    if(currentPage>1) {
        if (currentshow == 1) {
            $("#mid_right_show").html("");
            showlist(parseInt(currentPage) - 1, everyPage_count, 0);
        }
        if (currentshow == 2) {
            $("#mid_right_show").html("");
        }
    }else{
        alert("不能再上一页了 ");
    }
}

//下一页点击事件
function Next(){
    var currentPage=parseInt($("strong a span").text());//当前页的页码值
    var currentindex=parseInt($("#limitpage a").index($("strong a")));//当前a标签的index
    var TemtotalPage=parseInt($("#totalPage").val());//总页数9
    var lastindex=parseInt($("#limitpage span").index($("#limitpage span").last()))+1;

    if(currentindex==3&&currentPage<TemtotalPage){
        $("a span").each(function (){
            $(this).text(parseInt($(this).text())+1);
        })
        // $("#totalPage").val($("#totalPage").val()-1);
    }
    if(currentindex<lastindex)
        addStrong($("strong").next());

    if(currentPage<TemtotalPage) {
        if (currentshow == 1) {
            $("#mid_right_show").html("");
            showlist(parseInt(currentPage) + 1, everyPage_count, 0);
        }
        if (currentshow == 2) {
            $("#mid_right_show").html("");
        }
    }else{
        alert("不能再下一页了");
    }
}

/*选择排序方式*/
function orderby(obj){
    if (currentshow == 1) {
        $("#mid_right_show").html("");
        showlist(1, everyPage_count, 1);
    }
}

/*显示修改页面*/
function showUpdate(obj){
    $("#mid_right_update label[id$=_trip]").text("");
    var lid=$(obj).find("label:nth-child(2)").text();
    var lnum=$(obj).find("label:nth-child(3)").text();
    var lname=$(obj).find("label:nth-child(4)").text();
    var pwd=$(obj).find("label:nth-child(5)").text();
    var lemail=$(obj).find("label:nth-child(6)").text();
    var camount=$(obj).find("label:nth-child(7)").text();
    displaydiv($("#mid_right_update"));
    $("#mid_right_update input[name='LID']").val(lid);
    $("#mid_right_update input[name='LNum']").val(lnum);
    $("#mid_right_update input[name='LNum']").attr('preValue',lnum);//这里是填充到到preValue，这个值可以用来判断修改前后的值是否一致
    $("#mid_right_update input[name='LName']").val(lname);
    $("#mid_right_update input[name='Pwd']").val(pwd);
    $("#mid_right_update input[name='LEmail']").val(lemail);
    $("#mid_right_update input[name='CAmount']").val(camount);
}