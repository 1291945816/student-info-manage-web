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
}
