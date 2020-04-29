package controller.dao;

import controller.pojo.Student;
import controller.utils.JDBCUtils;

import java.sql.*;

/**
 * @author: Hps
 * @date: 2020/4/29 20:26
 * @description:
 */
public class StudentDao {
    private  Connection conn=null;

    /**
     * 用于返回学生的信息
     * @param userid 用户的id
     */
    public Student student_info(String userid) {
        conn=JDBCUtils.getConnection();
        String sql = "select * from student where id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(userid);
                student.setName(resultSet.getString("name"));
                student.setSex(resultSet.getString("sex"));
                student.setDepartment(resultSet.getString("department"));
                student.setClass_(resultSet.getString("class"));
                student.setBirthday(resultSet.getDate("birthday"));
                return student;
            } else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}
