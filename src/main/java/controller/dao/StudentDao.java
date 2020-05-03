package controller.dao;

import model.pojo.Class;
import model.pojo.Course;
import model.pojo.Student;
import model.pojo.StudentGrade;
import controller.utils.JDBCUtils;
import org.junit.Test;

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

    /**
     * 用于查询所有的相同班级的同学
     * @param classId 班级
     * @return
     */
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

    /**
     * 根据学号获取该同学的所有选课信息
     * @param usernamId
     * @return
     */
    public List<Course> queryAllCourse(String usernamId){
        conn= JDBCUtils.getConnection();
        String sql= "select course.courseid,course.name,startdate,credit,teacher.name as teacher"
        +" from course ,teacher,sc "
        +"where"
        +" sc.courseid = course.courseid and sc.sno=?"
        +" and teacher.jobId = course.teacher";
        List<Course> list=null;
        Course course=null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,usernamId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if(resultSet.getRow() > 0){
                list=new ArrayList<Course>();
                resultSet.beforeFirst();
                while(resultSet.next())
                {
                    course= new Course();
                    course.setCourseId(resultSet.getString("courseid"));
                    course.setName(resultSet.getString("name"));
                    course.setStartDate(resultSet.getString("startdate"));
                    course.setCredit(resultSet.getInt("credit"));
                    course.setTeacher(resultSet.getString("teacher"));
                    list.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCUtils.closeConnection(conn);
        }

        return list;
    }

    public List<StudentGrade> queryAllCourseGrade(String sno){
        conn=JDBCUtils.getConnection();
        List<StudentGrade> list=null;
        StudentGrade studentGrade=null;
        String sql="select course.courseid,course.name,sc.daygrade,sc.examgrade,sc.grade,course.credit " +
                "from course ,sc " +
                "where " +
                "sc.courseid = course.courseid and sc.sno=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,sno);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if(resultSet.getRow()>0){
                list=new ArrayList<StudentGrade>();
                resultSet.beforeFirst();
                while(resultSet.next()){
                    studentGrade=new StudentGrade();
                    studentGrade.setCourseId(resultSet.getString("courseid"));
                    studentGrade.setName(resultSet.getString("name"));
                    studentGrade.setCredit(resultSet.getInt("credit"));
                    studentGrade.setDaygrade(resultSet.getFloat("daygrade"));
                    studentGrade.setExamgrade(resultSet.getFloat("examgrade"));
                    studentGrade.setGrade(resultSet.getFloat("grade"));
                    list.add(studentGrade);
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(conn);
        }
        return list;
    }


    @Test
    public  void testDataConnection(){
        StudentDao studentDao= new StudentDao();

        List<StudentGrade> studentGrades = studentDao.queryAllCourseGrade("2000100101");
        System.out.println(studentGrades);


    }





}
