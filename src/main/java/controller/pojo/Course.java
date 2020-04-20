package controller.pojo;

/**
 * @author: Hps
 * @date: 2020/4/20 21:49
 * @description:
 */
public class Course {
    private String courseId;
    private String name;
    private String startDate;
    private Integer credit;
    private String teacher;

    public Course(String courseId, String name, String startDate, Integer credit, String teacher) {
        this.courseId = courseId;
        this.name = name;
        this.startDate = startDate;
        this.credit = credit;
        this.teacher = teacher;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", credit=" + credit +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
