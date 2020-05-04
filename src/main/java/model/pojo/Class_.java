package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 13:55
 * @description: 提供班级的信息
        */
public class Class_ {
    private String clno;
    private String tno;
    private String dno;

    public Class_(String clno, String tno, String dno){
        this.clno = clno;
        this.tno = tno;
        this.dno = dno;
    }

    public Class_(){

    }

    public String getClno() {
        return clno;
    }

    public void setClno(String clno) {
        this.clno = clno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    @Override
    public String
    toString() {
        return "Class_{" +
                "clno='" + clno + '\'' +
                ", tno='" + tno + '\'' +
                ", dno='" + dno + '\'' +
                '}';
    }
}
