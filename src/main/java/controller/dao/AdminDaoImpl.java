package controller.dao;

import controller.dao.service.AdminDao;
import controller.utils.JDBCUtils;
import model.pojo.Course;
import model.pojo.Courseplan;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/5/5 8:33
 * @description:
 */
public class AdminDaoImpl implements AdminDao {
    private Connection connection=null;
    public int get_Nums(String tableName){
        int size=0;
        connection = JDBCUtils.getConnection();
        String sql="select count(*) as num from "+tableName;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if(resultSet.next()){
                size=resultSet.getInt("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return size;
    }



    @Override
    public List<Courseplan> query_courseplansInfo(String pageCode, String limit) {
        connection= JDBCUtils.getConnection();
        List<Courseplan> list=null;
        Courseplan courseplan=null;
        String sql="select * from courseplan limit ?,?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int page=Integer.valueOf(pageCode);
            int limit_i=Integer.valueOf(limit);

            //展开分页查询，并且起始页由参数决定
            statement.setInt(1,(page-1)*limit_i);
            statement.setInt(2,limit_i);

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();

            if(resultSet.getRow() > 0)
            {
                resultSet.beforeFirst();
                list=new ArrayList<>();
                while(resultSet.next()){
                    courseplan=new Courseplan();
                    courseplan.setCcode(resultSet.getString("ccode"));
                    courseplan.setCno(resultSet.getString("cno"));
                    courseplan.setStartdate(resultSet.getString("startdate"));
                    list.add(courseplan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }


        return list;
    }

    @Override
    public List<Course> query_coursesInfo(String pageCode, String limit) {
        return null;
    }

    @Override
    public boolean delete_courseplan(String cno) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();

        String sql = "delete from courseplan where cno=?";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,cno);
            int i = statement.executeUpdate();
            if(i>=1){
                flag=true;
            }

        } catch (SQLException e) {
          flag=false;
        }
        return flag;
    }

    @Override
    public boolean update_courseplanValue(Courseplan courseplan) {
        return false;
    }

    @Test
    public void testDate(){
            AdminDao adminDao=new AdminDaoImpl();
        boolean flag = adminDao.delete_courseplan("1");
        System.out.println(flag);


    }


}
