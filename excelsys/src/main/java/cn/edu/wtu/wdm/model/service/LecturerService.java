package cn.edu.wtu.wdm.model.service;

import cn.edu.wtu.wdm.model.dao.LecturerDao;
import cn.edu.wtu.wdm.model.po.Lecturer;
import org.apache.ibatis.annotations.Param;

public interface LecturerService {


    Lecturer CheckLNum(int LNum, String Pwd);//根据传入的账户和密码来进行数据库比对
    int Register(Lecturer lecturer);//注册一个讲师用户
    int Update(Lecturer lecturer);//修改当前登录进来的用户信息
    int CheckEmail(String LEmail);//ajax检查邮箱
    int CheckLNum(Integer LNum);//注册之前ajax检查用户名
}
