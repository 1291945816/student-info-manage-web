package controller.dao;

import controller.dao.service.LoginDao;
import controller.utils.JDBCUtils;
import model.pojo.Student;
import model.pojo.Teacher;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Hps
 * @date: 2020/4/28 21:06
 * @description: 分别实现对应的登陆 （学生登陆、教师登陆
 */
public class LoginDaoImpl implements LoginDao {
    private Connection connection=null;


    @Override
    public Student login(Student loginUser) {
        connection=JDBCUtils.getConnection();
        String sql= "select * from student where sno=? and password=?";
        Student loginStudent=null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,loginUser.getSno());
            statement.setString(2,loginUser.getPassword());
            ResultSet resultSet = statement.executeQuery();
            //如果查询结果有内容 就将返回的数据进行重新打包
            if(resultSet.next()){
                 loginStudent=new Student();
                 loginStudent.setSno(resultSet.getString("sno"));
                loginStudent.setSname(resultSet.getString("sname"));
                loginStudent.setBirthday(resultSet.getDate("birthday"));
                loginStudent.setClno(resultSet.getString("clno"));
                loginStudent.setSsex(resultSet.getString("ssex"));
                loginStudent.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginStudent;
    }

    @Override
    public Teacher login(Teacher loginAdmin) {
        return null;
    }
}
