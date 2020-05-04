package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 14:37
 * @description: 提供学生选课的信息
 */
public class Selectcourse {
    private String sno;
    private String cno;
    private Double daygrade;
    private Double examgrade;
    private Double totalgrade;

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

    public Double getDaygrade() {
        return daygrade;
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

    public Double getTotalgrade() {
        return totalgrade;
    }

    public void setTotalgrade(Double totalgrade) {
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
