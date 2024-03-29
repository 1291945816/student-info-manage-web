package controller.dao;

import controller.dao.service.AdminDao;
import controller.utils.JDBCUtils;

import model.pojo.*;
import org.junit.Test;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
        }finally {
            JDBCUtils.closeConnection(connection);
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
        }finally {
            JDBCUtils.closeConnection(connection);
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
        }finally {
            JDBCUtils.closeConnection(connection);
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
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;
    }

    /**
     * 插入成功则返回 true  插入失败则返回 false
     * @param course 需要增加的课程信息
     * @return
     */
    @Override
    public boolean addcourse(Course course) {

        boolean flag=false;
        connection=JDBCUtils.getConnection();

        String sql = "insert into course values (?, ?, ?)";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,course.getCcode());
            statement.setNString(2, course.getCname());
            statement.setDouble(3,course.getCredit());
            int i = statement.executeUpdate();
            if(i>=1){
                flag=true;
            }

        } catch (SQLException e) {
            flag=false;
        }finally {
            JDBCUtils.closeConnection(connection);
        }

        return flag;
    }

    /**
     * 向数据库插入课程计划
     * @param courseplan
     * @return
     */
    @Override
    public boolean addcourseplan(Courseplan courseplan) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();

        String sql = "insert into courseplan values (?, ?, ?)";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,courseplan.getCno());
            statement.setString(2, courseplan.getCcode());
            statement.setString(3,courseplan.getStartdate());
            int i = statement.executeUpdate();
            if(i>=1){
                flag=true;
            }

        } catch (SQLException e) {
            flag=false;
        }finally {
            JDBCUtils.closeConnection(connection);
        }

        return flag;
    }





    @Override
    public boolean changePassword(Teacher teacher) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();
        String sql = "update teacher set password=? where tno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getPassword());
            preparedStatement.setString(2,teacher.getTno());
            int i = preparedStatement.executeUpdate();
            if(i >= 1)
                flag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;


    }

    @Override
    public boolean changeBirthday(Teacher teacher) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();
        String sql = "update teacher set birthday=? where tno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(teacher.getBirthday().toString()));
            preparedStatement.setString(2,teacher.getTno());
            int i = preparedStatement.executeUpdate();
            if(i >= 1)
                flag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;
    }

    /**
     * 向选择课程表插入新的可选课程
     * @param
     * @return
     */
    @Override
    public boolean addTeachCourse(Teachcourse teachcourse) {
        boolean flag=false;
        connection=JDBCUtils.getConnection();
        String sql = "insert into teachcourse values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teachcourse.getCno());
            preparedStatement.setString(2,teachcourse.getTno());
            preparedStatement.setDouble(3,teachcourse.getCnum());
            int i = preparedStatement.executeUpdate();
            if(i >= 1)
                flag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;
    }

    @Test
    public void testDate(){
        AdminDao adminDao=new AdminDaoImpl();
        Teacher teacher=new Teacher();
        teacher.setTno("13001");
        String birth="1933-04-21";
        teacher.setBirthday(Date.valueOf(birth));


        boolean flag = adminDao.changeBirthday(teacher);
        System.out.println(flag);

    }
}
