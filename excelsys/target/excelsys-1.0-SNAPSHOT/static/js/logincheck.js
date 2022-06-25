// var InterValObj=null;
var time=11;
$(function (){
    var result=false;
    var result2=false;
    $("#login").submit(function (event) {
        result=checkUser();
        if(result) {
            result2 = checkPwd();
        }
        if(result2){
            $.ajax({
                type:"post",
                url:"Lecturer/login.do",
                data:{
                    LNum:$("input[name='LNum']").val(),
                    Pwd:$("input[name='Pwd']").val()
                },
                dataType:"json",
                success:function (data){
                    if(data==1){
                        $(location).attr("href","ToPage/homepage.action");
                    }
                    if(data==0){
                        $(".errormessage").text("");
                        $(".errormessage").text("用户名或密码错误，请重新输入");
                    }
                    if(data==2){
                        $(".errormessage").text("");
                        $(".errormessage").text("一个浏览器只能一个用户登录！");
                    }
                    // $.each(data,function (i,n){//当是一个数组，数组元素是一个对象时，这样可以拿到元素
                    //     alert(n.lname);
                    // })

                }
            })
        }
        event.preventDefault();
    });

    //管理员登录
    $("#slogin").submit(function (event){
        if(time==11){
            $.ajax({
                type:"post",
                url:"Admin/login.do",
                data:{
                    SName:$("input[name='SName']").val(),
                    Pwd:$("input[name='SPwd']").val()
                },
                dataType:"json",
                success:function (data){
                    if(data==0){
                        time--;
                      setTimeout(function fn(){
                          $(".admin_login_tips").text("请等"+time+"秒再试");
                          time--;
                          //还能倒计时
                          if(time>=0){
                              setTimeout(fn,1000);
                          }
                          /*代表十秒倒计时已过*/
                          if(time<0){
                              $(".admin_login_tips").text("");
                              time=11;
                              return false;
                          }

                      },1000)

                    }
                    if(data!=0&&data!=9&&data!=10){
                        $(".admin_login_tips").text("账号或密码输入错误，还有"+data+"次将锁定！");
                    }

                    if(data==9){
                        $(location).attr("href","ToPage/management.action");
                    }
                    if(data==10){
                        $(".admin_login_tips").text("");
                        $(".admin_login_tips").text("一个浏览器只能登录一个管理员！");
                    }
                }
            })
        }
        event.preventDefault();
    })

})

function checkUser() {
    if ($("#inLNum").val() == "" || $("#inLNum").val() == null) {
        $(".errormessage").text("请输入用户名");
        return false;
    }
    var LNum = $("#inLNum").val();
    var reg = /^\d{4}$/;
    if (reg.test(LNum) == false) {
        $(".errormessage").text("用户名格式不正确");
        return false;
    }
    $(".errormessage").text("");
    return true;
}

function checkPwd() {
    if ($("#inPwd").val() == "" || $("#inPwd").val() == null) {
        $(".errormessage").text("请输入密码");
        return false;
    }
    var pwd = $("#inPwd").val();
    var reg = /^[A-Za-z0-9]{6,10}$/;
    if (reg.test(pwd) == false) {
        $(".errormessage").text("密码格式不正确");
        return false;
    }
    $(".errormessage").text("");
    return true;
}

