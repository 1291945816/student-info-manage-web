package controller.dao;

import controller.dao.service.UserDao;
import controller.utils.JDBCUtils;
import controller.utils.MD5Utils;
import model.pojo.Class_;
import model.pojo.Student;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/5/5 8:02
 * @description: 该类用于访问数据库，并返回相应的数据
 */


public class UserDaoImpl implements UserDao {

    //获取连接
    private Connection connection=null;

    /**
     * 通过给定的班级，查询到这个班级的老师并返回
     * @param class_ 由登录学生用户查询到的班级信息
     * @return 这个班级的班主任
     */
    @Override
    public String teacherName(Class_ class_) {
        connection= JDBCUtils.getConnection(); //获取连接
        String sql = "SELECT * FROM teacher WHERE tno=?";
        String string = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,class_.getTno());
            ResultSet resultSet =  preparedStatement.executeQuery();
            resultSet.last();
            if (resultSet.getRow() >0){
                resultSet.beforeFirst();
                resultSet.next();
                string = resultSet.getString("tname");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }

        return string;
    }

    /**
     * 通过给定登陆学生的用户信息，查询学生所在的班级信息
     * @param loginStudent 这是登陆的学生用户的信息
     * @return 登陆学生所在的班级信息
     */
    @Override
    public Class_ query_classInfo(Student loginStudent) {
        connection= JDBCUtils.getConnection(); //获取连接
        String sql = " SELECT * FROM class WHERE clno=? ";
        Class_ class_ = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginStudent.getClno());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if (resultSet.getRow() > 0){
                resultSet.beforeFirst();
                resultSet.next();
                class_=new Class_();
                class_.setClno(resultSet.getString("clno"));
                class_.setTno(resultSet.getString("tno"));
                class_.setDno(resultSet.getString("dno"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return class_;
    }

    /**
     * 通过给定的学生信息，可以查询到同班的所有同学信息
     * @param loginStudent 登录学生的信息
     * @return 所有的同学
     */
    @Override
    public List<Student> query_students(Student loginStudent) {
        connection= JDBCUtils.getConnection(); //获取连接
        List<Student> list=null;
        String sql="select * from student where clno=? "; //sql 语句 printf("%d")
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql); //预备语句  这个语句是可以有占位符
            preparedStatement.setString(1,loginStudent.getClno()); //设置 第一个占位符的值为 学生的班级号

            ResultSet result = preparedStatement.executeQuery();
            result.last();
            if(result.getRow() > 0){
                result.beforeFirst();
                list=new ArrayList<Student>();
                Student student=null;
                while(result.next()){
                    student = new Student();
                    student.setSno(result.getString("sno"));
                    student.setSname(result.getString("sname"));
                    student.setSsex(result.getString("ssex"));
                    list.add(student);
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
    public boolean change_password(Student student) {
        boolean flag = false;
        connection = JDBCUtils.getConnection();
        String sql = "UPDATE student set `password`= ? WHERE sno = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getPassword());
            preparedStatement.setString(2,student.getSno());
            int i = preparedStatement.executeUpdate();
            if(i >= 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;
    }

    @Override
    public boolean chang_birstday(Student student) {
        boolean flag = false;
        connection = JDBCUtils.getConnection();
        String sql = "UPDATE student set `birthday`= ? WHERE sno = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(student.getBirthday().toString()));
            preparedStatement.setString(2,student.getSno());
            int i = preparedStatement.executeUpdate();
            if(i >= 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;
    }


    @Test
    public void testQuery(){
        UserDao test=new UserDaoImpl();

        Student student=new Student();
        student.setSno("1800100100");
        student.setClno("18001001");
        student.setPassword(MD5Utils.getMD5String("000000"));
        student.setBirthday(Date.valueOf("2020-04-02"));
        //System.out.println(test.query_students(student));
        //System.out.println(test.query_classInfo(student));
        System.out.println(test.change_password(student));
        System.out.println(test.chang_birstday(student));



    }
}
