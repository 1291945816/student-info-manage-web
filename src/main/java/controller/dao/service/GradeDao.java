package controller.dao.service;

import model.pojo.CourseGrade;
import model.pojo.Grades;
import model.pojo.Selectcourse;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/25 13:57
 * @description:
 */
public interface GradeDao {
    List<Integer> getSelectedCourseCodeByTno(String tno);

    List<CourseGrade> getSelectedCourseGradeByCno(String cno);

    boolean updateStudentGradeBySelectedCourse(Selectcourse selectcourse);

    //通过学号 获取学生的成绩
    List<Grades> getStudentGradesBySno(String sno);
}
