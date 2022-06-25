package cn.edu.wtu.wdm.model.service;

import cn.edu.wtu.wdm.model.po.Administrator;
import cn.edu.wtu.wdm.model.po.LecturerList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    Administrator CheckAdmin(String SName,String Pwd);//登录时根据传入的账户和密码来进行数据库比对
    List<LecturerList> SelectAllLecturer(String category,String sequence);//查询所有讲师以及他的授课信息
    int DeleteLecturer(Integer LNum);//删除讲师信息
    int CheckLNum(Integer LNum);//修改之前ajax检查讲师编号是否存在
    int UpdateLecturer(LecturerList lecturerList);//修改当前课程信息
}
