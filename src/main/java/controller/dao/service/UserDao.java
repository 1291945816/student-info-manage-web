package controller.dao.service;

import model.pojo.Student;

/**
 * @author: Hps
 * @date: 2020/5/4 17:25
 * @description:
 */
public interface UserDao {
    public Student student_info(String userid);
}
