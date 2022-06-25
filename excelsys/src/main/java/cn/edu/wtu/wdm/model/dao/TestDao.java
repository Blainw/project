package cn.edu.wtu.wdm.model.dao;

import cn.edu.wtu.wdm.model.po.Lecturer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestDao {
    public List<Lecturer> selectAll();
    public int deleteUser(@Param("LNum") Integer LNum);
}
