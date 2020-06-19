package controller.servlet;

import com.alibaba.fastjson.JSON;
import controller.dao.AdminDaoImpl;
import controller.dao.service.AdminDao;
import controller.utils.MD5Utils;
import model.pojo.Teacher;
import org.omg.CORBA.PRIVATE_MEMBER;
import sun.plugin.javascript.JSObject;

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
 * @author: Hps
 * @date: 2020/6/19 16:41
 * @description:  用于管理员的个人信息处理
 */

@WebServlet("/admininfo")
public class AdminInfo extends HttpServlet {
    private AdminDao adminDao = new AdminDaoImpl();
    private String action=null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Class<AdminInfo> adminInfoClass=AdminInfo.class;
        action=req.getParameter("action");


        try {
            Method method = adminInfoClass.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

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

    private void changepassword(HttpServletRequest  request,HttpServletResponse response) throws ServletException,IOException{
        String password = request.getParameter("password");
        Teacher login_teacher = (Teacher) request.getSession().getAttribute("teacher");
        login_teacher.setPassword(MD5Utils.getMD5String(password));
        boolean b = adminDao.changePassword(login_teacher);
        Map<String,Object> map=new HashMap<>();
        if(b){
            map.put("code","200");
        }else
            map.put("code","500");
        String jsonString = JSON.toJSONString(map);
        response.getWriter().write(jsonString);
    }
    private void changebirthday(HttpServletRequest  request,HttpServletResponse response) throws ServletException,IOException{
        String birthday=request.getParameter("birthday");
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        teacher.setBirthday(Date.valueOf(birthday));


        boolean b = adminDao.changeBirthday(teacher);
        Map<String,Object> map=new HashMap<>();
        if(b){
            map.put("code","200");
        }else
            map.put("code","500");
        String jsonString = JSON.toJSONString(map);
        response.getWriter().write(jsonString);
    }
}
