package cn.edu.wtu.wdm.model.service.impl;

import cn.edu.wtu.wdm.model.dao.AdminDao;
import cn.edu.wtu.wdm.model.dao.CourseDao;
import cn.edu.wtu.wdm.model.po.Administrator;
import cn.edu.wtu.wdm.model.po.LecturerList;
import cn.edu.wtu.wdm.model.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao ad=null;//这也创建，并且加一个set方法，就可以用spring的bean容器来注入这个对象

    public void setAd(AdminDao ad) {
        this.ad = ad;
    }

    @Override
    public Administrator CheckAdmin(String SName, String Pwd) {
        return ad.CheckAdmin(SName,Pwd);
    }

    @Override
    public List<LecturerList> SelectAllLecturer(String category,String sequence) {
        return ad.SelectAllLecturer(category,sequence);
    }

    @Override
    public int DeleteLecturer(Integer LNum) {
        return ad.DeleteLecturer(LNum);
    }

    @Override
    public int CheckLNum(Integer LNum) {
        return ad.CheckLNum(LNum);
    }

    @Override
    public int UpdateLecturer(LecturerList lecturerList) {
        return ad.UpdateLecturer(lecturerList);
    }


}
