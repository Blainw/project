package cn.edu.wtu.wdm.model.po;

public class Administrator {
    private Integer SID;//管理员ID
    private String SName;//管理员账号
    private String RName;//管理员真实姓名
    private String Pwd;//管理员密码

    public Integer getSID() {
        return SID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "SID=" + SID +
                ", SName='" + SName + '\'' +
                ", RName='" + RName + '\'' +
                ", Pwd='" + Pwd + '\'' +
                '}';
    }
}
