package cn.edu.wtu.wdm.interceptor;

import cn.edu.wtu.wdm.model.po.Administrator;
import cn.edu.wtu.wdm.model.po.Lecturer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    /*
    * 这个是预处理方法
    *  参数：
    *   Object handler：是被拦截的控制器对象
    * 返回值boolean
    *   true：通过了拦截器的验证，可以执行处理器方法。
    *   false：没有通过
    *
    *   特点：
    *       1.是在控制器方法执行之前先执行的，用户的请求首先到达此方法
    *
    *       2.在这个方法中可以获取请求信息，验证请求是否符合要求，可以验证用户是否登陆，用户是否有权限访问某个url
    *         如果验证失败，可以截断请求，请求不能被处理
    *         如果验证成功，可以放行请求，此时控制器方法才能执行
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //管理员没登录不能访问管理界面
        String uri=request.getRequestURI();
//        if("/excelsys/ToPage/login.action".equals(uri)){
//            Administrator currentAdmin= (Administrator) request.getSession().getAttribute("currentAdmin");
//            Lecturer currentlecturer = (Lecturer) request.getSession().getAttribute("currentLecturer");
//            if(currentlecturer!=null&&currentAdmin==null){
//                request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request,response);
//                return true;
//            }
//            if(currentlecturer==null&&currentAdmin!=null){
//                request.getRequestDispatcher("/WEB-INF/jsp/management.jsp").forward(request,response);
//                return true;
//            }
//            if(currentlecturer!=null&&currentAdmin!=null){
//                return false;
//            }
//        }
        if(uri.equals("/excelsys/ToPage/management.action")){
            Administrator currentAdmin= (Administrator) request.getSession().getAttribute("currentAdmin");
            if(currentAdmin==null){
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
                return false;
            }else{
                return true;
            }
        }
        //用户没登录不能访问主页
        if(uri.equals("/excelsys/ToPage/homepage.action")) {
            Lecturer currentlecturer = (Lecturer) request.getSession().getAttribute("currentLecturer");
            if (currentlecturer == null) {
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }


    /*
    * 这个是后处理方法
    * 参数：
    *   Object handler：被拦截的处理器对象
    *   ModelAndView：处理器方法的返回值
    *
    * 特点：
    *    1.在处理器方法之后执行的
    *    2.能够获取到处理器方法的返回值ModelAndView，可以修改ModelAndView中的数据和视图，可以影响到最后的执行结果
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /*
    * 这个是最后执行方法
    * 参数
    *   Object handler：被拦截的处理器对象
    *   Exception ex：程序中发生的异常
    * 特点
    *   1.在请求处理完成后执行的，框架中规定是当你的视图处理完成后，对视图执执行了forward，就认为请求处理完成。
    *   2.这个方法一般做资源回收工作的，程序请求过程中创建了一些对象，在这里删除，把占用的内存回收。
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
