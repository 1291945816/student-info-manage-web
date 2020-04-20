package controller.pojo;

import java.util.Date;

/**
 * @author: Hps
 * @date: 2020/4/20 21:55
 * @description:
 */
public class Student {
    private String id;
    private String name;
    private String sex;
    private Date birthday;
    private String department;
    private String class_;

    public Student(String id, String name, String sex, Date birthday, String department, String class_) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.department = department;
        this.class_ = class_;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", department='" + department + '\'' +
                ", class_='" + class_ + '\'' +
                '}';
    }
}
