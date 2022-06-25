package cn.edu.wtu.wdm.model.service;

import cn.edu.wtu.wdm.model.po.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    List<Course> ShowHomePage(String isPhase);//显示所有课程
    List<Course> ShowOneCourse(Integer LNum,String isPhase);//根据用户名查询该用户名的所有课程
    int CheckCNO(Integer CNO);//注册之前ajax检查课程编号是否存在
    int UpdateCourse(Course course);//修改当前课程信息
    int AddCourse(Course course);//添加课程
    int DeleteCourse(Integer CNO);//删除课程信息

    List<Course> QueryByCName(String CName,Integer LNum);//根据课程名称查询课程信息
    int AddVideoUrl(Integer CNO,String CVideo);//给对应课程添加视频地址
    int IsHaveCNO(Integer CNO,Integer LNum);//查询该用户是否有这个课程号
    int DeleteVideo( Integer CNO);//删除该课程的视频地址
}
