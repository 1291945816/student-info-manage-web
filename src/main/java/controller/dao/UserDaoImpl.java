package controller.dao;

import controller.dao.service.UserDao;
import model.pojo.Class_;
import model.pojo.Student;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/5/5 8:02
 * @description: 该类用于访问数据库，并返回相应的数据
 */


public class UserDaoImpl implements UserDao {

    /**
     * 通过给定的班级，查询到这个班级的老师并返回
     * @param class_ 由登录学生用户查询到的班级信息
     * @return 这个班级的班主任
     */
    @Override
    public String teacherName(Class_ class_) {
        return null;
    }

    /**
     * 通过给定登陆学生的用户信息，查询学生所在的班级信息
     * @param loginStudent 这是登陆的学生用户的信息
     * @return 登陆学生所在的班级信息
     */
    @Override
    public Class_ query_classInfo(Student loginStudent) {
        return null;
    }

    /**
     * 通过给定的学生信息，可以查询到同班的所有同学信息
     * @param loginStudent 登录学生的信息
     * @return 所有的同学
     */
    @Override
    public List<Student> query_students(Student loginStudent) {
        return null;
    }
}
