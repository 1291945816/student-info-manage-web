package controller.servlet;

import controller.servlet.service.AdminService;

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
 * @date: 2020/5/4 22:13
 * @description:
 */

@WebServlet("/adminservlet")
public class AdminServlet extends HttpServlet implements AdminService {
    private String action=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action=req.getParameter("action");


        Class<AdminServlet> adminServletClass = AdminServlet.class;
        //  try {
        try {
            Method method = adminServletClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    public void query_TeacherInfo(HttpServletRequest request, HttpServletResponse response) {

    }
}
