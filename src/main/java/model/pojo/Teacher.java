package model.pojo;

import java.util.Date;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 16:13
 * @description: 提供教师的信息
 */
public class Teacher {
    private String tno;
    private String tname;
    private String tsex;
    private String president;
    private String password;
    private String dno;
    private Date birthday;

    public Teacher(String tno, String tname, String tsex, String president, String password, String dno, Date birthday){
        this.tno = tno;
        this.tname = tname;
        this.tsex = tsex;
        this.president = president;
        this.password = password;
        this.dno = dno;
        this.birthday = birthday;
    }

    public Teacher(){

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

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", tsex='" + tsex + '\'' +
                ", president='" + president + '\'' +
                ", password='" + password + '\'' +
                ", dno='" + dno + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
