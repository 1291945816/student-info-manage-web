package controller.dao;

import controller.dao.service.*;
import controller.utils.JDBCUtils;
import model.pojo.Department;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Department> queryDepartmentByPageAndLimit(String pageCode, String limit) {
        connection= JDBCUtils.getConnection();
        List<Department> list=null;
        Department department=null;
        String sql="select * from department limit ?,?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int page=Integer.valueOf(pageCode);
            int limit_i=Integer.valueOf(limit);

            //展开分页查询，并且起始页由参数决定
            statement.setInt(1,(page-1)*limit_i);
            statement.setInt(2,limit_i);

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();

            if(resultSet.getRow() > 0)
            {
                resultSet.beforeFirst();
                list=new ArrayList<Department>();
                while(resultSet.next()){
                    department=new Department();
                    department.setDno(resultSet.getString("dno"));
                    department.setDleader(resultSet.getString("dleader"));
                    department.setDname(resultSet.getString("dname"));
                    list.add(department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return list;
    }

    @Override
    public boolean deleteDepartmentByDno(String dno) {

        boolean flag=false;
        connection=JDBCUtils.getConnection();
        String sql = "delete from department where dno=?";

        try {
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,dno);
            int i = statement.executeUpdate();
            if(i>=1){
                flag=true;
            }
        } catch (SQLException e) {
            flag=false;
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return flag;
    }

    @Override
    public Department queryDepartmentInfoByClno(String clno) {
        connection = JDBCUtils.getConnection();
        Department department = null;
        String sql = "SELECT department.dno,dleader,dname FROM department,class WHERE class.dno=department.dno AND clno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clno);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if (resultSet.getRow() > 0){
                resultSet.beforeFirst();
                resultSet.next();
                department = new Department();
                department.setDno(resultSet.getString("dno"));
                department.setDleader(resultSet.getString("dleader"));
                department.setDname(resultSet.getString("dname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection(connection);
        }
        return department;
    }

    @Test
    public void testDep(){

        DepartmentDao departmentDao=new DepartmentDaoImpl();

        System.out.println(departmentDao.queryDepartmentInfoByClno("18001001"));


    }
}
