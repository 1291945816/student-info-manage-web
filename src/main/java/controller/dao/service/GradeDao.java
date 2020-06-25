package controller.dao.service;

import model.pojo.CourseGrade;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/25 13:57
 * @description:
 */
public interface GradeDao {
    List<Integer> getSelectedCourseCodeByTno(String tno);

    List<CourseGrade> getSelectedCourseGradeByCno(String cno);

}
