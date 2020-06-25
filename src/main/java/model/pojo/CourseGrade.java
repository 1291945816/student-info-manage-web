package model.pojo;

/**
 * @author: Hps
 * @date: 2020/6/25 20:51
 * @description:
 */
public class CourseGrade {
    private String sno;
    private String sname;
    private String cname;
    private double daygrade;
    private double examgrade;
    private double totalgrade;

    public CourseGrade() {
    }

    public CourseGrade(String sno, String sname, String cname, double daygrade, double examgrade, double totalgrade) {
        this.sno = sno;
        this.sname = sname;
        this.cname = cname;
        this.daygrade = daygrade;
        this.examgrade = examgrade;
        this.totalgrade = totalgrade;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getDaygrade() {
        return daygrade;
    }

    public void setDaygrade(double daygrade) {
        this.daygrade = daygrade;
    }

    public double getExamgrade() {
        return examgrade;
    }

    public void setExamgrade(double examgrade) {
        this.examgrade = examgrade;
    }

    public double getTotalgrade() {
        return totalgrade;
    }

    public void setTotalgrade(double totalgrade) {
        this.totalgrade = totalgrade;
    }

    @Override
    public String toString() {
        return "CourseGrade{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", cname='" + cname + '\'' +
                ", daygrade=" + daygrade +
                ", examgrade=" + examgrade +
                ", totalgrade=" + totalgrade +
                '}';
    }
}
