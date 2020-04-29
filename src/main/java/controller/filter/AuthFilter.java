package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: Hps
 * @date: 2020/4/28 23:27
 * @description:
 */

public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session=request.getSession();
        String username = (String) session.getAttribute("username");
        String uri=request.getRequestURI();
        if(uri.endsWith("login.jsp") || uri.endsWith("userLoginServlet") || uri.endsWith("adminLoginServlet")||uri.endsWith("checkcode"))
        {
            chain.doFilter(request,response);
        }else
        {
            if(username == null){
                request.setAttribute("error_msg","请你重新登陆");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                chain.doFilter(request,response);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
