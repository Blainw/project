package cn.edu.wtu.wdm.controller;

import cn.edu.wtu.wdm.model.po.Administrator;
import cn.edu.wtu.wdm.model.po.LecturerList;
import cn.edu.wtu.wdm.model.service.AdminService;
import cn.edu.wtu.wdm.utils.Export;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/Admin")
public class AdminController {

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    //默认输入次数
    private static int times=4;
    //默认为true，表示是否可以进入倒计时
    private boolean flag=true;

    //管理员验证账号密码
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Integer Login(@RequestParam(value = "SName", required = true) String SName, String Pwd, HttpServletRequest request) {
        int result = 0;
        Administrator admin= (Administrator) request.getSession().getAttribute("currentAdmin");
        if(admin!=null) {
            result=10;
            return result;
        }
        if(times>0){
            Administrator administrator = adminService.CheckAdmin(SName, Pwd);
            if(administrator!=null) {
                request.getSession().setAttribute("currentAdmin", administrator);
                result=9;
            }else {
                result=--times;
            }
        }

       if(times==0&&flag){
           flag=false;
           Thread t=new Thread(new Runnable() {//采用匿名内部类创建线程
               @Override
               public void run() {
                   try {
                       Thread.sleep(10000);
                       times=4;
                       flag=true;
                       Thread.interrupted();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           });
           t.start();//启动线程
       }

        return result;
    }


    /*管理员退出操作，删除session中的currentAdmin，并返回到登录页面*/
    @RequestMapping(value = "/loginout.do")
    public String LoginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("currentAdmin");

        return "redirect:/ToPage/login.action";
    }

    /*查询所有讲师以及其授课信息*/
    /*显示所有课程*/
    @RequestMapping(value = "/selectalllecturer.do")
    @ResponseBody
    public List<LecturerList> SelectAllLecturer(Integer pageNum, Integer pageSize,String isCategory, HttpServletRequest request) {
        String category="";//按照哪个类别排序
        String sequence="";//升序还是降序
        if(isCategory.equals("按讲师号")){
            category="LNum";
            sequence="asc";
        }
        if(isCategory.equals("按课程数")){
            category="CAmount";
            sequence="desc";
        }
        List<LecturerList> list=null;
        PageHelper.startPage(pageNum,pageSize);
        list= adminService.SelectAllLecturer(category,sequence);
        //使用PageInfo包装结果
        PageInfo<LecturerList> pageInfo = new PageInfo<>(list);
        request.getSession().setAttribute("totalPage",(int)Math.ceil(pageInfo.getTotal()/(float)pageSize));
        return list;
    }


    /*删除讲师信息*/
    @RequestMapping(value = "/delete.do")
    public ModelAndView DeleteLecturer(Integer LNum) {
        ModelAndView mv = new ModelAndView();
        int isOK=adminService.DeleteLecturer(LNum);
        if (isOK == 1) {
            mv.setViewName("redirect:/ToPage/management.action");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }

    /*检查用户名是否存在*/
    @RequestMapping(value = "/checklnum.do")
    @ResponseBody
    public Integer CheckLNum(Integer LNum ,HttpServletRequest request) {
        int result=0;
        result = adminService.CheckLNum(LNum);
        return result;
    }

    /*修改讲师信息*/
    @RequestMapping(value = "/updatelecturer.do")
    public ModelAndView UpdateLecturer(LecturerList lecturerList, HttpServletRequest request) {
        System.out.println(lecturerList);
        ModelAndView mv = new ModelAndView();
        int isOK=adminService.UpdateLecturer(lecturerList);
        if (isOK == 1) {
            mv.setViewName("redirect:/ToPage/management.action");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }


    /*导出当前页面数据*/
    @RequestMapping(value = "/exportcurrent.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String ExportCurrentLecturer(Integer currentPage,Integer currentShow,Integer currentCount,String currentCategory,HttpServletRequest request) {
        List<LecturerList> list=null;
        if(currentShow==1){
            list=SelectAllLecturer(currentPage,currentCount,currentCategory,request);
        }

        Export ex=new Export();
        int result=ex.Export_Lecturer(list);
        if(result==1){
            return "导出成功";
        }else {
            return "导出失败";
        }
    }

    /*导出目前选项卡的所有页面数据*/
    @RequestMapping(value = "/exportall.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String ExportCurrentLecturer(Integer currentShow,String currentCategory,HttpServletRequest request) {
        List<LecturerList> list=null;
        String category="";//按照哪个类别排序
        String sequence="";//升序还是降序
        if(currentCategory.equals("按讲师号")){
            category="LNum";
            sequence="asc";
        }
        if(currentCategory.equals("按课程数")){
            category="CAmount";
            sequence="desc";
        }
        if(currentShow==1){
            list=adminService.SelectAllLecturer(category,sequence);
        }

        Export ex=new Export();
        int result=ex.Export_Lecturer(list);
        if(result==1){
            return "导出成功";
        }else {
            return "导出失败";
        }
    }
}
