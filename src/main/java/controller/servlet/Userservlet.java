package controller.servlet;

import com.alibaba.fastjson.JSON;
import controller.dao.StudentDao;

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

    public void query_allCourse(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String sno = (String)request.getSession().getAttribute("username");
        StudentDao studentDao=new StudentDao();
        List<Course> list = studentDao.queryAllCourse("2000100101");
        Map<String,Object> map= new HashMap<String, Object>();

        //状态码  500 表示没有该数据
        //200 表示查到有关数据  并且写入数据
        if(list == null){
            map.put("code","500");
        }else
        {
            map.put("code","200");
            map.put("courses",list);
        }
        String s = JSON.toJSONString(map);
        response.getWriter().write(s);
    }

    public void query_allCourseGrade(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String sno = (String)request.getSession().getAttribute("username");
        StudentDao studentDao=new StudentDao();

        List<StudentGrade> studentGrades = studentDao.queryAllCourseGrade("2000100101");
        Map<String,Object> map = new HashMap<String, Object>();

        if(studentGrades == null){
            map.put("code","500");
        }else
        {
            //优秀门数，不及格门数,及格门数
            int count=0,failCount=0,passCount=0;
            //求平均分
            double avgGrade=0,sumCrdit=0,sumGrade=0;
            double creditGrade=0; //学分绩
            for (StudentGrade studentGrade:studentGrades){
                if(studentGrade.getGrade() >= 60){
                    passCount++;
                    if(studentGrade.getGrade()>=90)
                        count++;

                }else {
                    failCount++;
                }
                sumCrdit+=studentGrade.getCredit();
                sumGrade+=studentGrade.getGrade();
                creditGrade+=studentGrade.getGrade()*studentGrade.getCredit();
            }
            avgGrade=sumGrade/(failCount+passCount);
            creditGrade/=sumCrdit;

            map.put("code","200");
            map.put("grades",studentGrades);
            map.put("count",count);
            map.put("failCount",failCount);
            map.put("passCount",passCount);
            map.put("avgGrade",avgGrade);
            map.put("creditGrade",creditGrade);

        }
        String s = JSON.toJSONString(map);
        response.getWriter().write(s);
    }





}
