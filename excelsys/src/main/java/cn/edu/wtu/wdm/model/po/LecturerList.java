package cn.edu.wtu.wdm.model.po;

public class LecturerList {
    private int seq;//排名
    private int LID;//ID
    private int LNum;//教师号
    private String LName;//讲师姓名
    private String Pwd;//密码
    private String LEmail;//邮箱
    private int CAmount;//讲师所教课程门数

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getLID() {
        return LID;
    }

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

    public int getCAmount() {
        return CAmount;
    }

    public void setCAmount(int CAmount) {
        this.CAmount = CAmount;
    }


    @Override
    public String toString() {
        return "LecturerList{" +
                "seq=" + seq +
                ", LID=" + LID +
                ", LNum=" + LNum +
                ", LName='" + LName + '\'' +
                ", Pwd='" + Pwd + '\'' +
                ", LEmail='" + LEmail + '\'' +
                ", CAmount=" + CAmount +
                '}';
    }
}
