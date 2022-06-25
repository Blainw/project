package cn.edu.wtu.wdm.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Course {
    private Integer CID;
    private Integer CNO;
    private String CName;
    private String CPhase;
    private String CIntroduce;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date CTime;
    private Integer LNum;
    private String LName;
    private String CVideo;

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public Integer getCID() {
        return CID;
    }

    public void setCID(Integer CID) {
        this.CID = CID;
    }

    public Integer getCNO() {
        return CNO;
    }

    public void setCNO(Integer CNO) {
        this.CNO = CNO;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCPhase() {
        return CPhase;
    }

    public void setCPhase(String CPhase) {
        this.CPhase = CPhase;
    }

    public String getCIntroduce() {
        return CIntroduce;
    }

    public void setCIntroduce(String CIntroduce) {
        this.CIntroduce = CIntroduce;
    }

    public Date getCTime() {
        return CTime;
    }

    public void setCTime(Date CTime) {
        this.CTime = CTime;
    }

    public Integer getLNum() {
        return LNum;
    }

    public void setLNum(Integer LNum) {
        this.LNum = LNum;
    }

    public String getCVideo() {
        return CVideo;
    }

    public void setCVideo(String CVideo) {
        this.CVideo = CVideo;
    }

    public Course(Integer CID, Integer CNO, String CName, String CPhase, String CIntroduce, Date CTime, Integer LNum) {
        this.CID = CID;
        this.CNO = CNO;
        this.CName = CName;
        this.CPhase = CPhase;
        this.CIntroduce = CIntroduce;
        this.CTime = CTime;
        this.LNum = LNum;
    }

    public Course(Integer CID, Integer CNO, String CName, String CPhase, String CIntroduce, Date CTime) {
        this.CID = CID;
        this.CNO = CNO;
        this.CName = CName;
        this.CPhase = CPhase;
        this.CIntroduce = CIntroduce;
        this.CTime = CTime;
    }

    public Course(Integer CID, Integer CNO, String CName, String CPhase, String CIntroduce, Date CTime, String LName) {
        this.CID = CID;
        this.CNO = CNO;
        this.CName = CName;
        this.CPhase = CPhase;
        this.CIntroduce = CIntroduce;
        this.CTime = CTime;
        this.LName = LName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CID=" + CID +
                ", CNO=" + CNO +
                ", CName='" + CName + '\'' +
                ", CPhase='" + CPhase + '\'' +
                ", CIntroduce='" + CIntroduce + '\'' +
                ", CTime=" + CTime +
                ", LNum=" + LNum +
                ", LName='" + LName + '\'' +
                ", CVideo='" + CVideo + '\'' +
                '}';
    }

    public Course() {
    }
}
