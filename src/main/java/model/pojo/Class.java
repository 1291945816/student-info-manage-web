package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/5/3 20:39
 * @description: 班级类
 */
public class Class {
    private String clid ;
    private String dname;
    private String clheader;

    public Class(String clid, String dname, String clheader) {
        this.clid = clid;
        this.dname = dname;
        this.clheader = clheader;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getClheader() {
        return clheader;
    }

    public void setClheader(String clheader) {
        this.clheader = clheader;
    }

    @Override
    public String toString() {
        return "Class{" +
                "clid='" + clid + '\'' +
                ", dname='" + dname + '\'' +
                ", clheader='" + clheader + '\'' +
                '}';
    }
}
