$(function (){

    //导出当前页面课程为excel
    $(document).on('click', '#exportcurrent_course',function(){
        var currentCount=parseInt($("#currentSize option:selected").text());
        var currentPage=parseInt($("strong a span").text());
        var currentCategory=$("#selectedCPhase").find("option:selected").text();
        $.ajax({
            type:"post",
            url:"Course/exportcurrent.do",
            data:{
                currentPage:currentPage,
                currentShow:parseInt(currentshow),
                currentCount:currentCount,
                currentCategory:currentCategory,
            },
            dataType:"text",
            success:function (data){
                alert(data);
            }
        })
    })

    $(document).on('click', '#exportall_course',function(){
        $.ajax({
            type:"post",
            url:"Course/exportall.do",
            data:{
                currentShow:parseInt(currentshow),
            },
            dataType:"text",
            success:function (data){
                alert(data);
            }
        })
    })

    //导出此选项卡所有课程信息为excel
    // $("#exportall_course").click(function (){
    //     $.ajax({
    //         type:"post",
    //         url:"Course/exportall.do",
    //         data:{
    //             currentShow:parseInt(currentshow),
    //         },
    //         dataType:"text",
    //         success:function (data){
    //             alert(data);
    //         }
    //     })
    // })

    //导出当前页面的老师相关信息
    $(document).on('click', '#exportcurrent_lecturer',function(){
        var currentPage=parseInt($("strong a span").text());
        var currentCategory=$("#category").find("option:selected").text();
        $.ajax({
            type:"post",
            url:"Admin/exportcurrent.do",
            data:{
                currentPage:currentPage,
                currentShow:parseInt(currentshow),
                currentCount:everyPage_count,
                currentCategory:currentCategory
            },
            dataType:"text",
            success:function (data){
                alert(data);
            }
        })
    })


    //导出此选项卡所有老师信息
    $(document).on('click', '#exportall_lecturer',function(){
        var currentCategory=$("#category").find("option:selected").text();
        $.ajax({
            type:"post",
            url:"Admin/exportall.do",
            data:{
                currentShow:parseInt(currentshow),
                currentCategory:currentCategory
            },
            dataType:"text",
            success:function (data){
                alert(data);
            }
        })
    })



})