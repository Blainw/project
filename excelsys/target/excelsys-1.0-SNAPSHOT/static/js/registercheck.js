$(function (){
    var isLNum=false;
    var isRpwd=false;
    var isPwd=false;
    var isLEmail=false;

    $("input[name='Rpwd']").blur(function (){
        var Pwd=$("input[name='Pwd']").val() ;
        var Rpwd=$("input[name='Rpwd']").val() ;
        if (Rpwd!=Pwd){
            $("#RePwd_trip").text("两次密码不一致");
        }else {
            isRpwd=true;
            $("#RePwd_trip").html("<font style='color: green'>"+"√"+"</font>");
        }
    })

    $("input[name='Pwd']").blur(function (){

        if(checkPwd()){
            isPwd=true;
        }
    })

    $("input[name='LName']").blur(function (){
        var name=$("input[name='LName']").val();
        if(name!=""){
            $("#LName_trip").html("<font style='color: green'>"+"√"+"</font>");
        }else{
            $("#LName_trip").text("");
        }
    })

    $("input[name='LEmail']").blur(function (){
        if(checkEmail())
            $.ajax({
                type:"post",
                url:"Lecturer/checkmail.do",
                data:{
                    LEmail:$("input[name='LEmail']").val()
                },
                dataType:"json",
                success:function (data){
                    if(data==1){
                        $("#LEmail_trip").text("此邮箱已存在");
                    }
                    if(data==0){
                        isLEmail=true;
                        $("#LEmail_trip").html("<font style='color: green'>"+"√"+"</font>");
                    }
                    // $.each(data,function (i,n){//当是一个数组，数组元素是一个对象时，这样可以拿到元素
                    //     alert(n.lname);
                    // })

                }
            })
    })
    $("input[name='LNum']").blur(function (){
        if(checkLNum())
            $.ajax({
                type:"post",
                url:"Lecturer/checklnum.do",
                data:{
                    LNum:$("input[name='LNum']").val()
                },
                dataType:"json",
                success:function (data){
                    if(data==1){
                        $("#LNum_trip").text("此用户名已存在");
                    }
                    if(data==0){
                        isLNum=true;
                        $("#LNum_trip").html("<font style='color: green'>"+"√"+"</font>");
                    }

                }
            })
    })

    $("#register").submit(function (event){
        if(!(isLNum&&isPwd&&isRpwd&&isLEmail)){
            event.preventDefault();
        }
    })
    $("input[name='reset']").click(function (){
        $("label[id$=_trip]").text("");
    })
})


function checkLNum() {

    var LNum = $("input[name='LNum']").val();
    var reg = /^\d{4}$/;
    if (reg.test(LNum) == false) {
        $("#LNum_trip").text("用户名格式不正确");
        return false;
    }
    $("#LNum_trip").html("<font style='color: green'>"+"√"+"</font>");
    return true;
}
function checkEmail() {

    var Email = $("input[name='LEmail']").val();
    var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (reg.test(Email) == false) {
        $("#LEmail_trip").text("邮箱格式不正确");
        return false;
    }
    $("#LEmail_trip").html("<font style='color: green'>"+"√"+"</font>");
    return true;
}
function checkPwd() {

    var pwd = $("input[name='Pwd']").val();
    var reg = /^[A-Za-z0-9]{6,10}$/;
    if (reg.test(pwd) == false) {
        $("#Pwd_trip").text("密码格式不正确");
        return false;
    }
    $("#Pwd_trip").html("<font style='color: green'>"+"√"+"</font>");
    return true;
}