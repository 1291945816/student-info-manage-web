package model.pojo;

/**
 * @author: Hps
 * @date: 2020/5/1 15:01
 * @description: 这是一个用于包含学生成绩的
 */
public class StudentGrade {
    private String courseId;
    private String name;
    private Float daygrade;
    private Float examgrade;
    private Float grade;
    private Integer credit;

    public StudentGrade() {
    }

    public StudentGrade(String courseId, String name, Float daygrade, Float examgrade, Float grade, Integer credit) {
        this.courseId = courseId;
        this.name = name;
        this.daygrade = daygrade;
        this.examgrade = examgrade;
        this.grade = grade;
        this.credit = credit;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDaygrade() {
        return daygrade;
    }

    public void setDaygrade(Float daygrade) {
        this.daygrade = daygrade;
    }

    public Float getExamgrade() {
        return examgrade;
    }

    public void setExamgrade(Float examgrade) {
        this.examgrade = examgrade;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", daygrade=" + daygrade +
                ", examgrade=" + examgrade +
                ", grade=" + grade +
                ", credit=" + credit +
                '}';
    }

}
