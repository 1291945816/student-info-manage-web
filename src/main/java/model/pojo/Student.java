package model.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 14:45
 * @description: 提供学生的信息
 */
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;
    private String clno;
    private String password;

    public Student(String sno, String sname, String ssex, Date birthday, String clno, String password){
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.birthday = birthday;
        this.clno = clno;
        this.password = password;
    }

    public Student(){

    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClno() {
        return clno;
    }

    public void setClno(String clno) {
        this.clno = clno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", birthday=" + birthday +
                ", clno='" + clno + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
