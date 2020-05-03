package model.pojo;

import java.io.Serializable;

/**
 * @author: 蒙素靓
 * @date: 2020/5/3 20:32
 * @description: 教师类
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tno;
    private String tname;
    private String tsex;
    private Integer tage;
    private String tjob;
    private String dname;
    private String tpassword;

    public Teacher(String tno, String tname, String tsex,Integer tage, String tjob,String dname,String tpassword) {
        this.tno = tno;
        this.tname = tname;
        this.tsex = tsex;
        this.tage = tage;
        this.tjob = tjob;
        this.dname = dname;
        this.tpassword = tpassword;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public Integer getTage() {
        return tage;
    }

    public void setTage(Integer tage) {
        this.tage = tage;
    }

    public String getTjob() {
        return tjob;
    }

    public void setTjob(String tjob) {
        this.tjob = tjob;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", tsex='" + tsex +'\'' +
                ", tage=" + tage +
                ", tjob='" + tjob + '\'' +
                ", dname='" + dname + '\'' +
                ", tpassword='" + tpassword + '\'' +
                '}';
    }
}
