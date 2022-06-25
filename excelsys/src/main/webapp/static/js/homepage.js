var cphase="excel初级";
var everyPage_count=9;//一页中显示多少条数据，默认9条
var currentshow=1;//当前显示的页面，默认是是所有课程
var totalPage=0;
$(function() {

    var isCNO=true;//课程号是否通过检测
    var isVBCNO=false;//绑定视频课程号是否通过检测
    var isHasCNO=false;//用户是否有该课程号
    //加载所有课程
    loadHome(1,everyPage_count,1);

    /*点击所有课程，显示所有课程信息*/
    $("#showall").click(function (){
        showall(this);
    })


    /*根据当前讲师，显示他所讲的课程信息*/
    $("#showone").click(function (){
            showone(this);
    })

    /*点击发布课程，展示此页面*/
    $("#release").click(function (){
        displaydiv($("#mid_right_update"));//将修改的页面展示
        currentshow=3;
        painting(this);//给当前标签着色
        $("#updateCourse")[0].reset();//重置表单所有内容
        $("#mid_right_update label[id$=_trip]").text("");
        $("#updateCourse").attr("action","Course/add.do");
        $("#updateCourse>h1").text("添加课程");
        $("#updateCourse input[type='submit']").val("发布");
        $("#updateCourse input[value='删除']").attr("type","hidden");

    })

    //点击上传视频选项卡
    $("#uploadvideo").click(function (){
       displaydiv($("#mid_right_upload"));//显示视频上传
        currentshow=4;
        painting(this);//给当前标签着色
    })

    /*日历插件，聚焦在CTime时，进行日历显示*/
    $.datetimepicker.setLocale('ch');
    $("input[name='CTime']").datetimepicker({
        format:'Y-m-d',
        timepicker:false
    });

    /*点击课程修改界面的返回按钮，还原之前的显示效果*/
    $("input[name='reback']").click(function (){
        $("#mid_right_update label[id$=_trip]").text("");
        $('#CPhase option:selected').prop('selected', "");
        displaydiv($("#show_course_div"));
        painting($("#showone"));//给当前标签着色
        // if(currentshow!=2){
        //     showone($("#showone"));
        // }
        // $(location).attr('href','ToPage/homepage.action');
    })

    //删除课程信息
    $("#deleteCourse").click(function (){
        var CNO=parseInt($("#updateCourse input[name='CNO']").attr("prevalue"));
        var r=confirm("确认是否删除");
        if(r==true){
            $(window).attr("location","Course/delete.do?CNO="+CNO+"");
        }
    })

    //点击删除视频
    $("#deletevideo").click(function (){
        var CNO=parseInt($("input[name='VideoBindCNO']").val());
        if(isVBCNO&&isHasCNO){
            var r=confirm("确认是否删除");
            if(r==true){
                $(window).attr("location","Course/deletevideo.do?CNO="+CNO+"");
            }

        }

    })
    /*ajax检查CNO是否合法*/
    $("#mid_right_update input[name='CNO']").blur(function (){
        var preValue=$("#mid_right_update input[name='CNO']").attr('preValue');
        if($("#mid_right_update input[name='CNO']").val()!=preValue||$("#mid_right_update input[name='CNO']").val()=="") {
            if (checkCNO()) {//如果检查输入是合法的，再才进行ajax数据库匹配
                $.ajax({
                    type: "post",
                    url: "Course/checkcno.do",
                    data: {
                        CNO: $("#mid_right_update input[name='CNO']").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data == 1) {
                            isCNO = false;
                            $("#CNO_trip").text("此课程编号已存在");
                        }
                        if (data == 0) {
                            isCNO = true;
                            $("#CNO_trip").html("<font style='color: green'>" + "√" + "</font>");
                        }

                    }

                })
            }

        }else{
            isCNO=true;
            $("#CNO_trip").html("<font style='color: green'>" + "√" + "</font>");
        }

    })
    /*ajax检查VBCNO是否合法*/
    $("#mid_right_upload input[name='VideoBindCNO']").blur(function (){
            if (checkVBCNO()) {//如果检查输入是合法的，再才进行ajax数据库匹配
                $.ajax({
                    type: "post",
                    url: "Course/checkcnolegality.do",
                    data: {
                        CNO: $("#mid_right_upload input[name='VideoBindCNO']").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data == 0) {
                            isVBCNO = true;
                            isHasCNO=true;
                            $("#VBCNO_trip").html("<font style='color: green'>" + "√" + "</font>");
                        }
                        if (data == 1) {
                            isVBCNO = false;
                            $("#VBCNO_trip").text("此课程编号不存在");
                        }
                        if (data == 2) {
                            isVBCNO = true;
                            isHasCNO = false;
                            $("#VBCNO_trip").text("您没有该课程");
                        }

                    }

                })
            }



    })

    $("input[name='CName']").blur(function (){
        var name=$("input[name='CName']").val();
        if(name!=""){
            $("#CName_trip").html("<font style='color: green'>"+"√"+"</font>");
        }else{
            $("#CName_trip").text("");
        }
    })
    $("textarea[name='CIntroduce']").blur(function (){
        var introduce=$("textarea[name='CIntroduce']").val();
        if(introduce!=""){
            $("#CIntroduce_trip").html("<font style='color: green'>"+"√"+"</font>");
        }else{
            $("#CIntroduce_trip").text("");
        }
    })

    $("#updateCourse").submit(function (event){
            $("#mid_right_update input[name='CPhase']").val(cphase);
            if(!isCNO){
                event.preventDefault();
            }

    })

    //视频上传提交
    $("#form_upload").submit(function (event){
        //输入课号存在，且是当前用户的，才会提交
        if(!isVBCNO){
            event.preventDefault();
        }
        if(!isHasCNO){
            event.preventDefault();
        }

    })

})

/*显示所有课程时，启用此方法*/
function showall(obj){
    $("#mid_right_show").html("");
    displaydiv($("#show_course_div"));
    $("#selectedCPhase").find("option[value=0]").prop("selected",true);
    currentshow=1;
    showhomepage(1,everyPage_count,1);//查询所有课程的第一页
    totalPage=$("#totalPage").val();
    painting(obj);
}

function displaydiv(obj){
    $("#mid_right > *").css("display","none");//让每个选项卡的视图全都隐藏
    $(obj).css("display","block");//展示指定选项卡的视图
}

/*显示个人课程时，启用此方法*/
function showone(obj){
    $("#mid_right_show").html("");//清空展示课程信息的div
    displaydiv($("#show_course_div"));
    $("#selectedCPhase").find("option[value=0]").prop("selected",true);//将选择的课程类别置为所有
    currentshow=2;
    showonecourse(1,everyPage_count,1);//查询个人课程的第一页
    painting(obj);
}

function showhomepage(currentPage,everyPage_count,flag){
    $.ajax({
        type:"post",
        url:"Course/showhomepage.do",
        data:{
            pageNum:currentPage,
            pageSize:everyPage_count,
            isPhase:$("#selectedCPhase").find("option:selected").text()
        },
        dataType:"json",
        success:function (data){
                $.each(data,function (i,course){
                    $("#mid_right #mid_right_show").append("<div class='multip_div' onclick='showvideo(this)'><input type='hidden' value='"+course.cid+"' /><input type='hidden' value='"+course.cno+"' /><div id='show_firstdiv'><label id='cphase'>"+course.cphase+"</label><span>"+
                    course.cname+"</span></div><div id=\"show_seconddiv\"><p>"+course.cintroduce+
                        "</p><label>截止时间：</label><label>"+course.ctime+"</label><label >讲师："+course.lname+"</label></div><input type='hidden' value='"+course.cvideo+"' /></div>");
                    if(course.cphase=="excel高级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#e73908");
                    if(course.cphase=="excel中级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#441dd8");
                    if(course.cphase=="excel初级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#07a54c");
                })

                refreshdiv(flag);
        }
    })
    // addStrong($("#limitpage .pagination:first"));
}

//查询某位讲师的课程
function showonecourse(currentPage,everyPage_count,flag){
    $.ajax({
        type:"post",
        url:"Course/showonecourse.do",
        data:{
            pageNum:currentPage,
            pageSize:everyPage_count,
            isPhase:$("#selectedCPhase").find("option:selected").text()
        },
        dataType:"json",
        success:function (data){
            $.each(data,function (i,course){

                $("#mid_right #mid_right_show").append("<div class='multip_div' onclick='showUpdate(this)'><input type='hidden' value='"+course.cid+"' />" +
                    "<input type='hidden' value='"+course.cno+"' /><div id='show_firstdiv'><label id='cphase'>"+course.cphase+"</label><span>"+
                    course.cname+"</span></div><div id=\"show_seconddiv\"><p>"+course.cintroduce+
                    "</p><label>截止时间：</label><label>"+course.ctime+"</label><label >讲师："+course.lname+"</label></div></div>");
                if(course.cphase=="excel高级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#e73908");
                if(course.cphase=="excel中级")  $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#441dd8");
                if(course.cphase=="excel初级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#07a54c");
            })

            refreshdiv(flag);

            // window.location.reload();
        }
    })

}

//根据cname查询信息
function queryCName(currentPage,everyPage_count,cname,flag){
    $.ajax({
        type:"post",
        url:"Course/querycname.do",
        data:{
            pageNum:currentPage,
            pageSize:everyPage_count,
            CName:cname,
            currentTab:parseInt(currentshow)
        },
        dataType:"json",
        success:function (data){
            $.each(data,function (i,course){

                $("#mid_right #mid_right_show").append("<div class='multip_div' onclick='showUpdate(this)'><input type='hidden' value='"+course.cid+"' />" +
                    "<input type='hidden' value='"+course.cno+"' /><div id='show_firstdiv'><label id='cphase'>"+course.cphase+"</label><span>"+
                    course.cname+"</span></div><div id=\"show_seconddiv\"><p>"+course.cintroduce+
                    "</p><label>截止时间：</label><label>"+course.ctime+"</label><label >讲师："+course.lname+"</label></div><input type='hidden' value='"+course.cvideo+"' /></div>");
                if(course.cphase=="excel高级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#e73908");
                if(course.cphase=="excel中级")  $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#441dd8");
                if(course.cphase=="excel初级") $("#mid_right_show>div:nth-child("+(i+1)+")").css("color","#07a54c");
            })
            if(currentshow==1){
                $(".multip_div").attr("onclick","showvideo(this)");
            }
            if(currentshow==2){
                $(".multip_div").attr("onclick","showUpdate(this)");
            }
            refreshdiv(flag);

            // window.location.reload();
        }
    })
}

//给左侧栏中当前的标签着色
function painting(obj){
    $(".painting").removeClass("painting");
    $(obj).addClass("painting");
}
//局部刷新翻页div
function refreshdiv(flag){
    if(flag==1)
        $("#limitpage").load(location.href + " #limitpage>*");
}

//首页默认加载所有课程
function loadHome(currentPage,everyPage_count,flag){
    /*一进homepage.jsp就显示所有课程信息*/
    showhomepage(currentPage,everyPage_count,flag);
    totalPage=$("#totalPage").val();
    painting($("#showall"));
}

/*显示修改页面*/
function showUpdate(obj){
    $("#updateCourse").attr("action","Course/update.do");
    $("#updateCourse>h1").text("修改课程信息");
    $("#updateCourse input[type='submit']").val("修改");
    $("#updateCourse input[value='删除']").attr("type","button");
    $("#mid_right_update label[id$=_trip]").text("");
    var cid=$(obj).find("input:nth-child(1)").val();//获取课程id的值
    var cno=$(obj).find("input:nth-child(2)").val();//获取课程编号的值
    var cname=$(obj).find("#show_firstdiv>span").text();//获取课程名称的值
    cphase=$(obj).find("#show_firstdiv>label").text();//获取课程阶段的值
    var cintroduce=$(obj).find("#show_seconddiv>p").text();//获取课程介绍的值
    var ctime=$(obj).find("#show_seconddiv>label:nth-child(3)").text();//获取截止时间的值
    displaydiv($("#mid_right_update"));//将修改的页面展示
    $("#mid_right_update input[name='CID']").val(cid);//填充获取到的课程编号的值
    $("#mid_right_update input[name='CNO']").val(cno);//填充获取到的课程编号的值
    $("#mid_right_update input[name='CNO']").attr('preValue',cno);//这里是填充到到preValue，这个值可以用来判断修改前后的值是否一致
    $("#mid_right_update input[name='CName']").val(cname);//填充获取到的课程名称的值
    $('#CPhase option').each(function(){//填充获取到的课程阶段的值
        if ($(this).text() == cphase) {
            $(this).prop("selected", "selected");
        }
    });

    $("#mid_right_update textarea[name='CIntroduce']").val(cintroduce);//填充获取到的课程介绍的值
    $("#mid_right_update input[name='CTime']").val(ctime);//填充获取到的截止时间的值
}


/*检查课程号是否正确*/
function checkCNO() {
    if ($("#mid_right_update input[name='CNO']").val() == "" || $("#mid_right_update input[name='CNO']").val() == null) {
        $("#CNO_trip").text("");
        return false;
    }
    var CNO = parseInt($("#mid_right_update input[name='CNO']").val());
    var reg = /^\d{5}$/;
    if (reg.test(CNO) == false) {
        $("#CNO_trip").text("课程号格式不正确");
        return false;
    }
    $("#CNO_trip").html("<font style='color: green'>"+"√"+"</font>");
    return true;
}

/*检查视频绑定的课程号是否正确*/
function checkVBCNO() {
    if ($("#mid_right_upload input[name='VideoBindCNO']").val() == "" || $("#mid_right_upload input[name='VideoBindCNO']").val() == null) {
        $("#VBCNO_trip").text("");
        return false;
    }
    var VBCNO = parseInt($("#mid_right_upload input[name='VideoBindCNO']").val());
    var reg = /^\d{5}$/;
    if (reg.test(VBCNO) == false) {
        $("#VBCNO_trip").text("课程号格式不正确");
        return false;
    }
    $("#VBCNO_trip").html("<font style='color: green'>"+"√"+"</font>");
    return true;
}

/*select值一改变，就把值给赋给课程阶段*/
function changeselect(obj){
    cphase=$(obj).find("option:selected").text();

}

// select选择显示数量
function changeShowCout(obj){
    everyPage_count=$(obj).find("option:selected").text();
    if (currentshow == 1) {
        $("#mid_right_show").html("");
        showhomepage(1, everyPage_count, 1);
    }
    if (currentshow == 2) {
        $("#mid_right_show").html("");
        showonecourse(1, everyPage_count, 1);//parseInt($("strong a span").text())
    }
}

//select选择课程阶段
function changeShowCategory(obj){
    // alert($(obj).find("option:selected").val());
    if (currentshow == 1) {
        $("#mid_right_show").html("");
        showhomepage(1, everyPage_count, 1);
    }
    if (currentshow == 2) {
        $("#mid_right_show").html("");
        showonecourse(1, everyPage_count, 1);//parseInt($("strong a span").text())
    }
}

//根据课程名称查询对应的信息
function query_cname(){
    $("#mid_right_show").html("");
    queryCName(1,everyPage_count,$("input[name='query_cname']").val(),1);
}

//根据当前页码显示对应数据
function turningpage(obj){
    addStrong(obj);
   // var lastpage= $("#limitpage span").length;//获取最后一个页码
   //  var currentIndex = $("#limitpage a").index(obj);//获取当前a标签的下标
    var currentPage=$(obj).find("span").text();
    if(currentshow==1){
        $("#mid_right_show").html("");
        showhomepage(currentPage,everyPage_count,0);
    }
    if(currentshow==2){
        $("#mid_right_show").html("");
        showonecourse(currentPage,everyPage_count,0);
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
            showhomepage(parseInt(currentPage) - 1, everyPage_count, 0);
        }
        if (currentshow == 2) {
            $("#mid_right_show").html("");
            showonecourse(parseInt(currentPage) -1, everyPage_count, 0);
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

    if(currentindex==7&&currentPage<TemtotalPage){
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
            showhomepage(parseInt(currentPage) + 1, everyPage_count, 0);
        }
        if (currentshow == 2) {
            $("#mid_right_show").html("");
            showonecourse(parseInt(currentPage) + 1, everyPage_count, 0);
        }
    }else{
        alert("不能再下一页了");
    }
}

//展示视频
function showvideo(obj){
    var videourl=$(obj).children("input:last-child").val();
    if(videourl!="null"){
        var path="/video/"+videourl;
        window.open( path, "_blank" );
    }else {
        var funcName=$(obj).find("#show_firstdiv span").text();
        window.open( "https://baike.baidu.com/item/"+funcName, "_blank" );
    }

}
