package controller.servlet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Hps
 * @date: 2020/5/4 17:21
 * @description:
 */
public interface UserService {
    public void query_allCourseGrade(HttpServletRequest request, HttpServletResponse response) throws IOException;
    public void query_allCourse(HttpServletRequest request,HttpServletResponse response) throws IOException;
    public void query_classInfo(HttpServletRequest request,HttpServletResponse response) throws IOException;
    public void query_studentInfo(HttpServletRequest request,HttpServletResponse response);
}
