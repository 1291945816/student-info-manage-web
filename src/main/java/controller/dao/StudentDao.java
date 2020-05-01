package controller.dao;

import controller.pojo.Class;
import controller.pojo.Student;
import controller.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/4/29 20:26
 * @description:
 */
public class StudentDao {
    private  Connection conn=null;

    /**
     * 用于返回学生的信息
     * @param userid 用户的id
     */
    public Student student_info(String userid) {
        conn=JDBCUtils.getConnection();
        String sql = "select * from student where sno=?";
        Student student =null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                 student = new Student();
                student.setId(userid);
                student.setName(resultSet.getString("name"));
                student.setSex(resultSet.getString("sex"));
                student.setDepartment(resultSet.getString("department"));
                student.setClass_(resultSet.getString("classId"));
                student.setBirthday(resultSet.getDate("birthday"));

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return student;

    }

    /**
     * 用于返回班级的信息
     * @param classId 班级ID
     * @return
     */
    public Class queryClass(String classId){
        conn=JDBCUtils.getConnection();
        String sql = "select * from class where classId=? ";
        Class class_=null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,classId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                 class_=new Class("","");
                class_.setId(resultSet.getString("classId"));
                String classTeacherId=resultSet.getString("classteacher");
                sql="select name from teacher where jobId=? ";
                statement=conn.prepareStatement(sql);
                statement.setString(1,classTeacherId);
                 resultSet = statement.executeQuery();
                 if(resultSet.next()){
                     class_.setClassTeacher(resultSet.getString("name"));
                 }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return class_;

    }

    public List<Student> queryAllStudent(String classId){
        conn=JDBCUtils.getConnection();
        String sql = "select sno,name,department from student where classId=?";
        List<Student> list=null;
        Student student=null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,classId);
            ResultSet resultSet = preparedStatement.executeQuery();
             list=new ArrayList<Student>();
            while(resultSet.next()){
                 student=new Student();
                 student.setDepartment(resultSet.getString("department"));
                 student.setId(resultSet.getString("sno"));
                 student.setName(resultSet.getString("name"));
                 list.add(student);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return list;
    }





}
