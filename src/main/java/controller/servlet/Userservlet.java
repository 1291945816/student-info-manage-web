package controller.servlet;

import controller.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Hps
 * @date: 2020/4/29 20:54
 * @description:
 */
@WebServlet("/userservlet")
public class Userservlet extends HttpServlet implements UserService {
    private String action=null;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action=req.getParameter("action");


        java.lang.Class<Userservlet> userservletClass = Userservlet.class;
      //  try {
        try {
            Method method = userservletClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }


    @Override
    public void query_allCourseGrade(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void query_allCourse(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void query_classInfo(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void query_studentInfo(HttpServletRequest request, HttpServletResponse response) {

    }
}
