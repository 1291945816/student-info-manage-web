package controller.dao.service;

import model.pojo.Department;

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

}
