package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 14:14
 * @description: 提供课程的信息
 */
public class Course {
    private String ccode;
    private String cname;
    private Double credit;

    public Course(String ccode, String cname, Double credit){
        this.ccode = ccode;
        this.cname = cname;
        this.credit = credit;
    }

    public Course() {

    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ccode='" + ccode + '\'' +
                ", cname='" + cname + '\'' +
                ", credit=" + credit +
                '}';
    }
}
