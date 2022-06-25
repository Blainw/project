package cn.edu.wtu.wdm.utils;

import cn.edu.wtu.wdm.model.po.Course;
import cn.edu.wtu.wdm.model.po.LecturerList;
import org.apache.commons.collections4.Get;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Export {
    public int Export_Course(List<Course> courses,String LName){
        int result=0;
        String sheetname="课程列表";
        if(LName!=""){
            sheetname=LName+"的课程列表";
        }
        int length=courses.size();
        //创建一个HSSFWorkbook对象，这个对象对应一个excel文件
        HSSFWorkbook hwb=new HSSFWorkbook();
        //创建一个HSSFSheet，对应一个excel的页
        HSSFSheet hs=hwb.createSheet(sheetname);

        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        //创建行对象，从0开始，0代表第一行
        HSSFRow hr=hs.createRow(0);
        //创建该行的列对象，从0开始，0代表第一列
        HSSFCell hc=hr.createCell(0);
        hc.setCellValue("课程ID");
        hc=hr.createCell(1);
        hc.setCellValue("课程编号");
        hc=hr.createCell(2);
        hc.setCellValue("课程名称");
        hc=hr.createCell(3);
        hc.setCellValue("课程阶段");
        hc=hr.createCell(4);
        hc.setCellValue("课程介绍");
        hc=hr.createCell(5);
        hc.setCellValue("截止时间");
        hc=hr.createCell(6);
        hc.setCellValue("讲师姓名");
        for (int i = 1; i <=length; i++) {
            hr=hs.createRow(i);
            for (int j = 0; j < 7; j++) {
                hc=hr.createCell(0);
                hc.setCellValue(courses.get(i-1).getCID());
                hc=hr.createCell(1);
                hc.setCellValue(courses.get(i-1).getCNO());
                hc=hr.createCell(2);
                hc.setCellValue(courses.get(i-1).getCName());
                hc=hr.createCell(3);
                hc.setCellValue(courses.get(i-1).getCPhase());
                hc=hr.createCell(4);
                hc.setCellValue(courses.get(i-1).getCIntroduce());
                hc=hr.createCell(5);
                String ctime=df.format(courses.get(i-1).getCTime());
                hc.setCellValue(ctime);
                hc=hr.createCell(6);
                hc.setCellValue(courses.get(i-1).getLName());
            }
        }
        //给对应列设置宽
        hs.setColumnWidth(2,21*256);
        hs.setColumnWidth(3,12*256);
        hs.setColumnWidth(4,120*256);
        hs.setColumnWidth(5,14*256);
        //给B1加上一个筛选功能
        hs.setAutoFilter(CellRangeAddress.valueOf("B1"));
        String excelname="课程信息";
            try {
                OutputStream os=new FileOutputStream(GetRealPath.getPath(this)+"\\export\\"+excelname+".xls");
                hwb.write(os);
                os.close();
                hwb.close();
                result=1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  result;
        }

    public int Export_Lecturer(List<LecturerList> list){
        int result=0;
        int length=list.size();
        //创建一个HSSFWorkbook对象，这个对象对应一个excel文件
        HSSFWorkbook hwb=new HSSFWorkbook();
        //创建一个HSSFSheet，对应一个excel的页
        HSSFSheet hs=hwb.createSheet("讲师列表");
        //创建行对象，从0开始，0代表第一行
        HSSFRow hr=hs.createRow(0);
        //创建该行的列对象，从0开始，0代表第一列
        HSSFCell hc=hr.createCell(0);
        hc.setCellValue("序号");
        hc=hr.createCell(1);
        hc.setCellValue("讲师号");
        hc=hr.createCell(2);
        hc.setCellValue("姓名");
        hc=hr.createCell(3);
        hc.setCellValue("密码");
        hc=hr.createCell(4);
        hc.setCellValue("邮箱");
        hc=hr.createCell(5);
        hc.setCellValue("发布课程数");
        for (int i = 1; i <=length; i++) {
            hr=hs.createRow(i);
            for (int j = 0; j < 6; j++) {
                hc=hr.createCell(0);
                hc.setCellValue(list.get(i-1).getSeq());
                hc=hr.createCell(1);
                hc.setCellValue(list.get(i-1).getLNum());
                hc=hr.createCell(2);
                hc.setCellValue(list.get(i-1).getLName());
                hc=hr.createCell(3);
                hc.setCellValue(list.get(i-1).getPwd());
                hc=hr.createCell(4);
                hc.setCellValue(list.get(i-1).getLEmail());
                hc=hr.createCell(5);
                hc.setCellValue(list.get(i-1).getCAmount());
            }
        }
        String sheetname="讲师信息";
        hs.setColumnWidth(4,22*256);

        System.out.println();
        try {
            OutputStream os=new FileOutputStream(GetRealPath.getPath(this)+"\\export\\"+sheetname+".xls");
            hwb.write(os);
            os.close();
            hwb.close();
            result=1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
