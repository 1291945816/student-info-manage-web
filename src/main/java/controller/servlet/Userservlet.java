package controller.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import controller.dao.DepartmentDaoImpl;
import controller.dao.UserDaoImpl;
import controller.dao.service.DepartmentDao;
import controller.dao.service.UserDao;
import controller.servlet.service.UserService;
import model.pojo.Class_;
import model.pojo.Department;
import model.pojo.SelectedCourse;
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
 * @date: 2020/4/29 20:54
 * @description:
 */
@WebServlet("/userservlet")
public class Userservlet extends HttpServlet implements UserService {
    private String action=null;
    private final  UserDao userDao=new UserDaoImpl();
    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

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
    public void query_allCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Student login_student = (Student)  request.getSession().getAttribute("student");
        if(login_student == null) return;
        login_student.setPassword("");
        List<SelectedCourse> list = userDao.querySelectedCourseBySno(login_student.getSno());
        Map<String,Object> map = new HashMap<>();
        if(list != null){
            map.put("code",200);
            map.put("courses",list);
        }else
            map.put("code",500);
        String string = JSONObject.toJSONString(map);
        response.getWriter().write(string);

    }

    private void query_departmentInfo(HttpServletRequest request, HttpServletResponse response){
        Student login_student = (Student)  request.getSession().getAttribute("student");
        if(login_student == null) return;
        login_student.setPassword("");
        Department department = departmentDao.queryDepartmentInfoByClno(login_student.getClno());
        Map<String,Object> map = new HashMap<>();
        map.put("dno",department.getDno());
        map.put("dleader",department.getDleader());
        map.put("dname",department.getDname());
        String string = JSONObject.toJSONString(map);
        try {
            response.getWriter().write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void query_classInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student login_student = (Student)  request.getSession().getAttribute("student");
        if(login_student == null) return;
        login_student.setPassword("");
        //获取学生班级信息
        Class_ class_ = userDao.query_classInfo(login_student);
        //获取老师姓名
        String s = userDao.teacherName(class_);
        //获取班级同学的信息
        List<Student> students = userDao.query_students(login_student);

        Map<String,Object> map=new HashMap<>();
        map.put("class_",class_);
        map.put("teacher",s);
        map.put("students",students);
        String string = JSONObject.toJSONString(map);
        response.getWriter().write(string);

    }

    @Override
    public void query_studentInfo(HttpServletRequest request, HttpServletResponse response) {
        Student login_student = (Student)  request.getSession().getAttribute("student"); //获取登录的学生信息
        if(login_student == null) return;
        login_student.setPassword("");
        String s = JSON.toJSONString(login_student);
        try {
            response.getWriter().write(s);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
