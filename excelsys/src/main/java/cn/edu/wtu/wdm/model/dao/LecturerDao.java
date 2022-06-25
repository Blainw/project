package cn.edu.wtu.wdm.model.dao;

import cn.edu.wtu.wdm.model.po.Lecturer;
import org.apache.ibatis.annotations.Param;

public interface LecturerDao {
    Lecturer CheckLecturer(@Param("LNum") int LNum, @Param("Pwd") String Pwd);//登录时根据传入的账户和密码来进行数据库比对
    int InsertLecturer(Lecturer lecturer);//注册一个讲师用户
    int UpdateLecturer(Lecturer lecturer);//修改当前登录进来的用户信息
    int CheckEmail(@Param("LEmail") String LEmail);//注册之前ajax检查邮箱
    int CheckLNum(@Param("LNum") Integer LNum);//注册之前ajax检查用户名

}
