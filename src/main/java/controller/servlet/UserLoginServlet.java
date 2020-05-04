package controller.servlet;

import controller.dao.LoginDaoImpl;
import controller.dao.service.LoginDao;
import controller.dao.service.UserDao;
import controller.utils.MD5Utils;
import model.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Hps
 * @date: 2020/4/28 21:28
 * @description: 该类用于处理学生用户登陆时的情况
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数值 （因为login.jsp文件中的表单的input标签的属性name为如下名字，便可以获得对应的值

        String checkCode = req.getParameter("checkedCode"); //获取验证码
        req.removeAttribute("checkedCode"); //移除验证码 避免记录上次的值

        String check_result = (String)req.getSession().getAttribute("check_result");//获取本身生成的验证码结果

        if(check_result!=null && check_result.equals(checkCode)){
            LoginDao userDao=new LoginDaoImpl();

            String username = req.getParameter("username");//获取用户名
            String password = req.getParameter("password"); //获取密码
            Student student=new Student();
            student.setPassword(MD5Utils.getMD5String(password)); //先加密 再传  因为数据库存储的是加密的值
            student.setSno(username);
            Student login = userDao.login(student);
            if(login != null){
                //这个Session作用就是将这个键值对作用于全局，也就是项目没结束都可以通过key访问到value
                req.getSession().setAttribute("student",login);
                //设置用户名，由于右上角的显示
                req.getSession().setAttribute("username",login.getSname());
                //然后进行重定向 就是进行跳转到studen界面 （这是新的请求 所以需要重定向
                resp.sendRedirect(req.getContextPath()+"/student.jsp");
            }else {
                req.setAttribute("error_msg","您输入的用户名或密码错误!");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }


        }else
        {
            req.setAttribute("error_msg","您输入的验证码不正确!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp); //跳转回原来的界面 并把请求参数重新传递
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
