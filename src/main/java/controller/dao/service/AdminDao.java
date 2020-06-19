package controller.dao.service;

import model.pojo.Course;
import model.pojo.Courseplan;
import model.pojo.Selectcourse;
import model.pojo.Teacher;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/5/5 8:07
 * @description: 改类用于访问教师管理界面所需数据库数据
 */
public interface AdminDao {
    /**
     * 通过给定的页码（起始位置）每页的数据总数，返回指定数据
     * @param pageCode 页码 从1开始
     * @param limit    每页的数据
     * @return
     */
    public List<Courseplan> query_courseplansInfo(String pageCode,String limit);

    //获取指定表的长度
    public int get_Nums(String tableName);



    /**
     * 通过给定的页码，返回指定页码开始的数据总数
     * @param pageCode 页码 从1开始
     * @param limit 每页的数据
     * @return
     */
    public List<Course> query_coursesInfo(String pageCode, String limit);


    public boolean delete_courseplan(String cno);


    /**
     * 删除课程
     * @param ccode
     * @return
     */
    public boolean delete_course(String ccode);

    /**
     * 更新课程的值
     * @param course
     * @return
     */
    public boolean update_courseValue(Course course);

    /**
     * 用于修改信息，如果修改成功则返回true  修改失败则返回 false
     * @param courseplan
     * @return
     */
    public boolean update_courseplanValue(Courseplan courseplan);


    /**
     *
     * @param course 需要增加的课程信息
     * @return
     */
    public boolean addcourse(Course course);

    public boolean addcourseplan(Courseplan courseplan);



    public boolean changePassword(Teacher teacher);

    public boolean changeBirthday(Teacher teacher);

    public boolean addSelectCourse(Selectcourse selectcourse);





}
