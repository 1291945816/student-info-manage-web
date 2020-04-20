package controller.pojo;

/**
 * @author: Hps
 * @date: 2020/4/20 21:48
 * @description:
 */
public class Class {
    private String id ;
    private String classTeacher;

    public Class(String id, String classTeacher) {
        this.id = id;
        this.classTeacher = classTeacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", classTeacher='" + classTeacher + '\'' +
                '}';
    }
}
