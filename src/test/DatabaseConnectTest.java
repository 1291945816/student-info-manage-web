import controller.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.utils.JDBCUtils.getConnection;

/**
 * @author: Hps
 * @date: 2020/4/20 22:16
 * @description:
 */
public class DatabaseConnectTest {
    public static void main(String[] args) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "select  * from teacher";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("tno")+" "+resultSet.getString("password"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
