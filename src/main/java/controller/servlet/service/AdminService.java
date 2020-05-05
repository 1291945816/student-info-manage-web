package controller.servlet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Hps
 * @date: 2020/5/4 22:11
 * @description: 用于处理管理员界面的部分操作,如查询
 */
public interface AdminService {
    public void query_teacherInfo(HttpServletRequest request,HttpServletResponse response);
}
