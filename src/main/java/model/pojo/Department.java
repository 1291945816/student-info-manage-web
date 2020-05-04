package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 14:31
 * @description: 提供部门的信息
 */
public class Department {
    private String dno;
    private String dleader;
    private String dname;

    public Department(String dno, String dleader, String dname){
        this.dno = dno;
        this.dleader = dleader;
        this.dname = dname;
    }

    public Department(){

    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getDleader() {
        return dleader;
    }

    public void setDleader(String dleader) {
        this.dleader = dleader;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dno='" + dno + '\'' +
                ", dleader='" + dleader + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }
}
