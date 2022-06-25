package cn.edu.wtu.wdm.model.dao;

import cn.edu.wtu.wdm.model.po.Administrator;
import cn.edu.wtu.wdm.model.po.Course;
import cn.edu.wtu.wdm.model.po.Lecturer;
import cn.edu.wtu.wdm.model.po.LecturerList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    Administrator CheckAdmin(@Param("SName") String SName, @Param("Pwd") String Pwd);//登录时根据传入的账户和密码来进行数据库比对
    List<LecturerList> SelectAllLecturer(@Param("category")String category,@Param("sequence")String sequence);//查询所有讲师以及他的授课信息
    int DeleteLecturer(@Param("LNum") Integer LNum);//删除讲师信息
    int CheckLNum(@Param("LNum") Integer LNum);//修改之前ajax检查讲师编号是否存在
    int UpdateLecturer(LecturerList lecturerList);//修改当前课程信息
}
