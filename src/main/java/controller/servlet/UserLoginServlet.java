package controller.servlet;

import controller.dao.LoginDaoImpl;
import controller.dao.service.LoginDao;
import controller.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Hps
 * @date: 2020/4/28 21:28
 * @description:
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkedCode = req.getParameter("checkedCode");
        String checkedCode_=(String)req.getSession().getAttribute("checkCode_session");
        if( checkedCode_!=null&&checkedCode_.equalsIgnoreCase(checkedCode)){
            User loginUser=new User(username,password);
            LoginDao loginDao = new LoginDaoImpl();
            User user = loginDao.login(loginUser);
            if(user == null){
                req.setAttribute("error_msg","账户不存在!请确定账户/密码是否输入正确?");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }else
            {
                req.getSession().setAttribute("username",username);
                resp.sendRedirect(req.getContextPath()+"/student.jsp");
            }


        }else {
            req.setAttribute("error_msg","验证码验证失败,请重新输入");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
