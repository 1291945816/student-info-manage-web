package controller.servlet.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Hps
 * @date: 2020/5/4 22:11
 * @description: 用于处理管理员界面的部分操作,如查询
 */
public interface AdminService {
    public void query_teacherInfo(HttpServletRequest request,HttpServletResponse response);
    public void query_courseInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException;

}
