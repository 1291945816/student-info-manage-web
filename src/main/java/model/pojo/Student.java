package model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 蒙素靓
 * @date: 2020/5/3 20:42
 * @description: 学生类
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sno;
    private String sname;
    private String ssex;
    private Date sbirth_day;
    private String clid;
    private String spassword;

    public Student(String sno, String sname, String ssex, Date sbirth_day, String clid, String spassword) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sbirth_day = sbirth_day;
        this.clid = clid;
        this.spassword = spassword;
    }
    public  Student(){};

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Date getSbirth_day() {
        return sbirth_day;
    }

    public void setSbirth_day(Date sbirth_day) {
        this.sbirth_day = sbirth_day;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sbirth_day=" + sbirth_day +
                ", clid='" + clid + '\'' +
                ", spassword='" + spassword + '\'' +
                '}';
    }
}
