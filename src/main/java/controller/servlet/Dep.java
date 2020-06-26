package controller.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import controller.dao.AdminDaoImpl;
import controller.dao.DepartmentDaoImpl;
import controller.dao.service.AdminDao;
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
import java.util.HashMap;
import java.util.List;
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

    private void query_department_info(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");


        AdminDao adminDao=new AdminDaoImpl();

        Map<String,Object> map =new HashMap<>();
        if(page != null && limit != null){
            List<Department> departments = departmentDao.queryDepartmentByPageAndLimit(page, limit);
            if(departments != null&&!departments.isEmpty()){
                map.put("code","0");
                map.put("msg"," 查询成功");
                map.put("count",adminDao.get_Nums("department"));
                map.put("data",departments);
            }else{
                map.put("code","500");
                map.put("msg","Error:查询失败，无数据");
            }

        }else {
            map.put("code","500");
            map.put("msg","Error:查询失败，参数无效");
        }
        String jsonString = JSONObject.toJSONString(map);
        response.getWriter().write(jsonString);
    }
    private void delete_department(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        String dno = request.getParameter("dno");
        boolean status_deleteByDep = departmentDao.deleteDepartmentByDno(dno);
        Map<String,Object> map = new HashMap<>();
        if(status_deleteByDep){
            map.put("code","200");
            map.put("msg","删除成功!");
        }else {
            map.put("code","500");
            map.put("msg","删除失败，部门不存在/该部门存在学生和老师，请先清除学生/老师...");
        }

        String jsonString = JSONObject.toJSONString(map);
        response.getWriter().write(jsonString);
    }




}
