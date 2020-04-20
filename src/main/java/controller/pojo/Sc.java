package controller.pojo;

/**
 * @author: Hps
 * @date: 2020/4/20 21:53
 * @description:
 */
public class Sc {
    private String studentId;
    private String class_;
    private String courseId;
    private Float grade;

    public Sc(String studentId, String class_, String courseId, Float grade) {
        this.studentId = studentId;
        this.class_ = class_;
        this.courseId = courseId;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "studentId='" + studentId + '\'' +
                ", class_='" + class_ + '\'' +
                ", courseId='" + courseId + '\'' +
                ", grade=" + grade +
                '}';
    }
}
