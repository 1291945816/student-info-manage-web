package controller.dao;

import controller.dao.service.LoginDao;
import controller.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Hps
 * @date: 2020/4/28 21:06
 * @description:
 */
public class LoginDaoImpl implements LoginDao {
    private Connection conn =null;

    public User login(User loginUser) {
        try {
            conn=JDBCUtils.getConnection();
            String sql = "select sno,password from student where sno=? and password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,loginUser.getUsername());
            preparedStatement.setString(2,loginUser.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new User(resultSet.getString("sno"),resultSet.getString("password"));
            }else
            {
                return null;
            }



        } catch (SQLException e) {
            return null;
        }
    }

    public Admin login(Admin loginAdmin) {
        try {
            conn=JDBCUtils.getConnection();
            String sql = "select jobId,password from teacher where jobId=? and password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,loginAdmin.getAdmin_username());
            preparedStatement.setString(2,loginAdmin.getAdmin_password());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Admin(resultSet.getString("jobId"),resultSet.getString("password"));
            }else
            {
                return null;
            }



        } catch (SQLException e) {
            return null;
        }
    }

    /*测试*/
    @Test
    public void testConnect(){
        Admin login = login(new Admin("admin", "admin"));
        System.out.println(login);

    }
}
