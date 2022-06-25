package cn.edu.wtu.wdm.model.dao;

import cn.edu.wtu.wdm.model.po.Course;
import cn.edu.wtu.wdm.model.po.Lecturer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    List<Course> ShowHomePage(@Param("isPhase") String isPhase);//显示所有课程
    List<Course> ShowOneCourse(@Param("LNum")Integer LNum,@Param("isPhase") String isPhase);//根据用户名查询该用户名的所有课程
    int CheckCNO(@Param("CNO") Integer CNO);//注册之前ajax检查课程编号是否存在
    int UpdateCourse(Course course);//修改当前课程信息
    int AddCourse(Course course);//添加课程
    int DeleteCourse(@Param("CNO") Integer CNO);//删除课程信息

    List<Course> QueryByCName(@Param("CName")String CName,@Param("LNum")Integer LNum);//根据课程号查询课程

    int AddVideoUrl(@Param("CNO") Integer CNO,@Param("CVideo") String CVideo);//给对应课程添加视频地址
    int IsHaveCNO(@Param("CNO") Integer CNO,@Param("LNum") Integer LNum);//查询该用户是否有这个课程号
    int DeleteVideo(@Param("CNO") Integer CNO);//删除该课程的视频地址
}
