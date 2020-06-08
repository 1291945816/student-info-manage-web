package controller.dao;

import controller.dao.service.DepartmentDao;
import controller.utils.JDBCUtils;
import model.pojo.Department;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: Hps
 * @date: 2020/6/8 15:30
 * @description:
 */
public class DepartmentDaoImpl implements DepartmentDao {
    //获取连接
    private Connection connection=null;
    @Override
    public boolean addDepartment(Department department) {
        boolean result=false;
        connection=JDBCUtils.getConnection();
        String sql="insert into department value(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,department.getDno());
            preparedStatement.setNString(2,department.getDleader());
            preparedStatement.setNString(3,department.getDname());
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                result =true;
            }else {
                result =false;
            }


        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return result;
    }

    @Test
    public void testDep(){
        Department department=new Department();
        department.setDleader("111");
        department.setDname("111");
        department.setDno("100");
        DepartmentDao departmentDao=new DepartmentDaoImpl();
        System.out.println(departmentDao.addDepartment(department));

    }
}
