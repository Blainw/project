package cn.edu.wtu.wdm.controller;

import cn.edu.wtu.wdm.model.po.Course;
import cn.edu.wtu.wdm.model.po.Lecturer;
import cn.edu.wtu.wdm.model.service.CourseService;
import cn.edu.wtu.wdm.utils.Export;
import cn.edu.wtu.wdm.utils.FileDelete;
import cn.edu.wtu.wdm.utils.GetRealPath;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/Course")
public class CourseController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    /*显示所有课程*/
    @RequestMapping(value = "/showhomepage.do")
    @ResponseBody
    public List<Course> ShowHomePage(Integer pageNum,Integer pageSize,String isPhase,HttpServletRequest request) {
        List<Course> list=null;
        PageHelper.startPage(pageNum,pageSize);
        if(isPhase.equals("所有类别")) {
           list = courseService.ShowHomePage(null);
        }
        else {
           list = courseService.ShowHomePage(isPhase);
        }
        //使用PageInfo包装结果
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        request.getSession().setAttribute("totalPage",(int)Math.ceil(pageInfo.getTotal()/(float)pageSize));
        return list;
    }

    /*显示某位讲师发布的课程*/
    @RequestMapping(value = "/showonecourse.do")
    @ResponseBody
    public List<Course> ShowOneCourse(Integer pageNum,Integer pageSize,String isPhase,HttpServletRequest request) {
        List<Course> list=null;
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        PageHelper.startPage(pageNum,pageSize);
        if(isPhase.equals("所有类别")) {
            list=courseService.ShowOneCourse(lecturer.getLNum(),null);
        }
        else {
            list=courseService.ShowOneCourse(lecturer.getLNum(),isPhase);
        }

        //使用PageInfo包装结果
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        request.getSession().setAttribute("totalPage",(int)Math.ceil(pageInfo.getTotal()/(float)pageSize));
        return list;
    }

    /*根据课程名称查询信息*/
    @RequestMapping(value = "/querycname.do")
    @ResponseBody
    public List<Course> queryCName(Integer pageNum,Integer pageSize,String CName,Integer currentTab,HttpServletRequest request) {
        List<Course> list=null;
        PageHelper.startPage(pageNum,pageSize);
        if(currentTab==1){
            list=courseService.QueryByCName(CName,null);
        }
        if(currentTab==2){
            Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
            list=courseService.QueryByCName(CName,lecturer.getLNum());
        }
        //使用PageInfo包装结果
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        request.getSession().setAttribute("totalPage",(int)Math.ceil(pageInfo.getTotal()/(float)pageSize));
        return list;
    }

    /*检查课程编号是否存在*/
    @RequestMapping(value = "/checkcno.do")
    @ResponseBody
    public Integer CheckCNO(Integer CNO ,HttpServletRequest request) {
        int result=0;
        result = courseService.CheckCNO(CNO);
        return result;
    }

    /*修改课程信息*/
    @RequestMapping(value = "/update.do")
    public ModelAndView UpdateCourse(Course course, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int isOK=courseService.UpdateCourse(course);
        if (isOK == 1) {
            mv.setViewName("redirect:/ToPage/homepage.action");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }

    /*添加课程信息*/
    @RequestMapping(value = "/add.do")
    public ModelAndView AddCourse(Course course, HttpServletRequest request) {
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        System.out.println(course.getCPhase());
        course.setLNum(lecturer.getLNum());
        int isOK=courseService.AddCourse(course);
        ModelAndView mv = new ModelAndView();
        if (isOK == 1) {
            mv.setViewName("redirect:/ToPage/homepage.action");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }

    /*删除课程信息*/
    @RequestMapping(value = "/delete.do")
    public ModelAndView DeleteCourse(Integer CNO) {
        ModelAndView mv = new ModelAndView();
        int isOK=courseService.DeleteCourse(CNO);
        System.out.println("删除："+isOK);
        if (isOK == 1) {
            mv.setViewName("redirect:/ToPage/homepage.action");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }

    /*导出当前页面数据*/
    @RequestMapping(value = "/exportcurrent.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String ExportCurrentCourse(Integer currentPage,Integer currentShow,Integer currentCount,String currentCategory,HttpServletRequest request) {
        List<Course> list=null;//声明一个集合用来装结果
        Export ex=new Export();//声明导出用的工具类对象
        int result=0;//声明一个结果变量，用来保存导出成功或失败的结果信息
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        if(currentShow==1){//如果当前页面是1，那么就查询所有
            list=ShowHomePage(currentPage,currentCount,currentCategory,request);
            result=ex.Export_Course(list,"");
        }
        if(currentShow==2){//如果当前页面是2，就查询个人
            list=ShowOneCourse(currentPage,currentCount,currentCategory,request);
            result=ex.Export_Course(list,lecturer.getLName());
        }

        if(result==1){
            return "导出成功";
        }else {
            return "导出失败";
        }
    }

    /*导出目前选项卡的所有页面数据*/
    @RequestMapping(value = "/exportall.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String ExportAllCourse(Integer currentShow,HttpServletRequest request) {
        List<Course> list=null;
        Export ex=new Export();
        int result=0;
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        if(currentShow==1){
            list=courseService.ShowHomePage(null);
            result=ex.Export_Course(list,"");
        }
        if(currentShow==2){
            list=courseService.ShowOneCourse(lecturer.getLNum(),null);
            result=ex.Export_Course(list,lecturer.getLName());
        }


        if(result==1){
            return "导出成功";
        }else {
            return "导出失败";
        }

    }
    /*视频上传*/
    @RequestMapping(value ="/videoup.do")
    public ModelAndView videoupload(HttpServletRequest request, MultipartFile video, @RequestParam(value = "VideoBindCNO")Integer CNO) throws IOException {
        ModelAndView mv = new ModelAndView();
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        String filename = video.getOriginalFilename();//获取上传文件名
        String videourl = GetRealPath.getPath(this)+"\\video\\" + lecturer.getLName() +lecturer.getLNum()+ "\\课程号" + CNO + "\\" + filename;//指定文件本地存放地址
        File file = new File(GetRealPath.getPath(this)+"\\video\\" + lecturer.getLName() +lecturer.getLNum()+ "\\课程号" + CNO + "\\");
        FileDelete.deleteFile(file);//删除对应目录下的所有文件
        video.transferTo(new File(videourl));//将视频传到指定地址
        courseService.AddVideoUrl(CNO, lecturer.getLName() +lecturer.getLNum()+ "/课程号" + CNO + "/" + filename);//将视频地址绑定给课程号，存入数据库中

        mv.setViewName("redirect:/ToPage/homepage.action");
        return mv;
    }
    /*视频删除*/
    @RequestMapping(value ="/deletevideo.do")
    public ModelAndView DeleteVideo(Integer CNO,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        if(courseService.DeleteVideo(CNO)==1){
            File file = new File(GetRealPath.getPath(this)+"\\video\\" + lecturer.getLName() +lecturer.getLNum()+ "\\课程号" + CNO + "\\");
            FileDelete.deleteFile(file);
            mv.setViewName("redirect:/ToPage/homepage.action");
        }else{
            mv.setViewName("loginError");
        }
        return mv;
    }

    //检查绑定视频的课程号是否合法
    @RequestMapping(value = "/checkcnolegality.do")
    @ResponseBody
    public Integer CheckCNOLegality(Integer CNO ,HttpServletRequest request) {
        Lecturer lecturer= (Lecturer) request.getSession().getAttribute("currentLecturer");
        if(courseService.CheckCNO(CNO)!=1){//检查课程是否在数据库中
            return 1;//返回1代表课程号不存在
        }
        if(courseService.IsHaveCNO(CNO,lecturer.getLNum())!=1){//检查课程号是否属于用户
            return 2;//返回2代表该用户没有该课程号
        }
        return 0;//返回0表示课程号存在，且该用户拥有该课程号的课程
    }
}
