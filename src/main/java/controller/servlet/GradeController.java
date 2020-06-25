package controller.servlet;

import com.alibaba.fastjson.JSONObject;
import controller.dao.DepartmentDaoImpl;
import controller.dao.GradeDaoImpl;
import controller.dao.service.DepartmentDao;
import controller.dao.service.GradeDao;
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
 * @author: Hps
 * @date: 2020/6/25 14:45
 * @description:
 */

@WebServlet("/gradeController")
public class GradeController  extends HttpServlet {
    private final GradeDao gradeDao= new GradeDaoImpl();

    private String action=null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action=req.getParameter("action");
        Class<GradeController> gradeControllerClass =GradeController.class;
        //  try {
        try {
            Method method = gradeControllerClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            if(method != null)
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

    private void query_selected_course(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        Map<String,Object> map=new HashMap<>();

        List<Integer> selectedCourseCodeByTno = gradeDao.getSelectedCourseCodeByTno(teacher.getTno());
        if(selectedCourseCodeByTno != null){
            map.put("data",selectedCourseCodeByTno);
        }
        String jsonString = JSONObject.toJSONString(map);
        resp.getWriter().write(jsonString);


    }
}
