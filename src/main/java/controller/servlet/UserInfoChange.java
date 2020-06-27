package controller.servlet;

import com.alibaba.fastjson.JSONObject;
import controller.dao.UserDaoImpl;
import controller.dao.service.UserDao;
import controller.utils.MD5Utils;
import model.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 蒙素靓
 * @date: 2020/06/26 16:17
 * @description: 用于修改用户信息
 */
@WebServlet("/userInfoChange")
public class UserInfoChange extends HttpServlet {
    private String action=null;
    private final UserDao userDao=new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action=req.getParameter("action");


        java.lang.Class<UserInfoChange> userInfoChangeClass = UserInfoChange.class;
        //  try {
        try {
            Method method = userInfoChangeClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

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

    private void changepassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Student login_student = (Student)  req.getSession().getAttribute("student");
        if(login_student == null) return;
        login_student.setPassword(MD5Utils.getMD5String( req.getParameter("password")));//对前端传来的密码进行加密，再存入对象中
        //修改密码
        //根据返回的布尔值  分别向前端传值
        boolean b = userDao.change_password(login_student);
        Map<String,Object> map=new HashMap<>();
        if (b){
            map.put("code",200);
        }else {
            map.put("code",500);
        }
        String string = JSONObject.toJSONString(map);
        resp.getWriter().write(string);

    }

    private void changebirthday(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Student login_student = (Student)  req.getSession().getAttribute("student");
        login_student.setBirthday(Date.valueOf(req.getParameter("birthday")));
        //修改生日
        //根据返回的布尔值  分别向前端传值
        boolean b = userDao.chang_birstday(login_student);
        Map<String,Object> map = new HashMap<>();
        if (b){
            map.put("code",200);
        }else {
            map.put("code",500);
        }
        String string = JSONObject.toJSONString(map);
        resp.getWriter().write(string);



    }
}
