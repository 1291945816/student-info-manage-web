package controller.utils;

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
 * @description:
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
    public  static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

}
