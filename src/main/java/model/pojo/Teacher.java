package model.pojo;

import java.io.Serializable;

/**
 * @author: Hps
 * @date: 2020/4/20 21:57
 * @description:
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private Integer age;
    private String job;

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Teacher(String id, String name, Integer age, String job) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.job = job;
    }
}
