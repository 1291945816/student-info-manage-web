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
        String sql = "select * from student where id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(userid);
                student.setName(resultSet.getString("name"));
                student.setSex(resultSet.getString("sex"));
                student.setDepartment(resultSet.getString("department"));
                student.setClass_(resultSet.getString("class"));
                student.setBirthday(resultSet.getDate("birthday"));
                return student;
            } else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用于返回班级的信息
     * @param classId 班级ID
     * @return
     */
    public Class queryClass(String classId){
        conn=JDBCUtils.getConnection();
        String sql = "select * from class where id=? ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,classId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Class class_=new Class("","");
                class_.setId(resultSet.getString("id"));
                String classTeacherId=resultSet.getString("classteacher");
                sql="select name from teacher where id=? ";
                statement=conn.prepareStatement(sql);
                statement.setString(1,classTeacherId);
                 resultSet = statement.executeQuery();
                 if(resultSet.next()){
                     class_.setClassTeacher(resultSet.getString("name"));
                     return class_;
                 }

            }


        } catch (SQLException e) {
            return  null;
        }
        return null;

    }

    public List<Student> queryAllStudent(String classId){
        conn=JDBCUtils.getConnection();
        String sql = "select id,name,department from student where class=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> list=new ArrayList<Student>();
            Student student=null;
            while(resultSet.next()){
                 student=new Student();
                 student.setDepartment(resultSet.getString("department"));
                 student.setId(resultSet.getString("id"));
                 student.setName(resultSet.getString("name"));
                 list.add(student);
            }
            return list;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



    }

}
