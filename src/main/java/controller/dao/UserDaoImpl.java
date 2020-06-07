package controller.dao;

import controller.dao.service.UserDao;
import controller.utils.JDBCUtils;
import model.pojo.Class_;
import model.pojo.Student;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    /**
     * 通过给定登陆学生的用户信息，查询学生所在的班级信息
     * @param loginStudent 这是登陆的学生用户的信息
     * @return 登陆学生所在的班级信息
     */
    @Override
    public Class_ query_classInfo(Student loginStudent) {
        return null;
    }

    /**
     * 通过给定的学生信息，可以查询到同班的所有同学信息
     * @param loginStudent 登录学生的信息
     * @return 所有的同学
     */
    @Override
    public List<Student> query_students(Student loginStudent) {
        connection= JDBCUtils.getConnection();
        List<Student> list=null;
        String sql="select * from student where clno=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginStudent.getClno());
            ResultSet result = preparedStatement.executeQuery();
            result.last();
            if(result.getRow() > 0){
                result.beforeFirst();
                list=new ArrayList<>();
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


    @Test
    public void testQuery(){
        UserDao test=new UserDaoImpl();
        Student student=new Student();
        student.setSno("1800100100");
        student.setClno("18001001");
        System.out.println(test.query_students(student));



    }
}
