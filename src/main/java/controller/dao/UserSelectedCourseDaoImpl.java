package controller.dao;

import controller.dao.service.UserSelectedCourseDao;
import model.pojo.CourseInfo;

import java.sql.Connection;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/26 13:34
 * @description:
 */
public class UserSelectedCourseDaoImpl implements UserSelectedCourseDao {

    //获取连接
    private Connection connection=null;

    @Override
    public List<CourseInfo> queryUnselectCourseBySno(String sno, String page, String limits) {
        String sql="SELECT\n" +
                "	courseInfo.cno,\n" +
                "	courseInfo.ccode,\n" +
                "	courseInfo.cname,\n" +
                "	courseInfo.tname,\n" +
                "	courseInfo.cnum\n" +
                "FROM\n" +
                "	(SELECT cno FROM \n" +
                "		teachcourse\n" +
                "		LEFT JOIN ( SELECT cno AS C FROM selectcourse WHERE selectcourse.sno = ? ) AS select_T ON select_T.C = cno \n" +
                "	WHERE\n" +
                "		select_T.C IS NULL \n" +
                "	) AS teach INNER JOIN courseInfo ON courseInfo.cno = teach.cno LIMIT ?,?";
        return null;

    }
}
