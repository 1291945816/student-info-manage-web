package controller.dao.service;

import model.pojo.Class_;
import model.pojo.Student;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/5/4 17:25
 * @description:
 */
public interface UserDao {

    //通过班级 查询班主任的名字
    public String teacherName(Class_ class_);

    //通过登陆学生的信息，查询班级的信息
    public Class_ query_classInfo(Student loginStudent);

    //通过登陆的学生 查询同班同学的信息
    public List<Student> query_students(Student loginStudent);
}
