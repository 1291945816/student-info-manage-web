package controller.servlet;

import com.alibaba.fastjson.JSON;
import controller.dao.DepartmentDaoImpl;
import controller.dao.service.DepartmentDao;
import model.pojo.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/6/8 15:19
 * @description:
 */
@WebServlet("/dep")
public class Dep extends HttpServlet {
    private DepartmentDao departmentDao=new DepartmentDaoImpl();
    private String action=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action=req.getParameter("action");


        Class<Dep> DepClass =Dep.class;
        //  try {
        try {
            Method method = DepClass.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
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

    private void adddepartment(HttpServletRequest request,HttpServletResponse response) {
        String dno = request.getParameter("dno");
        String dleader=request.getParameter("dleader");
        String dname = request.getParameter("dname");


        Department department=new Department();
        department.setDno(dno);
        department.setDname(dname);
        department.setDleader(dleader);

        boolean b = departmentDao.addDepartment(department);
        Map<String,Object> map = new HashMap<String, Object>();
        if(b ) map.put("code","200");
        else map.put("code","500");
        String s = JSON.toJSONString(map);
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
