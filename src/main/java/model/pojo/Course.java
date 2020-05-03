package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/5/3 20:29
 * @description: 课程类
 */
public class Course {
    private String cno;
    private String cname;
    private Double cnumber;

    public Course(){

    }

    public Course(String cno, String cname, Double cnumber) {
        this.cno = cno;
        this.cname = cname;
        this.cnumber = cnumber;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Double getCnumber() {
        return cnumber;
    }

    public void setCnumber(Double cnumber) {
        this.cnumber = cnumber;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", cnumber=" + cnumber +
                '}';
    }
}
