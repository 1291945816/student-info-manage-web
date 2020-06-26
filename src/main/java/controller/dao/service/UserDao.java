package controller.dao.service;

import model.pojo.Class_;
import model.pojo.SelectedCourse;
import model.pojo.Student;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/5/4 17:25
 * @description:
 */
public interface UserDao {

    //通过班级 查询班主任的名字
    String teacherName(Class_ class_);

    //通过登陆学生的信息，查询班级的信息
     Class_ query_classInfo(Student loginStudent);

    //通过登陆的学生 查询同班同学的信息
     List<Student> query_students(Student loginStudent);


     //通过学号修改学生的密码
    boolean change_password(Student student);


    //通过学号修改学生的生日
    boolean chang_birstday(Student student);


    List<SelectedCourse> querySelectedCourseBySno(String sno);

}
