package controller.dao;

import controller.dao.service.AdminDao;
import controller.utils.JDBCUtils;
import jdk.internal.util.xml.impl.ReaderUTF8;
import model.pojo.Course;
import model.pojo.Courseplan;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

/**
 * @author: Hps, CAgAG
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
                list=new ArrayList<Courseplan>();
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
        connection= JDBCUtils.getConnection();
        List<Course> list=null;
        Course courseplan=null;
        String sql="select * from course limit ?,?";
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
                list=new ArrayList<Course>();
                while(resultSet.next()){
                    courseplan=new Course();
                    courseplan.setCcode(resultSet.getString("ccode"));
                    courseplan.setCname(resultSet.getString("cname"));
                    courseplan.setCredit(resultSet.getDouble("credit"));
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
    public boolean delete_course(String ccode) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();

        String sql = "delete from course where ccode=?";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,ccode);
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
    public boolean update_courseValue(Course course) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();

        String sql = "update course SET ccode=?, cname=?, credit=? where ccode=?";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,course.getCcode());
            statement.setNString(2, course.getCname());
            statement.setDouble(3,course.getCredit());
            statement.setString(4,course.getCcode());
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
        boolean flag=false;
        connection=JDBCUtils.getConnection();

        String sql = "update courseplan SET cno=?, ccode=?, startdate=? where cno=?";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,courseplan.getCno());
            statement.setString(2,courseplan.getCcode());
            statement.setString(3,courseplan.getStartdate());
            statement.setString(4,courseplan.getCno());
            int i = statement.executeUpdate();
            if(i>=1){
                flag=true;
            }

        } catch (SQLException e) {
            flag=false;
        }
        return flag;
    }

    @Test
    public void testDate(){
        AdminDao adminDao=new AdminDaoImpl();
        Course cp = new Course();
        cp.setCname("程序设计");
        cp.setCcode("AX1001");
        cp.setCredit(0.5);

        boolean flag = adminDao.update_courseValue(cp);
        System.out.println(flag);

    }
}
