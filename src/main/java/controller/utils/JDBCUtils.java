package controller.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.imageio.IIOException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: Hps
 * @date: 2020/4/20 22:11
 * @description: 一个jdbc工具类，相关的配置位于 resource内，提供了数据课的连接、关闭
 */
public class JDBCUtils {
    private static DataSource dataSource;
    static {
        Properties properties=new Properties();
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
    public  static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void closeConnection(Connection connection){
        try {

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
