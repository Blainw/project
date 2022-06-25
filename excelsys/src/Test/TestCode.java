import cn.edu.wtu.wdm.model.dao.LecturerDao;
import cn.edu.wtu.wdm.model.dao.TestDao;
import cn.edu.wtu.wdm.model.po.Administrator;
import cn.edu.wtu.wdm.model.po.Course;
import cn.edu.wtu.wdm.model.po.Lecturer;
import cn.edu.wtu.wdm.model.po.LecturerList;
import cn.edu.wtu.wdm.model.service.AdminService;
import cn.edu.wtu.wdm.model.service.CourseService;
import cn.edu.wtu.wdm.model.service.LecturerService;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TestCode {
    @Test
    public void TestCheck(){//没有整合spring框架使用
//        SqlSession sqlSession= MybatisUtils.getSqlSession();
//        LecturerDao lec=sqlSession.getMapper(LecturerDao.class);
//        /*这里LecturerDao是个接口，为什么接口在下面代码中又能调用方法呢，那么肯定是getMapper传回的是一个实现类对象
//         *   那么可以猜测getMapper私下为我们创建了一个对象，这就涉及到了jdk动态代理，动态代理就是创建了一个实现了一个接口的目标类
//         * ，然后再创建一个代理类去代理目标类，最后通过代理类来访问目标类的方法，而这个方法就是接口中的方法，mybatis就是帮我们把实现了接口的
//         * 目标类进行封装了，然后传回一个代理类对象让你去操作方法，这也就是为什么不需要创建LecturerDaoImpl实现类来自己去执行，因为别人已经帮我们封装好了，
//         * 实现类LecturerDaoImpl就相当于动态代理中的目标类，LecturerDoa接口就相当于动态代理中的接口，而LecturerDao lec声明的这个变量，就是
//         * 指向了mybatis返回的一个代理类对象，你通过这个代理类就能访问接口中的方法
//         * */
//        Lecturer lecturer= lec.CheckLecturer(1001,"123456");
//        System.out.println(lecturer);
//        sqlSession.close();
    }

    @Test
    public void TestInsert(){
//        SqlSession sqlSession= MybatisUtils.getSqlSession();
//        LecturerDao lec=sqlSession.getMapper(LecturerDao.class);
//        Lecturer user=new Lecturer("王者","123456","wz@163.com");
//        System.out.println(lec.InsertLecturer(user));
//        sqlSession.commit();
//        sqlSession.close();

    }
    @Test
    public void TestUpdate(){
//        SqlSession sqlSession=MybatisUtils.getSqlSession();
//        LecturerDao lec=sqlSession.getMapper(LecturerDao.class);
//        Lecturer lecturer=new Lecturer(1018,"网六","555555","lisi@163.com");
//        System.out.println(lec.UpdateLecturer(lecturer));
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Test
    public void TestDelete(){//整合了spring框架使用
        String config="applicationContext.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        TestDao lec= (TestDao) ac.getBean("testDao");
        System.out.println(lec.deleteUser(1015));
    }

    @Test
    public void TestSelectAll(){
        String config="applicationContext.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        TestDao td= (TestDao) ac.getBean("testDao");
        PageHelper.startPage(2,3);//从第一页开始，每一页有三行
        List<Lecturer> lecturers=td.selectAll();
        for (Lecturer lecturer:lecturers
             ) {
            System.out.println(lecturer);
        }

    }

    @Test
    public void TestMybatisandspring(){
        String config="applicationContext.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        LecturerService les= (LecturerService) ac.getBean("lecturereService");
        Lecturer lecturer= les.CheckLNum(1002,"555555");
        System.out.println(lecturer);
    }
    @Test
    public void TestUpdates(){
        String config="applicationContext.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        LecturerService les= (LecturerService) ac.getBean("lecturereService");
        Lecturer lecturer=new Lecturer(1,1001,"王德明","123456","1831486177@qq.com");

    }

    @Test
    public void Testshowlist(){
//        String config="applicationContext.xml";
//        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
//        AdminService as= (AdminService) ac.getBean("adminServiceImpl");
//        List<LecturerList> les=as.SelectAllLecturer();
//        for (LecturerList lec:les
//             ) {
//            System.out.println(lec.toString());
//        }
    }

    @Test
    public void TestExport() throws IOException {
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);
    }

    @Test
    public void TestQueryByCName(){
        String config="applicationContext.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        CourseService cs= (CourseService) ac.getBean("courseServiceImpl");
        List<Course> courses=cs.QueryByCName("and函数",null);
        for (Course css:courses
             ) {
            System.out.println(css.toString());
        }
    }

}
