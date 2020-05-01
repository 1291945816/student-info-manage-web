package controller.servlet;

import com.alibaba.fastjson.JSON;
import controller.dao.StudentDao;
import controller.pojo.Class;
import controller.pojo.Student;

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
public class Userservlet extends HttpServlet {
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

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void query_studentInfo(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException{
        String userid = (String)request.getSession().getAttribute("username");
        StudentDao studentDao=new StudentDao();
        Student student = studentDao.student_info("2000100101");
        System.out.println(student);

        if(student != null){
            String student_json = JSON.toJSONString(student);
            System.out.println("1");
            response.getWriter().write(student_json);
        }


    }
    public void query_classInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String sno = (String)request.getSession().getAttribute("username");
        StudentDao studentDao=new StudentDao();
        Student student=studentDao.student_info("2000100101");
        if(student != null) {
            Class aClass = studentDao.queryClass(student.getClass_());
            List<Student> students=studentDao.queryAllStudent(student.getClass_());
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Class",aClass);
            map.put("students",students);
            String s = JSON.toJSONString(map);
            response.getWriter().write(s);

        }


    }





}
