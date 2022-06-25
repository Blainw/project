package cn.edu.wtu.wdm.controller;

import cn.edu.wtu.wdm.model.po.Lecturer;
import cn.edu.wtu.wdm.model.service.LecturerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 * @Controller:创建处理器对象，对象放在springmvc容中。
 * 位置：在类的上面
 * @RequestMapping放在类上面，value值表示Lecture模块，加上下面方法上面的 @RequestMapping的值，构成了一个  /模块/功能  的地址，更具可读性
 * */
@Controller
@RequestMapping(value = "/Lecturer")
public class LecturerController {


    /*因为Controller类中加了组件扫描器，故可以扫描到Resource注解，自动按byName方式注入,又我们在spring容器中创建的service的对象是用bean方式
    * 创建的，而不是用注解方式创建的service，所以我们这里得按照bean中的名称来注入，这里名称是lecturereService
    * */
    @Resource(name="lecturereService")
    private LecturerService lecturerService;
    /*
     * 处理用户提价的请求，springmvc中是使用方法来处理的。
     * 方法是自定义的，可以有多种返回值，多种参数，方法名称自定义
     * */


    /*
     * 准备使用doSome方法处理some.do的请求
     * @RequestMapping:请求映射，作用是把一个请求地址和方法绑定在一起。
     * 就是一个请求指定一个方法处理
     *       属性：1.value,是一个string，表示请求的uri地址。value值必须是唯一的，不能重复
     *               在使用时，推荐地址以“/”开头
     *       位置：1.在方法上面，最常用的
     *            2.在类的上面
     *       返回值：ModelAndView
     *          model:数据，请求处理完成后，要显示给用户的数据
     *          view：视图，比如jsp等等.
     *
     *    说明：使用RequestMapping修饰的方法叫做处理器方法或者控制器方法，是可以处理请求的，类似于servlet中的doPost
     *
     *
     * 注解RequestParam是解决jsp页面参数和这的形参名不一致的问题的，因为往往我们前后端是分离的，不是同一个人开发，所有我想要获取到
     * 前端传来的值，就加这个注解，获取到之后，然后传给这里的形参，就可以使用了
     * required值为true表示必须接收这个参数，不接收就会报错
     * */
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Integer Login(@RequestParam(value = "LNum", required = true) Integer LNum, String Pwd,HttpServletRequest request) {
        int result;
        Lecturer lec= (Lecturer) request.getSession().getAttribute("currentLecturer");
        if(lec!=null) {
         result=2;
         return result;
        }
        Lecturer lecturer = lecturerService.CheckLNum(LNum, Pwd);
        if(lecturer!=null) {
            result=1;
            request.getSession().setAttribute("currentLecturer", lecturer);
        }else {
            result=0;
        }
        return result;
    }

    /*注册用户*/
    @RequestMapping(value = "/register.do")
    public ModelAndView Register(Lecturer lecturer) {
        ModelAndView mv = new ModelAndView();
        int isOK = lecturerService.Register(lecturer);
        if (isOK == 1) {
            mv.setViewName("redirect:/ToPage/login.action");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }

    /*修改用户*/
    @RequestMapping(value = "/update.do")
    public ModelAndView UpdateLecturer(Lecturer lecturer,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int isOK = lecturerService.Update(lecturer);
        if (isOK == 1) {
            mv.setViewName("redirect:/Lecturer/loginout.do");
        } else {
            mv.setViewName("loginError");
        }
        return mv;
    }

    /*
     * ResponseBody注解作用是，把处理器方法返回的对象转为json，通过HttpServletResponse输出给浏览器
     * 位置在方法的上面。和其它注解没有顺序的先后
     * 如果加了ResponseBody，并且返回值是String，则返回的是数据，如“hello world”；这时，ajax中就应该以dataType：“text”来接收
     * 同时，我们要在RequestMapping中加入produce=“text/plain;charset=utf-8”，因为StringHttpMessageConverter默认使用的ISO-8859-1来解析数据
     * 如果不加ResponseBody，返回值是String，则返回的是视图   如“login.jsp”；
     *
     * */

    /*ajax检查用户名*/
    @RequestMapping(value = "/checklnum.do")
    @ResponseBody
    public Integer CheckLNum(Integer LNum ,HttpServletRequest request) {
        int result=0;
        Lecturer lecturer=(Lecturer) request.getSession().getAttribute("currentLecturer");//登录成功才会得到对象
        if(lecturer!=null) {//这里如果有对象，那么执行1.修改,如果这里为空，意味着没有对象，那么执行的是2.注册
            //1.修改
            if (LNum != lecturer.getLNum())//如果传入的LNum和登录的对象的LNum不相等，表明改了LNum，则对改了的LNum进行检测
                result = lecturerService.CheckLNum(LNum);
            return result;//如果传入的和登录对象的LNum相等，意味着没有修改用户名，我们没必要去做检测，直接返回
        }
        //2.注册
        result = lecturerService.CheckLNum(LNum);
        return result;
    }
    /*这是加了注解驱动的ajax进行邮箱检测*/
    @RequestMapping(value = "/checkmail.do")
    @ResponseBody
    public Integer CheckEmail(String LEmail,HttpServletRequest request) {
        int result=0;//这里的思路和上面的思路一模一样
        Lecturer lecturer=(Lecturer) request.getSession().getAttribute("currentLecturer");
        if(lecturer!=null) {
            //1.修改
            if (!LEmail.equals(lecturer.getLEmail()) ) {
                result = lecturerService.CheckEmail(LEmail);
            }
            return result;

        }
        //2.注册
        result = lecturerService.CheckEmail(LEmail);
        return result;//会自动被框架转为json格式
    }

    /*用户退出操作，删除session中的LName，并返回到登录页面*/
    @RequestMapping(value = "/loginout.do")
    public String LoginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("currentLecturer");
        request.getSession().removeAttribute("currentAdmin");
        return "redirect:/ToPage/login.action";
    }



    

}
