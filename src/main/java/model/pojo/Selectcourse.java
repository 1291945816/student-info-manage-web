package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 14:37
 * @description: 提供学生选课信息
 */
public class Selectcourse {
    private String sno;
    private String cno;
    private Double daygrade;
    private Double examgrade;
    private Double totalgrade;

    public Selectcourse(String sno, String cno, double daygrade, double examgrade, double totalgrade){
        this.sno = sno;
        this.cno = cno;
        this.daygrade = daygrade;
        this.examgrade = examgrade;
        this.totalgrade = totalgrade;
    }

    public Selectcourse(){

    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
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
        return "Selectcourse{" +
                "sno='" + sno + '\'' +
                ", cno='" + cno + '\'' +
                ", daygrade=" + daygrade +
                ", examgrade=" + examgrade +
                ", totalgrade=" + totalgrade +
                '}';
    }
}
