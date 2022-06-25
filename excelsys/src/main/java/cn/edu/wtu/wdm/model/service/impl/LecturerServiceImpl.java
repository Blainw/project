package cn.edu.wtu.wdm.model.service.impl;

import cn.edu.wtu.wdm.model.dao.CourseDao;
import cn.edu.wtu.wdm.model.dao.LecturerDao;
import cn.edu.wtu.wdm.model.po.Lecturer;
import cn.edu.wtu.wdm.model.service.LecturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class LecturerServiceImpl implements LecturerService {

    private LecturerDao ld=null;//这也创建，并且加一个set方法，就可以用spring的bean容器来注入这个对象

    public void setLd(LecturerDao ld) {
        this.ld = ld;
    }

    @Override
    public Lecturer CheckLNum(int LNum, String Pwd) {

        return ld.CheckLecturer(LNum,Pwd);
    }

    @Override
    public int Register(Lecturer lecturer) {

        return ld.InsertLecturer(lecturer);
    }


    @Override
    public int Update(Lecturer lecturer) {
        return ld.UpdateLecturer(lecturer) ;
    }

    @Override
    public int CheckEmail(String LEmail) {
        return ld.CheckEmail(LEmail);
    }

    @Override
    public int CheckLNum(Integer LNum) {
        return ld.CheckLNum(LNum);
    }
}
