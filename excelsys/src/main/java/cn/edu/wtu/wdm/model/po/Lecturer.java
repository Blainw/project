package cn.edu.wtu.wdm.model.po;

public class Lecturer {


    public int getLID() {
        return LID;
    }  private int LID;//ID
    private int LNum;//教师号
    private String LName;//讲师姓名
    private String Pwd;//密码
    private String LEmail;//邮箱

    public void setLID(int LID) {
        this.LID = LID;
    }

    public int getLNum() {
        return LNum;
    }

    public void setLNum(int LNum) {
        this.LNum = LNum;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getLEmail() {
        return LEmail;
    }

    public void setLEmail(String LEmail) {
        this.LEmail = LEmail;
    }

    public Lecturer() {
    }



    public Lecturer(int LNum, String LName, String pwd, String LEmail) {
        this.LNum = LNum;
        this.LName = LName;
        Pwd = pwd;
        this.LEmail = LEmail;
    }

    public Lecturer(int LID, int LNum, String LName, String pwd, String LEmail) {
        this.LID = LID;
        this.LNum = LNum;
        this.LName = LName;
        Pwd = pwd;
        this.LEmail = LEmail;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "LID=" + LID +
                ", LNum=" + LNum +
                ", LName='" + LName + '\'' +
                ", Pwd='" + Pwd + '\'' +
                ", LEmail='" + LEmail + '\'' +
                '}';
    }
}
