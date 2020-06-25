package controller.dao.service;

import model.pojo.Department;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/6/8 15:27
 * @description:
 */
public interface DepartmentDao {
    /**
     * 将提供的部门信息增加到数据库
     * @param department 要增加的部门信息
     * @return
     */
    public boolean addDepartment(Department department);


    /**
     * 该函数时用来查询所有部门的信息
     * @param page 页码
     * @param limit 限制长度
     * @return 查询到的所有部门信息
     */
    List<Department> queryDepartmentByPageAndLimit(String page,String limit);


    /**
     * 根据给定的部门编号删除部门
     * @param dno 部门编号
     * @return
     */
    boolean deleteDepartmentByDno(String dno);







}
