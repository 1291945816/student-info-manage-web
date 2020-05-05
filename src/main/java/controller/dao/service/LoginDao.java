package controller.dao.service;

import model.pojo.Student;
import model.pojo.Teacher;

/**
 * @author: Hps
 * @date: 2020/4/28 21:04
 * @description: 提供两种不同的东路方法
 */
public interface LoginDao {
    public Student login(Student loginUser);
    public Teacher login(Teacher loginAdmin);

}
