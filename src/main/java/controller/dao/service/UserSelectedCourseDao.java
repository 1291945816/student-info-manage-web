package controller.dao.service;

import model.pojo.CourseInfo;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/26 13:33
 * @description:
 */
public interface UserSelectedCourseDao {

    List<CourseInfo> queryUnselectCourseBySno(String sno,String page,String limits);

    Long queryResult(String sno);

    boolean insertSelectCourseByStudent(String cno,String sno);

    boolean subNumByCno(String cno,Integer num);

}

