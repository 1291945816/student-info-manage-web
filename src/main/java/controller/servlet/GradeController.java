package controller.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import controller.dao.DepartmentDaoImpl;
import controller.dao.GradeDaoImpl;
import controller.dao.service.DepartmentDao;
import controller.dao.service.GradeDao;
import model.pojo.Selectcourse;
import model.pojo.CourseGrade;
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


    private  void query_courseGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<CourseGrade> selectedCourseGradeByCno = gradeDao.getSelectedCourseGradeByCno("33");
        if(selectedCourseGradeByCno != null){
            int greatNum=0,failNum = 0;
            double grade=0.00;

            for (CourseGrade courseGrade:selectedCourseGradeByCno){
                if(courseGrade.getTotalgrade() > 90){
                    greatNum ++;
                }else if(courseGrade.getTotalgrade()<60){
                    failNum++;
                }
                grade+=courseGrade.getTotalgrade();
            }
            Map<String,Object> map=new HashMap<>();
            map.put("greatNum",greatNum);
            map.put("failNum",failNum);
            map.put("avggrade",grade/selectedCourseGradeByCno.size());
            map.put("courseName",selectedCourseGradeByCno.get(0).getCname());
            map.put("data",selectedCourseGradeByCno);
            String jsonString = JSONObject.toJSONString(map);
            resp.getWriter().write(jsonString);
        }
    }

    /**
     *  selectccode
     *  sno
     *  daygrade
     *  examgrade
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void update_studentgrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取参数
        String selectccode = req.getParameter("selectccode");
        String sno = req.getParameter("sno");
        double daygrade = Double.valueOf(req.getParameter("daygrade"));
        double examgrade = Double.valueOf(req.getParameter("examgrade"));

        Selectcourse selectcourse = new Selectcourse();
        selectcourse.setSno(sno);
        selectcourse.setCno(selectccode);
        selectcourse.setDaygrade(daygrade);
        selectcourse.setExamgrade(examgrade);
        selectcourse.setTotalgrade(0.2*daygrade + 0.8*examgrade);
        //
        boolean b = gradeDao.updateStudentGradeBySelectedCourse(selectcourse);

        Map<String ,String> map=new HashMap<String, String>();
        if(b){
            map.put("code","200"); //成功则返回200

        }else
        {
            map.put("code","500"); //失败则返回 500
        }
        resp.getWriter().write(JSON.toJSONString(map));
    }
}
