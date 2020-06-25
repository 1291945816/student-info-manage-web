package controller.dao;

import controller.dao.service.GradeDao;
import controller.utils.JDBCUtils;
import model.pojo.Course;
import model.pojo.CourseGrade;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/25 13:59
 * @description:
 */
public class GradeDaoImpl implements GradeDao {
    //获取连接
    private Connection connection=null;
    @Override
    public List<Integer> getSelectedCourseCodeByTno(String tno)  {
        connection= JDBCUtils.getConnection();
        List<Integer> list=null;
        String sql="SELECT\n" +
                "	 DISTINCT selectcourse.cno \n" +
                "FROM\n" +
                "	selectcourse \n" +
                "WHERE\n" +
                "	EXISTS ( SELECT  * from teachcourse WHERE selectcourse.cno = teachcourse.cno AND teachcourse.tno = ? ) ORDER BY selectcourse.cno ASC ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tno);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if(resultSet.getRow()>1){
                resultSet.beforeFirst();
                list=new ArrayList<>();
                while(resultSet.next()){
                    list.add(resultSet.getInt("cno"));

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
    public List<CourseGrade> getSelectedCourseGradeByCno(String cno) {
        connection= JDBCUtils.getConnection();
        List<CourseGrade> list=null;
        String sql = "SELECT\n" +
                "	student.sno,\n" +
                "	student.sname,\n" +
                "	course.cname,\n" +
                "	selectcourse.daygrade,\n" +
                "	selectcourse.examgrade,\n" +
                "	selectcourse.totalgrade \n" +
                "FROM\n" +
                "	selectcourse,\n" +
                "	student,\n" +
                "	courseplan,\n" +
                "	course \n" +
                "WHERE\n" +
                "	selectcourse.sno = student.sno \n" +
                "	AND selectcourse.cno = courseplan.cno \n" +
                "	AND courseplan.ccode=course.ccode\n" +
                "	AND selectcourse.cno = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cno);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if(resultSet.getRow()>1){
                resultSet.beforeFirst();
                list=new ArrayList<>();
                CourseGrade courseGrade=null;
                while(resultSet.next()){
                    courseGrade=new CourseGrade();
                    courseGrade.setCname(resultSet.getString("cname"));
                    courseGrade.setSno(resultSet.getString("sno"));
                    courseGrade.setSname(resultSet.getString("sname"));
                    courseGrade.setDaygrade(resultSet.getDouble("daygrade"));
                    courseGrade.setExamgrade(resultSet.getDouble("examgrade"));
                    courseGrade.setTotalgrade(resultSet.getDouble("totalgrade"));
                    list.add(courseGrade);

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
    public void test(){
        GradeDao gradeDao = new GradeDaoImpl();
        System.out.println(gradeDao.getSelectedCourseGradeByCno("22").size());

    }


}
