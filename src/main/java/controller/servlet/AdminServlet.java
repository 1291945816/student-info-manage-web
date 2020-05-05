package controller.servlet;

import com.alibaba.fastjson.JSON;
import controller.dao.AdminDaoImpl;
import controller.dao.service.AdminDao;
import controller.servlet.service.AdminService;
import model.pojo.Course;
import model.pojo.Courseplan;
import model.pojo.Teacher;

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
 * @author: Hps, CAgAG
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
    public void query_teacherInfo(HttpServletRequest request, HttpServletResponse response) {
        Teacher login_teacher = (Teacher) request.getSession().getAttribute("teacher");
        login_teacher.setPassword("");
        String s = JSON.toJSONString(login_teacher);
        try {
            response.getWriter().write(s);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void query_courseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        AdminDao adminDao = new AdminDaoImpl();
        Map<String,Object> map=new HashMap<String, Object>();
        List<Courseplan> list = adminDao.query_courseplansInfo(page, limit);
        if(list!=null){
            map.put("code","0");
            map.put("msg","");
            map.put("count",adminDao.get_Nums("courseplan"));
            map.put("data",list);
        }else
        {
            map.put("code","-1");
        }
        String s = JSON.toJSONString(map);
        response.getWriter().write(s);

    }

    @Override
    public void update_deleteCourseplan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cno=(String)request.getParameter("cno");
        AdminDao adminDao=new AdminDaoImpl();
        boolean b = adminDao.delete_courseplan(cno);
        Map<String ,String> map=new HashMap<String, String>();
        if(b){
            map.put("code","200"); //删除成功则返回200

        }else
        {
            map.put("code","500"); //删除失败则返回 500
        }
        response.getWriter().write(JSON.toJSONString(map));
    }

    /**
     *  用于查询课程信息 返回的格式参见查询计划方法的操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void query_courseDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        AdminDao adminDao = new AdminDaoImpl();
        Map<String,Object> map=new HashMap<String, Object>();
        List<Course> list = adminDao.query_coursesInfo(page, limit);
        if(list!=null){
            map.put("code","0");
            map.put("msg","");
            map.put("count",adminDao.get_Nums("course"));
            map.put("data",list);
        }else
        {
            map.put("code","-1");
        }
        String s = JSON.toJSONString(map);
        response.getWriter().write(s);
    }

    /**
     * 用于处理数据的更新 参见 删除的操作 根据是否修改成功返回对应的状态码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void update_updateCourseplan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Courseplan cp = new Courseplan();
        cp.setCno((String)request.getParameter("cno"));
        cp.setCcode((String)request.getParameter("ccode"));
        cp.setStartdate((String)request.getParameter("startdate"));

        AdminDao adminDao=new AdminDaoImpl();
        boolean b = adminDao.update_courseplanValue(cp);

        Map<String ,String> map=new HashMap<String, String>();
        if(b){
            map.put("code","200"); //成功则返回200

        }else
        {
            map.put("code","500"); //失败则返回 500
        }
        response.getWriter().write(JSON.toJSONString(map));
    }


    /**
     * 根据从参数获取到的课程代码 从而删除该课程，如果删除成功则返回200 否则 500
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void update_deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao=new AdminDaoImpl();
        boolean b = adminDao.delete_course((String)request.getParameter("ccode"));

        Map<String ,String> map=new HashMap<String, String>();
        if(b){
            map.put("code","200"); //成功则返回200

        }else
        {
            map.put("code","500"); //失败则返回 500
        }
        response.getWriter().write(JSON.toJSONString(map));
    }

    /**
     * 根据给定的 课程代码 修改课程名称、课程学分信息 修改成功返回 200 否则返回 500
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void update_updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course cp = new Course();
        cp.setCcode((String)request.getParameter("ccode"));
        cp.setCname((String)request.getParameter("cname"));
        cp.setCredit(Double.parseDouble(request.getParameter("credit")));

        AdminDao adminDao=new AdminDaoImpl();
        boolean b = adminDao.update_courseValue(cp);

        Map<String ,String> map=new HashMap<String, String>();
        if(b){
            map.put("code","200"); //成功则返回200

        }else
        {
            map.put("code","500"); //失败则返回 500
        }
        response.getWriter().write(JSON.toJSONString(map));
    }
}
