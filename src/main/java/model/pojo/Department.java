package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/5/3 20:30
 * @description: 部门类
 */
public class Department {
    private String dname;
    private String dleader;

    public Department(String dname, String dleader) {
        this.dname = dname;
        this.dleader = dleader;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDleader() {
        return dleader;
    }

    public void setDleader(String dleader) {
        this.dleader = dleader;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dname='" + dname + '\'' +
                ", dleader='" + dleader + '\'' +
                '}';
    }
}
