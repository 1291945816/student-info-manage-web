package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/06/27 12:05
 * @description: 学生端的课程成绩
 */
public class Grades {
    private String cno;
    private String cname;
    private Double daygrade;

    public Double getDaygrade() {
        return daygrade;
    }


    private Double examgrade;
    private Double grade;
    private  Double credit;

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Grades() {
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

    public Double getDaygrade(double daygrade) {
        return this.daygrade;
    }

    public void setDaygrade(Double daygrade) {
        this.daygrade = daygrade;
    }

    public Double getExamgrade() {
        return examgrade;
    }

    public void setExamgrade(Double examgrade) {
        this.examgrade = examgrade;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Grades(String cno, String cname, Double daygrade, Double examgrade, Double grade) {
        this.cno = cno;
        this.cname = cname;
        this.daygrade = daygrade;
        this.examgrade = examgrade;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", daygrade=" + daygrade +
                ", examgrade=" + examgrade +
                ", grade=" + grade +
                '}';
    }
}
