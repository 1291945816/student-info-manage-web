package controller.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import controller.dao.DepartmentDaoImpl;
import controller.dao.UserDaoImpl;
import controller.dao.UserSelectedCourseDaoImpl;
import controller.dao.service.DepartmentDao;
import controller.dao.service.UserDao;
import controller.dao.service.UserSelectedCourseDao;
import model.pojo.CourseInfo;
import model.pojo.Department;
import model.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/6/26 13:25
 * @description:
 */
@WebServlet("/userSelectedCourse")
public class UserSelectedCourse extends HttpServlet {
    private String action=null;
    private final UserDao userDao=new UserDaoImpl();
    private final UserSelectedCourseDao userSelectedCourseDao=new UserSelectedCourseDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action=req.getParameter("action");


        java.lang.Class<UserSelectedCourse> userSelectedCourseClass = UserSelectedCourse.class;
        //  try {
        try {
            Method method = userSelectedCourseClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

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

    private void selectCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Student student = (Student)req.getSession().getAttribute("student");
        List<CourseInfo> list = userSelectedCourseDao.queryUnselectCourseBySno(student.getSno(), page, limit);
        Long num=userSelectedCourseDao.queryResult(student.getSno());
        Map<String,Object> map=new HashMap<>();
        if(num != 0)
        {
            map.put("code","0");
            map.put("msg","查询成功");
            map.put("count",num);
            map.put("data",list);
        }else {
            map.put("code","-1");
            map.put("msg","查询失败");
        }
        String jsonString = JSONObject.toJSONString(map);
        try {
            resp.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void selectCourseforstudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student)req.getSession().getAttribute("student");
        String cno = req.getParameter("cno");
        Integer num = Integer.valueOf(req.getParameter("cnum"));
        Map<String,Object> map = new HashMap<>();
        if(num != 0)
        {
            boolean b1 = userSelectedCourseDao.insertSelectCourseByStudent(cno, student.getSno());
            if(b1)
            {
                num-=1;
                userSelectedCourseDao.subNumByCno(cno,num);
                map.put("code","200");
                map.put("msg","选课成功");

            }else {
                map.put("code","500");
                map.put("msg","选课失败，请检查是否已选?");
            }

        }else {
            map.put("code","500");
            map.put("msg","选课失败，数量不足");
        }

        String jsonString = JSONObject.toJSONString(map);
        resp.getWriter().write(jsonString);
    }

}
