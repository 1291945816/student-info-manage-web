package controller.dao;

import controller.dao.service.*;
import controller.utils.JDBCUtils;
import model.pojo.CourseInfo;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/26 13:34
 * @description:
 */
public class UserSelectedCourseDaoImpl implements UserSelectedCourseDao {

    //获取连接
    private Connection connection = null;

    @Override
    public List<CourseInfo> queryUnselectCourseBySno(String sno, String page, String limits) {
        connection = JDBCUtils.getConnection();
        List<CourseInfo> list = null;
        String sql = "SELECT\n" +
                "	courseInfo.cno,\n" +
                "	courseInfo.ccode,\n" +
                "	courseInfo.cname,\n" +
                "	courseInfo.tname,\n" +
                "	courseInfo.cnum\n" +
                "FROM\n" +
                "	(SELECT cno FROM \n" +
                "		teachcourse\n" +
                "		LEFT JOIN ( SELECT cno AS C FROM selectcourse WHERE selectcourse.sno = ? ) AS select_T ON select_T.C = cno \n" +
                "	WHERE\n" +
                "		select_T.C IS NULL \n" +
                "	) AS teach INNER JOIN courseInfo ON courseInfo.cno = teach.cno  LIMIT ?,?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sno);
            int page_ = Integer.valueOf(page);
            int limit_i = Integer.valueOf(limits);

            //展开分页查询，并且起始页由参数决定
            preparedStatement.setInt(2, (page_ - 1) * limit_i);
            preparedStatement.setInt(3, limit_i);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if (resultSet.getRow() > 0) {
                resultSet.beforeFirst();
                list = new ArrayList<>();
                CourseInfo courseInfo = null;
                while (resultSet.next()) {
                    courseInfo = new CourseInfo();
                    courseInfo.setCno(resultSet.getString("cno"));
                    courseInfo.setCcode(resultSet.getString("ccode"));
                    courseInfo.setCname(resultSet.getString("cname"));
                    courseInfo.setCnum(resultSet.getInt("cnum"));
                    courseInfo.setTname(resultSet.getString("tname"));
                    if(resultSet.getInt("cnum")  > 0){
                        list.add(courseInfo);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Long queryResult(String sno) {
        long num = 0;

        connection = JDBCUtils.getConnection();
        String sql = "SELECT COUNT(*) as num \n" +
                "FROM teachcourse LEFT JOIN ( SELECT cno AS C FROM selectcourse WHERE selectcourse.sno =? ) AS select_T ON select_T.C = cno  \n" +
                "WHERE\n" +
                "	select_T.C IS NULL and  cnum != 0";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sno);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                num = resultSet.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public boolean insertSelectCourseByStudent(String cno, String sno) {

        boolean flag=false;
        connection=JDBCUtils.getConnection();
        String sql = "insert into selectcourse(sno, cno) values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,sno);
            preparedStatement.setString(2,cno);
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
    public boolean subNumByCno(String cno,Integer num) {

        boolean flag=false;
        connection=JDBCUtils.getConnection();
        String sql = "update teachcourse set cnum=? where cno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,num);
            preparedStatement.setString(2,cno);
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
    public void testQuery() {

        UserSelectedCourseDao userSelectedCourseDao = new UserSelectedCourseDaoImpl();
        System.out.println(userSelectedCourseDao.insertSelectCourseByStudent("1001","1800100101"));
    }
}
