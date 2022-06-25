package cn.edu.wtu.wdm.model.service.impl;

import cn.edu.wtu.wdm.model.dao.CourseDao;
import cn.edu.wtu.wdm.model.dao.LecturerDao;
import cn.edu.wtu.wdm.model.po.Course;
import cn.edu.wtu.wdm.model.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao cd=null;//这也创建，并且加一个set方法，就可以用spring的bean容器来注入这个对象

    public void setCd(CourseDao cd) {
        this.cd = cd;
    }

    @Override
    public List<Course> ShowHomePage(String isPhase) {
        return cd.ShowHomePage(isPhase);
    }

    @Override
    public List<Course> ShowOneCourse(Integer LNum,String isPhase) {
        return cd.ShowOneCourse(LNum,isPhase);
    }

    @Override
    public int CheckCNO(Integer CNO) {
        return cd.CheckCNO(CNO);
    }

    @Override
    public int UpdateCourse(Course course) {
        return cd.UpdateCourse(course);
    }

    @Override
    public int AddCourse(Course course) {
        return cd.AddCourse(course);
    }

    @Override
    public int DeleteCourse(Integer CNO) {
        return cd.DeleteCourse(CNO);
    }

    @Override
    public List<Course> QueryByCName(String CName, Integer LNum) {
        return cd.QueryByCName(CName,LNum);
    }

    @Override
    public int AddVideoUrl(Integer CNO, String CVideo) {
        return cd.AddVideoUrl(CNO,CVideo);
    }

    @Override
    public int IsHaveCNO(Integer CNO, Integer LNum) {
        return cd.IsHaveCNO(CNO,LNum);
    }

    @Override
    public int DeleteVideo(Integer CNO) {
        return cd.DeleteVideo(CNO);
    }
}
